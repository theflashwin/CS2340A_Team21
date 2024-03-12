package com.example.cs2340a_team21;

import static junit.framework.TestCase.assertEquals;

import com.example.cs2340a_team21.model.User;

import org.junit.Before;
import org.junit.Test;

public class UserBackendTests {

    @Before
    public void signIn() {

    }

    @Test
    public void getHeights() {

        User.updateInfo(10, 10, "Male");

        assertEquals(User.getUserHeight(), 10);

    }

}
