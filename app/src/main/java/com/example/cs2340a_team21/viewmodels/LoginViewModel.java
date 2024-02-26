package com.example.cs2340a_team21.viewmodels;

import com.example.cs2340a_team21.model.User;

public class LoginViewModel {

    public static boolean verifyLoginInputs(String username, String password) {

        // logic to verify inputs

        // send to FireBase auth logic
        return User.login(username, password);

    }

}
