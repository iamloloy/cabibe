package com.cabibe.models;

/**
 * Created by ekxia on 5/2/2018.
 */

public class FoodCategory {

    public int id;
    public int restaurantId;
    public String name;

    public FoodCategory(int id, int restaurantId, String name) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
    }
}
