package com.example.cs2340a_team21.factory;

import android.util.Log;

public abstract class ItemFactory {
    public Item createItem(String name, int quantity, int calories, String expirationDate) {
        Log.w("creating item...", "");

        Item myItem = makeIngredient(name, quantity, calories, expirationDate);
        return myItem;
    }

    public abstract Item makeIngredient(String name, int quantity, int calories,
                                        String expirationDate);

}