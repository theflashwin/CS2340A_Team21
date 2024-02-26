package com.example.cs2340a_team21.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.SignInViewModel;
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

        submitButton.setOnClickListener(v -> {

            String username = this.usernameInput.getText().toString();
            String password = this.passwordInput.getText().toString();

            if (SignInViewModel.verifySignUp(username, password)) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this, "Some error occurred when making your account", Toast.LENGTH_SHORT);
                toast.show();
            }

        });

    }


}