package com.cabibe.models;

import java.util.List;

/**
 * Created by ekxia on 5/2/2018.
 */

public class Menu {

    public String categoryName;
    public List<Food> foodList;

    public Menu(String categoryName, List<Food> foodList) {
        this.categoryName = categoryName;
        this.foodList = foodList;
    }
}
