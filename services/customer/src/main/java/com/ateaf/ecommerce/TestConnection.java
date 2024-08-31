package com.ateaf.ecommerce;

import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
     public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/customer";
        String username = "postgres";
        String password = "root";

        try {
            DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
