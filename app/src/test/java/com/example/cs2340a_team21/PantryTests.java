package com.example.cs2340a_team21;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;
import com.example.cs2340a_team21.viewmodels.PersonalInfoViewModel;

public class PantryTests {

    @Before
    public void setup() {
//        IngredientsViewModel.handleOnLoad();
    }


    @Test
    public void testDuplicateEntry() {

        IngredientsViewModel.addIngredient("Protein Powder", "50", "50", "N/A");
        assertEquals("duplicate", IngredientsViewModel.addIngredient("Protein Powder", "50", "50", "N/A"));

    }

    @Test
    public void testNegativeEntry() {

        assertEquals("negative", IngredientsViewModel.addIngredient("Protein Powder", "-5", "50", "N/A"));

    }

    @Test
    public void testZeroEntry() {

        assertEquals("negative", IngredientsViewModel.addIngredient("Protein Powder", "0", "50", "N/A"));

    }

    @Test
    public void testNullEntry() {
        assertEquals("null", IngredientsViewModel.addIngredient("Strawberries", null, "25", "01/31"));
    }

//    @Test
//    public void testNullEntry() {
//        assertEquals("null", IngredientsViewModel.addIngredient("Strawberries", null, "25", "01/31");
//    }


}