package com.foodDelivery.model;

public class Menu {

    private int menuId;
    private int restaurantId;
    private String restaurantName;   // ✅ NEW
    private String name;
    private String description;
    private double price;
    private double rating;
    private String imageUrl;
    private String type;
    private boolean available;

    // ✅ Constructor used by DAO
    public Menu(int menuId,
                int restaurantId,
                String restaurantName,
                String name,
                String description,
                double price,
                double rating,
                String imageUrl,
                String type,
                boolean available) {

        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.type = type;
        this.available = available;
    }

    // ===== GETTERS =====

    public int getMenuId() {
        return menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }
}
