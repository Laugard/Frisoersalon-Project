package com.example.frisoersalonprojekt.Utils;

import com.example.frisoersalonprojekt.Klasser.Medarbejder;
import com.example.frisoersalonprojekt.Klasser.Service;
import com.example.frisoersalonprojekt.Klasser.Tidsbestilling;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UseCase {
    private DbSql dbSql;

    public UseCase() throws SQLException {
        this.dbSql = new DbSql();
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

    public boolean opretService(String serviceNavn, String varighed, int pris) throws SQLException {
        return dbSql.opretService(serviceNavn, varighed, pris);
    }

    public ArrayList<Service> hentAlleServices() throws SQLException {
        return dbSql.hentAlleServices();
    }

    public boolean sletService(int serviceId) throws SQLException {
        return dbSql.sletService(serviceId);
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


    public boolean opretMedarbejder(String fornavn, String efternavn, String telefon, String email, boolean admin, String brugernavn, String adgangskode) throws SQLException {
        return dbSql.opretMedarbejder(fornavn, efternavn, telefon, email, admin, brugernavn, adgangskode);
    }

    public ArrayList<Medarbejder> hentMedarbejdere() throws SQLException {
        return dbSql.hentMedarbejdere();
    }

    public boolean sletMedarbejder(int medarbejderId) throws SQLException {
        return dbSql.sletMedarbejder(medarbejderId);
    }

    public boolean opretTidsbestilling(int kundeId, int medarbejderId, int serviceId, Timestamp tidspunkt) throws SQLException {
        return dbSql.opretTidsbestilling(kundeId, medarbejderId, serviceId, tidspunkt);
    }

    public List<Timestamp> hentLedigeTidspunkter(int medarbejderId, LocalDate dato) throws SQLException {
        return dbSql.hentLedigeTidspunkter(medarbejderId, dato);
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

    public List<String> hentServiceNavneMedPriser() throws SQLException {
        return dbSql.hentServiceNavneMedPriser();
    }

    public int findServiceId(String serviceItem) throws SQLException {
        return dbSql.findServiceId(serviceItem);
    }

    public List<Tidsbestilling> hentTidsbestillingerForMedarbejder() throws SQLException {
        return dbSql.hentTidsbestillingerForMedarbejder();
    }

    // Yderligere metoder kan tilføjes her, baseret på dine behov
}
