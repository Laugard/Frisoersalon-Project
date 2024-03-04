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

import java.io.IOException;
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



    // Method to initialize the TableView with data
    /*
    public void initializeTableView(List<Medarbejder> medarbejdere) {
        ObservableList<Medarbejder> medarbejderList = FXCollections.observableArrayList(medarbejdere);

        medarbejderColumn.setCellValueFactory(cellData -> cellData.getValue().//metode der finder medarbejdernavnet fra bestillingen());
        tidColumn.setCellValueFactory(cellData -> /* metode der finder tiden fra bestillingen /);
        serviceColumn.setCellValueFactory(cellData -> /* metode der findes service for bestillingen /);

        medarbejderTableView.setItems(medarbejderList);*/


    }
}
