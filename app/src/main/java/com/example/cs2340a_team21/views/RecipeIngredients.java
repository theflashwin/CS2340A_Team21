package com.example.cs2340a_team21.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;
import android.util.Log;


public class RecipeIngredients extends AppCompatActivity {

    private TextView info;
    private Button back;
    private Button cook;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients);

        this.info = findViewById(R.id.recipeInfoText);
        this.back = findViewById(R.id.backBut);
        this.cook = findViewById(R.id.cookRecipe);

        Intent intent = getIntent();
        info.setText(intent.getStringExtra("Recipe"));
        back.setOnClickListener(v -> goBack());

        this.cook.setOnClickListener(v -> {

            int recipePosition = intent.getIntExtra("RecipePosition", 3);
            Recipe r = RecipeViewModel.getRecipes().get(recipePosition);

            String message = r.getName() + ", Position: " + recipePosition;

            Log.w("Calling method to cook", message);
            RecipeViewModel.cookMeal(r);
        });
    }

    private void goBack() {
        finish();
    }
}