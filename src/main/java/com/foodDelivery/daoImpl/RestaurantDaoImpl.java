package com.foodDelivery.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.RestaurantDAO;
import com.foodDelivery.model.Restaurant;
import com.foodDelivery.util.DBconnection;

public class RestaurantDaoImpl implements RestaurantDAO {

    private static final String INSERT_RESTAURANT =
        "INSERT INTO restaurant " +
        "(name, address, cuisine_type, rating, eta, image_url, is_available) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_RESTAURANTS =
        "SELECT restaurant_id, name, address, cuisine_type, rating, eta, image_url, is_available " +
        "FROM restaurant WHERE is_available = 1 " +
        "ORDER BY rating DESC, eta ASC";

    @Override
    public int addRestaurant(Restaurant restaurant) {

        int rows = 0;

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_RESTAURANT)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getCuisineType());
            ps.setDouble(4, restaurant.getRating());
            ps.setInt(5, restaurant.getEta());
            ps.setString(6, restaurant.getImageUrl());
            ps.setBoolean(7, restaurant.isAvailable());

            rows = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurants = new ArrayList<>();
        
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_RESTAURANTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Restaurant restaurant = new Restaurant(
                    rs.getInt("restaurant_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("cuisine_type"),
                    rs.getDouble("rating"),
                    rs.getInt("eta"),
                    rs.getString("image_url"),
                    rs.getBoolean("is_available")
                );

                restaurants.add(restaurant);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurants;
    }
    private static final String GET_NAME =
            "SELECT name FROM restaurant WHERE restaurant_id = ?";

        @Override
        public String getRestaurantNameById(int restaurantId) {
            try (Connection con = DBconnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(GET_NAME)) {

                ps.setInt(1, restaurantId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("name");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Unknown Restaurant";
        }
}
