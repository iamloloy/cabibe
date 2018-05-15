package com.cabibe.models;

/**
 * Created by ekxia on 5/2/2018.
 */

public class Food {

    public int id;
    public int categoryId;
    public int restaurantId;
    public String name;
    public String price;

    public Food(int id, int categoryId, int restaurantId, String name, String price) {
        this.id = id;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }
}
