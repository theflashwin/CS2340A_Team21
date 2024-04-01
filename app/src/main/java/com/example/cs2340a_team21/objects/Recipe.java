package com.example.cs2340a_team21.objects;

import java.util.List;

public class Recipe {

    public String name;
    public List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {

        this.name = name;
        this.ingredients = ingredients;

    }

}
