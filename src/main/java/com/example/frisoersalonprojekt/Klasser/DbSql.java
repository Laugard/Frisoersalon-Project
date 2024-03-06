package com.example.frisoersalonprojekt.Klasser;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DbSql {
    public Connection connection;
    private PreparedStatement Pstmt;
    private Statement stmt;


    public DbSql() throws SQLException {
        connection = null;
        Statement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/frisoersalon";
            connection = DriverManager.getConnection(url,"root","");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void opretKunde(String fornavn, String efternavn, String telefon, String email, String brugernavn, String adgangskode) {
        String sql = "INSERT INTO Kunde (kundeFornavn, kundeEfternavn, kundeTelefon, kundeEmail, brugernavn, adgangskode) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fornavn);
            pstmt.setString(2, efternavn);
            pstmt.setString(3, telefon);
            pstmt.setString(4, email);
            pstmt.setString(5, brugernavn);
            pstmt.setString(6, adgangskode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean opretService(String serviceNavn, String varighed, int pris) {
        String sql = "INSERT INTO Service (serviceNavn, varighed, pris) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, serviceNavn);
            pstmt.setString(2, varighed);
            pstmt.setInt(3, pris);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Service> hentAlleServices() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM Service";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Service service = new Service(
                        rs.getInt("serviceId"),
                        rs.getString("serviceNavn"),
                        rs.getString("varighed"),
                        rs.getInt("pris"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public boolean sletService(int serviceId) {
        String sql = "DELETE FROM Service WHERE serviceId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, serviceId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public String validateLogin(String brugernavn, String adgangskode) {
        // Tjek først i Medarbejdere tabellen
        String medarbejderSql = "SELECT medarbejderId, admin FROM Medarbejdere WHERE brugernavn = ? AND adgangskode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(medarbejderSql)) {
            pstmt.setString(1, brugernavn);
            pstmt.setString(2, adgangskode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // Hvis der findes en medarbejder
                boolean isAdmin = rs.getBoolean("admin");
                int medarbejderId = rs.getInt("medarbejderId");
                SessionManager.setLoggedInUser(medarbejderId, "medarbejder");
                return isAdmin ? "AdminForside" : "MedarbejderForside";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Hvis ikke fundet i Medarbejdere, tjek i Kunde tabellen
        String kundeSql = "SELECT kundeId FROM Kunde WHERE brugernavn = ? AND adgangskode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(kundeSql)) {
            pstmt.setString(1, brugernavn);
            pstmt.setString(2, adgangskode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // Hvis der findes en kunde
                int kundeId = rs.getInt("kundeId");
                SessionManager.setLoggedInUser(kundeId, "kunde");
                return "Forside";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SessionManager.resetLoggedInUser();
        return "LoginFejl";
    }


    public boolean opretMedarbejder(String fornavn, String efternavn, String telefon, String email, boolean admin, String brugernavn, String adgangskode) {
        String sql = "INSERT INTO Medarbejdere (medarbejderFornavn, medarbejderEfternavn, medarbejderTelefon, medarbejderEmail, admin, brugernavn, adgangskode) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fornavn);
            pstmt.setString(2, efternavn);
            pstmt.setString(3, telefon);
            pstmt.setString(4, email);
            pstmt.setBoolean(5, admin);
            pstmt.setString(6, brugernavn);
            pstmt.setString(7, adgangskode);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Returnerer true, hvis en eller flere rækker er påvirket
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returnerer false, hvis der opstår en fejl
        }
    }



    public ArrayList<Medarbejder> hentMedarbejdere() {
        ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
        String sql = "SELECT * FROM Medarbejdere";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medarbejder medarbejder = new Medarbejder(
                        rs.getInt("medarbejderId"),
                        rs.getString("medarbejderFornavn"),
                        rs.getString("medarbejderEfternavn"),
                        rs.getString("medarbejderEmail"),
                        rs.getString("medarbejderTelefon"),
                        rs.getBoolean("admin"),
                        rs.getString("brugernavn"),
                        rs.getString("adgangskode"));
                medarbejdere.add(medarbejder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medarbejdere;
    }




    public boolean sletMedarbejder(int medarbejderId) {
        String sql = "DELETE FROM Medarbejdere WHERE medarbejderId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, medarbejderId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean erTidOptaget(int medarbejderId, Timestamp tidspunkt) {
        String sql = "SELECT COUNT(*) FROM Tidsbestillinger WHERE medarbejderId = ? AND tidspunkt = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, medarbejderId);
            pstmt.setTimestamp(2, tidspunkt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean opretTidsbestilling(int kundeId, int medarbejderId, int serviceId, Timestamp tidspunkt) {
        if (!erTidOptaget(medarbejderId, tidspunkt)) {
            String sql = "INSERT INTO Tidsbestillinger (medarbejderId, kundeId, serviceId, tidspunkt, status) VALUES (?, ?, ?, ?, 'PLANLAGT')";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, medarbejderId);
                pstmt.setInt(2, kundeId);
                pstmt.setInt(3, serviceId);
                pstmt.setTimestamp(4, tidspunkt);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Tidsrummet er allerede optaget.");
        }
        return false;
    }



    public Map<String, Integer> hentMedarbejdereMedId() {
        Map<String, Integer> medarbejderMap = new HashMap<>();
        String sql = "SELECT medarbejderId, medarbejderFornavn, medarbejderEfternavn FROM Medarbejdere";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String navn = rs.getString("medarbejderFornavn") + " " + rs.getString("medarbejderEfternavn");
                Integer id = rs.getInt("medarbejderId");
                medarbejderMap.put(navn, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medarbejderMap;
    }



    public List<String> hentServiceNavneMedPriser() {
        List<String> services = new ArrayList<>();
        String sql = "SELECT serviceNavn, Pris FROM Service";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String serviceNavn = rs.getString("serviceNavn");
                double Pris = rs.getDouble("Pris");
                services.add(serviceNavn + " - Pris: " + Pris + " kr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }


    public int findServiceId(String serviceItem) {
        // Split serviceItem strengen for at ekstrahere servicenavnet
        String[] parts = serviceItem.split(" - Pris: ");
        String serviceName = parts[0]; // Det faktiske servicenavn er den første del

        // Her er din eksisterende SQL-logik, nu anvendt med det ekstraherede servicenavn
        String sql = "SELECT serviceId FROM Service WHERE serviceNavn = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, serviceName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("serviceId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Returner -1 hvis servicen ikke findes
    }




    public List<Timestamp> hentLedigeTidspunkter(int medarbejderId, LocalDate dato) {
        List<Timestamp> ledigeTidspunkter = new ArrayList<>();
        // Antagelse: Åbningstider fra 9:00 til 17:00, booking hver halve time
        LocalDateTime startTid = dato.atTime(9, 0);
        LocalDateTime slutTid = dato.atTime(17, 0);
        while (startTid.isBefore(slutTid)) {
            Timestamp tidspunkt = Timestamp.valueOf(startTid);
            if (!erTidOptaget(medarbejderId, tidspunkt)) {
                ledigeTidspunkter.add(tidspunkt);
            }
            startTid = startTid.plusMinutes(30); // Gå til næste tidsinterval
        }
        return ledigeTidspunkter;
    }


    public List<Tidsbestilling> hentAlleTidsbestillinger() {
        List<Tidsbestilling> tidsbestillinger = new ArrayList<>();
        int loggetIndKundeId = SessionManager.getLoggedInKundeId();

        // Tjek om en kunde er logget ind
        if (loggetIndKundeId == -1) {
            // Ingen kunde er logget ind, måske returner en tom liste eller håndter det på en anden måde
            return tidsbestillinger;
        }

        // Opdateret SQL-forespørgsel til kun at hente tidsbestillinger for den logget ind kunde
        String sql = "SELECT T.tidsbestillingId, T.medarbejderId, T.kundeId, T.serviceId, T.tidspunkt, T.status, S.serviceNavn, S.pris " +
                "FROM Tidsbestillinger T " +
                "INNER JOIN Service S ON T.serviceId = S.serviceId " +
                "WHERE T.kundeId = ?"; // Bruger parameterbinding for at undgå SQL-injektion

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, loggetIndKundeId); // Sætter den logget ind kundeId som parameter til forespørgslen

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int tidsbestillingsId = rs.getInt("tidsbestillingId");
                    int medarbejderId = rs.getInt("medarbejderId");
                    int kundeId = rs.getInt("kundeId");
                    int serviceId = rs.getInt("serviceId");
                    Timestamp tidspunkt = rs.getTimestamp("tidspunkt");
                    String status = rs.getString("status");
                    String serviceNavn = rs.getString("serviceNavn");
                    int pris = rs.getInt("pris");

                    // Opret et Tidsbestilling objekt med hentede data
                    Tidsbestilling tidsbestilling = new Tidsbestilling(tidsbestillingsId, medarbejderId, kundeId, serviceId, tidspunkt, status, serviceNavn, pris);

                    tidsbestillinger.add(tidsbestilling);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tidsbestillinger;
    }



    public void opdaterStatusTilAflyst(int tidsbestillingsId) {
        String sql = "UPDATE Tidsbestillinger SET status = 'Aflyst' WHERE tidsbestillingId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tidsbestillingsId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void opdaterTidsbestillingTidspunkt(int tidsbestillingsId, Timestamp nytTidspunkt) {
        String sql = "UPDATE Tidsbestillinger SET tidspunkt = ? WHERE tidsbestillingId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, nytTidspunkt);
            pstmt.setInt(2, tidsbestillingsId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void opdaterTidsbestillingerTilAfholdt() {
        String sql = "UPDATE Tidsbestillinger SET status = 'Afholdt' WHERE tidspunkt < NOW() AND status = 'PLANLAGT'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " tidsbestillinger blev opdateret til 'Afholdt'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sletGamleTidsbestillinger() {
        // SQL-kommando til at slette tidsbestillinger ældre end 5 år
        String sql = "DELETE FROM Tidsbestillinger WHERE tidspunkt < DATE_SUB(NOW(), INTERVAL 5 YEAR)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " gamle tidsbestillinger blev slettet.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}