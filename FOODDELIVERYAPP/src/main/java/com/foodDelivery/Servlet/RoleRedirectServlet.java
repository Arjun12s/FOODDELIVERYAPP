package com.foodDelivery.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/role-redirect")
public class RoleRedirectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = request.getParameter("role");

        if ("ADMIN".equals(role)) {
            response.sendRedirect("admin-register.html");
        } 
        else if ("USER".equals(role)) {
            response.sendRedirect("user-register.html");
        } 
        else if ("RESTAURANT".equals(role)) {
            response.sendRedirect("restaurant-register.html");
        } 
        else {
            response.sendRedirect("index.html");
        }
    }
}
