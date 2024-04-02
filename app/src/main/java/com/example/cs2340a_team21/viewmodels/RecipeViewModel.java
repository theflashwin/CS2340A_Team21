package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.Strategies.SortingStrategy;
import com.example.cs2340a_team21.model.Cookbook;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel {

    public static List<Recipe> recipes;

    public static void handleOnLoad() {
        if (recipes == null) {
            recipes = Cookbook.getInstance().getRecipes();
        }
    }

    public static String getCanClick(Recipe r, List<Ingredient> ingredients) {

        for (Ingredient i : r.ingredients) {

            boolean found = false;

            for (Ingredient x : ingredients) {
                Log.w("get name ", x.getName() + " " + x.getName().equalsIgnoreCase(i.getName()));
                if (x.getName().equalsIgnoreCase(i.getName())) {
                    found = true;

                    if (i.quantity > x.quantity) {
                        Log.w("Quantity Issue: ", x.getName());
                        return "Can't Make";
                    }

                }
            }

            if (!found) {
                Log.w("name Issue: ", i.getName());
                return "Can't Make";
            }

        }

        return "Open";

    }

    public static void sendRecipe(String name, String ingredients) {

        if (name == null) {
            return;
        }

        if (name.equals("")) {
            return;
        }

        if (ingredients == null) {
            return;
        }

        if (ingredients.equals("")) {
            return;
        }

        ingredients = ingredients.trim();
        List<Ingredient> arr = new ArrayList<>();
        String[] ings = ingredients.split(",");

        for (String str : ings) {
            int index = str.indexOf(":");
            if (!str.substring(0, index).isEmpty()) {
                arr.add(new Ingredient(str.substring(0, index), Integer.parseInt(
                        str.substring(index + 1)), 0, ""));
            }
        }

        Cookbook.getInstance().addRecipe(name, arr);

        Log.d("hey", "KLJH");

    }

    public static void sort(SortingStrategy strategy) {

        recipes.sort(strategy.getComparator());

    }

}
