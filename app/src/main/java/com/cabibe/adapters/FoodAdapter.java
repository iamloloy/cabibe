package com.cabibe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cabibe.R;
import com.cabibe.models.Food;

/**
 * Created by ekxia on 5/2/2018.
 */

public class FoodAdapter
        extends ArrayAdapter<Food, FoodAdapter.ViewHolder> {

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.adapter_food, viewGroup, false);;
        context = viewGroup.getContext();
        return new ViewHolder(v);
    }

    @Override
    public long getItemId(int position) {
        return get(position).id;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Food food = get(position);

        holder.foodName.setText(food.name);
        holder.foodPrice.setText("P"+ food.price);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView foodName;
        private TextView foodPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
        }
    }
}
