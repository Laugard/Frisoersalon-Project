package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Controller.Startside;
import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class Startside {

    @FXML
    private Button LoginButton;
    @FXML
    private Button OpretKundeButton;

    @FXML
    private void SkiftTilLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("LoginSide.fxml");
    }

    @FXML
    private void SkiftTilOpretKunde(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("opretKunde.fxml");
    }
}
