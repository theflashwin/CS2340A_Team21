package com.example.cs2340a_team21.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Fragment representing an ingredients list.
 * Class is responsible for displaying ingredients to the user.
 * Encapsulates the UI and logic needed to show ingredients possibly for a recipe or a food item.
 * <p>
 * Activities containing this fragment must use the {@link IngredientsFragment#newInstance} method
 * to create an instance of this fragment and add it to their user interface.
 */
public class IngredientsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText name;
    private EditText quantity;
    private EditText calories;
    private EditText expiration;

    private Button submitButton;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IngredientsFragment.
     */
    public static IngredientsFragment newInstance(String param1, String param2) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ingredients, container, false);

        IngredientsViewModel.handleOnLoad();

        this.name = view.findViewById(R.id.editName);
        this.quantity = view.findViewById(R.id.editQuantity);
        this.calories = view.findViewById(R.id.editCalories);
        this.expiration = view.findViewById(R.id.editExpiration);

        this.submitButton = view.findViewById(R.id.submit);

        this.submitButton.setOnClickListener(v -> {
            String result = IngredientsViewModel.addIngredient(this.name.getText().toString(), this.quantity.getText().toString(),
                    this.calories.getText().toString(), this.expiration.getText().toString());

            if (result.equals("negative")) {
                Toast.makeText(getContext(), "Quantity must be positive", Toast.LENGTH_LONG).show();
            }

        });

        return view;

    }
}