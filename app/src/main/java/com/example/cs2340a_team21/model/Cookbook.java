package com.example.cs2340a_team21.model;

public class Cookbook {

    private static Cookbook cookbook;

    private Cookbook() {

    }

    public static Cookbook getInstance() {

        if (cookbook == null) {
            cookbook = new Cookbook();
        }

        return cookbook;

    }

}
