package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.model.Meal;

public class InputMealViewModel {

    public static boolean sendMeal(String name, String calories) {

        if (name == null || calories == null) {
            return false;
        }

        if (name.equals("")) {
            return false;
        }

        if (calories.equals("")) {
            return false;
        }

        try {
            int caloriesValue = Integer.parseInt(calories);
            return Meal.sendMeal(name, caloriesValue);
        } catch(Exception e) {
            return false;
        }

    }

}
