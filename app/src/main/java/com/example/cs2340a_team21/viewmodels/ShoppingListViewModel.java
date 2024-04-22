package com.example.cs2340a_team21.viewmodels;

import android.util.Log;
import android.widget.Button;

import com.example.cs2340a_team21.model.ShoppingList;
import com.example.cs2340a_team21.model.User;
import com.example.cs2340a_team21.objects.ShoppingListItem;

import java.util.ArrayList;

public class ShoppingListViewModel {

    private static ShoppingList shoppingList;
    private static ArrayList<ShoppingListItem> items;

    private static ArrayList<ShoppingListItem> cart = new ArrayList<>();

    public static void onLoad() {

    }

    public static void fetchItems() {

        ArrayList<ShoppingListItem> list = shoppingList.getItems();

        list.forEach(v -> {
            Log.d("Item Name:", v.getName());
            Log.d("Item Calories", String.valueOf(v.getCalories()));
            Log.d("Item Quantity", String.valueOf(v.getQuantity()));
        });

        items = list;

    }

    public static void addToCart(ShoppingListItem item) {
        cart.add(item);
    }

    public static void removeFromCart(ShoppingListItem item) {
        cart.remove(item);
    }

    public static void toggleCartItem(ShoppingListItem item, Button button) {

        if (cart.contains(item)) {
            removeFromCart(item);
            button.setText("Add to Cart");
        } else {
            addToCart(item);
            button.setText("Remove from Cart");
        }

    }

    public static void checkout() {

        for (ShoppingListItem item : cart) {
            User.getPantry().addIngredient(item.getName(), item.getQuantity(),
                    item.getCalories(), "N/A", true);
        }

    }

    public static void increseQuantity(ShoppingListItem e) {

        User.getShoppingList().updateQuantity(e, 1);

    }

    public static void decreaseQuantity(ShoppingListItem e) {

        User.getShoppingList().updateQuantity(e, -1);

    }

    public static String addToShoppingList(String name, String quantity, String calories) {

        if (name == null || name.equals("")) {
            return "null";
        }

        if (quantity == null || quantity.equals("")) {
            return "null";
        }

        if (calories == null || calories.equals("")) {
            return "null";
        }

        ShoppingList.getInstance().addToShoppingList(new ShoppingListItem(name,
                Integer.parseInt(quantity), Integer.parseInt(calories)));

        return "success";

    }

    public static ArrayList<ShoppingListItem> getItems() {
        shoppingList = User.getShoppingList();
        return shoppingList.getItems();
    }
}
