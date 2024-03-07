package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

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
    private UseCase useCase;

    public OpretMedarbejder() {
        try {
            this.useCase = new UseCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void opretMedarbejderHandler() {
        boolean success = useCase.opretMedarbejder(
                OpretFornavnTF.getText(),
                OpretEfternavnTF.getText(),
                OpretTelefonTF.getText(),
                OpretEmailTF.getText(),
                Boolean.parseBoolean(OpretAdminTF.getText()), // Formodning om, at "true" eller "false" er input
                OpretBrugernavnTF.getText(),
                OpretAdgangskodeTF.getText()
        );
        if (success) {
            // Rydder alle tekstfelter, når kunden er oprettet succesfuldt
            OpretFornavnTF.setText("");
            OpretEfternavnTF.setText("");
            OpretTelefonTF.setText("");
            OpretEmailTF.setText("");
            OpretAdminTF.setText("");
            OpretBrugernavnTF.setText("");
            OpretAdgangskodeTF.setText("");
        }
    }

    @FXML
    private void tilbageTilForside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("AdminForside.fxml");

    }
}
