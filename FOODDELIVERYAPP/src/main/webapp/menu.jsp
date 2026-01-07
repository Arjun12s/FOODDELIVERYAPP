<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.foodDelivery.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Menu | Food Delivery</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
/* ===== RESET ===== */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Segoe UI", Arial, sans-serif;
}

body {
    background-color: #f4f4f4;
    color: #333;
}

/* ===== NAVBAR ===== */
.navbar {
    position: sticky;
    top: 0;
    background: #ffffff;
    padding: 14px 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
    z-index: 1000;
}

.logo {
    font-size: 22px;
    font-weight: 700;
    color: #ff4d4d;
}

.nav-links a {
    margin-left: 24px;
    text-decoration: none;
    color: #333;
    font-weight: 500;
}

.nav-links a:hover {
    color: #ff4d4d;
}

/* ===== HEADER ===== */
.header {
    padding: 30px 40px;
    background: #ffffff;
    border-bottom: 1px solid #eaeaea;
}

.header h2 {
    font-size: 26px;
    margin-bottom: 6px;
}

.header p {
    color: #777;
}

/* ===== MENU GRID ===== */
.container {
    padding: 40px;
}

.menu-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
    gap: 24px;
}

/* ===== MENU CARD ===== */
.menu-card {
    background: #ffffff;
    border-radius: 14px;
    overflow: hidden;
    box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.menu-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.14);
}

.menu-card img {
    width: 100%;
    height: 160px;
    object-fit: cover;
}

.menu-body {
    padding: 14px 16px 18px;
}

.menu-title {
    font-size: 18px;
    font-weight: 600;
    margin-bottom: 6px;
}

.menu-desc {
    font-size: 14px;
    color: #666;
    margin-bottom: 10px;
}

/* PRICE + RATING */
.menu-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 12px;
}

.price {
    font-size: 16px;
    font-weight: 600;
    color: #333;
}

.rating {
    background: #2ecc71;
    color: #fff;
    padding: 3px 8px;
    font-size: 13px;
    border-radius: 6px;
    font-weight: 600;
}

/* ADD TO CART */
.add-btn {
    width: 100%;
    padding: 10px 0;
    border: none;
    border-radius: 8px;
    background: #ff4d4d;
    color: #fff;
    font-size: 15px;
    font-weight: 600;
    cursor: pointer;
}

.add-btn:hover {
    background: #e84343;
}

/* ===== RESPONSIVE ===== */
@media (max-width: 768px) {
    .navbar, .header, .container {
        padding: 16px 20px;
    }
}
</style>
</head>

<body>

<!-- ===== NAVBAR ===== -->
<div class="navbar">
    <div class="logo">FoodExpress</div>
    <div class="nav-links">
        <a href="#">Home</a>
        <a href="cart">Cart</a>
        <a href="#">Profile</a>
    </div>
</div>

<!-- ===== HEADER ===== -->
<div class="header">
    <h2>Restaurant Menu</h2>
    <p>Choose your favorite dishes</p>
</div>

<!-- ===== MENU ITEMS ===== -->
<div class="container">
    <div class="menu-grid">

        <%
            List<Menu> allMenuByRestaurantId =
                    (List<Menu>) request.getAttribute("allMenuByRestaurantId");

            if (allMenuByRestaurantId != null) {
                for (Menu menu : allMenuByRestaurantId) {
        %>

        <div class="menu-card">
            <img src="<%= menu.getImageUrl() %>" alt="<%= menu.getName() %>">

            <div class="menu-body">
                <div class="menu-title"><%= menu.getName() %></div>
                <div class="menu-desc"><%= menu.getDescription() %></div>

                <div class="menu-info">
                    <span class="price">₹ <%= menu.getPrice() %></span>
                    <span class="rating"><%= menu.getRating() %> ★</span>
                </div>

                <!-- ✅ CORRECT ADD TO CART -->
                <form action="addToCart" method="get">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                    <button class="add-btn" type="submit">
                        Add to Cart
                    </button>
                </form>
            </div>
        </div>

        <%
                }
            }
        %>

    </div>
</div>

</body>
</html>
