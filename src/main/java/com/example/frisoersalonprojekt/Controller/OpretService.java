package com.example.frisoersalonprojekt.Controller;

import com.example.frisoersalonprojekt.Klasser.Service;
import com.example.frisoersalonprojekt.Main;
import com.example.frisoersalonprojekt.Utils.UseCase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

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
    private Button tilbageTilForsideBtn;
    @FXML
    private TableView<Service> ServiceTabel;
    @FXML
    private TableColumn<Service, String> serviceColumn;
    @FXML
    private TableColumn<Service, Integer> varighedColumn;
    @FXML
    private TableColumn<Service, Integer> prisColumn;
    private UseCase useCase;

    public OpretService() {
        try {
            this.useCase = new UseCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        int pris = Integer.parseInt(prisTF.getText()); // Husk at tilføje fejlhåndtering for parsing
        boolean success = useCase.opretService(serviceNavnTF.getText(), varighedTF.getText(), pris);

        if (success) {
            opdaterServiceTabel();
            serviceNavnTF.clear();
            varighedTF.clear();
            prisTF.clear();
            System.out.println("Service oprettet");
        } else {
            System.out.println("Fejl under opretning af service");
        }
    }

    private void opdaterServiceTabel() {
        ObservableList<Service> serviceListe = useCase.hentAlleServices();
        ServiceTabel.setItems(serviceListe);
    }

    @FXML
    private void handleSletServiceAction() {
        Service selected = ServiceTabel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean success = useCase.sletService(selected.getServiceId());
            if (success) {
                opdaterServiceTabel();
                System.out.println("Service slettet");
            } else {
                System.out.println("Fejl under sletning af service");
            }
        }
    }

    @FXML
    private void tilbageTilForside(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("AdminForside.fxml");
    }

}
