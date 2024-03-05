package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Klasser.Medarbejder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedarbejderForside {

    @FXML
    private Button LogUdBtn;

    @FXML
    private TableView<Medarbejder> medarbejderTableView;

    @FXML
    private TableColumn<Medarbejder, String> medarbejderColumn;

    @FXML
    private TableColumn<Medarbejder, String> tidColumn;

    @FXML
    private TableColumn<Medarbejder, String> serviceColumn;

    @FXML
    private void LogUd(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
        System.out.println("Du er nu logget af");
    }



    }

    /*
    public void initializeTableView(List<Medarbejder> medarbejdere) {
        ObservableList<Medarbejder> medarbejderList = FXCollections.observableArrayList(medarbejdere);

        medarbejderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getWorkerName(cellData.getValue().getMedarbejderId())));
        //  tidColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTiden()));
        //serviceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getService()));

        medarbejderTableView.setItems(medarbejderList);
    }
*/



