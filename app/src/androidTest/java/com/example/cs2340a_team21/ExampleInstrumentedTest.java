package com.example.cs2340a_team21;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cs2340a_team21.model.ShoppingList;
import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.objects.ShoppingListItem;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;
import com.example.cs2340a_team21.viewmodels.ShoppingListViewModel;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.cs2340a_team21", appContext.getPackageName());
    }
    @Test
    public void testAddIngredients() {
        IngredientsViewModel.addIngredient("banana", "5", "100", "10/24");
        IngredientsViewModel.addIngredient("muffin", "3", "300", "10/23");
        AssertEquals(2, IngredientsViewModel.getIngredients().size());
    }

    @Test
    public void testShoppingListAdding() {
        ShoppingListViewModel.addToShoppingList("popcorn", "5", "200");
        ShoppingListViewModel.addToShoppingList("apple", "1", "200");
        ShoppingListViewModel.addToShoppingList("pie", "2", "450");
        AssertEquals(3, ShoppingListViewModel.getItems().size());
    }

    @Test
    public void testSuccessfulShoppingAdd() {
        assertEquals("success", ShoppingListViewModel.addToShoppingList("popcorn", "5", "200"));
    }
    @Test
    public void testShoppingListRemove() {
        ShoppingListItem item = new ShoppingListItem("banana", 5, 100);
        ShoppingListViewModel.addToCart(item);
        ShoppingListViewModel.removeFromCart(item);
        AssertEquals(0, ShoppingListViewModel.getItems().size());
    }
    @Test
    public void testGetShoppingList() {
        AssertFalse(ShoppingListViewModel.getItems().isEmpty());
    }
    @Test
    public void testNull() {
        assertEquals(0, ShoppingListViewModel.getItems().size());

    }
    @Test
    public void testInvalidShoppingListAddition() {
        assertEquals("null", ShoppingListViewModel.addToShoppingList("", "5", "200"));
    }
    @Test
    public void testCheckout() {
        ShoppingListItem item1 = new ShoppingListItem("curry", 5, 150);

        ShoppingListViewModel.addToCart(item1);
        ShoppingListViewModel.checkout();
        assertTrue(User.getPantry().getIngredients().contains(new Ingredient("curry", 5, 150, "N/A")));
    }
    @Test
    public void testGetAfterAdding() {
        ShoppingListViewModel.addToShoppingList("Blueberries", "5", "10");
        assertFalse(ShoppingListViewModel.getItems().isEmpty());
    }
    @Test
    public void testSendRecipe() {
        RecipeViewModel.sendRecipe("muffin", "Flour, sugar, Cocoa Powder");
        int size = RecipeViewModel.getRecipes().size();
        Recipe last_item = RecipeViewModel.getRecipes().get(size-1);
        assertEquals("muffin", last_item.getName());
    }
    @Test
    public void testGetRecipes() {
        RecipeViewModel.handleOnLoad();
        assertNotNull(RecipeViewModel.getRecipes());
    }
    @Test
    public void testSendNullRecipe() {
        RecipeViewModel.sendRecipe(null, "Cookies");
        assertEquals(0, RecipeViewModel.getRecipes().size());
    }
    @Test

}