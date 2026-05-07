<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.foodDelivery.model.Cart,
                 com.foodDelivery.model.RestaurantCart,
                 com.foodDelivery.model.CartItem"%>

<%
Integer userId = (Integer) session.getAttribute("userId");
Cart cart = (Cart) session.getAttribute("cart");

/* 🔒 USER MUST BE LOGGED IN */
if (userId == null) {
    // remember where user wanted to go
    session.setAttribute("redirectAfterLogin", "checkout.jsp");
    response.sendRedirect(request.getContextPath() + "/login.html");
    return;
}

/* 🛒 CART MUST EXIST */
if (cart == null || cart.isEmpty()) {
    response.sendRedirect(request.getContextPath() + "/cart");
    return;
}
%>



<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>

<!-- Leaflet (FREE) -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

<style>
body {
	font-family: Arial;
	background: #f5f5f5;
}

.container {
	width: 70%;
	margin: auto;
	background: #fff;
	padding: 20px;
	border-radius: 8px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 15px;
}

th, td {
	padding: 10px;
	border-bottom: 1px solid #ddd;
	text-align: center;
}

th {
	background: #ff5722;
	color: #fff;
}

.btn {
	padding: 8px 14px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.loc-btn {
	background: #2196F3;
	color: #fff;
}

.submit-btn {
	background: green;
	color: #fff;
	font-size: 16px;
}

.total {
	text-align: right;
	font-size: 18px;
	font-weight: bold;
}

textarea {
	width: 100%;
}

#map {
	height: 300px;
	display: none;
	border-radius: 8px;
	margin-top: 10px;
}

#locationName {
	margin-top: 8px;
	font-weight: bold;
}

.note {
	font-size: 12px;
	color: #666;
}
</style>
</head>

<body>
	<div class="container">
		<h2>Checkout</h2>

		<form action="<%= request.getContextPath() %>/place-order"
			method="post">

			<h3>Delivery Address</h3>

			<button type="button" class="btn loc-btn"
				onclick="useCurrentLocation()">Use Current Location</button>

			<br>
			<br>

			<!-- CRITICAL: no whitespace inside textarea -->
			<textarea id="addressBox" name="deliveryAddress" rows="4" required></textarea>

			<div id="locationName"></div>
			<div id="map"></div>
			<div class="note">Drag the pin if the location is slightly off.</div>

			<hr>

			<% for (RestaurantCart rc : cart.getRestaurantCarts()) { %>
			<h3><%= rc.getRestaurantName() %></h3>
			<table>
				<tr>
					<th>Item</th>
					<th>Qty</th>
					<th>Total</th>
				</tr>
				<% for (CartItem i : rc.getItems()) { %>
				<tr>
					<td><%= i.getItemName() %></td>
					<td><%= i.getQuantity() %></td>
					<td>₹ <%= i.getTotalPrice() %></td>
				</tr>
				<% } %>
				<tr>
					<td colspan="3" align="right">Subtotal: ₹ <%= rc.getSubTotal() %>
					</td>
				</tr>
			</table>
			<% } %>

			<div class="total">
				Grand Total: ₹
				<%= cart.getGrandTotal() %>
			</div>

			<!-- FORCE UPI -->
			<input type="hidden" name="paymentMethod" value="UPI"> <br>
			<button class="btn submit-btn" type="submit">Proceed to UPI
				Payment</button>

		</form>


	</div>

	<script>
let map, marker;

function useCurrentLocation() {
    if (!navigator.geolocation) {
        alert("Geolocation not supported");
        return;
    }

    navigator.geolocation.getCurrentPosition(
        pos => {
            const lat = pos.coords.latitude;
            const lon = pos.coords.longitude;

            fetch(`https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lon}`, {
                headers: { "Accept": "application/json" }
            })
            .then(r => r.json())
            .then(data => {
                document.getElementById("addressBox").value =
                    data.display_name || `Lat:${lat}, Lng:${lon}`;
                document.getElementById("locationName").innerText =
                    "📍 Location detected";
            });

            document.getElementById("map").style.display = "block";

            if (!map) {
                map = L.map("map").setView([lat, lon], 17);
                L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png")
                 .addTo(map);

                marker = L.marker([lat, lon], { draggable:true }).addTo(map);
                marker.on("dragend", () => {
                    const p = marker.getLatLng();
                    document.getElementById("addressBox").value =
                        `Lat:${p.lat.toFixed(6)}, Lng:${p.lng.toFixed(6)}`;
                });
            } else {
                map.setView([lat, lon], 17);
                marker.setLatLng([lat, lon]);
            }
        },
        () => alert("Location permission denied"),
        { enableHighAccuracy:true, timeout:10000, maximumAge:0 }
    );
}
</script>

</body>
</html>
