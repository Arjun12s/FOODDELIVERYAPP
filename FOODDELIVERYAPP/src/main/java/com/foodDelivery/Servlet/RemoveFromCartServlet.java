package com.foodDelivery.Servlet;

import java.io.IOException;

import com.foodDelivery.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        int menuId = Integer.parseInt(req.getParameter("menuId"));

        cart.removeItem(restaurantId, menuId);

        resp.sendRedirect("cart");
    }
}



