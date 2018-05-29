package com.example.aamirkhan.pillreminder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.aamirkhan.pillreminder.R;
import com.example.aamirkhan.pillreminder.database.Database;
import com.example.aamirkhan.pillreminder.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import java.util.ArrayList;

public class AddDetailActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView iv;
    int currentItemIndex;
    ArrayList<User> allUserList;
    Database database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        iv=(ImageView) findViewById(R.id.imageView);
        allUserList = new ArrayList<>();
        database = new Database(AddDetailActivity.this);
        database.open();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        addBottomNavigationItems();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AddDetailActivity.class);
                startActivity(i);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("user");


        databaseReference.addValueEventListener(new ValueEventListener() {
            // ArrayList<TrainSchedule> trainScheduleList = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User c = snapshot.getValue(User.class);
                    //Log.d("Categories: ", c.name + " " + c.food_items);
                    allUserList.add(c);


                    database.saveUser(c.getUser_name(),c.getMob_number());
                }
              //  addRows();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addBottomNavigationItems(){

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Add Reminder", ContextCompat.getColor(this, R.color.colorAccent), R.drawable.ic_add_remainder);
        final BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Group Member", ContextCompat.getColor(this, R.color.colorActive), R.drawable.ic_group_member);
        final BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Reminders", ContextCompat.getColor(this, R.color.colorActive), R.drawable.ic_remainders);

//        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
//                ("Outgoing", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_call_outgoing);
//        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
//                ("Favorite", ContextCompat.getColor(this, R.color.colorPrimaryDark), R.drawable.ic_interest);
        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
//        bottomNavigationView.addTab(bottomNavigationItem3);
        bottomNavigationView.isWithText(true);
        bottomNavigationView.isColoredBackground(false);
        bottomNavigationView.clearAnimation();

        currentItemIndex = bottomNavigationView.getCurrentItem();

//        bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.colorActive);
//        bottomNavigationItem.setColor(R.color.colorActive);
        bottomNavigationView.setTextActiveSize(30);
        bottomNavigationView.setTextInactiveSize(30);
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {

                switch (index) {
                    case 0: {
                        currentItemIndex = bottomNavigationView.getCurrentItem();

                    }
                    break;
                    case 1: {
                        currentItemIndex = bottomNavigationView.getCurrentItem();
                        //setMainFragment(new SettingsFragment(), "settings", "settings", currentItemIndex, index);
                    }
                    break;
                    case 2: {
                        currentItemIndex = bottomNavigationView.getCurrentItem();
                     //   setMainFragment(new ViewLog(), "viewlog", "viewlog", currentItemIndex, index);
                    }
                    break;

                    default: {
                        //setMainFragment(new DashboardFragment(), "dashboard", "dashboard");
                    }
                    break;
                }

            }
        });
    }


}
