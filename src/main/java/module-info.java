module com.akadoblee.wordle_adriano {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.akadoblee.wordle_adriano to javafx.fxml;
    exports com.akadoblee.wordle_adriano;
}