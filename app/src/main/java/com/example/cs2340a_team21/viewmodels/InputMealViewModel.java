package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.model.Meal;
import com.example.cs2340a_team21.model.User;

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

    public static int getUserHeight() {
        try{
            return User.getUserHeight();
        } catch (Exception E) {
            return -1;
        }
    }

    public static int getUserWeight() {
        try{
            return User.getUserWeight();
        } catch (Exception E) {
            return -1;
        }
    }

    public static String getUserGender() {
        try{
            return User.getGender();
        } catch (Exception E) {
            return "";
        }
    }

}
