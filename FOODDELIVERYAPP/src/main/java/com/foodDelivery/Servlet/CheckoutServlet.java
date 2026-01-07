package com.foodDelivery.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.foodDelivery.model.Cart;
import com.foodDelivery.model.CartItem;
import com.foodDelivery.model.RestaurantCart;
import com.foodDelivery.util.DBconnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class CheckoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    	


        System.out.println(">>> CHECKOUT SERVLET HIT <<<");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // 🔧 TEMP FIX (remove userId check)
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        Integer userId = (Integer) session.getAttribute("userId");

    	if (userId == null) {
    	    response.sendRedirect(request.getContextPath() + "/login.jsp");
    	    return;
    	}

        // OPTIONAL (later)
     
        String address = request.getParameter("deliveryAddress");

        try (Connection con = DBconnection.getConnection()) {
            con.setAutoCommit(false);

            String orderSQL =
                "INSERT INTO orders (user_id, delivery_address, grand_total, payment_method, payment_status, order_status) " +
                "VALUES (?, ?, ?, 'UPI', 'CREATED', 'CREATED')";

            PreparedStatement psOrder =
                con.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS);

            psOrder.setInt(1, userId);
            psOrder.setString(2, address);
            psOrder.setDouble(3, cart.getGrandTotal());
            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);

            String itemSQL =
                "INSERT INTO order_items (order_id, restaurant_id, menu_id, quantity, price, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement psItem = con.prepareStatement(itemSQL);

            for (RestaurantCart rc : cart.getRestaurantCarts()) {
                for (CartItem i : rc.getItems()) {
                    psItem.setInt(1, orderId);
                    psItem.setInt(2, rc.getRestaurantId());
                    psItem.setInt(3, i.getMenuId());
                    psItem.setInt(4, i.getQuantity());
                    psItem.setDouble(5, i.getPrice());
                    psItem.setDouble(6, i.getTotalPrice());
                    psItem.addBatch();
                }
            }

            psItem.executeBatch();
            con.commit();

            response.sendRedirect(
                request.getContextPath() + "/upi-payment.jsp?orderId=" + orderId
            );

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(e.getMessage());
        }
    }
}
