package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.Strategies.SortingStrategy;
import com.example.cs2340a_team21.model.Cookbook;
import com.example.cs2340a_team21.model.Pantry;
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
                Log.w("get name ", x.name + " " + x.name.equalsIgnoreCase(i.name));
                if (x.name.equalsIgnoreCase(i.name)) {
                    found = true;

                    if (i.quantity > x.quantity) {
                        Log.w("Quantity Issue: ", x.name);
                        return "Can't Make";
                    }

                }
            }

            if (!found) {
                Log.w("name Issue: ", i.name);
                return "Can't Make";
            }

        }

        return "Open";

    }

    public static void sendRecipe(String name, String ingredients) {

        ingredients = ingredients.trim();
        List<Ingredient> arr = new ArrayList<>();
        String[] ings = ingredients.split(",");

        for(String str : ings) {
            int index = str.indexOf(":");
            arr.add(new Ingredient(str.substring(0, index), Integer.parseInt(str.substring(index+1)), 0, ""));
        }

        Cookbook.getInstance().addRecipe(name, arr);

        Log.d("hey", "KLJH");

    }

    public static void sort(SortingStrategy strategy) {

        recipes.sort(strategy.getComparator());

    }

}
