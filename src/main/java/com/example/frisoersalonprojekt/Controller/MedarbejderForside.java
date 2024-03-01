package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MedarbejderForside {

    @FXML
    private Button LogUdBtn;

    @FXML
    private void LogUd(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
        System.out.println("Du er nu logget af");

    }
}
