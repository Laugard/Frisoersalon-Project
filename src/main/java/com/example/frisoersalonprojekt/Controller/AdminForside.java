package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminForside {

    @FXML
    private Button OpretMedarbejderBtn;
    @FXML
    private Button OpretTidsbestillingBtn;
    @FXML
    private Button OpretKundeBtn;
    @FXML
    private TableView TiderTable;
    @FXML
    private Button LogUdBtn;

    @FXML
    private void SkiftTilOpretMedarbejder(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretMedarbejder.fxml");
    }
    @FXML
    private void SkiftTilOpretKunde(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretKunde.fxml");
    }

    @FXML
    private void LogUd(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
        System.out.println("Du er nu logget af");
    }

}
