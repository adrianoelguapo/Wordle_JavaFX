package com.akadoblee.wordle_adriano;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.beans.value.ChangeListener;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class MainController {
    @FXML private Pane root;
    private TextField[][] grid = new TextField[6][5];
    private int currentRow = 0;
    private int currentCol = 0;
    private String targetWord;
    private final List<String> WORDS = Arrays.asList(
        // Sustantivos comunes
        "PAPEL", "LIBRO", "PLUMA", "RELOJ", "MUNDO",
        "TABLA", "SILLA", "COCHE", "PLAYA", "PLATO",
        "PIANO", "CLARA", "PERRO", "GATOS", "MESAS",
        // Adjetivos
        "VERDE", "AZULA", "ROJAS", "NEGRO", "SUAVE",
        "DUROS", "FELIZ", "FUERA", "LARGO", "CORTO",
        // Verbos
        "COMER", "BEBER", "SALIR", "JUGAR", "MIRAR",
        "NADAR", "SALTO", "BESAR", "VIVIR", "SOÑAR",
        // Naturaleza
        "FUEGO", "AGUAS", "HOJAS", "FLORA", "ARBOL",
        "PLAYA", "MONTE", "NIEVE", "CAMPO", "ARENA",
        // Otros
        "FECHA", "HORAS", "NOCHE", "TARDE", "ABRIL",
        "MARZO", "LUNES", "PARED", "TECHO", "SUELO"
    );

    @FXML
    public void initialize() {
        // Asegurarnos de que los estilos se apliquen
        if (!root.getStyleClass().contains("pane")) {
            root.getStyleClass().add("pane");
        }

        // Inicializar la matriz grid con las referencias a los TextField
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                final String id = "row" + i + "col" + j;
                grid[i][j] = (TextField) root.lookup("#" + id);
                if (grid[i][j] != null) {
                    grid[i][j].setEditable(false);
                    // Asegurarse de que el borde sea visible desde el inicio
                    grid[i][j].setStyle("-fx-border-color: #3a3a3c; -fx-border-width: 2;");
                }
            }
        }

        // Agregar el event listener para el teclado directamente a la raíz del panel
        root.setOnKeyPressed(this::handleKeyPress);
        root.requestFocus(); // Esto asegura que el panel tenga el foco desde el inicio

        // Seleccionar una palabra aleatoria
        targetWord = WORDS.get(new Random().nextInt(WORDS.size()));
        System.out.println("Palabra objetivo seleccionada: " + targetWord);

        // Seleccionar una palabra aleatoria
        Random random = new Random();
        targetWord = WORDS.get(random.nextInt(WORDS.size()));
        System.out.println("Palabra objetivo seleccionada: " + targetWord);  // Debug
    }

    @FXML
    private void onKeyPressed(javafx.event.ActionEvent event) {
        if (currentRow < 6 && currentCol < 5) {
            Button button = (Button) event.getSource();
            String letter = button.getText();
            if (letter.matches("[A-ZÑÁÉÍÓÚ]")) {
                grid[currentRow][currentCol].setText(letter);
                currentCol++;
            }
        }
    }

    @FXML
    private void onDelete() {
        if (currentCol > 0) {
            currentCol--;
            grid[currentRow][currentCol].setText("");
        }
    }

    @FXML
    private void onEnter() {
        System.out.println("onEnter llamado");  // Debug
        if (currentCol == 5) {
            StringBuilder guess = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                guess.append(grid[currentRow][i].getText());
            }

            String guessWord = guess.toString();
            System.out.println("Palabra intentada: " + guessWord);  // Debug
            System.out.println("Palabra objetivo: " + targetWord);  // Debug
            
            checkWord(guessWord);
            if (guessWord.equals(targetWord)) {
                // ¡Victoria!
                System.out.println("¡Felicidades! ¡Has ganado!");
                disableInput();
            } else if (currentRow == 5) {
                // Juego terminado - sin más intentos
                System.out.println("¡Juego terminado! La palabra era: " + targetWord);
                disableInput();
            } else {
                currentRow++;
                currentCol = 0;
            }
        } else {
            System.out.println("No hay suficientes letras. currentCol = " + currentCol);  // Debug
        }
    }

    private void checkWord(String guess) {
        for (int i = 0; i < 5; i++) {
            TextField field = grid[currentRow][i];
            char guessChar = guess.charAt(i);
            char targetChar = targetWord.charAt(i);

            // Mantener el estilo del borde
            field.setStyle("-fx-border-color: #3a3a3c; -fx-border-width: 2;");

            if (guessChar == targetChar) {
                field.getStyleClass().add("letter-correct");
                updateKeyboardButton(String.valueOf(guessChar), "correct");
            } else if (targetWord.indexOf(guessChar) != -1) {
                field.getStyleClass().add("letter-present");
                updateKeyboardButton(String.valueOf(guessChar), "present");
            } else {
                field.getStyleClass().add("letter-absent");
                updateKeyboardButton(String.valueOf(guessChar), "absent");
            }
        }
    }

    private void updateKeyboardButton(String letter, String status) {
        root.lookupAll(".keyboard-button").stream()
            .filter(node -> node instanceof Button)
            .map(node -> (Button) node)
            .filter(button -> button.getText().equals(letter))
            .forEach(button -> {
                button.getStyleClass().removeAll("correct", "present", "absent");
                button.getStyleClass().add(status);
            });
    }

    private void disableInput() {
        root.lookupAll(".keyboard-button").forEach(node -> node.setDisable(true));
        root.getScene().setOnKeyPressed(null);
    }

    private void handleKeyPress(KeyEvent event) {
        if (!event.isConsumed()) {
            if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                onDelete();
                event.consume();
            } else if (event.getCode() == KeyCode.ENTER) {
                onEnter();
                event.consume();
            } else {
                String keyText = event.getText().toUpperCase();
                // Solo procesar letras
                if (keyText.matches("[A-ZÑ]")) {
                    // Simular el clic en el botón correspondiente
                    root.lookupAll(".keyboard-button").stream()
                        .filter(node -> node instanceof Button)
                        .map(node -> (Button) node)
                        .filter(button -> button.getText().equals(keyText))
                        .findFirst()
                        .ifPresent(button -> {
                            if (!button.isDisabled()) {
                                onKeyPressed(new javafx.event.ActionEvent(button, null));
                            }
                        });
                    event.consume();
                }
            }
        }
    }
}