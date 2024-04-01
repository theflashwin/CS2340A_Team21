package com.example.cs2340a_team21.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340a_team21.R;

public class RecipeIngredients extends AppCompatActivity {

    private TextView info;
    private Button back;

    private String val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients);

        this.info = findViewById(R.id.recipeInfoText);
        this.back = findViewById(R.id.backBut);

        Intent intent = getIntent();
        this.val = intent.getStringExtra("recipe");
        info.setText(val);
        back.setOnClickListener(v -> goBack());
    }

    private void goBack() {
        finish();
    }
}