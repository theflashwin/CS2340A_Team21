package com.example.cs2340a_team21.viewmodels;

import com.example.cs2340a_team21.model.User;

public class SignInViewModel {

    public static boolean verifySignUp(String username, String password) {

        // verify inputs
        if (username == null || password == null) {
            return false;
        }

        // send to model
        return User.signup(username, password);

    }

}
