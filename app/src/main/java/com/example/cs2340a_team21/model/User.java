package com.example.cs2340a_team21.model;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cs2340a_team21.views.LoginActivity;
import com.example.cs2340a_team21.views.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class User {

    private static final FirebaseAuth auth = FirebaseAuth.getInstance();

    static boolean ret = false; // login return value

    static boolean ret2 = false; // signup return value

    public static boolean login(String username, String password) {

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

    public static boolean updateInfo(int height, int weight, String gender) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("weight", weight);
        user.put("height", height);
        user.put("gender", gender);
        user.put("user", auth.getUid());

        db.collection("users").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding user", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Failed", "Error adding document", e);
                    }
                });

        return true;

    }

}
