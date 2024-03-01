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
        String loginResult = dbSql.validateLogin(BrugernavnTF.getText(), AdgangskodeTF.getText());
        switch (loginResult) {
            case "AdminForside":
                // Omdiriger til AdminForside
                System.out.println("Login success - Admin");
                Main mAdmin = new Main();
                mAdmin.changeScene("AdminForside.fxml");
                break;
            case "MedarbejderForside":
                // Omdiriger til MedarbejderForside
                System.out.println("Login success - Medarbejder");
                Main mMedarbejder = new Main();
                mMedarbejder.changeScene("MedarbejderForside.fxml");
                break;
            case "Forside":
                // Omdiriger til Forside for kunder
                System.out.println("Login success - Kunde");
                Main mKunde = new Main();
                mKunde.changeScene("Forside.fxml");
                break;
            default:
                // Login fejlede
                System.out.println("Login failed");
                break;
        }
    }



    @FXML
    private void SkiftTilOpretKunde(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretKunde.fxml");
    }

}
