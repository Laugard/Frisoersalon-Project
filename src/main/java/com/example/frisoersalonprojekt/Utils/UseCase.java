package com.example.frisoersalonprojekt.Utils;

import com.example.frisoersalonprojekt.Klasser.Medarbejder;
import com.example.frisoersalonprojekt.Klasser.Service;
import com.example.frisoersalonprojekt.Klasser.Tidsbestilling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UseCase {
    private DbSql dbSql;

    public UseCase() {
        try {
            this.dbSql = new DbSql();
        } catch (SQLException e) {
            // Log fejlen, vis en fejlmeddelelse, eller på anden vis håndter exceptionen
            e.printStackTrace();
            // Eventuelt sæt dbSql til null eller initialiser den til en standardtilstand
            this.dbSql = null; // Overvej dette, kun hvis det giver mening i din applikations kontekst
        }
    }


    public boolean opretKunde(String fornavn, String efternavn, String telefon, String email, String brugernavn, String adgangskode) {
        // Kalder dbSql.opretKunde og returnerer resultatet
        boolean success = dbSql.opretKunde(fornavn, efternavn, telefon, email, brugernavn, adgangskode);

        if (success) {
            System.out.println("Kunde oprettet succesfuldt.");
        } else {
            System.out.println("Der opstod en fejl under oprettelsen af kunden.");
        }

        return success;
    }

    public boolean opretService(String serviceNavn, String varighed, int pris) {
        boolean success = dbSql.opretService(serviceNavn, varighed, pris);
        return success;
    }
    public boolean sletService(int serviceId) {
        boolean success = dbSql.sletService(serviceId);
        return success;
    }
    public ObservableList<Service> hentAlleServices() {
        try {
            return FXCollections.observableArrayList(dbSql.hentAlleServices());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public String login(String brugernavn, String adgangskode) {
        String loginResult = dbSql.validateLogin(brugernavn, adgangskode);

        switch (loginResult) {
            case "AdminForside":
                System.out.println("Login success - Admin");
                break;
            case "MedarbejderForside":
                System.out.println("Login success - Medarbejder");
                break;
            case "Forside":
                System.out.println("Login success - Kunde");
                break;
            default:
                System.out.println("Login failed");
                break;
        }
        return loginResult;
    }


    public boolean opretMedarbejder(String fornavn, String efternavn, String telefon, String email, boolean admin, String brugernavn, String adgangskode) {
        // Kalder dbSql.opretMedarbejder og returnerer resultatet
        boolean success = dbSql.opretMedarbejder(fornavn, efternavn, telefon, email, admin, brugernavn, adgangskode);

        // Feedback til brugeren via konsollen (kan ændres til GUI feedback hvis ønsket)
        if (success) {
            System.out.println("Medarbejder oprettet succesfuldt.");

        } else {
            System.out.println("Der opstod en fejl under oprettelsen af medarbejderen.");
        }
        return success;
    }

    public ObservableList<Medarbejder> hentMedarbejdere() {
        return FXCollections.observableArrayList(dbSql.hentMedarbejdere());
    }

    public boolean sletMedarbejder(int medarbejderId) {
        boolean success = dbSql.sletMedarbejder(medarbejderId);
        return success;
    }


    public boolean opretTidsbestilling(int kundeId, int medarbejderId, int serviceId, Timestamp valgtTidspunkt) {
        return dbSql.opretTidsbestilling(kundeId, medarbejderId, serviceId, valgtTidspunkt);
    }

    public ObservableList<Timestamp> hentLedigeTidspunkter(int medarbejderId, LocalDate dato) {
        return FXCollections.observableArrayList(dbSql.hentLedigeTidspunkter(medarbejderId, dato));
    }

    public List<Tidsbestilling> hentAlleTidsbestillinger() throws SQLException {
        return dbSql.hentAlleTidsbestillinger();
    }

    public void opdaterStatusTilAflyst(int tidsbestillingsId) throws SQLException {
        dbSql.opdaterStatusTilAflyst(tidsbestillingsId);
    }

    public void opdaterTidsbestillingTidspunkt(int tidsbestillingsId, Timestamp nytTidspunkt) throws SQLException {
        dbSql.opdaterTidsbestillingTidspunkt(tidsbestillingsId, nytTidspunkt);
    }

    public void opdaterTidsbestillingerTilAfholdt() throws SQLException {
        dbSql.opdaterTidsbestillingerTilAfholdt();
    }

    public void sletGamleTidsbestillinger() throws SQLException {
        dbSql.sletGamleTidsbestillinger();
    }

    public Map<String, Integer> hentMedarbejdereMedId() throws SQLException {
        return dbSql.hentMedarbejdereMedId();
    }
    public ObservableList<String> hentMedarbejdereMedNavne() {
        Map<String, Integer> medarbejderMap = dbSql.hentMedarbejdereMedId();
        return FXCollections.observableArrayList(medarbejderMap.keySet());
    }

    public ObservableList<String> hentServiceNavneMedPriser() {
        return FXCollections.observableArrayList(dbSql.hentServiceNavneMedPriser());
    }

    public int findServiceId(String serviceItem) throws SQLException {
        return dbSql.findServiceId(serviceItem);
    }

    public List<Tidsbestilling> hentTidsbestillingerForMedarbejder() throws SQLException {
        return dbSql.hentTidsbestillingerForMedarbejder();
    }
}
