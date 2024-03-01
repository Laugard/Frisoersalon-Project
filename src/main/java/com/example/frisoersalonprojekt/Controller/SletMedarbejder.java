package com.example.frisoersalonprojekt.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.frisoersalonprojekt.Klasser.Medarbejder;
import com.example.frisoersalonprojekt.Klasser.DbSql;

import java.sql.SQLException;

public class SletMedarbejder {

    @FXML
    private Button SletMedarbejderBtn; // Ændret fra ToggleButton til Button

    @FXML
    private TableView<Medarbejder> SletMedarbejderTabel;

    @FXML
    private TableColumn<Medarbejder, String> fornavnColumn;

    @FXML
    private TableColumn<Medarbejder, String> efternavnColumn;

    @FXML
    private TableColumn<Medarbejder, String> emailColumn;

    private DbSql dbSql = new DbSql();

    public SletMedarbejder() throws SQLException {
    }

    @FXML
    public void initialize() {
        fornavnColumn.setCellValueFactory(new PropertyValueFactory<>("medarbejderFornavn"));
        efternavnColumn.setCellValueFactory(new PropertyValueFactory<>("medarbejderEfternavn"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("medarbejderEmail"));

        opdaterMedarbejderTabel();
    }

    private void opdaterMedarbejderTabel() {
        try {
            ObservableList<Medarbejder> medarbejderListe = FXCollections.observableArrayList(dbSql.hentMedarbejdere());
            SletMedarbejderTabel.setItems(medarbejderListe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSletMedarbejderAction() {
        Medarbejder selected = SletMedarbejderTabel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Bekræft sletning");
            confirmDialog.setHeaderText(null);
            confirmDialog.setContentText("Er du sikker på, at du vil slette denne medarbejder?");
            confirmDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            confirmDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean success = dbSql.sletMedarbejder(selected.getMedarbejderId());
                    if (success) {
                        opdaterMedarbejderTabel(); // Genindlæs medarbejderlisten efter sletning
                        System.out.println("Medarbejder Slettet");
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Fejl ved sletning af medarbejder.");
                        errorAlert.showAndWait();
                    }
                }
            });
        } else {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Vælg venligst en medarbejder for at slette.");
            infoAlert.showAndWait();
        }
    }
}
