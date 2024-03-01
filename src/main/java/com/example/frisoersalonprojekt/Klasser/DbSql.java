package com.example.frisoersalonprojekt.Klasser;

import java.sql.*;
import java.util.ArrayList;

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


    public String validateLogin(String brugernavn, String adgangskode) {
        // Tjek først i Medarbejdere tabellen
        String medarbejderSql = "SELECT admin FROM Medarbejdere WHERE brugernavn = ? AND adgangskode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(medarbejderSql)) {
            pstmt.setString(1, brugernavn);
            pstmt.setString(2, adgangskode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // Hvis der findes en medarbejder
                boolean isAdmin = rs.getBoolean("admin");
                if (isAdmin) {
                    return "AdminForside"; // Hvis medarbejderen er admin
                } else {
                    return "MedarbejderForside"; // Hvis medarbejderen ikke er admin
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Hvis ikke fundet i Medarbejdere, tjek i Kunde tabellen
        String kundeSql = "SELECT * FROM kunde WHERE brugernavn = ? AND adgangskode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(kundeSql)) {
            pstmt.setString(1, brugernavn);
            pstmt.setString(2, adgangskode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // Hvis der findes en kunde
                return "Forside"; // Henvise til kundeforsiden
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "LoginFejl"; // Hvis ingen bruger er fundet, eller der opstår en fejl
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













/*

    public Kunde soegKunde(int kundeId) {
        Kunde kunde = new Kunde();
        kunde.setKundeId(kundeId);
        try {
            String sql = "select * from Kunde where kundeId =" + String.valueOf(kundeId);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                kunde.setKundeId(rs.getInt("kundeId"));
                kunde.setKundeFornavn(rs.getString("kundeFornavn"));
                kunde.setKundeEfternavn(rs.getString("kundeEfternavn"));
                kunde.setKundeEmail(rs.getString("kundeEmail"));
                kunde.setKundeTelefon(rs.getInt("KundeTelefon"));

                return kunde;
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }






    public ArrayList udskrivAlleTidsbestilling() {
        ArrayList<tidspunkt> tidspunktList = new ArrayList<>();
        try {
            String sql = "select * from tidspunkt";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tidspunkt l = new tidspunkt();
                l.setkundeId(rs.getInt(" kunde Id"));
                l.setserviceId(rs.getInt("service Id"));
                TidsbestillingList.add(l);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return TidsbestillingList;

    }
*/
}