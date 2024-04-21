package com.example.cs2340a_team21.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.objects.ShoppingListItem;
import com.example.cs2340a_team21.viewmodels.ShoppingListViewModel;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    ArrayList<ShoppingListItem> items;

    public ShoppingListAdapter(ArrayList<ShoppingListItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);
        return new ShoppingListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShoppingListItem item = items.get(position);

        holder.getName().setText(item.getName());
        holder.getCalories().setText(String.valueOf(item.getCalories()));
        holder.getQuantity().setText(String.valueOf(item.getQuantity()));

        holder.getAddToCart().setOnClickListener(l -> {

            ShoppingListViewModel.toggleCartItem(item, holder.getAddToCart());

        });

        holder.getDecreaseButton().setOnClickListener(v -> {

            ShoppingListViewModel.decreaseQuantity(item);

        });

        holder.getIncreaseButton().setOnClickListener(v -> {

            ShoppingListViewModel.increseQuantity(item);

        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView quantity;
        private TextView calories;
        private Button addToCart;
        private Button increaseButton;
        private Button decreaseButton;

        public ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.itemName);
            this.quantity = view.findViewById(R.id.itemQuantity);
            this.calories = view.findViewById(R.id.itemCalories);
            this.addToCart = view.findViewById(R.id.addToCartButton);
            this.increaseButton = view.findViewById(R.id.shoppingListIncrease);
            this.decreaseButton = view.findViewById(R.id.shoppingListDecrease);
        }

        public TextView getName() {
            return this.name;
        }

        public TextView getCalories() {
            return this.calories;
        }

        public TextView getQuantity() {
            return this.quantity;
        }

        public Button getAddToCart() {
            return this.addToCart;
        }

        public Button getIncreaseButton() {
            return this.increaseButton;
        }

        public Button getDecreaseButton() {
            return this.decreaseButton;
        }

    }



}
