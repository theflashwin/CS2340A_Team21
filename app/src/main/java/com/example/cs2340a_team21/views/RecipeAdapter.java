package com.example.cs2340a_team21.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.Strategies.NumIngredientsSortingStrategy;
import com.example.cs2340a_team21.Strategies.QuantityIngredientsSortingStrategy;
import com.example.cs2340a_team21.Strategies.SortingStrategy;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.viewmodels.IngredientsViewModel;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.w3c.dom.Text;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipes;

    private Context context;

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

        public Button getOpen() {
            return open;
        }

    }

    public RecipeAdapter(List<Recipe> data, Context c) {

        this.recipes = data;
        this.context = c;

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

        if (canOpen.equals("Open")) {
            viewHolder.getOpen().setText("Click to open");
        } else {
            viewHolder.getOpen().setText("Can't open");
        }
        viewHolder.getOpen().setOnClickListener(v -> {
            if (canOpen.equals("Open")) {
                Intent intent = new Intent(this.context, RecipeIngredients.class);
                intent.putExtra("Recipe", getRecipeText(r));
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Ingredients not Available", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this.context, RecipeIngredients.class);
                intent.putExtra("Recipe", getRecipeText(r));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.w("Item Count", recipes.size() + "");
        return recipes.size();
    }

    public String getRecipeText(Recipe re) {
        String output = re.name + " - ";
        List<Ingredient> iList = re.ingredients;
        for (int idx = 0; idx < iList.size() - 1; idx++) {
            output += iList.get(idx).name + " (qty: " + iList.get(idx).quantity + "), ";
        }
        output += iList.get(iList.size() - 1).name + " (qty: " + iList.get(iList.size() - 1).quantity + ").";
        return output;
    }

    public void sortRecipesNum() {
        SortingStrategy strategy = new NumIngredientsSortingStrategy();
        RecipeViewModel.sort(strategy);
    }

    public void sortRecipesQuantity() {
        SortingStrategy strategy = new QuantityIngredientsSortingStrategy();
        RecipeViewModel.sort(strategy);
    }

    public void refresh() {
        this.recipes = RecipeViewModel.recipes;
    }


}
