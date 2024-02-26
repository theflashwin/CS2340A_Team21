package com.example.cs2340a_team21.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;

import com.example.cs2340a_team21.R;
// Define SplashActivity class which extends AppCompatActivity.
// AppCompatActivity provides compatibility support for older versions of Android.
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Creating the new Handler.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer ends.
                // Create an Intent to start the LoginActivity.
                Intent iHome = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(iHome);
            }
            // Set a delay time of 4000ms (4 seconds) before the run() method is called.
        }, 4000);



    }
}