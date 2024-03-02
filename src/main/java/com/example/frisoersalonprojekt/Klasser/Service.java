package com.example.frisoersalonprojekt.Klasser;

public class Service {

    private int serviceId;
    private String serviceNavn;
    private String varighed;
    private int pris;

    public Service() {
    }

    public Service(int serviceId, String serviceNavn, String varighed, int pris) {
        this.serviceId = serviceId;
        this.serviceNavn = serviceNavn;
        this.varighed = varighed;
        this.pris = pris;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceNavn() {
        return serviceNavn;
    }

    public void setServiceNavn(String serviceNavn) {
        this.serviceNavn = serviceNavn;
    }

    public String getVarighed() {
        return varighed;
    }

    public void setVarighed(String varighed) {
        this.varighed = varighed;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}
