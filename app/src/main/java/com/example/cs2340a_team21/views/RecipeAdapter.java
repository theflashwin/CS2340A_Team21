package com.example.cs2340a_team21.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;

import java.util.List;

import org.w3c.dom.Text;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    List<Recipe> recipes;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final Button open;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.recipeName);
            open = view.findViewById(R.id.openButton);
        }

        public TextView getName() {
            return name;
        }

        public TextView getOpen() {
            return open;
        }

    }

    public RecipeAdapter(List<Recipe> data) {

        this.recipes = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Recipe r = recipes.get(position);

        Log.w("Item Name", r.name + "");

        viewHolder.getName().setText(r.name);

        String canOpen = RecipeViewModel.getCanClick(r);

        viewHolder.getOpen().setText(canOpen);
    }

    @Override
    public int getItemCount() {
        Log.w("Item Count", recipes.size() + "");
        return recipes.size();
    }


}
