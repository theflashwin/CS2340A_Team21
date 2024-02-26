package com.example.cs2340a_team21.viewmodels;

import com.example.cs2340a_team21.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class SignInViewModel {

    public static boolean verifySignUp(String username, String password) {

        // verify inputs

        // send to model
        return User.signup(username, password);

    }

}
