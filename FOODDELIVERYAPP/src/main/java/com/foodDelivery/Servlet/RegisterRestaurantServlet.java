package com.foodDelivery.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.foodDelivery.dao.RestaurantDAO;
import com.foodDelivery.daoImpl.RestaurantDaoImpl;
import com.foodDelivery.model.Restaurant;

@WebServlet("/register-restaurant")
public class RegisterRestaurantServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String cuisineType = request.getParameter("cuisine");
        int eta = Integer.parseInt(request.getParameter("eta"));
        String imageUrl = request.getParameter("imageUrl");

        // Default values on registration
        double rating = 0.0;          // New restaurant
        boolean isAvailable = true;

        Restaurant restaurant = new Restaurant(
                0,                  // restaurantId (ignored by DB AUTO_INCREMENT)
                name,
                address,
                cuisineType,
                rating,
                eta,
                imageUrl,
                isAvailable
        );

        RestaurantDAO dao = new RestaurantDaoImpl();
        int result = dao.addRestaurant(restaurant);

        response.setContentType("text/html");

        if (result > 0) {
            response.getWriter().println("<h3>Restaurant registered successfully</h3>");
        } else {
            response.getWriter().println("<h3>Restaurant registration failed</h3>");
        }
    }
}
