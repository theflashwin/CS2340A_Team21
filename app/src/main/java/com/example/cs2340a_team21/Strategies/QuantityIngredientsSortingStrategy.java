package com.example.cs2340a_team21.Strategies;

import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;

import java.util.Comparator;

public class QuantityIngredientsSortingStrategy implements SortingStrategy {

    public Comparator<Recipe> getComparator() {

        return new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {

                int q1 = 0;
                int q2 = 0;

                for (Ingredient i : recipe.getIngredients()) {
                    q1 += i.getQuantity();
                }

                for (Ingredient i : t1.getIngredients()) {
                    q2 += i.getQuantity();
                }

                return q1 - q2;

            }
        };

    }

}
