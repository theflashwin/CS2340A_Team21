package com.example.cs2340a_team21.objects;

import android.util.Log;

public class Ingredient {

    public String name;
    public int quanity;
    public int calories;
    public String expirationDate;

    public Ingredient(String name, int quanity, int calories, String expirationDate) {

        Log.w("creating ingredient...", "");

        this.name = name;
        this.quanity = quanity;
        this.calories = calories;
        this.expirationDate = expirationDate;
    }

}
