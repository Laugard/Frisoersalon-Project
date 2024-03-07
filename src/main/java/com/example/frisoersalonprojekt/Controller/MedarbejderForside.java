package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
import com.example.frisoersalonprojekt.Klasser.Tidsbestilling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyStringWrapper;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MedarbejderForside {

    @FXML
    private Button LogUdBtn;
    @FXML
    private TableView<Tidsbestilling> medarbejderTableView;
    @FXML
    private TableColumn<Tidsbestilling, String> datoColumn;
    @FXML
    private TableColumn<Tidsbestilling, String> tidColumn;
    @FXML
    private TableColumn<Tidsbestilling, String> serviceColumn;

    private UseCase useCase;

    public MedarbejderForside() {
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
    private void LogUd(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
        System.out.println("Du er nu logget af");
    }
}
