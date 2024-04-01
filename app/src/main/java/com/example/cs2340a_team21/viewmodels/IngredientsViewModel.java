package com.example.cs2340a_team21.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.anychart.data.Tree;
import com.example.cs2340a_team21.model.Pantry;
import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.views.IngredientsFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IngredientsViewModel extends ViewModel{

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
            ingredients = Pantry.getInstance().getIngredients();

        } catch (Exception e) {
        }

        return "success";
    }

    public static void increaseIngredient(String name) {

        Pantry.getInstance().increaseIngredient(name);
        ingredients = Pantry.getInstance().getIngredients();
    }

    public static void decreaseIngredient(String name) {

        Pantry.getInstance().decreaseIngredient(name);
        ingredients = Pantry.getInstance().getIngredients();
    }

    public static List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = User.pantry.getIngredients();
        return ingredients;

    }



}
