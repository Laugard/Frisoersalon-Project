package com.example.frisoersalonprojekt.Klasser;

public class Tidsbestilling {

    private int id;
    private int medarbejderId;
    private int kundeId;
    private int serviceId;
    private String tidspunkt;
    private boolean status;
    private boolean anuller;

    public Tidsbestilling() {
    }

    public Tidsbestilling(int id, int medarbejderId, int kundeId, int serviceId, String tidspunkt, boolean status, boolean anuller) {
        this.id = id;
        this.medarbejderId = medarbejderId;
        this.kundeId = kundeId;
        this.serviceId = serviceId;
        this.tidspunkt = tidspunkt;
        this.status = status;
        this.anuller = anuller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedarbejderId() {
        return medarbejderId;
    }

    public void setMedarbejderId(int medarbejderId) {
        this.medarbejderId = medarbejderId;
    }

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(String tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAnuller() {
        return anuller;
    }

    public void setAnuller(boolean anuller) {
        this.anuller = anuller;
    }
}
