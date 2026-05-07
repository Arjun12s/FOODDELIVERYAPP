package com.foodDelivery.Servlet;
import java.io.IOException;

import com.foodDelivery.model.Cart;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/increaseQuantity")
public class IncreaseQuantityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        int menuId = Integer.parseInt(req.getParameter("menuId"));

        cart.increaseItem(restaurantId, menuId);

        resp.sendRedirect("cart");
    }
}
