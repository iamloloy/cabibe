package com.cabibe.data;

import com.cabibe.models.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ekxia on 5/2/2018.
 */

public class RestaurantFeeder {

    public static List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1, "The Hideout Tea and Coffee House", "69 Caimito Road Corner Lapu-Lapu Street, South Caloocan, Caloocan City", 14.6586,  120.9826, "Mon - Sun 01:00 pm - 10:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(2, "Silver Crown Panciteria Inc ", "240 Samson Road, South Caloocan, Caloocan City", 14.6574, 120.9810, "Mon - Sun 10:00 am - 09:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(3, "Samgyeopsal House by 25th Turning Point", "1061 Gen. San Miguel Street, Sangandaan, South Caloocan, Caloocan City", 14.3717, 120.5802, "Mon - Sun 01:00 pm - 10:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(4, "Nibbles and Blends Café", "Second Floor, Vera Building, North Caloocan, Caloocan City", 14.4425, 121.0132, "Mon - Sun 01:00 pm - 11:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(5, "Playground Hobby Café", "Second Floor, L.O.L. Realty Building, EDSA Corner Asuncion Street, Morning Breeze, Monumento, South Caloocan, Caloocan City 1401", 14.6576, 120.9877, "Mon - Sun 10:00 am - 11:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(6, "Café Poblacion", "Ground Floor, Vizione Plaza Building, 505 A. Mabini Avenue, South Caloocan, Caloocan City", 14.3914, 120.5827, "Mon - Sun 10:00 am - 10:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(7, "Chef Patrick's Kitchen", "263 UE Tech Street, University Hills Subdivision, South Caloocan, Caloocan City 1400", 14.3932, 120.5830, "Mon - Sun 08:00 am - 08:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(8, "Orient Pearl", "181 A. Del Mundo Street Corner 10th Avenue, South Caloocan, Caloocan City", 14.3901, 120.5852, "Mon - Sun 09:00 am - 10:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(9, "Max's Restaurant", "EDSA Extension Near World Citi College, South Caloocan, Caloocan City", 14.3916, 120.5937, "Mon - Sun 08:00 am - 10:00 pm");
        restaurants.add(restaurant);
        restaurant = new Restaurant(10, "Angel's Town Café", "164-B M.H. Del Pilar Street, South Caloocan, Caloocan City", 14.3902, 120.5859, "Mon - Sun 08:30 am - 06:30 pm");
        restaurants.add(restaurant);

        Collections.sort(restaurants, new Comparator<Restaurant>() {
            public int compare(Restaurant r1, Restaurant r2) {
                return r1.name.compareTo(r2.name);
            }
        });

        return restaurants;
    }
}
