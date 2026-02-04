package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/clothing_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "112123";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Driver not found!");
            System.out.println("Please add postgresql JDBC driver to your project.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println("Please check:");
            System.out.println("  - PostgreSQL is running");
            System.out.println("  - Database 'clothing_db' exists");
            System.out.println("  - Username and password are correct");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("⚠️ Warning: Failed to close database connection");
                e.printStackTrace();
            }
        }
    }

    public static boolean testConnection() {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Database connection successful!");
            closeConnection(conn);
            return true;
        } else {
            System.out.println("Database connection failed!");
            return false;
        }
    }
}