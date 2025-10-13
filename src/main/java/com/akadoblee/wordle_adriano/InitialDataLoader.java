package com.akadoblee.wordle_adriano;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InitialDataLoader {
    private static final List<String> INITIAL_WORDS = Arrays.asList(
        "GATOS", "PERRO", "CASAS", "COCHE", "PLAYA", 
        "VERDE", "DULCE", "NEGRO", "PLATO", "RELOJ",
        "LIBRO", "PAPEL", "BOLSA", "LECHE", "PIZZA",
        "JUGAR", "FUEGO", "RADIO", "PLUMA", "BARCO",
        "SUELO", "CIELO", "NOCHE", "PLATA", "CABLE",
        "TABLA", "BELLO", "FLUIR", "GRANO", "JOVEN"
    );

    public static void initializeDatabase() {
        try {
            // Primero verificamos si ya hay palabras en la base de datos
            List<String> existingWords = WordRepository.getAllWords();
            if (existingWords.isEmpty()) {
                System.out.println("Inicializando base de datos con palabras...");
                WordRepository.insertInitialWords(INITIAL_WORDS);
                System.out.println("Base de datos inicializada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}