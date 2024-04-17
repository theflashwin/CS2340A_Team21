package com.example.cs2340a_team21.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.ShoppingListItem;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;
import com.example.cs2340a_team21.viewmodels.ShoppingListViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;

    private EditText name;

    private EditText calories;

    private EditText quantity;

    private Button submit;

    private Button checkout;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingListFragment.
     */
    public static ShoppingListFragment newInstance(String param1, String param2) {
        ShoppingListFragment fragment = new ShoppingListFragment();
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

        ArrayList<ShoppingListItem> list = ShoppingListViewModel.getItems();

        ShoppingListViewModel.onLoad();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        this.name = view.findViewById(R.id.shoppingListName);
        this.calories = view.findViewById(R.id.shoppingListCalories);
        this.quantity = view.findViewById(R.id.shoppingListQuantity);

        this.submit = view.findViewById(R.id.shoppingListSubmit);
        this.checkout = view.findViewById(R.id.shoppingListCheckout);

        this.submit.setOnClickListener(v -> {

            ShoppingListViewModel.addToShoppingList(name.getText().toString(),
                    quantity.getText().toString(), calories.getText().toString());

        });

        this.checkout.setOnClickListener(v -> {

            ShoppingListViewModel.checkout();

        });

        this.recyclerView = view.findViewById(R.id.shopping_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ShoppingListAdapter(ShoppingListViewModel.getItems()));

        return view;

    }
}