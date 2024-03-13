package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meal {

    public static boolean sendMeal(String name, int calories) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> meal = new HashMap<>();
        meal.put("Name", name);
        meal.put("Calories", calories);
        meal.put("timestamp", FieldValue.serverTimestamp());
        meal.put("User", User.getUserId());

        db.collection("meals").add(meal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding meal", "DocumentSnapshot added with ID: "
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

    public static List<Map<String, Object>> getMeals() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference meals = db.collection("meals");

        List<Map<String, Object>> ret = new ArrayList<>();
        meals.whereEqualTo("User", User.getUserId()).orderBy("timestamp").
                get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("Name", document.get("Name"));
                                map.put("Calories", document.get("Calories"));
                                ret.add(map);
                                Log.d("Got successfully", document.getId() + " => ");
                            }
                        } else {
                            Log.d("Couldn't get", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return ret;

    }


}
