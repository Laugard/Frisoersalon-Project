package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Controller.Startside;
import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class Startside {

    @FXML
    private Button LoginButton;
    @FXML
    private Button OpretKundeButton;
    @FXML
    private TextField BrugernavnTF;
    @FXML
    private TextField AdgangskodeTF;


    private DbSql dbSql = new DbSql();

    public Startside() throws SQLException {
    }

    @FXML
    private void loginHandler() throws IOException {
        boolean isValid = dbSql.validateLogin(BrugernavnTF.getText(), AdgangskodeTF.getText());
        if (isValid) {
            // Login success
            Main m = new Main();
            m.changeScene("Forside.fxml");
            System.out.println("Login successful");
        } else {
            // Login failed
            System.out.println("Login failed");
        }
    }


    @FXML
    private void SkiftTilOpretKunde(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretKunde.fxml");
    }

}
