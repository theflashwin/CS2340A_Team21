package com.example.cs2340a_team21.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            Log.d("item", item.toString());

            switch (item.toString()) {
            case "Input Meal":
                replaceFragment(new InputMealFragment());
                break;
            case "Recipe":
                replaceFragment(new RecipeFragment());
                break;
            case "Ingredients":
                replaceFragment(new IngredientsFragment());
                break;
            case "Shopping List":
                replaceFragment(new ShoppingListFragment());
                break;
            case "Personal Info":
                replaceFragment(new PersonalFragment());
                break;
            default:
                break;
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}
