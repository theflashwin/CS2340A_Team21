package com.example.cs2340a_team21.Strategies;

import com.example.cs2340a_team21.objects.Recipe;

import java.util.Comparator;

public interface SortingStrategy {

    public Comparator<Recipe> getComparator();

}

//strategy pattern

