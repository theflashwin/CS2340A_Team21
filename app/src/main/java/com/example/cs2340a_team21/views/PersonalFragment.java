package com.example.cs2340a_team21.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.model.Pantry;
import com.example.cs2340a_team21.viewmodels.PersonalInfoViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // adding text fields

    private EditText height;
    private EditText weight;
    private RadioGroup gender;
    private Button submitButton;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
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

        Pantry.getInstance().initializeStaticIngredients();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        this.height = (EditText) view.findViewById(R.id.editTextHeight);
        this.weight = (EditText) view.findViewById(R.id.editTextWeight);
        this.gender = (RadioGroup) view.findViewById(R.id.radioGroupGender);
        this.submitButton = (Button) view.findViewById(R.id.submitButton);

        this.submitButton.setOnClickListener(v -> {

            RadioButton selectedGender = view.findViewById(gender.getCheckedRadioButtonId());


            PersonalInfoViewModel.setUserInfo(this.height.getText().toString(),
                    this.weight.getText().toString(), selectedGender.getText().toString());
            Log.d("Submit Button: ", String.valueOf(gender.getCheckedRadioButtonId()));

        });

        return view;
    }
}