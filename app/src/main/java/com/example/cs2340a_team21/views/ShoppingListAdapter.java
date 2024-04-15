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
        holder.getPrice().setText(String.valueOf(item.getPrice()));
        holder.getQuantity().setText(String.valueOf(item.getQuantity()));

        holder.getAddToCart().setOnClickListener(l -> {

            // do this

        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView quantity;
        private TextView price;
        private Button addToCart;

        public ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.itemName);
            this.quantity = view.findViewById(R.id.itemQuantity);
            this.price = view.findViewById(R.id.itemPrice);
            this.addToCart = view.findViewById(R.id.addToCartButton);
        }

        public TextView getName() {
            return this.name;
        }

        public TextView getPrice() {
            return this.price;
        }

        public TextView getQuantity() {
            return this.quantity;
        }

        public Button getAddToCart() {
            return this.addToCart;
        }

    }





}
