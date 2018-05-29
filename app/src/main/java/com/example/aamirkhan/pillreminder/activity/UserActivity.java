package com.example.aamirkhan.pillreminder.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aamirkhan.pillreminder.R;
import com.example.aamirkhan.pillreminder.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.aamirkhan.pillreminder.utill.SaveValueSharedPreference.saveBoleanValueSharedPreferences;
import static com.example.aamirkhan.pillreminder.utill.SaveValueSharedPreference.saveToSharedPreferences;

public class UserActivity extends AppCompatActivity {

    EditText edtMobNumber,edtUserName;
    String mobile = "";
    String userName = "";
    Button btnNext;
    String mobileregx;
    DatabaseReference databaseReference;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilenum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnNext=(Button)findViewById(R.id.btn_next);
        user = new User();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtMobNumber = (EditText) findViewById(R.id.edt_mob_number);
                edtUserName = (EditText) findViewById(R.id.edt_user_name);

                mobileregx = "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$";
                mobile = edtMobNumber.getText().toString();
                userName = edtUserName.getText().toString();
                saveToSharedPreferences("my_number",mobile,UserActivity.this);


                if (TextUtils.isEmpty(mobile)) {
                    edtMobNumber.setError("Enter Mobile number");
                }
                else if(!mobile.matches(mobileregx)){
                    edtMobNumber.setError("Wrong Input"); }
                else {
                    user.setUser_name(userName);
                    user.setMob_number(mobile);
                    try {
                        databaseReference.child(user.getMob_number()).setValue(user);
                        Toast.makeText(UserActivity.this, "Successfully send to server", Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(UserActivity.this, "Some Issue (Check your Internet connection and try again)", Toast.LENGTH_SHORT).show();
                    }

                    saveBoleanValueSharedPreferences("register",true,UserActivity.this);
                    Intent i = new Intent(getApplicationContext(), AddDetailActivity.class);
                    startActivity(i);
                }
            }
            });
    }}