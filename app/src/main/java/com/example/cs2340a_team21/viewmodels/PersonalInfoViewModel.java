package com.example.cs2340a_team21.viewmodels;

import com.example.cs2340a_team21.model.User;

public class PersonalInfoViewModel {

    public static boolean setUserInfo(String height, String weight, String gender) {
        //Null checks
        if (height == null || weight == null || gender == null) {
            return false;
        }

        if (height.equals("") || weight.equals("") || gender.equals("")) {
            return false;
        }

// cleaned up here

        try {

            int hin = Integer.parseInt(height);
            int win = Integer.parseInt(weight);
            User.updateInfo(hin, win, gender);

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
