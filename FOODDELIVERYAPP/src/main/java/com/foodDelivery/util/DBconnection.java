package com.foodDelivery.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    private static Connection connection;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {

                String host = System.getenv("MYSQL_HOST");
                String port = System.getenv("MYSQL_PORT");
                String db   = System.getenv("MYSQL_DATABASE");
                String user = System.getenv("MYSQL_USER");
                String pass = System.getenv("MYSQL_PASSWORD");

                String url;

                // ✅ CLOUD (Railway)
                if (host != null && port != null && db != null && user != null && pass != null) {

                    url = "jdbc:mysql://" + host + ":" + port + "/" + db
                            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

                }
                // ✅ LOCAL (Fallback)
                else {
                    url = "jdbc:mysql://localhost:3306/food_delivery_app"
                            + "?useSSL=false&serverTimezone=UTC";

                    user = "root";
                    pass = "Arjun@12345"; // local password only
                }

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
