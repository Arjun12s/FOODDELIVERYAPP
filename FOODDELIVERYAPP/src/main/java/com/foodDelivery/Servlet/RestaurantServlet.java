package com.foodDelivery.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.foodDelivery.dao.RestaurantDAO;
import com.foodDelivery.daoImpl.RestaurantDaoImpl;
import com.foodDelivery.model.Restaurant;

@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RestaurantDAO dao = new RestaurantDaoImpl();
        List<Restaurant> restaurantList = dao.getAllRestaurants();
//        System.out.println("==== RESTAURANTS FROM DB ====");
//
//        for (Restaurant r : restaurantList) {
//            System.out.println(
//                "ID: " + r.getRestaurantId() +
//                " | Name: " + r.getName() +
//                " | Cuisine: " + r.getCuisineType() +
//                " | Rating: " + r.getRating() +
//                " | ETA: " + r.getEta() + " mins"
//            );
//        }
//        System.out.println("Total Restaurants: " + restaurantList.size());

//        response.setContentType("text/plain");
//        response.getWriter().println("Restaurants printed in console successfully");
        request.setAttribute("restaurants", restaurantList);
        request.getRequestDispatcher("restaurant.jsp").forward(request, response);
    }
}
