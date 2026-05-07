package com.foodDelivery.Servlet;

import java.io.IOException;
import java.util.List;

import com.foodDelivery.dao.MenuDAO;
import com.foodDelivery.daoImpl.MenuDaoImpl;
import com.foodDelivery.model.Cart;
import com.foodDelivery.model.CartItem;
import com.foodDelivery.model.Menu;
import com.foodDelivery.model.RestaurantCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // ✅ Always ensure cart exists
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        List<Menu> similarMenus = List.of();

        // ✅ Safe recommendation logic
        if (!cart.isEmpty()) {
            RestaurantCart rc = cart.getRestaurantCarts().iterator().next();

            if (!rc.getItems().isEmpty()) {
                CartItem item = rc.getItems().iterator().next();

                MenuDAO dao = new MenuDaoImpl();
                similarMenus = dao.getSimilarMenus(
                        item.getType(),
                        rc.getRestaurantId()
                );
            }
        }

        req.setAttribute("similarMenus", similarMenus);

        // ✅ Forward only (never redirect here)
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
