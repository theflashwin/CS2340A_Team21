package com.example.cs2340a_team21.viewmodels;

import android.util.Log;

import com.example.cs2340a_team21.model.User;

public class PersonalInfoViewModel {

    public static boolean setUserInfo(String height, String weight, String gender) {

        if (height == null || weight == null || gender == null) {
            Log.w("werg", "reach first!");
            return false;
        }

        if (height.equals("") || weight.equals("") || gender.equals("")) {
            Log.w("werg", "reach! second");
            return false;
        }

        try {

            int h = Integer.parseInt(height);
            int w = Integer.parseInt(weight);
            Log.w("werg", "reach!");
            User.updateInfo(h, w, gender);

        } catch (Exception e) {
            Log.w("werg", "reach!");
            return false;
        }

        return true;
    }
}
