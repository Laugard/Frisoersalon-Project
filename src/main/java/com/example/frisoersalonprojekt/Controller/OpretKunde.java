package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private TextField OpretAdgangskodeTF;
    @FXML
    private Button opretBrugerbtn;



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
                OpretAdgangskodeTF.getText()
        );
    }

}
