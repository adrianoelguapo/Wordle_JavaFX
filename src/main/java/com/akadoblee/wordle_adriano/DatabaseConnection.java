package com.akadoblee.wordle_adriano;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/wordle_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            try {

                createDatabase();
                
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                
                createTables();
                
                return connection;

            } catch (SQLException e) {

                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
                throw e;

            }

        }

        return connection;
    }

    private static void createDatabase() {
        
        try (Connection tempConn = DriverManager.getConnection("jdbc:mysql://localhost:3306", USER, PASSWORD)) {

            Statement stmt = tempConn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS wordle_db");

        } catch (SQLException e) {

            System.err.println("Error al crear la base de datos: " + e.getMessage());

        }

    }

    private static void createTables() throws SQLException {

        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS words (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "word VARCHAR(5) NOT NULL UNIQUE" +
                ")"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS games (" +
                "wins INT DEFAULT 0, " +
                "losses INT DEFAULT 0" +
                ")"
            );

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM games");
            rs.next();

            if (rs.getInt(1) == 0) {

                stmt.executeUpdate("INSERT INTO games (wins, losses) VALUES (0, 0)");

            }

        } catch (SQLException e) {

            System.err.println("Error al crear las tablas: " + e.getMessage());
            throw e;

        }

    }

    public static void closeConnection() {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException e) {

                System.err.println("Error al cerrar la conexión: " + e.getMessage());

            }

        }

    }
    
}