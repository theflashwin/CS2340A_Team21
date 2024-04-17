package com.example.cs2340a_team21.Strategies;

import com.example.cs2340a_team21.objects.Recipe;

import java.util.Comparator;

public class NumIngredientsSortingStrategy implements SortingStrategy {

    public Comparator<Recipe> getComparator() {

        Comparator<Recipe> ret = new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return recipe.getIngredients().size() - t1.getIngredients().size();
            }
        };

        return ret;

    }

}
