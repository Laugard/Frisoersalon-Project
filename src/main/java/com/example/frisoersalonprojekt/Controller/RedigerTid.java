package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Klasser.Tidsbestilling;
import com.example.frisoersalonprojekt.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;




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


    private DbSql dbSql = new DbSql();

    public RedigerTid() throws SQLException {
    }

    public void initialize() {
        datoPicker.setOnAction(event -> opdaterTilgængeligeTidspunkter());
        bindTableColumns(); // Sørg for at kalde denne metode for at binde tabelkolonner
        loadTidsbestillinger(); // Dette sikrer, at data indlæses ved opstart
    }



    private void opdaterTilgængeligeTidspunkter() {
        if (datoPicker.getValue() != null) {
            LocalDate valgtDato = datoPicker.getValue();
            // Antagelse: medarbejderId er fastsat. Erstat med faktisk logik for at hente valgt medarbejderId.
            int medarbejderId = 1; // Eksempelværdi, erstattes med faktisk logik

            List<Timestamp> ledigeTidspunkter = dbSql.hentLedigeTidspunkter(medarbejderId, valgtDato);
            tidspunktComboBox.setItems(FXCollections.observableArrayList(ledigeTidspunkter));
            if (!ledigeTidspunkter.isEmpty()) {
                tidspunktComboBox.getSelectionModel().selectFirst();
            }
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




    @FXML
    private void aflysTidsbestilling() {
        Tidsbestilling valgtTidsbestilling = serviceTabel.getSelectionModel().getSelectedItem();
        if (valgtTidsbestilling != null) {
            // Opret en bekræftelsesdialog
            Alert bekræftelsesDialog = new Alert(Alert.AlertType.CONFIRMATION);
            bekræftelsesDialog.setTitle("Bekræft Aflysning");
            bekræftelsesDialog.setHeaderText("Aflys tidsbestilling");
            bekræftelsesDialog.setContentText("Er du sikker på, at du vil aflyse den valgte tidsbestilling?");

            // Venter på brugerens respons
            Optional<ButtonType> resultat = bekræftelsesDialog.showAndWait();
            if (resultat.isPresent() && resultat.get() == ButtonType.OK) {
                // Brugeren bekræftede, at de ønsker at aflyse tidsbestillingen
                dbSql.opdaterStatusTilAflyst(valgtTidsbestilling.getTidsbestillingsId());
                valgtTidsbestilling.setStatus("Aflyst");
                serviceTabel.refresh(); // Opdaterer tabellen for at vise den nye status
            }
        } else {
            // Viser en advarsel, hvis ingen tidsbestilling er valgt
            Alert advarselsDialog = new Alert(Alert.AlertType.WARNING);
            advarselsDialog.setTitle("Ingen Tidsbestilling Valgt");
            advarselsDialog.setHeaderText(null);
            advarselsDialog.setContentText("Vælg venligst en tidsbestilling at aflyse.");
            advarselsDialog.showAndWait();
        }
    }


    private void loadTidsbestillinger() {
        // Antager en metode i DbSql klassen der returnerer en liste af Tidsbestilling objekter
        List<Tidsbestilling> tidsbestillinger = dbSql.hentAlleTidsbestillinger(); // Denne metode skal implementeres i DbSql
        serviceTabel.setItems(FXCollections.observableArrayList(tidsbestillinger));
    }


    @FXML
    private void gemTidsbestillingÆndringer() {
        Tidsbestilling valgtTidsbestilling = serviceTabel.getSelectionModel().getSelectedItem();
        if (valgtTidsbestilling != null && datoPicker.getValue() != null && tidspunktComboBox.getValue() != null) {
            Timestamp nyTidspunkt = tidspunktComboBox.getValue();

            dbSql.opdaterTidsbestillingTidspunkt(valgtTidsbestilling.getTidsbestillingsId(), nyTidspunkt);

            valgtTidsbestilling.setTidspunkt(nyTidspunkt);
            serviceTabel.refresh();
        } else {
            // Vis en advarsel hvis noget gik galt
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ingen Tidsbestilling Valgt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst en tidsbestilling og et nyt tidspunkt.");
            alert.showAndWait();
        }
    }


    @FXML
    private void tilbageTilForside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Forside.fxml");

    }


}

