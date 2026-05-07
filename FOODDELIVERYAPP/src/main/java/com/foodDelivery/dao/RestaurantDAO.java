package com.foodDelivery.dao;

import java.util.List;

import com.foodDelivery.model.Restaurant;

public interface RestaurantDAO {
    int addRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    String getRestaurantNameById(int restaurantId);
   
}
