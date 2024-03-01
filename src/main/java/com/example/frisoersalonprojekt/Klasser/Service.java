package com.example.frisoersalonprojekt.Klasser;

public class Service {

    private int serviceId;
    private String serviceNavn;
    private int varighed;


    public Service() {
    }

    public Service(int serviceId, String serviceNavn, int varighed) {
        this.serviceId = serviceId;
        this.serviceNavn = serviceNavn;
        this.varighed = varighed;
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

    public int getVarighed() {
        return varighed;
    }

    public void setVarighed(int varighed) {
        this.varighed = varighed;
    }
}
