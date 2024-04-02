package com.example.cs2340a_team21.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> ingredients;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewQuantity;

        private final Button decrease;
        private final Button increase;

        public ViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.ingredientName);
            textViewQuantity = view.findViewById(R.id.ingredientQuantity);
            decrease = view.findViewById(R.id.decreaseButton);
            increase = view.findViewById(R.id.increaseButton);
        }

        public TextView getTextViewName() {
            return textViewName; }
        public TextView getTextViewQuantity() {
            return textViewQuantity; }

        public Button getDecrease() {
            return decrease;
        }

        public Button getIncrease() {
            return increase;
        }
    }

    public IngredientsAdapter(List<Ingredient> dataSet) {
        ingredients = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ingredients_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Ingredient i = ingredients.get(position);
        viewHolder.getTextViewName().setText(i.getName());
        viewHolder.getTextViewQuantity().setText(String.valueOf(i.getQuantity()));

        viewHolder.getDecrease().setOnClickListener(v -> {
            IngredientsViewModel.decreaseIngredient(i.getName());
            viewHolder.getTextViewQuantity().setText(String.valueOf(i.getQuantity()));
        });

        viewHolder.getIncrease().setOnClickListener(v -> {
            IngredientsViewModel.increaseIngredient(i.getName());
            viewHolder.getTextViewQuantity().setText(String.valueOf(i.getQuantity()));
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void refreshData(List<Ingredient> newList){
        this.ingredients.clear();
        this.ingredients.addAll(newList);
        notifyDataSetChanged();
    }
}

