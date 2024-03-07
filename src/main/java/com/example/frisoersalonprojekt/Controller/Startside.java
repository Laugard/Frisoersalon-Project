package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Utils.UseCase;
import com.example.frisoersalonprojekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Startside {

    @FXML
    private Button LoginButton;
    @FXML
    private Button OpretKundeButton;
    @FXML
    private TextField BrugernavnTF;
    @FXML
    private TextField AdgangskodePF;

    private UseCase useCase;

    public Startside() {
        try {
            this.useCase = new UseCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginHandler() throws IOException {
        // Opkald til UseCase login, der nu også udskriver beskeder til konsollen
        String loginResult = useCase.login(BrugernavnTF.getText(), AdgangskodePF.getText());

        switch (loginResult) {
            case "AdminForside":
                changeScene("AdminForside.fxml");
                break;
            case "MedarbejderForside":
                changeScene("MedarbejderForside.fxml");
                break;
            case "Forside":
                changeScene("Forside.fxml");
                break;
            default:
                break;
        }
    }

    private void changeScene(String fxmlFile) throws IOException {
        Main m = new Main();
        m.changeScene(fxmlFile);
    }

    @FXML
    private void SkiftTilOpretKunde() throws IOException {
        changeScene("OpretKunde.fxml");
    }
}
