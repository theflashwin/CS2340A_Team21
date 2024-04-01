package com.example.cs2340a_team21.viewmodels;

import com.example.cs2340a_team21.Strategies.SortingStrategy;
import com.example.cs2340a_team21.model.Cookbook;
import com.example.cs2340a_team21.objects.Recipe;
import java.util.List;

public class RecipeViewModel {

    public static List<Recipe> recipes;

    public static void handleOnLoad() {
        recipes = Cookbook.getInstance().getRecipes();
    }

    public static void sendRecipe(String name, String ingredients) {

    }

    public static void sort(SortingStrategy strategy) {

        recipes.sort(strategy.getComparator());

    }

}
