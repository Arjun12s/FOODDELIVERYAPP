package com.foodDelivery.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.foodDelivery.dao.UserDAO;
import com.foodDelivery.daoImpl.UserdaoImpl;
import com.foodDelivery.model.User;

@WebServlet("/register-user")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO userDAO = new UserdaoImpl();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        // Email validation
        if (userDAO.emailExists(email)) {
            response.getWriter().println("Email already exists!");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // hash later
        user.setAddress(address);
        user.setPhoneNumber(phone);
        user.setRole("USER");

        int result = userDAO.addUser(user);

        if (result > 0) {
            response.getWriter().println("User registered successfully");
        } else {
            response.getWriter().println("User registration failed");
        }
    }
}
