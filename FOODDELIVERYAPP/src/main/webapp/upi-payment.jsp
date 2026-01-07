<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*,java.net.URLEncoder,com.foodDelivery.util.DBconnection" %>

<%
int orderId = Integer.parseInt(request.getParameter("orderId"));
double amount = 0;

try (Connection con = DBconnection.getConnection()) {
    PreparedStatement ps =
        con.prepareStatement("SELECT grand_total FROM orders WHERE order_id=?");
    ps.setInt(1, orderId);
    ResultSet rs = ps.executeQuery();
    
    if (rs.next()) {
        amount = rs.getDouble(1);
    }
   
    
}

String upiId = "9036347478@kotak"; // ✅ REAL UPI ID
String payeeName = "Food Delivery App";
String note = "Order ID " + orderId;

String upiUrl =
    "upi://pay" +
    "?pa=" + URLEncoder.encode(upiId, "UTF-8") +
    "&pn=" + URLEncoder.encode(payeeName, "UTF-8") +
    "&am=" + String.format("%.2f", amount) +
    "&cu=INR" +
    "&tn=" + URLEncoder.encode(note, "UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<title>UPI Payment</title>
<style>
body{text-align:center;font-family:Arial;background:#f5f5f5}
.box{background:#fff;width:360px;margin:60px auto;padding:20px;border-radius:8px}
</style>
</head>

<body>
<div class="box">
    <h2>Pay ₹ <%= String.format("%.2f", amount) %></h2>

    <img
      src="https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=<%= URLEncoder.encode(upiUrl,"UTF-8") %>"
      alt="UPI QR Code">

    <p>UPI ID: <strong><%= upiId %></strong></p>
    <p>Order ID: <strong><%= orderId %></strong></p>

    <p><small>Amount is locked for this order</small></p>
</div>
</body>
</html>
