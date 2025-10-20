package com.akadoblee.wordle_adriano;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InitialDataLoader {

    private static final List<String> INITIAL_WORDS = Arrays.asList(
        "LIBRO", "PLATO", "RELOJ", "PLUMA", "TECHO", "BANCO", "CAMPO", "PLAYA", "MUSEO", "HOTEL",
        "METRO", "SALON", "MUNDO", "PLAZA", "PALMA", "CALLE", "ALDEA", "BARCA", "NORTE", "BARCO",
        "POLLO", "CARNE", "ARROZ", "LECHE", "QUESO", "SUSHI", "PIZZA", "DULCE", "PASTEL",
        "FRUTA", "PESCA", "CERDO", "MANGO", "MELON", "LIMON", "PASTO", "PASTA", "CREMA", "SALSA",
        "NADAR", "BEBER", "SALIR", "JUGAR", "MIRAR", "ANDAR", "COMER", "TOMAR", "PASAR", "VENIR",
        "PESAR", "BAJAR", "SUBIR", "HACER", "TENER", "ESTAR", "PODER", "VIVIR", "VOLAR", "SOÑAR",
        "VERDE", "NEGRO", "DULCE", "CRUEL", "SUAVE", "GRAVE", "VELOZ", "FELIZ", "LIBRE", "FIRME",
        "DIGNO", "NOBLE", "GORDO", "RUBIO", "LARGO", "SABIO", "SERIO", "RECTO", "SUTIL",
        "NIEVE", "MONTE", "PLATA", "FUEGO", "ARENA", "SELVA", "NOCHE", "PLAYA", "COSTA",
        "CLIMA", "FAUNA", "FLORA", "MONTE", "SILVA", "VALLE", "CERRO", "PRADO", "CORAL", "PUNTA",
        "PERRO", "TIGRE", "COBRA", "CERDO", "PULPO", "CEBRA", "PANDA", "CISNE", "CABRA", "HIENA",
        "MOSCA", "PULGA", "ZORRO", "BURRO", "POTRO", "PERCA", "GARZA", "CABRA",
        "DENTE", "LABIO", "PECHO", "NARIZ", "BRAZO", "MUELA", "CODOS", "DEDOS", "TORSO",
        "CEJAS", "BARBA", "MANOS", "MUSLO", "PECHO", "OREJA",
        "MOTOR", "RADIO", "DISCO", "CABLE", "PANEL", "ROBOT", "METAL", "TECLA", "FIBRA", "PASTA",
        "VIRUS", "PIXEL", "DATOS", "AUDIO", "VIDEO", "LASER", "MICRO", "RADAR", "SEÑAL",
        "RITMO", "POETA", "PROSA", "DRAMA", "MURAL", "CLAVE", "PAPEL", "DANZA", "CANTO", "PIANO",
        "OPERA", "FERIA", "MUSEO", "CIRCO", "CINTA", "BAILE", "LIBRO", "DISCO", "MURAL",
        "BOINA", "FALDA", "BOLSO", "POLAR", "MANGA", "SUELA", "BOTAS", "GORRA", "FUNDA", "BOLSA",
        "MEDIA", "GAFAS", "TRAJE", "CINTA", "PARKA"
    );

    public static void initializeDatabase() {
        try {

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