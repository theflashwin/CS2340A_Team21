package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cookbook {

    private static Cookbook cookbook;

    private CollectionReference ref;

    private Cookbook() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        this.ref = db.collection("cookbook");
    }

    public static Cookbook getInstance() {

        if (cookbook == null) {
            cookbook = new Cookbook();
        }

        return cookbook;

    }

    public List<Recipe> getRecipes() {

        ArrayList<Recipe> ret = new ArrayList<>();

        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        ArrayList<Ingredient> ingredients = new ArrayList<>();
                        ((List<Map<String, Object>>) document.get("ingredients"))
                                .forEach(ingredient -> {

                                    Long quantity = ((Long) ingredient.get("quantity"));
                                    Long calories = (Long) ingredient.get("calories");

                                    String expiration = "N/A";

                                    if (ingredient.containsKey("expiration")) {
                                        expiration = (String) ingredient.get("expiration");
                                    }

                                    ingredients.add(new Ingredient((String) ingredient.get("name"),
                                            quantity.intValue(),
                                            calories.intValue(),
                                            expiration));

                                });

                        ret.add(new Recipe((String) document.get("name"), ingredients));

                    }
                }
            }
        });

        return ret;

    }

    public void addRecipe(String name, List<Ingredient> ingredients) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> recipe = new HashMap<>();
        recipe.put("user", User.getUserId());
        recipe.put("name", name);
        recipe.put("ingredients", ingredients);

        db.collection("cookbook").add(recipe)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding recipe", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Failed", "Error adding document", e);
                    }
                });

    }



}
