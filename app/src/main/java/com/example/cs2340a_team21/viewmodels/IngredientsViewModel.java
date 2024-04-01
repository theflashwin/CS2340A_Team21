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

        if (nameIn.equals("")) {
            return "null";
        }

        if (quantityIn == null) {
            return "null";
        }

        if (quantityIn.equals("")) {
            return "null";
        }

        if (caloriesIn == null) {
            return "null";
        }

        if (caloriesIn.equals("")) {
            return "null";
        }

        if (expiration == null) {
            return "null";
        }


        int quantity = Integer.parseInt(quantityIn);
        int calories = Integer.parseInt(caloriesIn);

        if (quantity <= 0) {
            return "negative";
        }


        for (Ingredient i : ingredients) {
            if (i.name.equals(nameIn)) {
                if (i.quantity != 0) {
                    return "duplicate";
                }
            }
        }

        try {

            if (expiration.equals("")) {
                User.pantry.addIngredient(nameIn, quantity, calories, expiration, true);
            } else {
                User.pantry.addIngredient(nameIn, quantity, calories, null, false);
            }

        } catch (Exception e) {
        }

        return "success";
    }

}
