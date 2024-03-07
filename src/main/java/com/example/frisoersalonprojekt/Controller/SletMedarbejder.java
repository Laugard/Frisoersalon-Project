package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.frisoersalonprojekt.Klasser.Medarbejder;
import com.example.frisoersalonprojekt.Utils.DbSql;
import java.io.IOException;
import java.sql.SQLException;


public class SletMedarbejder {

    @FXML
    private Button SletMedarbejderBtn; // Ændret fra ToggleButton til Button
    @FXML
    private Button tilbageTilForsideBtn;
    @FXML
    private TableView<Medarbejder> SletMedarbejderTabel;
    @FXML
    private TableColumn<Medarbejder, String> fornavnColumn;
    @FXML
    private TableColumn<Medarbejder, String> efternavnColumn;
    @FXML
    private TableColumn<Medarbejder, String> emailColumn;
    private UseCase useCase;

    public SletMedarbejder() {
        try {
            this.useCase = new UseCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        fornavnColumn.setCellValueFactory(new PropertyValueFactory<>("medarbejderFornavn"));
        efternavnColumn.setCellValueFactory(new PropertyValueFactory<>("medarbejderEfternavn"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("medarbejderEmail"));

        opdaterMedarbejderTabel();
    }

    private void opdaterMedarbejderTabel() {
        ObservableList<Medarbejder> medarbejderListe = useCase.hentMedarbejdere();
        SletMedarbejderTabel.setItems(medarbejderListe);
    }

    @FXML
    private void handleSletMedarbejderAction() {
        Medarbejder selected = SletMedarbejderTabel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Konfirmationsdialog før sletning
            boolean confirmed = confirmDeletion();
            if (confirmed) {
                boolean success = useCase.sletMedarbejder(selected.getMedarbejderId());
                if (success) {
                    opdaterMedarbejderTabel(); // Opdater tabel efter sletning
                    System.out.println("Medarbejder slettet");
                } else {
                    showErrorDialog("Fejl ved sletning af medarbejder.");
                }
            }
        } else {
            showInfoDialog("Vælg venligst en medarbejder for at slette.");
        }
    }

    private boolean confirmDeletion() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på, at du vil slette denne medarbejder?", ButtonType.YES, ButtonType.NO);
        confirmDialog.setTitle("Bekræft sletning");
        confirmDialog.setHeaderText(null);
        return confirmDialog.showAndWait().filter(response -> response == ButtonType.YES).isPresent();
    }

    private void showErrorDialog(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message);
        errorAlert.setHeaderText(null);
        errorAlert.showAndWait();
    }

    private void showInfoDialog(String message) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, message);
        infoAlert.setHeaderText(null);
        infoAlert.showAndWait();
    }
    @FXML
    private void tilbageTilForside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("AdminForside.fxml");

    }

}
