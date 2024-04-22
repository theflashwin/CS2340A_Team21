package com.example.cs2340a_team21.model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.cs2340a_team21.factory.Item;
import com.example.cs2340a_team21.factory.ItemFactory;
import com.example.cs2340a_team21.objects.ShoppingListItem;
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

public class ShoppingList {

    private static ShoppingList instance;

    private static ArrayList<ShoppingListItem> items;

    private DocumentReference ref;

    private FirebaseFirestore db;

    private ItemFactory shoppingListItemFactory = new ShoppingListItemFactory();

    public String toString() {

        return ref.getPath();

    }

    private ShoppingList() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d("enter", "constructor");

        Query searchForPantry = db.collection("shopping-list")
                .whereEqualTo("user", User.getUserId());
        searchForPantry.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ref = document.getReference();
                        Log.d("Got shopping list successfully", "sldjkhnfsd");
                    }

                    if (ref == null) {
                        Log.e("Shopping List Error", "Couldn't get shopping list");
                        createShoppingList();
                    }

                } else {
                    Log.d("Couldn't get", "Error getting documents: ",
                            task.getException());
                }
            }
        });

        Log.d("exit", "constructor");

    }

    public void update() {
        fetchItems();
    }

    public void updateQuantity(ShoppingListItem e, int x) {

        ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>)
                            document.get("items");
                    for (Map<String, Object> item : items) {
                        if (item.get("name").equals(e.getName())) {

                            Long quantity = ((Long) item.get("quantity"));

                            if (quantity.intValue() + x <= 0) {
                                items.remove(item);
                            } else {
                                item.put("quantity", quantity.intValue() + x);
                            }
                            break;
                        }
                    }
                    // Now update the document with the modified array
                    ref.update("items", items);
                } else {
                    Log.d("Document", "No such document");
                }
            } else {
                Log.d("Document", "get failed with ", task.getException());
            }
        });

    }

    public void addToShoppingList(ShoppingListItem e) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // check if it is already in the shopping list

        ArrayList<ShoppingListItem> list = getItems();

        Log.w("Entered add shopping list item", ".");

        boolean hasElement = false;

        for (ShoppingListItem i : items) {
            if (i.equals(e)) {
                hasElement = true;
            }
        }

        if (hasElement) {

            Log.w("updating", "quantity");
            updateQuantity(e, e.getQuantity());

        } else {

            Map<String, Object> item = new HashMap<>();

            item.put("name", e.getName());
            item.put("calories", e.getCalories());
            item.put("quantity", e.getQuantity());

            ref.update("items", FieldValue.arrayUnion(item));
            Log.w("Item placed", e.getName());

        }

    }

    public static void createShoppingList() {

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
                    Long calories = (Long) item.get("calories");

                    items.add(new ShoppingListItem(
                            (String) item.get("name"),
                            quantity.intValue(),
                            calories.intValue()));
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

        ArrayList<ShoppingListItem> ret = new ArrayList<>();

        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                List<Map<String, Object>> l = (List<Map<String, Object>>) doc.get("items");
                l.forEach(item -> {

                    Long quantity = (Long) item.get("quantity");
                    Long calories = (Long) item.get("calories");

                    ShoppingListItem newItem =
                            (ShoppingListItem) shoppingListItemFactory.createItem(
                            (String) item.get("name"), quantity.intValue(), calories.intValue(),
                            "0");

                    ret.add(newItem);
                });
            }
        });

        if (items == null) {
            items = ret;
        }

        return ret;

    }

    public class ShoppingListItemFactory extends ItemFactory {
        @Override
        public Item makeIngredient(String name,
                                    int quantity, int calories, String expirationDate) {
            return new ShoppingListItem(name, quantity, calories);
        }
    }

}
