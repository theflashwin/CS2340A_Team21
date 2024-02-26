package com.example.cs2340a_team21.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    // firebase instance

    FirebaseAuth auth;

    // input boxes

    private EditText usernameInput;
    private EditText passwordInput;
    private Button submitButton;
    private Button signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usernameInput = findViewById(R.id.usernameInput);
        this.passwordInput = findViewById(R.id.passwordInput);
        this.submitButton = findViewById(R.id.submitButton);
        this.signUpLink = findViewById(R.id.signUpPageLink);

        auth = FirebaseAuth.getInstance();

        this.submitButton.setOnClickListener(v -> {

            String username = this.usernameInput.getText().toString();
            String password = this.passwordInput.getText().toString();
            boolean successful = LoginViewModel.verifyLoginInputs(username, password);

            // sends to LoginViewModel

            if (successful) {
                Toast toast = Toast.makeText(this, "Login Successful!!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {

                Toast toast = Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT);
                toast.show();

            }

        });

        this.signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Close all activities
                System.exit(0); // Exit the application
            }

        });
    }
}