module com.example.frisoersalonprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.frisoersalonprojekt to javafx.fxml;
    exports com.example.frisoersalonprojekt;
    opens com.example.frisoersalonprojekt.Klasser to javafx.fxml;
    exports com.example.frisoersalonprojekt.Klasser;
}
