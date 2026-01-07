package com.foodDelivery.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/food_delivery_app";
	private static final String PASSWORD = "Arjun@12345";
	private static Connection connection;
	public static final Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
