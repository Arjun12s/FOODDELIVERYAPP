package com.foodDelivery.Servlet;

import java.io.IOException;
import java.util.List;

import com.foodDelivery.daoImpl.MenuDaoImpl;
import com.foodDelivery.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet{
	
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		MenuDaoImpl menuDaoImpl = new MenuDaoImpl();
		List<Menu> allMenuByRestaurantId = menuDaoImpl.getAllMenuByRestaurantId(restaurantId);
//		for(Menu menu:allMenuByRestaurantId) {
//			System.out.println(menu.getName());
//		}
		req.setAttribute("allMenuByRestaurantId", allMenuByRestaurantId);
		req.getRequestDispatcher("menu.jsp").forward(req, resp);;
		
	}
}
