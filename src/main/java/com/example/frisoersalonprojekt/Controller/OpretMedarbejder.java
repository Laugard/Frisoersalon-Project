package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Utils.DbSql;
import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class OpretMedarbejder {

    @FXML
    private TextField OpretFornavnTF;
    @FXML
    private TextField OpretEfternavnTF;
    @FXML
    private TextField OpretTelefonTF;
    @FXML
    private TextField OpretEmailTF;
    @FXML
    private TextField OpretAdminTF;
    @FXML
    private TextField OpretBrugernavnTF;
    @FXML
    private TextField OpretAdgangskodeTF;
    @FXML
    private Button opretBrugerbtn;
    @FXML
    private Button tilbageTilForsideBtn;



    private DbSql dbSql = new DbSql();

    public OpretMedarbejder() throws SQLException {
    }


    public void opretMedarbejderHandler() {
        boolean success = dbSql.opretMedarbejder(
                OpretFornavnTF.getText(),
                OpretEfternavnTF.getText(),
                OpretTelefonTF.getText(),
                OpretEmailTF.getText(),
                Boolean.valueOf(OpretAdminTF.getText()),
                OpretBrugernavnTF.getText(),
                OpretAdgangskodeTF.getText()
        );
        if (success) {
            System.out.println("Medarbejder oprettet");
        } else {
            System.out.println("Fejl ved oprettelse af medarbejder");
        }
    }
    @FXML
    private void tilbageTilForside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("AdminForside.fxml");

    }
}
