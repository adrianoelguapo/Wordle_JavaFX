package com.akadoblee.wordle_adriano;

import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;

public class GameStats {
    private static GameStats instance;
    private String targetWord;
    private int attempts;
    private Set<String> usedLetters;
    private int gamesWon;
    private int gamesLost;

    private GameStats() {
        usedLetters = new HashSet<>();
    }

    public static GameStats getInstance() {
        if (instance == null) {
            instance = new GameStats();
        }
        return instance;
    }

    public void resetGameStats(String newTargetWord) {
        this.targetWord = newTargetWord;
        this.attempts = 0;
        this.usedLetters.clear();
        try {
            this.gamesWon = GameStatsRepository.getWins();
            this.gamesLost = GameStatsRepository.getLosses();
        } catch (SQLException e) {
            e.printStackTrace();
            this.gamesWon = 0;
            this.gamesLost = 0;
        }
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void addUsedLetter(String letter) {
        this.usedLetters.add(letter);
    }

    public void incrementGamesWon() {
        try {
            GameStatsRepository.incrementWins();
            this.gamesWon++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementGamesLost() {
        try {
            GameStatsRepository.incrementLosses();
            this.gamesLost++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTargetWord() {
        return targetWord;
    }

    public int getAttempts() {
        return attempts;
    }

    public Set<String> getUsedLetters() {
        return new HashSet<>(usedLetters);
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }
}