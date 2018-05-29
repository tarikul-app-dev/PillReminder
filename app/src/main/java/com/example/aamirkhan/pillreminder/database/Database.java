package com.example.aamirkhan.pillreminder.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database {
    // db version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pill";
    private static final String DATABASE_TABLE_USER = "table_user";
    private Database.DBHelper dbhelper;
    private final Context context;
    private SQLiteDatabase database;

    public static final String KEY_ROWID = "id";
    public static final String KEY_USER_NAME= "username";

    public static final String KEY_MOBILE_NUMBER = "mobilenumber";


    private static class DBHelper extends SQLiteOpenHelper {

        @SuppressLint("NewApi")
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // create table to store msgs
            db.execSQL(" CREATE TABLE " + DATABASE_TABLE_USER + " ("
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                    + KEY_USER_NAME + " TEXT, "

                    + KEY_MOBILE_NUMBER + " TEXT );");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USER);


            onCreate(db);
        }

    }
    // constructor
    public Database(Context c) {
        context = c;
    }

    // open db
    public Database open() {
        dbhelper = new  DBHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    // close db
    public void close() {
        dbhelper.close();
    }


    public long saveUser( String userName ,String mobileNumber

    ){
        ContentValues cv = new ContentValues();

        cv.put(KEY_USER_NAME, userName);

        cv.put(KEY_MOBILE_NUMBER,  mobileNumber);

        long dbInsert = database.insert(DATABASE_TABLE_USER, null, cv);

        if(dbInsert != -1) {

            Toast.makeText(context, "New row added , row id: " + dbInsert, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
        }

        return dbInsert;

    }


//    public ArrayList getInputData(){
//
//        ArrayList<HelloCmpOneModel> helloCmpOneModelArrayList = new ArrayList<>();
//        String select_query = "SELECT * FROM " + DATABASE_TABLE_INFO ;
//
//        Cursor cursor = database.rawQuery(select_query,null);
//        int rows = cursor.getCount();
//        int columns = cursor.getColumnCount();
//
//        int iUniqueID = cursor.getColumnIndex(KEY_ROWID);
//        int iCriminalInfo = cursor.getColumnIndex(KEY_INFO_CRIMINAL);
//        int iDivision = cursor.getColumnIndex(KEY_DIVISION);
//        int iDistrict = cursor.getColumnIndex(KEY_DISTRICT);
//        int iThana = cursor.getColumnIndex(KEY_THANA);
//        int iCameraImgPath = cursor.getColumnIndex(KEY_CAMERA_IMAGE);
//        int iAudioPath = cursor.getColumnIndex(KEY_AUDIO);
//        int iGalleryImgPath = cursor.getColumnIndex(KEY_GALLARY_IMAGE);
//
//
//
//
//        for (cursor.moveToLast(); ! cursor.isBeforeFirst(); cursor.moveToPrevious()) {
//            //    for (cursor.moveToFirst(); ! cursor.isAfterLast(); cursor.moveToNext()) {
//            String criminalInfo = cursor.getString(iCriminalInfo);
//            String division = cursor.getString(iDivision);
//            String district = cursor.getString(iDistrict);
//            String thana = cursor.getString(iThana);
//            String cameraImgP = cursor.getString(iCameraImgPath);
//            String audioP = cursor.getString(iAudioPath);
//            String galleryImgP = cursor.getString(iGalleryImgPath);
//            int    uniqueId = cursor.getInt(iUniqueID);
//
//
//            helloCmpOneModelArrayList.add(new HelloCmpOneModel(criminalInfo,division,district,thana,cameraImgP,audioP,galleryImgP,String.valueOf(uniqueId)));
//
//        }
//        cursor.close();
//        return helloCmpOneModelArrayList;
//    }


}