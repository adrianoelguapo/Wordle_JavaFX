package com.akadoblee.wordle_adriano;

import java.sql.*;

public class GameStatsRepository {
    
    public static void incrementWins() throws SQLException {
        String sql = "UPDATE games SET wins = wins + 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public static void incrementLosses() throws SQLException {
        String sql = "UPDATE games SET losses = losses + 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public static int getWins() throws SQLException {
        String sql = "SELECT wins FROM games LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("wins");
            }
        }
        return 0;
    }

    public static int getLosses() throws SQLException {
        String sql = "SELECT losses FROM games LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("losses");
            }
        }
        return 0;
    }

    public static void resetStats() throws SQLException {
        String sql = "UPDATE games SET wins = 0, losses = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}