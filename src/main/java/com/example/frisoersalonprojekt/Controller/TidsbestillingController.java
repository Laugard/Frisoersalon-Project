package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Utils.UseCase;
import com.example.frisoersalonprojekt.Utils.SessionManager;
import com.example.frisoersalonprojekt.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    private UseCase useCase;

    public TidsbestillingController() {
        this.useCase = new UseCase(); // Antager, at UseCase håndterer sin egen SQLException
    }

    @FXML
    private void initialize() {
        medarbejderComboBox.getItems().setAll(useCase.hentMedarbejdereMedNavne());
        serviceComboBox.getItems().setAll(useCase.hentServiceNavneMedPriser());
        datoVaelger.setOnAction(event -> {
            try {
                loadTidspunkter();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void loadTidspunkter() throws SQLException {
        if (medarbejderComboBox.getValue() != null && datoVaelger.getValue() != null) {
            int medarbejderId = useCase.hentMedarbejdereMedId().get(medarbejderComboBox.getValue());
            ObservableList<Timestamp> tidspunkter = useCase.hentLedigeTidspunkter(medarbejderId, datoVaelger.getValue());
            tidspunktComboBox.setItems(tidspunkter);
        }
    }
    @FXML
    private void handleBekraeftAction() throws SQLException {
        int kundeId = SessionManager.getLoggedInKundeId();
        if (kundeId != -1 && !medarbejderComboBox.getSelectionModel().isEmpty() && !serviceComboBox.getSelectionModel().isEmpty() && !tidspunktComboBox.getSelectionModel().isEmpty()) {
            int medarbejderId = useCase.hentMedarbejdereMedId().get(medarbejderComboBox.getValue());
            int serviceId = useCase.findServiceId(serviceComboBox.getValue());
            Timestamp valgtTidspunkt = tidspunktComboBox.getValue();

            if (useCase.opretTidsbestilling(kundeId, medarbejderId, serviceId, valgtTidspunkt)) {
                showAlert("Success", "Tidsbestilling oprettet succesfuldt.");
                System.out.println("Tidsbestilling oprettet");
            } else {
                showAlert("Fejl", "Kunne ikke oprette tidsbestilling.");
                System.out.println("Tidsbestilling ikke oprettet");
            }
        } else {
            showAlert("Fejl", "Du skal udfylde alle felter for at bekræfte tidsbestillingen.");
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
