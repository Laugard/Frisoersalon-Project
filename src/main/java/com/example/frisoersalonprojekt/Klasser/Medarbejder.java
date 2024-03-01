package com.example.frisoersalonprojekt.Klasser;

public class Medarbejder {

    private int medarbejderId;
    private String medarbejderFornavn;
    private String medarbejderEfternavn;
    private String medarbejderEmail;
    private String medarbejderTelefon;
    private boolean admin;
    private String brugernavn;
    private String adgangskode;

    public Medarbejder(int medarbejderId, String medarbejderFornavn, String medarbejderEfternavn, String medarbejderEmail, String medarbejderTelefon, boolean admin, String brugernavn, String adgangskode) {
        this.medarbejderId = medarbejderId;
        this.medarbejderFornavn = medarbejderFornavn;
        this.medarbejderEfternavn = medarbejderEfternavn;
        this.medarbejderEmail = medarbejderEmail;
        this.medarbejderTelefon = medarbejderTelefon;
        this.admin = admin;
        this.brugernavn = brugernavn;
        this.adgangskode = adgangskode;
    }

    public int getMedarbejderId() {
        return medarbejderId;
    }

    public void setMedarbejderId(int medarbejderId) {
        this.medarbejderId = medarbejderId;
    }

    public String getMedarbejderFornavn() {
        return medarbejderFornavn;
    }

    public void setMedarbejderFornavn(String medarbejderFornavn) {
        this.medarbejderFornavn = medarbejderFornavn;
    }

    public String getMedarbejderEfternavn() {
        return medarbejderEfternavn;
    }

    public void setMedarbejderEfternavn(String medarbejderEfternavn) {
        this.medarbejderEfternavn = medarbejderEfternavn;
    }

    public String getMedarbejderEmail() {
        return medarbejderEmail;
    }

    public void setMedarbejderEmail(String medarbejderEmail) {
        this.medarbejderEmail = medarbejderEmail;
    }

    public String getMedarbejderTelefon() {
        return medarbejderTelefon;
    }

    public void setMedarbejderTelefon(String medarbejderTelefon) {
        this.medarbejderTelefon = medarbejderTelefon;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getAdgangskode() {
        return adgangskode;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }
}
