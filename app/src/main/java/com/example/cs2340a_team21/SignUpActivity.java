package com.example.cs2340a_team21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;

    private EditText usernameInput;
    private EditText passwordInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.usernameInput = findViewById(R.id.usernameSignUpInput);
        this.passwordInput = findViewById(R.id.passwordSignUpInput);
        this.submitButton = findViewById(R.id.signUpSubmit);

        this.auth = FirebaseAuth.getInstance();

        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submitButton.setOnClickListener(v -> {

            String username = this.usernameInput.getText().toString();
            String password = this.passwordInput.getText().toString();

            auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                }
            });



        });

    }

    private void createNewUser(String username, String password) {



    }

}