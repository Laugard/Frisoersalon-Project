public class Medarbejder {

    private int MedarbejderId;
    private String medarbejderNavn;
    private String medarbejderKode;
    private String medarbejderEmail;
    private int medarbejderTelefon;
    private int medarbejderStilling;

    public int getMedarbejderId() {
        return MedarbejderId;
    }

    public void setMedarbejderId(int medarbejderId) {
        MedarbejderId = medarbejderId;
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
        MedarbejderId = medarbejderId;
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
