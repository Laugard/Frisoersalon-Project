package com.example.frisoersalonprojekt.Klasser;

public class Service {

    private int id;
    private String serviceNavn;
    private int varighed;


    public Service() {
    }

    public Service(int id, String serviceNavn, int varighed) {
        this.id = id;
        this.serviceNavn = serviceNavn;
        this.varighed = varighed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceNavn() {
        return serviceNavn;
    }

    public void setServiceNavn(String serviceNavn) {
        this.serviceNavn = serviceNavn;
    }

    public int getVarighed() {
        return varighed;
    }

    public void setVarighed(int varighed) {
        this.varighed = varighed;
    }



}
