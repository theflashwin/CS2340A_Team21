package com.example.cs2340a_team21;

import static junit.framework.TestCase.assertEquals;

import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.viewmodels.InputMealViewModel;
import com.example.cs2340a_team21.viewmodels.PersonalInfoViewModel;

import org.junit.Before;
import org.junit.Test;

public class UserBackendTests {

    @Before
    public void signIn() {

    }

    // valid info - Mukund
//    @Test
//    public void userInfoCorrect() {
//
//        boolean x = PersonalInfoViewModel.setUserInfo("70", "150", "Male");
//        assertEquals(true, x);
//
//    }

    // user info null checks - Ashwin
    @Test
    public void userInfoNull() {

        boolean x = PersonalInfoViewModel.setUserInfo(null, "34", "Male");
        assertEquals(false, x);

    }

    // user info null checks - Ashwin
    @Test
    public void userInfoNull2() {

        boolean x = PersonalInfoViewModel.setUserInfo("45", null, "Male");
        assertEquals(false, x);

    }

    // another user info null test: Suhas

    @Test
    public void userInfoNull3() {

        boolean x = PersonalInfoViewModel.setUserInfo("45", "34", null);
        assertEquals(false, x);

    }

    // user info empty test: Suhas

    @Test
    public void userInfoEmpty1() {

        boolean x = PersonalInfoViewModel.setUserInfo("", "34", "Male");
        assertEquals(false, x);

    }

    @Test
    public void userInfoEmpty2() {

        boolean x = PersonalInfoViewModel.setUserInfo("34", "", "Male");
        assertEquals(false, x);

    }

    @Test
    public void userInfoEmpty3() {

        boolean x = PersonalInfoViewModel.setUserInfo("34", "34", "");
        assertEquals(false, x);

    }

    @Test
    public void userInfoNotNumber1() {

        boolean x = PersonalInfoViewModel.setUserInfo("3r", "34", "Male");
        assertEquals(false, x);

    }

    @Test
    public void userInfoNotNumber2() {

        boolean x = PersonalInfoViewModel.setUserInfo("34", "34e", "Male");
        assertEquals(false, x);

    }

    // valid info - Mukund
    @Test
    public void mealInfoCorrect() {

        boolean x = InputMealViewModel.sendMeal("Brownie", "150");
        assertEquals(false, x);

    }

    @Test
    public void mealInfoNull() {

        boolean x = InputMealViewModel.sendMeal("Dosa", null);
        assertEquals(false, x);

    }

    @Test
    public void mealInfoNull2() {

        boolean x = InputMealViewModel.sendMeal(null, "300");
        assertEquals(false, x);

    }

    @Test
    public void mealInfoEmpty1() {

        boolean x = InputMealViewModel.sendMeal("Dosa", "");
        assertEquals(false, x);

    }

    @Test
    public void mealInfoEmpty2() {

        boolean x = InputMealViewModel.sendMeal("", "200");
        assertEquals(false, x);

    }

    @Test
    public void mealInfoNotNum() {

        boolean x = InputMealViewModel.sendMeal("Dosa", "200wet");
        assertEquals(false, x);

    }

}
