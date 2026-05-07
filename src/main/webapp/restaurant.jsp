<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,com.foodDelivery.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Restaurants | Food Delivery</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
/* ================= GLOBAL RESET ================= */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Segoe UI", Roboto, Arial, sans-serif;
}

body {
    background-color: #f5f5f5;
    color: #333;
}

/* ================= NAVBAR ================= */
.navbar {
    position: sticky;
    top: 0;
    z-index: 1000;
    background: #ffffff;
    padding: 14px 50px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 2px 12px rgba(0,0,0,0.08);
}

.logo {
    font-size: 22px;
    font-weight: 700;
    color: #ff4d4d;
}

.nav-links a {
    margin-left: 28px;
    text-decoration: none;
    font-size: 15px;
    font-weight: 500;
    color: #333;
}

.nav-links a:hover {
    color: #ff4d4d;
}

/* ================= HEADER ================= */
.page-header {
    padding: 30px 50px;
    background: #ffffff;
    border-bottom: 1px solid #eaeaea;
}

.page-header h2 {
    font-size: 26px;
    margin-bottom: 6px;
}

.page-header p {
    color: #777;
}

/* ================= GRID ================= */
.container {
    padding: 40px 50px;
}

.restaurant-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
    gap: 26px;
}

/* ================= CARD ================= */
.card {
    background: #ffffff;
    border-radius: 14px;
    overflow: hidden;
    box-shadow: 0 6px 18px rgba(0,0,0,0.08);
    transition: transform 0.25s ease, box-shadow 0.25s ease;
}

.card:hover {
    transform: translateY(-6px);
    box-shadow: 0 14px 30px rgba(0,0,0,0.15);
}

.card img {
    width: 100%;
    height: 165px;
    object-fit: cover;
}

/* ================= CARD BODY ================= */
.card-body {
    padding: 14px 15px 16px;
}

.card-title {
    font-size: 18px;
    font-weight: 600;
    margin-bottom: 6px;
}

/* Rating + ETA */
.card-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 8px;
}

.rating {
    background: #2ecc71;
    color: #fff;
    padding: 3px 9px;
    font-size: 13px;
    border-radius: 6px;
    font-weight: 600;
}

.eta {
    font-size: 13px;
    color: #555;
}

/* Description */
.description {
    font-size: 14px;
    color: #666;
    margin-bottom: 8px;
}

/* Location */
.location {
    font-size: 13px;
    color: #888;
}

/* ================= RESPONSIVE ================= */
@media (max-width: 768px) {
    .navbar,
    .page-header,
    .container {
        padding: 16px 20px;
    }

    .nav-links a {
        margin-left: 16px;
    }
}
</style>
</head>





<body>

<!-- ================= NAVBAR ================= -->
<div class="navbar">
    <div class="logo">FoodExpress</div>
    <div class="nav-links">
        <a href="<%= request.getContextPath() %>/restaurant">Home</a>
<a href="<%= request.getContextPath() %>/cart.jsp">Cart</a>
<a href="<%= request.getContextPath() %>/profile">Profile</a>
<a href="<%= request.getContextPath() %>/login">Login</a>
    </div>
</div>

<!-- ================= HEADER ================= -->
<div class="page-header">
    <h2>Restaurants Near You</h2>
    <p>Order from top-rated restaurants around your location</p>
</div>

<!-- ================= RESTAURANTS ================= -->
<div class="container">
    <div class="restaurant-grid">
			
		<%
		
		List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");
		for(Restaurant restaurant:restaurants){
			%>
			<a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>">
			 <div class="card">
	            <img src=<%=restaurant.getImageUrl() %> alt="Restaurant">
	            <div class="card-body">
	                <div class="card-title"><%=restaurant.getName()%></div>
	                <div class="card-info">
	                    <span class="rating"><%=restaurant.getRating()%> ★</span>
	                    <span class="eta"><%=restaurant.getEta()%> min<br></span>
	                </div>
	                <div class="description"><%=restaurant.getCuisineType()%></div>
	                <div class="location"><%=restaurant.getAddress()%></div>
	            </div>
	        </div>
	        </a>
	        <%
		}
		%>
        <!-- ===== CARD TEMPLATE (REPEAT 20 TIMES) ===== -->
        <!-- 1 -->
       

         

        <!-- Duplicate similar cards until 20 -->
        <!-- Change image keywords for variety -->
        <!-- pasta, cafe, biryani, south-indian, chinese-food, desserts -->

        <!-- You can safely copy-paste to reach 20 cards -->

    </div>
</div>

</body>
</html>
    