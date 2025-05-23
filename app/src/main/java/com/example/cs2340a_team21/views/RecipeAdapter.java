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
import com.example.cs2340a_team21.model.Pantry;
import com.example.cs2340a_team21.objects.Ingredient;
import com.example.cs2340a_team21.objects.Recipe;
import com.example.cs2340a_team21.viewmodels.RecipeViewModel;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipes;

    private Context context;

    public RecipeAdapter(List<Recipe> data, Context c) {

        this.recipes = data;
        this.context = c;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recipe_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Recipe r = recipes.get(position);

        viewHolder.getName().setText(r.getName());

        List<Ingredient> list = Pantry.getInstance().getStaticIngredients();

        String canOpen = RecipeViewModel.getCanClick(r,
                list);

        Log.w("Item Name", r.getName() + "  " + canOpen);

        if (canOpen.equals("Open")) {
            viewHolder.getOpen().setText("Click to open");
            viewHolder.getBuy().setText("No shopping needed!");
        } else {
            viewHolder.getOpen().setText("Can't open");
            viewHolder.getBuy().setText("Click to buy ingredients");
        }

        if (canOpen.equals("Open")) {
            viewHolder.getOpen().setOnClickListener(v -> {
                Intent intent = new Intent(this.context, RecipeIngredients.class);
                intent.putExtra("Recipe", getRecipeText(r));
                intent.putExtra("RecipePosition", position);

                Log.w("Position", String.valueOf(position));

                context.startActivity(intent);
            });

            viewHolder.getBuy().setOnClickListener(v -> {
                Toast.makeText(context, "Ingredients Already Available", Toast.LENGTH_LONG).show();
            });

        } else {
            viewHolder.getOpen().setOnClickListener(v -> {
                Toast.makeText(context, "Ingredients not Available", Toast.LENGTH_LONG).show();
            });

            // Add needed items to shopping list if not available in pantry
            viewHolder.getBuy().setOnClickListener(v -> {
                RecipeViewModel.shopIngredients(r, Pantry.getInstance().getStaticIngredients());
                Toast.makeText(context, "Ingredients succesfully added to shopping list!",
                        Toast.LENGTH_LONG).show();
            });
        }

        //        viewHolder.getOpen().setOnClickListener(v -> {
        //            if (canOpen.equals("Open")) {
        //                Intent intent = new Intent(this.context, RecipeIngredients.class);
        //                intent.putExtra("Recipe", getRecipeText(r));
        //                context.startActivity(intent);
        //            } else {
        //                Toast.makeText(context, "Ingredients not Available", Toast.LENGTH_LONG
        //                ).show();
        //            }
        //        });

    }

    @Override
    public int getItemCount() {
        Log.w("Item Count", recipes.size() + "");
        return recipes.size();
    }

    public String getRecipeText(Recipe re) {
        String output = re.getName() + " - ";
        List<Ingredient> iList = re.getIngredients();
        for (int idx = 0; idx < iList.size() - 1; idx++) {
            output += iList.get(idx).getName() + " (qty: " + iList.get(idx).getQuantity() + "), ";
        }
        output += iList.get(iList.size() - 1).getName() + " (qty: " + iList.get(iList.size()
                - 1).getQuantity() + ").";
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
        this.recipes = RecipeViewModel.getRecipes();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final Button open;

        private final Button buy;


        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.recipeName);
            open = view.findViewById(R.id.openButton);
            buy = view.findViewById(R.id.addToShopping);
        }

        public TextView getName() {
            return name;
        }

        public Button getOpen() {
            return open;
        }

        public Button getBuy() {
            return buy;
        }
    }
}
