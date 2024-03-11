package com.example.cs2340a_team21.model;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cs2340a_team21.views.LoginActivity;
import com.example.cs2340a_team21.views.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {

    static boolean ret = false; // login return value

    static boolean ret2 = false; // signup return value

    public static boolean login(String username, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("here:", "yayyy");
                    User.ret = true;
                } else {
                    User.ret = false;
                }
            }
        });

        return ret;

    }

    public static boolean signup(String username, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("here:", "Success!");
                    FirebaseUser user = auth.getCurrentUser();
                    User.ret2 = true;
                } else {
                    Log.d("here:", "Didn't work.");
                    User.ret2 = false;
                }
            }
        });

        return User.ret2;

    }

    

}
