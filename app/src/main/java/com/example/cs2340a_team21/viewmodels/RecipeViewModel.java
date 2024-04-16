package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.Strategies.SortingStrategy;
import com.example.cs2340a_team21.model.Cookbook;
import com.example.cs2340a_team21.model.ShoppingList;
import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.objects.ShoppingListItem;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel {

    private static List<Recipe> recipes;

    public static void handleOnLoad() {
        if (recipes == null) {
            recipes = Cookbook.getInstance().getRecipes();
        }
    }

    public static List<Recipe> getRecipes() {
        return recipes;
    }

    public static String getCanClick(Recipe r, List<Ingredient> ingredients) {

        for (Ingredient i : r.getIngredients()) {

            boolean found = false;

            for (Ingredient x : ingredients) {
                Log.w("get name ", x.getName() + " " + x.getName().equalsIgnoreCase(i.getName()));
                if (x.getName().equalsIgnoreCase(i.getName())) {
                    found = true;

                    if (i.getQuantity() > x.getQuantity()) {
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

    public static void shopIngredients (Recipe r, List<Ingredient> ingredients) {
        for (Ingredient i : r.getIngredients()) {

            boolean ingredientPresent = false;

            for (Ingredient x : User.getPantry().getStaticIngredients()) {
                Log.w("got name ", x.getName() + " " + x.getName().equalsIgnoreCase(i.getName()));

                if (x.getName().equalsIgnoreCase(i.getName())) {

                    ingredientPresent = true;

                    if (i.getQuantity() > x.getQuantity()) {
                        Log.w("Quantity Issue: ", x.getName());
                        User.getShoppingList().addToShoppingList(new ShoppingListItem(i.getName(),
                                (i.getQuantity() - x.getQuantity()), i.getCalories()));
                    }
                }
            }

            if (!ingredientPresent) {
                Log.w("Quantity Issue (None): ", i.getName());
                User.getShoppingList().addToShoppingList(new ShoppingListItem(i.getName(),
                        (i.getQuantity()), i.getCalories()));
            }
        }
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
