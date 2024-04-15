package com.example.cs2340a_team21.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.objects.ShoppingListItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

public class ShoppingList {

    private static ShoppingList instance;

    private ArrayList<ShoppingListItem> items;

    private CollectionReference ref;

    private FirebaseFirestore db;

    private ShoppingList() {

        this.db = FirebaseFirestore.getInstance();
        this.ref = db.collection("shopping-list");

        this.items = new ArrayList<>();

        fetchItems();

    }

    public void update() {
        fetchItems();
    }

    private void fetchItems() {

        items.clear();

        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Long quantity = (Long) document.get("quantity");
                        Float price = (Float) document.get("price");

                        items.add(new ShoppingListItem(
                                (String) document.get("name"),
                                quantity.intValue(),
                                price.doubleValue()));

                    }
                } else {

                }
            }
        });

    }

    public static synchronized ShoppingList getInstance() {

        if (instance == null) {
            instance = new ShoppingList();
        }

        return instance;

    }

    public ArrayList<ShoppingListItem> getItems() {
        return this.items;
    }

}
