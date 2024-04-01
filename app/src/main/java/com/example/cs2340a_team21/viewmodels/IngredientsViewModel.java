package com.example.cs2340a_team21.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cs2340a_team21.model.Pantry;
import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.views.IngredientsFragment;

import java.util.Date;
import java.util.List;

public class IngredientsViewModel {

    private static List<Ingredient> ingredients;

    public static void handleOnLoad() {
         ingredients = Pantry.getInstance().getIngredients();
    }

    public static String addIngredient(String nameIn, String quantityIn, String caloriesIn, String expiration) {


        if (nameIn == null) {
            return "null";
        }

        if (nameIn == "") {
            return "null";
        }

        if (quantityIn == null) {
            return "null";
        }

        int quantity = Integer.parseInt(quantityIn);
        int calories = Integer.parseInt(caloriesIn);

        if (quantity <= 0) {
            Log.d("NEGATIVE", "AHHHHHHH");
            return "negative";
        }

        Log.d("ingredients size:", ingredients.size() + "");

        for (Ingredient i : ingredients) {
            Log.w("Ingredient:", i.name);
            if (i.name.equals(nameIn)) {
                if (i.quanity != 0) {
                    return "duplicate";
                }
            }
        }

        try {

            if (expiration == "") {
                User.pantry.addIngredient(nameIn, quantity, calories, expiration, true);
            } else {
                User.pantry.addIngredient(nameIn, quantity, calories, null, false);
            }

        } catch (Exception e) {
            Log.d("Error", "lkjasd");
        }

        return "success";
    }

}
