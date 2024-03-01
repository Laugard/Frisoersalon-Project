package com.example.frisoersalonprojekt.Klasser;

public class Admin extends Medarbejder {

    private int adminId;


    public Admin() {
        super();

    }

    public Admin(int medarbejderId, String medarbejder, String medarbejderKode, String medarbejderEmail, int medarbejderTelefon, int medarbejderStilling) {
        super(medarbejderId, medarbejder, medarbejderKode, medarbejderEmail, medarbejderTelefon, medarbejderStilling);
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


}
