package com.akadoblee.wordle_adriano;

import java.util.HashSet;
import java.util.Set;

public class GameStats {
    private static GameStats instance;
    private String targetWord;
    private int attempts;
    private Set<String> usedLetters;
    private int gamesWon;
    private int gamesLost;

    private GameStats() {
        usedLetters = new HashSet<>();
        StatsStorage.loadStats(this);
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
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void addUsedLetter(String letter) {
        this.usedLetters.add(letter);
    }

    public void incrementGamesWon() {
        this.gamesWon++;
        StatsStorage.saveStats(this);
    }

    public void incrementGamesLost() {
        this.gamesLost++;
        StatsStorage.saveStats(this);
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