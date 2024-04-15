package com.example.cs2340a_team21.objects;

public class ShoppingListItem {

    private String name;
    private int quantity;
    private double price;

    public ShoppingListItem(String name, int quantity, double price) {

        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof ShoppingListItem) {

            ShoppingListItem i = (ShoppingListItem) o;

            if (i.getName().equals(this.name)
                    && i.getPrice() == this.price
                    && i.getQuantity() == this.quantity) {
                return true;
            }

        }

        return false;

    }

}
