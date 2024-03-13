package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.model.Meal;
import com.example.cs2340a_team21.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        } catch (Exception e) {
            return false;
        }

    }

    public static List<Map<String, Object>> getMealsFromCurrentDate() {

        List<Map<String, Object>> ret = new ArrayList<>();
        List<Map<String, Object>> meals = Meal.getMeals();

        // filter by date
        for (Map<String, Object> map : meals) {

            try {
                ret.add(map);
            } catch (Exception e) {
                //ret.add(map);
            }

        }

        return ret;

    }
    

    public static int sumCurrentCalories() {

        List<Map<String, Object>> list = Meal.getMeals();

        int sum = 0;

        for (Map<String, Object> meal : list) {
            if (meal.get("Calories") != null) {
                sum += Integer.valueOf((Integer) meal.get("Calories"));
                Log.d("Retrieved calorie successfully ", String.valueOf((Integer) meal.get("Calories")));
            }
        }

        return sum;

    }

    public static int getUserHeight() {
        try {
            return User.getUserHeight();
        } catch (Exception E) {
            return -1;
        }
    }

    public static int getUserWeight() {
        try {
            return User.getUserWeight();
        } catch (Exception E) {
            return -1;
        }
    }

    public static String getUserGender() {
        try {
            return User.getGender();
        } catch (Exception E) {
            return "";
        }
    }

}
