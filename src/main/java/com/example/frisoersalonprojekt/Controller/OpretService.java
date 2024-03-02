package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Klasser.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class OpretService {

    @FXML
    private TextField serviceNavnTF;
    @FXML
    private TextField varighedTF;
    @FXML
    private TextField prisTF;
    @FXML
    private Button opretServiceBtn;
    @FXML
    private Button SletServiceBtn;
    @FXML
    private TableView<Service> ServiceTabel;
    @FXML
    private TableColumn<Service, String> serviceColumn;
    @FXML
    private TableColumn<Service, Integer> varighedColumn;
    @FXML
    private TableColumn<Service, Integer> prisColumn;


    private DbSql dbSql = new DbSql();

    public OpretService() throws SQLException {
    }

    @FXML
    private void initialize() {
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("serviceNavn"));
        varighedColumn.setCellValueFactory(new PropertyValueFactory<>("varighed"));
        prisColumn.setCellValueFactory(new PropertyValueFactory<>("pris"));

        opdaterServiceTabel();
    }

    @FXML
    private void handleOpretServiceAction() {
        String serviceNavn = serviceNavnTF.getText();
        String varighed = varighedTF.getText();
        int pris = Integer.parseInt(prisTF.getText());

        boolean success = dbSql.opretService(serviceNavn, varighed, pris);
        if (success) {
            opdaterServiceTabel(); // Opdaterer tabellen for at vise den nyoprettede service
            // Ryd tekstfelterne efter succesfuld oprettelse
            serviceNavnTF.clear();
            varighedTF.clear();
            prisTF.clear();
        } else {
            // Vis fejlmeddelelse her
        }
    }

    private void opdaterServiceTabel() {
        try {
            ObservableList<Service> serviceListe = FXCollections.observableArrayList(dbSql.hentAlleServices());
            ServiceTabel.setItems(serviceListe);
        } catch (Exception e) {
            e.printStackTrace();
            // Du kan vælge at vise en fejlmeddelelse her
        }
    }

    @FXML
    private void handleSletServiceAction() {
        Service selected = ServiceTabel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Bekræft sletning");
            confirmDialog.setHeaderText(null);
            confirmDialog.setContentText("Er du sikker på, at du vil slette denne service?");
            confirmDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            confirmDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean success = dbSql.sletService(selected.getServiceId());
                    if (success) {
                        opdaterServiceTabel(); // Genindlæs servicelisten efter sletning
                        System.out.println("Service Slettet");
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Fejl ved sletning af service.");
                        errorAlert.showAndWait();
                    }
                }
            });
        } else {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Vælg venligst en service for at slette.");
            infoAlert.showAndWait();
        }
    }
}
