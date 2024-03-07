package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.Tidsbestilling;
import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AdminForside {

    @FXML
    private Button OpretMedarbejderBtn;
    @FXML
    private Button OpretTidsbestillingBtn;
    @FXML
    private Button OpretKundeBtn;
    @FXML
    private Button OpretServiceBtn;
    @FXML
    private TableView<Tidsbestilling> medarbejderTableView;
    @FXML
    private TableColumn<Tidsbestilling, String> datoColumn;
    @FXML
    private TableColumn<Tidsbestilling, String> tidColumn;
    @FXML
    private TableColumn<Tidsbestilling, String> serviceColumn;
    @FXML
    private Button LogUdBtn;

    private UseCase useCase;

    public AdminForside() {
        this.useCase = new UseCase();
    }

    @FXML
    public void initialize() throws SQLException {
        loadTidsbestillinger();
    }

    private void loadTidsbestillinger() throws SQLException {
        List<Tidsbestilling> tidsbestillinger = useCase.hentTidsbestillingerForMedarbejder();

        datoColumn.setCellValueFactory(cellData -> {
            Timestamp ts = cellData.getValue().getTidspunkt();
            return new ReadOnlyStringWrapper(new java.text.SimpleDateFormat("dd-MM-yyyy").format(ts));
        });

        tidColumn.setCellValueFactory(cellData -> {
            Timestamp ts = cellData.getValue().getTidspunkt();
            return new ReadOnlyStringWrapper(new java.text.SimpleDateFormat("HH:mm").format(ts));
        });

        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("serviceNavn"));

        medarbejderTableView.getItems().setAll(tidsbestillinger);
    }
    @FXML
    private void SkiftTilOpretMedarbejder(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretMedarbejder.fxml");
    }
    @FXML
    private void SkiftTilOpretKunde(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretKunde.fxml");
    }
    @FXML
    private void SkiftTilSletMedarbejder(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("SletMedarbejder.fxml");
    }
    @FXML
    private void SkiftTilOpretService(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OpretService.fxml");
    }
    @FXML
    private void LogUd(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
        System.out.println("Du er nu logget af");
    }







}
