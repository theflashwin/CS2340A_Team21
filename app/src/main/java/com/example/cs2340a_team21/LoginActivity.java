package com.example.cs2340a_team21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    // input boxes

    private EditText usernameInput;
    private EditText passwordInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usernameInput = findViewById(R.id.usernameInput);
        this.passwordInput = findViewById(R.id.passwordInput);
        this.submitButton = findViewById(R.id.submitButton);

        this.submitButton.setOnClickListener(v -> {

            // verify user credential

        });


    }

    protected boolean verifyCredentials(String username, String password) {
        return true;
    }





}