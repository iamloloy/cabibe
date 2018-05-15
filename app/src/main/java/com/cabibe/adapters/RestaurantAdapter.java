package com.cabibe.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cabibe.R;
import com.cabibe.activities.MainActivity;
import com.cabibe.activities.RestaurantDetailsActivity;
import com.cabibe.models.Restaurant;
import com.cabibe.utils.NetworkUtils;

/**
 * Created by ekxia on 5/2/2018.
 */

public class RestaurantAdapter
        extends ArrayAdapter<Restaurant, RestaurantAdapter.ViewHolder> {

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.adapter_restaurant, viewGroup, false);;
        context = viewGroup.getContext();
        return new ViewHolder(v);
    }

    @Override
    public long getItemId(int position) {
        return get(position).id;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Restaurant restaurant = get(position);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtils.isConnected(context))
                    context.startActivity(RestaurantDetailsActivity.newIntent(context, restaurant));
                else
                    showNetworkError();
            }
        });

        holder.name.setText(restaurant.name);
        holder.address.setText(restaurant.address);
        holder.schedule.setText(restaurant.schedule);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View container;
        private TextView name;
        private TextView address;
        private TextView schedule;

        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            schedule = itemView.findViewById(R.id.schedule);
        }
    }

    private void showNetworkError()
    {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cabibe")
                .setMessage("Please connect to the internet.")
                .setNegativeButton("Ok", null)
                .show();
    }

}
