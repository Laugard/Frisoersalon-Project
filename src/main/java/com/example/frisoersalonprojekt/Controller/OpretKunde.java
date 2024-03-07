package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Utils.DbSql;
import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
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
    private UseCase useCase;

    public OpretKunde() {
        try {
            this.useCase = new UseCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void opretKundeHandler() {
        boolean success = useCase.opretKunde(
                OpretFornavnTF.getText(),
                OpretEfternavnTF.getText(),
                OpretTelefonTF.getText(),
                OpretEmailTF.getText(),
                OpretBrugernavnTF.getText(),
                OpretAdgangskodePF.getText()
        );

        // Håndter responsen med en alert boks baseret på success værdien
        Alert alert = success ? new Alert(Alert.AlertType.INFORMATION) : new Alert(Alert.AlertType.ERROR);
        alert.setTitle(success ? "Kunde Oprettet" : "Fejl");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Kunde oprettet succesfuldt." : "Der opstod en fejl under oprettelsen af kunden.");
        alert.showAndWait();

        if (success) {
            // Rydder alle tekstfelter, når kunden er oprettet succesfuldt
            OpretFornavnTF.setText("");
            OpretEfternavnTF.setText("");
            OpretTelefonTF.setText("");
            OpretEmailTF.setText("");
            OpretBrugernavnTF.setText("");
            OpretAdgangskodePF.setText("");
        }
    }

    @FXML
    private void tilbageTilStartside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
    }
}
