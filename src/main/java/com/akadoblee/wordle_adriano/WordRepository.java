package com.akadoblee.wordle_adriano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordRepository {
    
    public static void insertWord(String word) throws SQLException {
        String sql = "INSERT INTO words (word) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word.toUpperCase());
            pstmt.executeUpdate();
        }
    }

    public static void insertInitialWords(List<String> words) throws SQLException {
        String sql = "INSERT IGNORE INTO words (word) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String word : words) {
                pstmt.setString(1, word.toUpperCase());
                pstmt.executeUpdate();
            }
        }
    }

    public static String getRandomWord() throws SQLException {
        String sql = "SELECT word FROM words ORDER BY RAND() LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString("word");
            }
        }
        throw new SQLException("No hay palabras disponibles en la base de datos");
    }

    public static List<String> getAllWords() throws SQLException {
        List<String> words = new ArrayList<>();
        String sql = "SELECT word FROM words";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                words.add(rs.getString("word"));
            }
        }
        return words;
    }

    public static boolean exists(String word) throws SQLException {
        String sql = "SELECT COUNT(*) FROM words WHERE word = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word.toUpperCase());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}