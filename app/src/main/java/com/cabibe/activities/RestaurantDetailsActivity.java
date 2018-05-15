package com.cabibe.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cabibe.R;
import com.cabibe.adapters.FoodAdapter;
import com.cabibe.data.FoodCategoryFeeder;
import com.cabibe.data.FoodFeeder;
import com.cabibe.databinding.ActivityRestaurantDetailsBinding;
import com.cabibe.models.FoodCategory;
import com.cabibe.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekxia on 5/2/2018.
 */

public class RestaurantDetailsActivity extends AppCompatActivity{

    public static final String KEY_RESTAURANT = "com.cabibe.activities.RestaurantDetailsActivity.RESTAURANT";
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRestaurantDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_details);
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra(KEY_RESTAURANT);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + restaurant.name + "</font>"));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        List<View> views = new ArrayList<>();

        for (FoodCategory category : FoodCategoryFeeder.getFoodCategories(restaurant.id)) {
            View v = vi.inflate(R.layout.layout_menu, null);
            TextView categoryName = v.findViewById(R.id.category_name);
            categoryName.setText(category.name);
            RecyclerView menuRecycler = v.findViewById(R.id.recycler_food);
            foodAdapter = new FoodAdapter();
            menuRecycler.setLayoutManager(new LinearLayoutManager(this));
            menuRecycler.setAdapter(foodAdapter);
            foodAdapter.clear();
            foodAdapter.addAll(FoodFeeder.getMenu(restaurant.id, category.id));
            foodAdapter.notifyDataSetChanged();
            views.add(v);
        }

        for (int i = 0; i < views.size(); i++) {
            binding.holderMenu.addView(views.get(i));
        }
    }

    public static Intent newIntent(Context context, Restaurant restaurant)
    {
        Intent i = new Intent(context, RestaurantDetailsActivity.class);
        i.putExtra(KEY_RESTAURANT, restaurant);
        return i;
    }
}
