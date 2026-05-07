package com.foodDelivery.Servlet;

import java.io.IOException;

import com.foodDelivery.dao.MenuDAO;
import com.foodDelivery.daoImpl.MenuDaoImpl;
import com.foodDelivery.model.Cart;
import com.foodDelivery.model.Menu;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        int menuId = Integer.parseInt(req.getParameter("menuId"));

        MenuDAO menuDAO = new MenuDaoImpl();
        Menu menu = menuDAO.getMenuById(menuId);

        if (menu != null) {
            cart.addItem(
                menu.getRestaurantId(),
                menu.getRestaurantName(),
                menu.getMenuId(),
                menu.getName(),
                menu.getPrice(),
                menu.getType()
            );
        }

        resp.sendRedirect("cart");
    }
}
