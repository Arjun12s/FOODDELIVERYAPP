package com.foodDelivery.model;

import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable {

    private Map<Integer, RestaurantCart> restaurantCarts = new HashMap<>();

    public synchronized void addItem(
            int restaurantId,
            String restaurantName,
            int menuId,
            String itemName,
            double price,
            String type
    ) {
        restaurantCarts
            .computeIfAbsent(restaurantId,
                id -> new RestaurantCart(restaurantId, restaurantName))
            .addItem(menuId, itemName, price, type);
    }

    public synchronized void increaseItem(int restaurantId, int menuId) {
        RestaurantCart rc = restaurantCarts.get(restaurantId);
        if (rc != null) rc.increaseItem(menuId);
    }

    public synchronized void decreaseItem(int restaurantId, int menuId) {
        RestaurantCart rc = restaurantCarts.get(restaurantId);
        if (rc != null) {
            rc.decreaseItem(menuId);
            if (rc.isEmpty()) restaurantCarts.remove(restaurantId);
        }
    }

    public synchronized void removeItem(int restaurantId, int menuId) {
        RestaurantCart rc = restaurantCarts.get(restaurantId);
        if (rc != null) {
            rc.removeItem(menuId);
            if (rc.isEmpty()) restaurantCarts.remove(restaurantId);
        }
    }

    public boolean isEmpty() { return restaurantCarts.isEmpty(); }

    public Collection<RestaurantCart> getRestaurantCarts() {
        return restaurantCarts.values();
    }

    public double getGrandTotal() {
        return restaurantCarts.values()
                .stream()
                .mapToDouble(RestaurantCart::getSubTotal)
                .sum();
    }
}
