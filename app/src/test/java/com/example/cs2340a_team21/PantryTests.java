package com.example.cs2340a_team21;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team21.model.Pantry;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;
import com.example.cs2340a_team21.viewmodels.PersonalInfoViewModel;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testRecipeCanClick() {
        Recipe recipe = new Recipe("Recipe1", new ArrayList<>());

        assertEquals("Can't Make", RecipeViewModel.getCanClick(recipe));
    }

    @Test
    public void testCanClickEnoughIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2, 0, ""));
        ingredients.add(new Ingredient("Sugar", 1, 0, ""));
        Pantry.getInstance().getIngredients().addAll(ingredients);
        Recipe recipe = new Recipe("Cake", ingredients);

        assertEquals("Open", RecipeViewModel.getCanClick(recipe));
    }


    @Test
    public void testCanClickMissingIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2, 0, ""));
        ingredients.add(new Ingredient("Sugar", 1, 0, ""));
        Pantry.getInstance().getIngredients().add(new Ingredient("Flour", 2, 0, ""));
        Recipe recipe = new Recipe("Cake", ingredients);

        assertEquals("Can't Make", RecipeViewModel.getCanClick(recipe));
    }

    @Test
    public void testCanClickExtraIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2, 0, ""));
        ingredients.add(new Ingredient("Sugar", 1, 0, ""));
        Pantry.getInstance().getIngredients().addAll(ingredients);
        Pantry.getInstance().getIngredients().add(new Ingredient("Eggs", 6, 0, ""));
        Recipe recipe = new Recipe("Cake", ingredients);

        assertEquals("Open", RecipeViewModel.getCanClick(recipe));
    }

//    @Test
//    public void testCanClickExtraIngredients() {
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient("Flour", 2, 0, ""));
//        ingredients.add(new Ingredient("Sugar", 1, 0, ""));
//        Pantry.getInstance().getIngredients().addAll(ingredients);
//        Pantry.getInstance().getIngredients().add(new Ingredient("Eggs", 6, 0, ""));
//        Recipe recipe = new Recipe("Cake", ingredients);
//
//        assertEquals("Open", RecipeViewModel.getCanClick(recipe));
//    }

}