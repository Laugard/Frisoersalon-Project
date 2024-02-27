public class Bruger {

private int brugerId;
private String brugerNavn;
private String brugerKode;
private int brugerTelefon;
private String brugerEmail;

    public int getBrugerId() {
        return brugerId;
    }

    public void setBrugerId(int brugerId) {
        this.brugerId = brugerId;
    }

    public String getBrugerNavn() {
        return brugerNavn;
    }

    public void setBrugerNavn(String brugerNavn) {
        this.brugerNavn = brugerNavn;
    }

    public String getBrugerKode() {
        return brugerKode;
    }

    public void setBrugerKode(String brugerKode) {
        this.brugerKode = brugerKode;
    }

    public int getBrugerTelefon() {
        return brugerTelefon;
    }

    public void setBrugerTelefon(int brugerTelefon) {
        this.brugerTelefon = brugerTelefon;
    }

    public String getBrugerEmail() {
        return brugerEmail;
    }

    public Bruger(int brugerId, String brugerNavn, String brugerKode, int brugerTelefon, String brugerEmail) {
        this.brugerId = brugerId;
        this.brugerNavn = brugerNavn;
        this.brugerKode = brugerKode;
        this.brugerTelefon = brugerTelefon;
        this.brugerEmail = brugerEmail;
    }

    public void setBrugerEmail(String brugerEmail) {
        this.brugerEmail = brugerEmail;

    }
}
