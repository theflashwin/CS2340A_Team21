package com.example.cs2340a_team21.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.SignInViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText usernameInput;
    private EditText passwordInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        // Accesses member Ids
        this.usernameInput = findViewById(R.id.usernameSignUpInput);
        this.passwordInput = findViewById(R.id.passwordSignUpInput);
        this.submitButton = findViewById(R.id.signUpSubmit);

        this.auth = FirebaseAuth.getInstance();

        this.submitButton.setOnClickListener(v -> {

            String username = this.usernameInput.getText().toString();
            String password = this.passwordInput.getText().toString();

            boolean successful = SignInViewModel.verifySignUp(username, password);

            // sends to SignInView model

            if (successful) {
                Log.d("here:", "signInWithCustomToken:success");
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this,
                        "Some error occurred when making your account", Toast.LENGTH_SHORT);
                toast.show();
            }

        });

    }


}
