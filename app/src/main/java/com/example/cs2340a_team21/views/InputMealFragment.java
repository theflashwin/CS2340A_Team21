package com.example.cs2340a_team21.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.InputMealViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputMealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputMealFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Layout Widgets

    private EditText nameInput;
    private EditText caloriesInput;
    private Button submitButton;

    public InputMealFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputMealFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputMealFragment newInstance(String param1, String param2) {
        InputMealFragment fragment = new InputMealFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_meal, container, false);

        this.nameInput = (EditText) view.findViewById(R.id.mealNameInput);
        this.caloriesInput = (EditText) view.findViewById(R.id.mealCaloriesInput);
        this.submitButton = (Button) view.findViewById(R.id.mealSubmit);

        this.submitButton.setOnClickListener(v -> {
            InputMealViewModel.sendMeal(this.nameInput.getText().toString(), this.caloriesInput.getText().toString());
        });

        //Update Personal Info
        int userHeight = InputMealViewModel.getUserHeight();
        int userWeight = InputMealViewModel.getUserWeight();
        String userGender = InputMealViewModel.getUserGender();

        // Set the text of the TextViews to display the data
        TextView textViewDataHeight = view.findViewById(R.id.textViewHeight);
        if (userHeight != -1) {
            textViewDataHeight.setText("Height: " + userHeight + " inches");
        } else {
            textViewDataHeight.setText("Height: Please update your height!");
        }

        TextView textViewDataWeight = view.findViewById(R.id.textViewWeight);
        if (userWeight != -1) {
            textViewDataWeight.setText("Weight: " + userWeight + " pounds");
        } else {
            textViewDataWeight.setText("Weight: Please update your weight!");
        }

        TextView textViewDataGender = view.findViewById(R.id.textViewGender);
        if (userGender != "") {
            textViewDataGender.setText("Gender: " + userGender);
        } else {
            textViewDataGender.setText("Gender: Please update your gender!");
        }

        return view;
    }
}