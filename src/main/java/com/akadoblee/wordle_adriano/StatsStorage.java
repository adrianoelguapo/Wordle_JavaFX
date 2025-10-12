package com.akadoblee.wordle_adriano;

import java.io.*;
import java.util.Properties;

public class StatsStorage {
    private static final String STATS_FILE = "wordle_stats.properties";

    public static void saveStats(GameStats stats) {
        Properties properties = new Properties();
        properties.setProperty("gamesWon", String.valueOf(stats.getGamesWon()));
        properties.setProperty("gamesLost", String.valueOf(stats.getGamesLost()));

        try (OutputStream output = new FileOutputStream(STATS_FILE)) {
            properties.store(output, "Wordle Statistics");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadStats(GameStats stats) {
        Properties properties = new Properties();
        File file = new File(STATS_FILE);
        
        if (file.exists()) {
            try (InputStream input = new FileInputStream(file)) {
                properties.load(input);
                
                // Cargar las estadísticas guardadas
                int gamesWon = Integer.parseInt(properties.getProperty("gamesWon", "0"));
                int gamesLost = Integer.parseInt(properties.getProperty("gamesLost", "0"));
                
                // Establecer las estadísticas cargadas
                for (int i = 0; i < gamesWon; i++) stats.incrementGamesWon();
                for (int i = 0; i < gamesLost; i++) stats.incrementGamesLost();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}