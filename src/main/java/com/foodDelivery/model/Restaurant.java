package com.foodDelivery.model;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String address;
    private String cuisineType;
    private double rating;
    private int eta;                 // in minutes
    private String imageUrl;
    private boolean available;

    // ✅ Full constructor (recommended)
    public Restaurant(int restaurantId,
                      String name,
                      String address,
                      String cuisineType,
                      double rating,
                      int eta,
                      String imageUrl,
                      boolean available) {

        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.rating = rating;
        this.eta = eta;
        this.imageUrl = imageUrl;
        this.available = available;
    }

    // ===== GETTERS & SETTERS =====

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public double getRating() {
        return rating;
    }

    public int getEta() {
        return eta;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
