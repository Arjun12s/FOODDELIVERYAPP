package com.foodDelivery.model;

import java.io.Serializable;

public class CartItem implements Serializable {

    private int menuId;
    private String itemName;
    private double price;
    private String type;
    private int quantity;

    public CartItem(int menuId, String itemName, double price, String type, int quantity) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public int getMenuId() { return menuId; }
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }

    public void incrementQuantity() { quantity++; }
    public void decrementQuantity() { quantity--; }

    public double getTotalPrice() {
        return price * quantity;
    }
}
