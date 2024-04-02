package com.example.cs2340a_team21.objects;

import java.util.List;

/**
 * Represents a recipe which includes a name and a list of ingredients required to
 * make the dish.
 * This class provides a structure to hold details about a recipe that can be used
 * within the application
 * to display recipes, manage them, and interact with other components that deal
 * with recipe information.
 */
public class Recipe {

    public String name;
    public List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {

        this.name = name;
        this.ingredients = ingredients;

    }

}
