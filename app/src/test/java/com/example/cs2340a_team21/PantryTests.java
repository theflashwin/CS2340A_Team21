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

    @Test
    public void testEmptyEntry() {
        assertEquals("null", IngredientsViewModel.addIngredient("", "2", "50", "N/A"));
    }

    @Test
    public void testSuccessfulEntry() {
        assertEquals("success", IngredientsViewModel.addIngredient("Protein Powder", "1", "50", "N/A"));
    }

    @Test
    public void testGetIngredients() {
        assertTrue(IngredientsViewModel.getIngredients().isEmpty());
    }

    @Test
    public void testGetIngredientsAfterAdding() {
        IngredientsViewModel.addIngredient("Strawberries", "10", "20", "N/A");
        assertFalse(IngredientsViewModel.getIngredients().isEmpty());
    }

    @Test
    public void testAddMultipleIngredients() {

        IngredientsViewModel.addIngredient("Flour", "100", "0", "N/A");
        IngredientsViewModel.addIngredient("Sugar", "200", "300", "N/A");
        IngredientsViewModel.addIngredient("Oil", "300", "400", "N/A");
        assertEquals(3, IngredientsViewModel.getIngredients().size());
    }

    @Test
    public void testRemoveIngredient() {
        IngredientsViewModel.addIngredient("Protein Powder", "50", "50", "N/A");
        assertTrue(IngredientsViewModel.getIngredients().size() > 0);
        IngredientsViewModel.getIngredients().remove(0);
        assertTrue(IngredientsViewModel.getIngredients().isEmpty());
    }



}