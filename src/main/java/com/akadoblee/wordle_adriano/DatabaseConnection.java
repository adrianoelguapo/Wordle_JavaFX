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
                // Intentar crear la base de datos si no existe
                createDatabase();
                
                // Conectar a la base de datos
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                
                // Crear las tablas si no existen
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
            // Crear tabla words
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS words (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "word VARCHAR(5) NOT NULL UNIQUE" +
                ")"
            );

            // Crear tabla games
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS games (" +
                "wins INT DEFAULT 0, " +
                "losses INT DEFAULT 0" +
                ")"
            );

            // Insertar registro inicial en games si no existe
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM games");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO games (wins, losses) VALUES (0, 0)");
            }
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