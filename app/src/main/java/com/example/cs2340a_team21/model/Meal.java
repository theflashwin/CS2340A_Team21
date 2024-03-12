package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Meal {

    public static boolean sendMeal(String name, int calories) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> meal = new HashMap<>();
        meal.put("Name", name);
        meal.put("Calories", calories);

        db.collection("meals").add(meal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding meal", "DocumentSnapshot added with ID: " + documentReference.getId());
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
