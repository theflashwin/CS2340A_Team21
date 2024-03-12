package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.model.Meal;

public class InputMealViewModel {

    public static boolean sendMeal(String name, String calories) {

        if (name.equals("")) {
            Log.d("Input Error", "Name is invalid");
        }

        if (name.equals("")) {
            Log.d("Input Error", "calories is invalid");
        }

        try {
            int caloriesValue = Integer.parseInt(calories);
            return Meal.sendMeal(name, caloriesValue);
        } catch(Exception e) {
            return false;
        }

    }

}
