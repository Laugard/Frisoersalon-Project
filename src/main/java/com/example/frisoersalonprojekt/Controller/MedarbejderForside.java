package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.DbSql;
import com.example.frisoersalonprojekt.Main;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Timestamp;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;


public class MedarbejderForside {

    @FXML
    private Button LogUdBtn;

    @FXML
    private TableView<com.example.frisoersalonprojekt.Klasser.Tidsbestilling> medarbejderTableView;
    @FXML
    private TableColumn<com.example.frisoersalonprojekt.Klasser.Tidsbestilling, String> datoColumn;
    @FXML
    private TableColumn<com.example.frisoersalonprojekt.Klasser.Tidsbestilling, String> tidColumn;
    @FXML
    private TableColumn<com.example.frisoersalonprojekt.Klasser.Tidsbestilling, String> serviceColumn;



    private DbSql dbSql = new DbSql();

    public MedarbejderForside() throws SQLException {
    }


    public void initialize() {
        try {
            DbSql db = new DbSql(); // Husk at håndtere SQLException her eller i konstruktøren
            List<com.example.frisoersalonprojekt.Klasser.Tidsbestilling> tidsbestillinger = db.hentTidsbestillingerForMedarbejder();

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

        } catch (Exception e) {
            e.printStackTrace();
            // Håndter fejl, f.eks. vis en dialogboks til brugeren
        }
    }

    @FXML
    private void LogUd(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Startside.fxml");
        System.out.println("Du er nu logget af");
    }






}






