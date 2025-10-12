package com.akadoblee.wordle_adriano;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.Set;

public class StatsController {
    @FXML private Label targetWordLabel;
    @FXML private Label attemptsLabel;
    @FXML private Label usedLettersLabel;
    @FXML private Label gamesWonLabel;
    @FXML private Label gamesLostLabel;

    private GameStats gameStats;

    @FXML
    public void initialize() {
        gameStats = GameStats.getInstance();
        updateStats();
    }

    public void updateStats() {
        targetWordLabel.setText(gameStats.getTargetWord());
        attemptsLabel.setText(String.valueOf(gameStats.getAttempts()));
        usedLettersLabel.setText(String.join(", ", gameStats.getUsedLetters()));
        gamesWonLabel.setText(String.valueOf(gameStats.getGamesWon()));
        gamesLostLabel.setText(String.valueOf(gameStats.getGamesLost()));
    }

    @FXML
    private void onNewGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            
            Stage stage = (Stage) targetWordLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}