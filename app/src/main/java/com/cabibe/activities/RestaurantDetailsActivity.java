package com.cabibe.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import com.cabibe.utils.LocationUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.cabibe.utils.Constants.CURRENT_POSITION;
import static com.cabibe.utils.Constants.DISMISS;
import static com.cabibe.utils.Constants.ENABLE;
import static com.cabibe.utils.Constants.MESSAGE_HELPS;
import static com.cabibe.utils.Constants.MESSAGE_LOCATION;
import static com.cabibe.utils.Constants.MESSAGE_PERMISSION_DENIED;
import static com.cabibe.utils.Constants.MESSAGE_REQUIRES;

/**
 * Created by ekxia on 5/2/2018.
 */

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final String KEY_RESTAURANT = "com.cabibe.activities.RestaurantDetailsActivity.RESTAURANT";
    private FoodAdapter foodAdapter;
    private Restaurant restaurant;

    private GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private android.location.Location mLastLocation;
    private Marker mCurrLocationMarker;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int LOCATION_REQUEST_CODE = 1;
    private Dialog enableLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRestaurantDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_details);
        restaurant = (Restaurant) getIntent().getSerializableExtra(KEY_RESTAURANT);

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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            LocationUtils.checkLocationPermission(this);

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(MESSAGE_PERMISSION_DENIED);
            builder.setMessage(MESSAGE_LOCATION);
            builder.setPositiveButton(ENABLE, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(DISMISS, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    public static Intent newIntent(Context context, Restaurant restaurant)
    {
        Intent i = new Intent(context, RestaurantDetailsActivity.class);
        i.putExtra(KEY_RESTAURANT, restaurant);
        return i;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {

            LatLng restau = new LatLng(restaurant.latitude, restaurant.longitude);
            map.addMarker(new MarkerOptions().position(restau)
                    .title(restaurant.name));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(restau, 12), 3000, null);

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient();
                    map.setMyLocationEnabled(true);
                }
            } else {
                buildGoogleApiClient();
                map.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null)
            mCurrLocationMarker.remove();

        LatLng current = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(current);
        markerOptions.title(CURRENT_POSITION);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = map.addMarker(markerOptions);

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        map.setMyLocationEnabled(true);
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(MESSAGE_REQUIRES);
                    builder.setMessage(MESSAGE_HELPS);
                    builder.setPositiveButton(ENABLE, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, LOCATION_REQUEST_CODE);
                                }
                            }
                    );
                    builder.setNegativeButton(DISMISS, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    enableLocation = builder.create();
                    enableLocation.setCanceledOnTouchOutside(false);
                    enableLocation.show();
                }
                return;
            }
        }
    }

}
