<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="
    com.foodDelivery.model.Cart,
    com.foodDelivery.model.RestaurantCart,
    com.foodDelivery.model.CartItem,
    java.util.List,
    com.foodDelivery.model.Menu
"%>

<%
Cart cart = (Cart) session.getAttribute("cart");
List<Menu> similarMenus = (List<Menu>) request.getAttribute("similarMenus");
%>

<!DOCTYPE html>
<html>
<head>
<title>Your Cart</title>

<style>
body { font-family: Arial; background:#f5f5f5; }
.container { width:80%; margin:auto; background:#fff; padding:20px; border-radius:8px; }

table { width:100%; border-collapse:collapse; margin-bottom:20px; }
th, td { padding:12px; border-bottom:1px solid #ddd; text-align:center; }
th { background:#ff5722; color:#fff; }

.btn { padding:6px 10px; color:#fff; border-radius:4px; text-decoration:none; font-size:14px; }
.plus { background:#4CAF50; }
.minus { background:#ff9800; }
.remove { background:red; }
.checkout { background:green; float:right; margin-top:15px; }

.qty-box { display:flex; justify-content:center; align-items:center; gap:8px; }
.total { text-align:right; font-size:18px; font-weight:bold; margin-top:10px; }

.card {
    width:220px;
    background:#fafafa;
    padding:12px;
    border-radius:8px;
    box-shadow:0 2px 6px rgba(0,0,0,.1);
}
.card img {
    width:100%;
    height:140px;
    object-fit:cover;
    border-radius:6px;
}
</style>
</head>

<body>
<div class="container">
<h2>Your Cart</h2>

<% if (cart == null || cart.isEmpty()) { %>
    <p>Your cart is empty.</p>
<% } else { %>

<%-- LOOP PER RESTAURANT --%>
<% for (RestaurantCart rc : cart.getRestaurantCarts()) { %>

<h3><%= rc.getRestaurantName() %></h3>

<table>
<tr>
    <th>Item</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Total</th>
    <th>Action</th>
</tr>

<% for (CartItem i : rc.getItems()) { %>
<tr>
    <td><%= i.getItemName() %></td>
    <td>₹ <%= i.getPrice() %></td>

    <td>
        <div class="qty-box">
            <!-- ❗ restaurantId MUST be ID, not name -->
            <a class="btn minus"
               href="decreaseQuantity?restaurantId=<%= rc.getRestaurantId() %>&menuId=<%= i.getMenuId() %>">−</a>

            <strong><%= i.getQuantity() %></strong>

            <a class="btn plus"
               href="increaseQuantity?restaurantId=<%= rc.getRestaurantId() %>&menuId=<%= i.getMenuId() %>">+</a>
        </div>
    </td>

    <td>₹ <%= i.getTotalPrice() %></td>

    <td>
        <a class="btn remove"
           href="removeFromCart?restaurantId=<%= rc.getRestaurantId() %>&menuId=<%= i.getMenuId() %>">
           Remove
        </a>
    </td>
</tr>
<% } %>

<tr>
    <td colspan="5" align="right">
        Subtotal: ₹ <%= rc.getSubTotal() %>
    </td>
</tr>
</table>

<% } %>

<div class="total">
    Grand Total: ₹ <%= cart.getGrandTotal() %>
</div>

<a class="btn checkout" href="<%= request.getContextPath() %>/checkout.jsp">
    Proceed to Checkout
</a>

<% } %>

<hr>
<h3>Similar items from nearby restaurants</h3>

<% if (similarMenus != null && !similarMenus.isEmpty()) { %>
<div style="display:flex; gap:16px; flex-wrap:wrap;">
<% for (Menu m : similarMenus) { %>

<div class="card">
    <img src="<%= m.getImageUrl() %>" alt="<%= m.getName() %>">

    <h4><%= m.getName() %></h4>
    <p>₹ <%= m.getPrice() %></p>
    <small>Restaurant: <%= m.getRestaurantName()%></small><br><br>

    <a class="btn plus"
      href="addToCart?menuId=<%= m.getMenuId() %>">
       Add to Cart
    </a>
</div>

<% } %>
</div>
<% } %>

</div>
</body>
</html>
