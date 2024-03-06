package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Klasser.SessionManager;
import com.example.frisoersalonprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TidsbestillingController {

    @FXML
    private ComboBox<String> medarbejderComboBox;
    @FXML
    private ComboBox<String> serviceComboBox;
    @FXML
    private ComboBox<Timestamp> tidspunktComboBox;
    @FXML
    private Button bekraeftButton;
    @FXML
    private Button tilbageTilForsideBtn;
    @FXML
    private DatePicker datoVaelger;
    private Map<String, Integer> medarbejderIdMap = new HashMap<>();






    private DbSql dbSql = new DbSql();

    public TidsbestillingController() throws SQLException {
    }

    @FXML
    private void initialize() {
        loadMedarbejdere();
        loadServices();
        datoVaelger.setOnAction(event -> loadTidspunkter());
    }

    private void loadMedarbejdere() {
        medarbejderIdMap = dbSql.hentMedarbejdereMedId();
        medarbejderComboBox.getItems().addAll(medarbejderIdMap.keySet());
    }


    private void loadServices() {
        List<String> services = dbSql.hentServiceNavneMedPriser();
        serviceComboBox.getItems().clear(); // Sørg for, at comboboxen er tom før tilføjelse
        serviceComboBox.getItems().addAll(services);
    }


    private int findMedarbejderId(String medarbejderNavn) {
        return medarbejderIdMap.getOrDefault(medarbejderNavn, -1); // Returner -1 eller et andet ugyldigt ID, hvis navnet ikke findes
    }


    private void loadTidspunkter() {
        if (medarbejderComboBox.getSelectionModel().getSelectedItem() != null && datoVaelger.getValue() != null) {
            int medarbejderId = findMedarbejderId(medarbejderComboBox.getSelectionModel().getSelectedItem());
            List<Timestamp> tidspunkter = dbSql.hentLedigeTidspunkter(medarbejderId, datoVaelger.getValue());
            tidspunktComboBox.getItems().clear();
            tidspunktComboBox.getItems().addAll(tidspunkter);
        }
    }


    private int findServiceId(String serviceName) {
        return dbSql.findServiceId(serviceName);
    }

    @FXML
    private void handleBekraeftAction() {
        int kundeId = SessionManager.getLoggedInKundeId(); // Hent den logget ind brugers ID

        if (kundeId == -1) {
            showAlert("Fejl", "Ingen bruger er logget ind.");
            return;
        }
        // Valider at der er valgt en medarbejder, service og tidspunkt
        if (medarbejderComboBox.getSelectionModel().isEmpty() || serviceComboBox.getSelectionModel().isEmpty() || tidspunktComboBox.getSelectionModel().isEmpty()) {
            // Vis en fejlbesked til brugeren
            showAlert("Fejl", "Du skal vælge en medarbejder, service og tidspunkt.");
            return;
        }

        // Hent den valgte medarbejders ID
        String valgtMedarbejderNavn = medarbejderComboBox.getSelectionModel().getSelectedItem();
        int medarbejderId = findMedarbejderId(valgtMedarbejderNavn);
        if (medarbejderId == -1) {
            // Fejlhåndtering hvis medarbejderID ikke findes
            showAlert("Fejl", "Valgt medarbejder ikke fundet.");
            return;
        }

        // Antager at der findes en metode til at finde serviceId baseret på service navn, lignende findMedarbejderId metoden
        String valgtServiceNavn = serviceComboBox.getSelectionModel().getSelectedItem();
        int serviceId = findServiceId(valgtServiceNavn);
        if (serviceId == -1) {
            // Fejlhåndtering hvis serviceId ikke findes
            showAlert("Fejl", "Valgt service ikke fundet.");
            return;
        }

        // Hent det valgte tidspunkt
        Timestamp valgtTidspunkt = tidspunktComboBox.getSelectionModel().getSelectedItem();

        // Forsøg at oprette tidsbestillingen i databasen
        try {
            if (dbSql.opretTidsbestilling(kundeId, medarbejderId, serviceId, valgtTidspunkt)) {
                showAlert("Success", "Tidsbestilling oprettet succesfuldt.");
            } else {
                // Håndter fejl, hvis oprettelsen mislykkes
                showAlert("Fejl", "Kunne ikke oprette tidsbestilling.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Fejl", "Der opstod en fejl under oprettelse af tidsbestilling.");
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void tilbageTilForside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Forside.fxml");

    }

}
