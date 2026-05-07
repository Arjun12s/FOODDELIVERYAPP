<%@ page import="java.sql.*,com.foodDelivery.util.DBconnection"%>

<%
int orderId = Integer.parseInt(request.getParameter("orderId"));
String status = "";

try (Connection con = DBconnection.getConnection()) {
    PreparedStatement ps =
        con.prepareStatement("SELECT payment_status FROM orders WHERE order_id=?");
    ps.setInt(1, orderId);
    ResultSet rs = ps.executeQuery();
    rs.next();
    status = rs.getString(1);
}

if (!"PAID".equals(status)) {
    response.sendRedirect("payment-pending.jsp?orderId=" + orderId);
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Order Success</title>
</head>
<body style="text-align:center;font-family:Arial">
<h2>Order Confirmed 🎉</h2>
<p>Your order has been accepted and is being prepared.</p>
</body>
</html>
