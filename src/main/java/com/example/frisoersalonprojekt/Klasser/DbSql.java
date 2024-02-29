package com.example.frisoersalonprojekt.Klasser;

import java.sql.*;

public class DbSql {
    public Connection connection;
    private PreparedStatement Pstmt;
    private Statement stmt;




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








    public void OpretBruger() {
        Connection connection = DbSql.getConnection();  // Hent forbindelsen fra MysqlConnection

        String brugerNavn = OpretBrugerBrugerNavnTF.getText();
        System.out.println("Checking if user exists: " + brugerNavn);

        // Check om brugeren allerede existerer
        if (getBruger(brugerNavn, connection) != null) {
            System.out.println("User already exists: " + brugerNavn);
            BrugerFindesLabel.setVisible(true);
            OpretBrugerBrugerNavnTF.setText("");
            OpretBrugerAdgangskodeTF.setText("");
            return; // Afslut metode hvis bruger allerede existerer
        }

        try {
            System.out.println("Creating user: " + brugerNavn);
            String sql = "insert into wentzelevent_dk_db_GaveListe.Bruger (BrugerNavn,Password) values (?,?)";

            try (PreparedStatement Pstmt = connection.prepareStatement(sql)) {
                Pstmt.setString(1, brugerNavn);
                Pstmt.setString(2, OpretBrugerAdgangskodeTF.getText());
                Pstmt.execute();
            }

            System.out.println("User created: " + brugerNavn);
            BrugerOprettetLabel.setVisible(true);
            BrugerFindesLabel.setVisible(false);
            OpretBrugerBrugerNavnTF.setText("");
            OpretBrugerAdgangskodeTF.setText("");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DbSql.closeConnection();
    }

    public String getBruger(String brugerNavn, Connection connection) {
        System.out.println("GetBruger");
        String dbBrugernavn = null;

        try {
            String sqlBruger = "SELECT * FROM wentzelevent_dk_db_GaveListe.Bruger WHERE BrugerNavn = ?";

            try (PreparedStatement stmtBruger = connection.prepareStatement(sqlBruger)) {
                stmtBruger.setString(1, brugerNavn);
                try (ResultSet rsBruger = stmtBruger.executeQuery()) {
                    if (rsBruger.next()) {
                        dbBrugernavn = rsBruger.getString("BrugerNavn");
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbBrugernavn;
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
