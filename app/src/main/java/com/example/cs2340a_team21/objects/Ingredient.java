package com.example.cs2340a_team21.objects;

import android.util.Log;

public class Ingredient {

    private String name;
    public int quantity;
    public int calories;
    public String expirationDate;

    public Ingredient(String name, int quantity, int calories, String expirationDate) {

        Log.w("creating ingredient...", "");

        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Ingredient) {
            Ingredient i = (Ingredient) o;
            return i.name.equals(this.name);
        } else {
            return false;
        }

    }

    public String getName() {return this.name;}

    public int getQuantity() {return this.quantity;}

    public int getCalories() {
        return calories;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
