package com.example.cs2340a_team21.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;

public class AddIngredientActivity extends AppCompatActivity {


    private EditText name;
    private EditText quantity;
    private EditText calories;
    private EditText expiration;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        this.name = findViewById(R.id.ingredientName);
        this.quantity = findViewById(R.id.ingredientQuantity);
        this.calories = findViewById(R.id.ingredientCalories);
        this.expiration = findViewById(R.id.ingredientExpirationDate);

        this.submitButton = findViewById(R.id.saveIngredientButton);
        submitButton.setOnClickListener(v -> saveIngredient());
    }

    private void saveIngredient() {
        String result = IngredientsViewModel.addIngredient(this.name.getText().toString(),
                this.quantity.getText().toString(),
                this.calories.getText().toString(), this.expiration.getText().toString());
        if (result.equals("negative")) {
            Toast.makeText(this, "Quantity must be positive",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}