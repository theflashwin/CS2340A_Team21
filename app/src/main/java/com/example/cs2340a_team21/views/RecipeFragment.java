package com.example.cs2340a_team21.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;

import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText nameInput;
    private EditText ingredientsInput;
    private Button submitButton;

    private Button sortButtonNum;

    private Button sortButtonQuantity;

    private RecyclerView recyclerView;

    private RecipeAdapter adapter;

    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
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

        RecipeViewModel.handleOnLoad();

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        this.recyclerView = view.findViewById(R.id.recipes_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecipeAdapter(RecipeViewModel.recipes, getActivity());
        recyclerView.setAdapter(adapter);

        this.nameInput = view.findViewById(R.id.recipeNameInput);
        this.ingredientsInput = view.findViewById(R.id.recipeIngredients);
        this.submitButton = view.findViewById(R.id.recipeSubmit);

        this.submitButton.setOnClickListener(v -> {
            RecipeViewModel.sendRecipe(nameInput.getText().toString(), ingredientsInput.getText().toString());
            adapter.refresh();
        });

        this.sortButtonNum = view.findViewById(R.id.sortRecipesNum);
        this.sortButtonQuantity = view.findViewById(R.id.sortRecipesQuantity);


        this.sortButtonNum.setOnClickListener(v -> {
            adapter.sortRecipesNum();
            adapter.refresh();
        });

        this.sortButtonQuantity.setOnClickListener(v -> {
            adapter.sortRecipesQuantity();
            adapter.refresh();
        });

        return view;
    }

}