package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class User {

    private static boolean ret = false; // result of login attempt

    private static boolean ret2 = false; // result of signup attempt

    private static int height = 0;
    private static int weight = 0;

    private static String gender = "";

    private static Pantry pantry;
    private static ShoppingList shoppingList;

    public static Pantry getPantry() {
        return pantry;
    }

    public static ShoppingList getShoppingList() {

        if (shoppingList == null) {
            shoppingList = ShoppingList.getInstance();
        }

        return shoppingList;
    }

    private static boolean foundUser = true; //is this variable being used?

    public static boolean login(String username, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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


        pantry = Pantry.getInstance();

        shoppingList = ShoppingList.getInstance();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

        return ret;

    }

    public static boolean signup(String username, String password) {

        CreateAuth c = CreateAuth.getInstance();
        FirebaseAuth auth = c.auth;

        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

    public static boolean userExists(String id) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query q = db.collection("users").whereEqualTo("user", id);
        QuerySnapshot user = q.get().getResult();

        return !user.isEmpty();

    }

    public static boolean updateInfo(int height, int weight, String gender) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("weight", weight);
        user.put("height", height);
        user.put("gender", gender);
        user.put("user", getUserId());
        user.put("timestamp", FieldValue.serverTimestamp());

        Log.d("ener", "ekljbwerg");

        db.collection("users").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding user", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
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

    public static String getUserId() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        return auth.getUid();
    }

    public static int getUserHeight() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference users = db.collection("users");
        users.whereEqualTo("user", getUserId()).orderBy("timestamp").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                height = Integer.parseInt(document.get("height").toString());
                                Log.d("Got successfully", document.getId() + " => " + height);
                            }
                        } else {
                            Log.d("Couldn't get", "Error getting documents: ", task.getException());
                        }
                    }
                });

        Log.d("Got successfully",  " => " + height);
        return height;

    }

    public static int getUserWeight() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference users = db.collection("users");
        users.whereEqualTo("user", getUserId()).orderBy("timestamp").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                weight = Integer.parseInt(document.get("weight").toString());
                                Log.d("Got successfully", document.getId() + " => " + weight);
                            }
                        } else {
                            Log.d("Couldn't get", "Error getting documents: ", task.getException());
                        }
                    }
                });

        Log.d("Got successfully", " => " + weight);

        return weight;
    }

    public static String getGender() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference users = db.collection("users");
        users.whereEqualTo("user", getUserId()).orderBy("timestamp").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gender = (String) document.get("gender");
                                Log.d("Got successfully", document.getId() + " => " + gender);
                            }
                        } else {
                            Log.d("Couldn't get", "Error getting documents: ", task.getException());
                        }
                    }
                });

        Log.w("Gender:", gender);

        return gender;

    }

    public static class CreateAuth {
        private static volatile CreateAuth uniqueInstance;
        private FirebaseAuth auth;

        private CreateAuth() {
            this.auth = FirebaseAuth.getInstance();
        }

        public static synchronized CreateAuth getInstance() {
            if (uniqueInstance == null) {
                uniqueInstance = new CreateAuth();
            }
            return uniqueInstance;
        }
    }




}
