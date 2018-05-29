package com.example.aamirkhan.pillreminder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.aamirkhan.pillreminder.R;

import static com.example.aamirkhan.pillreminder.utill.SaveValueSharedPreference.getBoleanValueSharedPreferences;

public class SplashActivity extends AppCompatActivity {
    boolean isRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        isRegister = getBoleanValueSharedPreferences("register",SplashActivity.this);
        TextView tv;
        tv = (TextView) findViewById(R.id.textView);
        tv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottomtoup));

        Thread splash = new Thread() {
            public void run() {
                try {
                    sleep(1 * 3000);


                    if(isRegister){
                        Intent i = new Intent(getApplicationContext(),AddDetailActivity
                                .class);
                        startActivity(i);
                        finish();
                    }else {
                        Intent i = new Intent(getApplicationContext(),UserActivity
                                .class);
                        startActivity(i);
                        finish();
                    }

                }
                catch (InterruptedException e2) {
                    e2.printStackTrace();

                }
            }
        };
splash.start();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
