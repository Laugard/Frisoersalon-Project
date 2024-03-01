package com.example.frisoersalonprojekt.Klasser;

public class Medarbejder {

    private int medarbejderId;
    private String medarbejderNavn;
    private String medarbejderKode;
    private String medarbejderEmail;
    private int medarbejderTelefon;
    private int medarbejderStilling;

    public Medarbejder() {

    }

    public int getMedarbejderId() {
        return medarbejderId;
    }

    public void setMedarbejderId(int medarbejderId) {
        this.medarbejderId = medarbejderId;
    }

    public String getMedarbejder() {
        return medarbejderNavn;
    }

    public void setMedarbejder(String medarbejder) {
        this.medarbejderNavn = medarbejder;
    }

    public String getMedarbejderKode() {
        return medarbejderKode;
    }

    public void setMedarbejderKode(String medarbejderKode) {
        this.medarbejderKode = medarbejderKode;
    }

    public String getMedarbejderEmail() {
        return medarbejderEmail;
    }

    public void setMedarbejderEmail(String medarbejderEmail) {
        this.medarbejderEmail = medarbejderEmail;
    }

    public int getMedarbejderTelefon() {
        return medarbejderTelefon;
    }

    public void setMedarbejderTelefon(int medarbejderTelefon) {
        this.medarbejderTelefon = medarbejderTelefon;
    }

    public int getMedarbejderStilling() {
        return medarbejderStilling;
    }

    public Medarbejder(int medarbejderId, String medarbejder, String medarbejderKode, String medarbejderEmail, int medarbejderTelefon, int medarbejderStilling) {
        this.medarbejderId = medarbejderId;
        this.medarbejderNavn = medarbejder;
        this.medarbejderKode = medarbejderKode;
        this.medarbejderEmail = medarbejderEmail;
        this.medarbejderTelefon = medarbejderTelefon;
        this.medarbejderStilling = medarbejderStilling;
    }

    public void setMedarbejderStilling(int medarbejderStilling) {
        this.medarbejderStilling = medarbejderStilling;

    }
}
