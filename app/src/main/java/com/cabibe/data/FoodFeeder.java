package com.cabibe.data;

import com.cabibe.models.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekxia on 5/2/2018.
 */

public class FoodFeeder {

    private static List<Food> getMenu() {
        List<Food> foodList = new ArrayList<>();
        Food food = new Food(1, 1, 1, "Burger", "90");
        foodList.add(food);
        food = new Food(2, 1, 1, "Chicken", "80");
        foodList.add(food);
        food = new Food(3, 1, 1, "Beef Tapa", "80");
        foodList.add(food);
        food = new Food(4, 1, 1, "Porkchop", "80");
        foodList.add(food);
        food = new Food(5, 1, 1, "Spam & Bacon", "80");
        foodList.add(food);
        food = new Food(6, 1, 1, "Tocino", "80");
        foodList.add(food);
        food = new Food(7, 1, 1, "Fish Fillet", "80");
        foodList.add(food);
        food = new Food(8, 2, 1, "Clubhouse", "90");
        foodList.add(food);
        food = new Food(9, 2, 1, "Bacon & Egg", "60");
        foodList.add(food);
        food = new Food(10, 2, 1, "Bacon & Cheese", "55");
        foodList.add(food);
        food = new Food(11, 2, 1, "Bacon", "50");
        foodList.add(food);
        food = new Food(12, 2, 1, "Ham & Egg", "55");
        foodList.add(food);
        food = new Food(13, 2, 1, "Ham & Cheese", "50");
        foodList.add(food);
        food = new Food(14, 2, 1, "Ham", "40");
        foodList.add(food);
        food = new Food(15, 2, 1, "Tuna", "30");
        foodList.add(food);
        food = new Food(16, 3, 1, "Chicken & Chips", "120");
        foodList.add(food);
        food = new Food(17, 3, 1, "Fish & Chips", "110");
        foodList.add(food);
        food = new Food(18, 3, 1, "Crispy Chicken Wings", "110");
        foodList.add(food);
        food = new Food(19, 3, 1, "Chicken Lollipop", "110");
        foodList.add(food);
        food = new Food(20, 3, 1, "Nachos", "90");
        foodList.add(food);
        food = new Food(21, 3, 1, "Mozarella Poppers", "85");
        foodList.add(food);
        food = new Food(22, 3, 1, "Bacon Cheese Fries", "85");
        foodList.add(food);
        food = new Food(23, 3, 1, "Mojoes", "80");
        foodList.add(food);
        food = new Food(24, 3, 1, "Pizza Roll", "80");
        foodList.add(food);
        food = new Food(25, 4, 1, "Lasagna", "110");
        foodList.add(food);
        food = new Food(26, 4, 1, "Baked Mac", "100");
        foodList.add(food);
        food = new Food(27, 4, 1, "Carbonara", "100");
        foodList.add(food);
        food = new Food(27, 5, 1, "Mojoes, French Fries, Pizza Roll, Nachos", "290");
        foodList.add(food);
        food = new Food(28, 5, 1, "Bacon, Cheese Fries", "320");
        foodList.add(food);
        food = new Food(29, 6, 1, "Choco Hazelnut Oreo", "125");
        foodList.add(food);
        food = new Food(30, 6, 1, "Black Forest", "125");
        foodList.add(food);
        food = new Food(31, 6, 1, "Matcha Kitkat", "125");
        foodList.add(food);
        food = new Food(32, 6, 1, "Choco Kitkat", "120");
        foodList.add(food);
        food = new Food(33, 6, 1, "Strawberry Kitkat", "120");
        foodList.add(food);
        food = new Food(34, 6, 1, "Hazelnut Oreo", "120");
        foodList.add(food);
        food = new Food(35, 7, 1, "Passion Yogurt", "75");
        foodList.add(food);
        food = new Food(36, 7, 1, "Strawberry Passion", "70");
        foodList.add(food);
        food = new Food(37, 7, 1, "Lychee", "70");
        foodList.add(food);
        food = new Food(38, 7, 1, "Citrus Green Tea", "40");
        foodList.add(food);



        return foodList;
    }

    public static List<Food> getMenu(int restaurantId, int categoryId) {
        List<Food> foodList = new ArrayList<>();
        for (Food food : getMenu()) {
            if (food.restaurantId == restaurantId && food.categoryId == categoryId)
                foodList.add(food);
        }
        return foodList;
    }
}
