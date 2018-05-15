package com.cabibe.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cabibe.R;
import com.cabibe.adapters.RestaurantAdapter;
import com.cabibe.data.RestaurantFeeder;
import com.cabibe.databinding.ActivityMainBinding;
import com.cabibe.models.Restaurant;
import com.cabibe.utils.Constants;
import com.cabibe.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekxia on 5/2/2018.
 */

public class MainActivity
        extends AppCompatActivity implements TextWatcher {

    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        binding.fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtils.isConnected(MainActivity.this))
                    startActivity(MapViewActivity.newIntent(MainActivity.this));
                else
                    showNetworkError();

            }
        });

        binding.recyclerRestau.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter();
        binding.recyclerRestau.setAdapter(restaurantAdapter);

        binding.autoSearch.addTextChangedListener(this);

        showRestaurantList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.show_information) {
            showAboutUsActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutUsActivity() {
        Intent intent = AboutUsActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.app_name))
                .setMessage(Constants.MESSAGE_CLOSE)
                .setPositiveButton(Constants.YES, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(Constants.NO, null)
                .show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
            showRestaurantList();
        } else {
            List<Restaurant> restaurants = new ArrayList<>();
            String searchText = s.toString().toLowerCase();
            for (Restaurant restaurant : RestaurantFeeder.getRestaurants()) {
                if (restaurant.name.toLowerCase().contains(searchText))
                    restaurants.add(restaurant);
            }
            showRestaurantList(restaurants);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void showNetworkError()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.app_name))
                .setMessage(Constants.MESSAGE_INTERNET)
                .setNegativeButton(Constants.OK, null)
                .show();
    }

    private void showRestaurantList(List<Restaurant> restaurants)
    {
        restaurantAdapter.clear();
        restaurantAdapter.addAll(restaurants);
        restaurantAdapter.notifyDataSetChanged();
    }

    private void showRestaurantList()
    {
        restaurantAdapter.clear();
        restaurantAdapter.addAll(RestaurantFeeder.getRestaurants());
        restaurantAdapter.notifyDataSetChanged();
    }

    public static Intent newIntent(Context context)
    {
        Intent i = new Intent(context, MainActivity.class);
        return i;
    }
}
