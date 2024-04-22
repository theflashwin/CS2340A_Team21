package com.example.cs2340a_team21.objects;

import android.util.Log;

import com.example.cs2340a_team21.factory.Item;

public class ShoppingListItem implements Item {

    private String name;
    private int quantity;
    private int calories;

    public ShoppingListItem(String name, int quantity, int calories) {

        this.name = name;
        this.quantity = quantity;
        this.calories = calories;

    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getCalories() {
        return this.calories;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof ShoppingListItem) {

            ShoppingListItem i = (ShoppingListItem) o;

            Log.d("ShoppingListItem", "In equals statement");

            return i.getName().equals(this.name);

        }

        return false;

    }


}
