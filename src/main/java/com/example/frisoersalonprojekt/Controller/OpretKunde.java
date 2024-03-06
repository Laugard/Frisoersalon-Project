package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        dbSql.opretKunde(
                OpretFornavnTF.getText(),
                OpretEfternavnTF.getText(),
                OpretTelefonTF.getText(),
                OpretEmailTF.getText(),
                OpretBrugernavnTF.getText(),
                OpretAdgangskodePF.getText()
        );
    }


    @FXML
    private void tilbageTilStartside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");

    }


}
