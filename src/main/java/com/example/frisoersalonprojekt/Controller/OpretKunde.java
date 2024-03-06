package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class OpretKunde {

    @FXML
    private TextField OpretFornavnTF;
    @FXML
    private TextField OpretEfternavnTF;
    @FXML
    private TextField OpretTelefonTF;
    @FXML
    private TextField OpretEmailTF;
    @FXML
    private TextField OpretBrugernavnTF;
    @FXML
    private PasswordField OpretAdgangskodePF;
    @FXML
    private Button opretBrugerbtn;
    @FXML
    private Button tilbageTilStartsideBtn;


    private DbSql dbSql = new DbSql();

    public OpretKunde() throws SQLException {
    }


    public void opretKundeHandler() {
        boolean success = dbSql.opretKunde(
                OpretFornavnTF.getText(),
                OpretEfternavnTF.getText(),
                OpretTelefonTF.getText(),
                OpretEmailTF.getText(),
                OpretBrugernavnTF.getText(),
                OpretAdgangskodePF.getText()
        );

        if (success) {
            // Viser en alert boks, hvis kunden er oprettet succesfuldt
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kunde Oprettet");
            alert.setHeaderText(null); // Ingen header tekst
            alert.setContentText("Kunde oprettet succesfuldt.");
            alert.showAndWait(); // Viser alertboksen og venter på, at brugeren lukker den


            // Rydder alle tekstfelter, når kunden er oprettet succesfuldt
            OpretFornavnTF.setText("");
            OpretEfternavnTF.setText("");
            OpretTelefonTF.setText("");
            OpretEmailTF.setText("");
            OpretBrugernavnTF.setText("");
            OpretAdgangskodePF.setText("");
        } else {
            // Viser en fejlmeddelelse, hvis kundeoprettelsen mislykkes
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null); // Ingen header tekst
            alert.setContentText("Der opstod en fejl under oprettelsen af kunden.");

            alert.showAndWait(); // Viser alertboksen og venter på, at brugeren lukker den
        }
    }





    @FXML
    private void tilbageTilStartside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");

    }
}
