package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cs2340a_team21.objects.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Pantry {

    private static Pantry instance;

    private DocumentReference pantryRef;

    private List<Ingredient> staticIngredients;

    public List<Ingredient> getStaticIngredients() {
        return staticIngredients;
    }

    private Pantry() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query searchForPantry = db.collection("pantries").whereEqualTo("user", User.getUserId());
        searchForPantry.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        pantryRef = document.getReference();
                        Log.d("Got meal successfully", "sldjkhnfsd");
                    }

                    if (pantryRef == null) {
                        createPantry();
                    }

                } else {
                    Log.d("Couldn't get", "Error getting documents: ", task.getException());
                }
            }
        });

    }

    public void initializeStaticIngredients() {
        this.staticIngredients = getInstance().getIngredients();
    }

    public static Pantry getInstance() {
        if (instance == null) {
            instance = new Pantry();
        }
        return instance;
    }

    public boolean addIngredient(String name, int quantity, int calories,
                                 String expiration, boolean includeExpiration)
            throws InterruptedException {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> ingredient = new HashMap<>();
        ingredient.put("name", name);
        ingredient.put("calories", calories);
        ingredient.put("quantity", quantity);
        ingredient.put("expiration", expiration);

        pantryRef.update("ingredients", FieldValue.arrayUnion(ingredient));

        return true;

    }

    public List<Ingredient> getIngredients() {

        ArrayList<Ingredient> ret = new ArrayList<>();

        if (pantryRef == null) {
            return new ArrayList<>();
        }

        pantryRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                List<Map<String, Object>> ingredients = (List<Map<String, Object>>)
                        doc.get("ingredients");
                ingredients.forEach(ingredient -> {

                    Long quantity = ((Long) ingredient.get("quantity"));
                    Long calories = (Long) ingredient.get("calories");

                    String expiration = "N/A";

                    if (ingredient.containsKey("expiration")) {
                        expiration = (String) ingredient.get("expiration");
                    }

                    ret.add(new Ingredient((String) ingredient.get("name"),
                            quantity.intValue(),
                            calories.intValue(),
                            expiration));
                });
            }
        });

        return ret;

    }

    private void createPantry() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> pantry = new HashMap<>();
        pantry.put("user", User.getUserId());
        pantry.put("ingredients", Collections.emptyList());

        db.collection("pantries").add(pantry)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding pantry", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
                        pantryRef = documentReference;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Failed", "Error adding document", e);
                    }
                });

    }

    public void increaseIngredient(String name) {

        pantryRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>)
                            document.get("ingredients");
                    for (Map<String, Object> item : items) {
                        if (item.get("name").equals(name)) {

                            Long quantity = ((Long) item.get("quantity"));

                            item.put("quantity", quantity.intValue() + 1);
                            break;
                        }
                    }
                    // Now update the document with the modified array
                    pantryRef.update("ingredients", items);
                } else {
                    Log.d("Document", "No such document");
                }
            } else {
                Log.d("Document", "get failed with ", task.getException());
            }
        });

    }

    public void decreaseIngredient(String name) {
        pantryRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>)
                            document.get("ingredients");
                    for (Map<String, Object> item : items) {
                        if (item.get("name").equals(name)) {

                            Long quantity = ((Long) item.get("quantity"));

                            item.put("quantity", quantity.intValue() - 1);

                            if (quantity.intValue() - 1 <= 0) {
                                items.remove(item);
                            }

                            break;
                        }
                    }
                    // Now update the document with the modified array
                    pantryRef.update("ingredients", items);
                } else {
                    Log.d("Document", "No such document");
                }
            } else {
                Log.d("Document", "get failed with ", task.getException());
            }
        });


    }

}
