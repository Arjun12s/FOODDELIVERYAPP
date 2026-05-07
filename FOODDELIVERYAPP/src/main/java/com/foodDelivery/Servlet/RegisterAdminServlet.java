package com.foodDelivery.Servlet;

import java.io.IOException;

import com.foodDelivery.dao.UserDAO;
import com.foodDelivery.daoImpl.UserdaoImpl;
import com.foodDelivery.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register-admin")
public class RegisterAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO userDAO = new UserdaoImpl();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userDAO.emailExists(email)) {
            response.getWriter().println("Admin email already exists!");
            return;
        }

        User admin = new User();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRole("ADMIN");

        int result = userDAO.addUser(admin);

        if (result > 0) {
            response.getWriter().println("Admin created successfully");
        } else {
            response.getWriter().println("Admin creation failed");
        }
    }
}
