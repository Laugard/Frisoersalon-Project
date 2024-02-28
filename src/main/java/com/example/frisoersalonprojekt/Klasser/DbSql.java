package com.example.frisoersalonprojekt.Klasser;

import java.sql.*;

public class DbSql {
    Connection connection;

    DbSql() throws SQLException {
        connection = null;
        Statement stmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/frisoersalon";
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretKunde(Kunde kunde) throws Exception {
        try {
            String sql = "INSERT INTO studerende (kundeId, kundeFornavn,kundeEfternavn, kundeTelefon, kundeEmail) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, kunde.getKundeId());
            stmt.setString(2, kunde.getKundeFornavn());
            stmt.setString(3, kunde.getKundeEfternavn());
            stmt.setInt(4, kunde.getKundeTelefon());
            stmt.setString(5, kunde.getKundeEmail());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }



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




    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/frisoersalon");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returnerer true, hvis brugeren findes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }




}
