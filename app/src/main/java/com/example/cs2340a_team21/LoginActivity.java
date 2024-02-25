package com.example.cs2340a_team21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            });

        });

        this.signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });


    }





}