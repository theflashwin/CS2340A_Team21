package com.example.cs2340a_team21.viewmodels;

import com.example.cs2340a_team21.model.Pantry;
import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.objects.Ingredient;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class IngredientsViewModel extends ViewModel {

    private static List<Ingredient> ingredients;

    public static void handleOnLoad() {
        ingredients = Pantry.getInstance().getIngredients();
        Pantry.getInstance().initializeStaticIngredients();
    }

    public static String addIngredient(String nameIn, String quantityIn,
                                       String caloriesIn, String expiration) {


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

//        if (expiration == null) {
//            return "null";
//        }


        int quantity = Integer.parseInt(quantityIn);
        int calories = Integer.parseInt(caloriesIn);

        if (quantity <= 0) {
            return "negative";
        }


        for (Ingredient i : ingredients) {
            if (i.getName().equals(nameIn)) {
                if (i.getQuantity() != 0) {
                    return "duplicate";
                }
            }
        }

        try {

            if (expiration.equals("")) {
                User.getPantry().addIngredient(nameIn, quantity, calories, expiration, true);
            } else {
                User.getPantry().addIngredient(nameIn, quantity, calories, null, false);
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
        List<Ingredient> ingredients = User.getPantry().getIngredients();
        return ingredients;

    }



}
