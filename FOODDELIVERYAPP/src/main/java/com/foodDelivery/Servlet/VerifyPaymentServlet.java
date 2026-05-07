package com.foodDelivery.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.foodDelivery.util.DBconnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/verify-payment")
public class VerifyPaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String utr = request.getParameter("utr");

        try (Connection con = DBconnection.getConnection()) {
            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE orders SET payment_status='PAYMENT_PENDING_VERIFICATION', utr_number=? WHERE order_id=?");
            ps.setString(1, utr);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(
        	    request.getContextPath() + "/payment-pending.jsp?orderId=" + orderId
        	);

    }
}


