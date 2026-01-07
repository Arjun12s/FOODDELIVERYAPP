package com.foodDelivery.model;

import java.io.Serializable;
import java.util.*;

public class RestaurantCart implements Serializable {

    private int restaurantId;
    private String restaurantName;
    private Map<Integer, CartItem> items = new HashMap<>();

    public RestaurantCart(int restaurantId, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public int getRestaurantId() { return restaurantId; }
    public String getRestaurantName() { return restaurantName; }

    public void addItem(int menuId, String name, double price, String type) {
        if (items.containsKey(menuId)) {
            items.get(menuId).incrementQuantity();
        } else {
            items.put(menuId, new CartItem(menuId, name, price, type, 1));
        }
    }

    public void increaseItem(int menuId) {
        CartItem item = items.get(menuId);
        if (item != null) item.incrementQuantity();
    }

    public void decreaseItem(int menuId) {
        CartItem item = items.get(menuId);
        if (item != null) {
            if (item.getQuantity() > 1) item.decrementQuantity();
            else items.remove(menuId);
        }
    }

    public void removeItem(int menuId) {
        items.remove(menuId);
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public Collection<CartItem> getItems() { return items.values(); }

    public double getSubTotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
