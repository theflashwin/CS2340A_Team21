package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.objects.ShoppingListItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

public class ShoppingList {

    private static ShoppingList instance;

    private ArrayList<ShoppingListItem> items;

    private DocumentReference ref;

    private FirebaseFirestore db;

    private ShoppingList() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d("enter", "constructor");

        Query searchForPantry = db.collection("shopping-list").whereEqualTo("user", User.getUserId());
        searchForPantry.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ref = document.getReference();
                        Log.d("Got shopping list successfully", "sldjkhnfsd");
                    }

                    if (ref == null) {
                        createShoppingList();
                    }

                } else {
                    Log.d("Couldn't get", "Error getting documents: ",
                            task.getException());
                }
            }
        });

        items = new ArrayList<>();

    }

    public void update() {
        fetchItems();
    }

    public void addToShoppingList(ShoppingListItem e) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> item = new HashMap<>();

        item.put("name", e.getName());
        item.put("price", e.getPrice());
        item.put("quantity", e.getQuantity());

        ref.update("items", FieldValue.arrayUnion(item));

    }

    public void createShoppingList() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> shoppingList = new HashMap<>();
        shoppingList.put("user", User.getUserId());
        shoppingList.put("items", Collections.emptyList());

        db.collection("shopping-list").add(shoppingList)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Adding pantry", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Failed", "Error adding document", e);
                    }
                });

        Query searchForPantry = db.collection("shopping-list").whereEqualTo("user", User.getUserId());
        searchForPantry.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ref = document.getReference();
                        Log.d("Got meal successfully", "sldjkhnfsd");
                    }

                } else {
                    Log.d("Couldn't get", "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void fetchItems() {

        items.clear();

        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                List<Map<String, Object>> l = (List<Map<String, Object>>) doc.get("items");
                l.forEach(item -> {

                    Long quantity = (Long) item.get("quantity");
                    Double price = (Double) item.get("price");

                    items.add(new ShoppingListItem(
                            (String) item.get("name"),
                            quantity.intValue(),
                            price.doubleValue()));
                });
            }
        });


    }

    public static ShoppingList getInstance() {

        if (instance == null) {
            instance = new ShoppingList();
        }

        return instance;

    }

    public ArrayList<ShoppingListItem> getItems() {

        update();
        return this.items;

    }

}
