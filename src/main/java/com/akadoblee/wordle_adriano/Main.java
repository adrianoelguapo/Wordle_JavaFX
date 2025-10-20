package com.akadoblee.wordle_adriano;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Wordle - Adriano");
        stage.setResizable(true);
        stage.show();

    }

    public static void main(String[] args) {

        InitialDataLoader.initializeDatabase();
        launch();

    }
    
}