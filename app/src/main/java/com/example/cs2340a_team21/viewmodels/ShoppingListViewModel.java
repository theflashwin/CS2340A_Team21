package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.model.ShoppingList;
import com.example.cs2340a_team21.objects.ShoppingListItem;

import java.util.ArrayList;

public class ShoppingListViewModel {

    private static ShoppingList shoppingList;
    private static ArrayList<ShoppingListItem> items;

    public static void onLoad() {
        shoppingList = ShoppingList.getInstance();
        shoppingList.update();
        fetchItems();
    }

    public static void fetchItems() {

        ArrayList<ShoppingListItem> list = shoppingList.getItems();

        list.forEach(v -> {
            Log.d("Item Name:", v.getName());
            Log.d("Item Price", String.valueOf(v.getPrice()));
            Log.d("Item Quantity", String.valueOf(v.getQuantity()));
        });

        items = list;

    }

    public static ArrayList<ShoppingListItem> getItems() {
        return items;
    }
}
