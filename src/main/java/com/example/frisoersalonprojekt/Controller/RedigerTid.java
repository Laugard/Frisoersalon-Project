package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Utils.DbSql;
import com.example.frisoersalonprojekt.Klasser.Tidsbestilling;
import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;


public class RedigerTid {


    @FXML
    private Button aflysTidBtn;
    @FXML
    private Button redigerTidBtn;
    @FXML
    private TableView<Tidsbestilling> serviceTabel;
    @FXML
    private TableColumn<Tidsbestilling, String> tidspunktColumn;
    @FXML
    private TableColumn<Tidsbestilling, String> serviceNavnColumn;
    @FXML
    private TableColumn<Tidsbestilling, Integer> prisColumn;
    @FXML
    private TableColumn<Tidsbestilling, String> statusColumn;
    @FXML
    private ComboBox<Timestamp> tidspunktComboBox;
    @FXML
    private DatePicker datoPicker;
    @FXML
    private Button tilbageTilForsideBtn;


    private UseCase useCase;

    public RedigerTid() {
        this.useCase = new UseCase(); // Håndter eventuelle exceptions som nødvendigt
    }

    @FXML
    public void initialize() throws SQLException {
        datoPicker.setOnAction(event -> opdaterTilgængeligeTidspunkter());
        bindTableColumns();
        loadTidsbestillinger();
    }

    private void opdaterTilgængeligeTidspunkter() {
        if (datoPicker.getValue() != null) {
            LocalDate valgtDato = datoPicker.getValue();
            int medarbejderId = 1; // Eksempel, skal erstattes med faktisk logik
            tidspunktComboBox.setItems(FXCollections.observableArrayList(useCase.hentLedigeTidspunkter(medarbejderId, valgtDato)));
        }
    }



    private void bindTableColumns() {
        tidspunktColumn.setCellValueFactory(new PropertyValueFactory<>("tidspunkt"));
        serviceNavnColumn.setCellValueFactory(new PropertyValueFactory<>("serviceNavn"));

        // Brug en cellFactory til at tilpasse visningen af pris
        prisColumn.setCellValueFactory(new PropertyValueFactory<>("pris"));
        prisColumn.setCellFactory(column -> new TableCell<Tidsbestilling, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item + " kr."); // Tilføj " kr." efter prisen
                }
            }
        });

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadTidsbestillinger() throws SQLException {
        serviceTabel.setItems(FXCollections.observableArrayList(useCase.hentAlleTidsbestillinger()));
    }

    @FXML
    private void aflysTidsbestilling() throws SQLException {
        Tidsbestilling valgtTidsbestilling = serviceTabel.getSelectionModel().getSelectedItem();
        if (valgtTidsbestilling != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på, at du vil aflyse denne tidsbestilling?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.setHeaderText("Bekræft Aflysning");
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.get() == ButtonType.YES) {
                useCase.opdaterStatusTilAflyst(valgtTidsbestilling.getTidsbestillingsId());
                loadTidsbestillinger(); // Genindlæs tidsbestillinger for at opdatere visningen
                showAlert("Aflysning", "Tidsbestillingen er blevet aflyst.");
                System.out.println("Tidsbestilling aflyst");

            }
        } else {
            showAlert("Ingen Valg", "Vælg venligst en tidsbestilling for at aflyse.");
            System.out.println("Tidsbestilling ikke aflyst");

        }
    }


    @FXML
    private void gemTidsbestillingÆndringer() throws SQLException {
        Tidsbestilling valgtTidsbestilling = serviceTabel.getSelectionModel().getSelectedItem();
        if (valgtTidsbestilling != null && datoPicker.getValue() != null && tidspunktComboBox.getValue() != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på, at du vil gemme ændringerne til denne tidsbestilling?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.setHeaderText("Bekræft Ændring");
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.get() == ButtonType.YES) {
                Timestamp nyTidspunkt = tidspunktComboBox.getValue();
                useCase.opdaterTidsbestillingTidspunkt(valgtTidsbestilling.getTidsbestillingsId(), nyTidspunkt);
                loadTidsbestillinger(); // Genindlæs tidsbestillinger for at opdatere visningen
                showAlert("Ændring Gemt", "Ændringerne til tidsbestillingen er blevet gemt.");
                System.out.println("Ny tidsbestilling gemt");

            }
        } else {
            showAlert("Ingen Valg", "Vælg venligst en tidsbestilling og et nyt tidspunkt.");
            System.out.println("Ny tidsbestilling ikke gemt");

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

