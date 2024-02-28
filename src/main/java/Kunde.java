public class Kunde{

    private int kundeId;
    private String kundeFornavn;
    private String kundeEfternavn;
    private int kundeTelefon;
    private String kundeEmail;

hallo
    public Kunde() {
    }

    public Kunde(int kundeId, String kundeFornavn, String kundeEfternavn, int kundeTelefon, String kundeEmail) {
        this.kundeId = kundeId;
        this.kundeFornavn = kundeFornavn;
        this.kundeEfternavn = kundeEfternavn;
        this.kundeTelefon = kundeTelefon;
        this.kundeEmail = kundeEmail;
    }

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    public String getKundeFornavn() {
        return kundeFornavn;
    }

    public void setKundeFornavn(String kundeFornavn) {
        this.kundeFornavn = kundeFornavn;
    }

    public String getKundeEfternavn() {
        return kundeEfternavn;
    }

    public void setKundeEfternavn(String kundeEfternavn) {
        this.kundeEfternavn = kundeEfternavn;
    }

    public int getKundeTelefon() {
        return kundeTelefon;
    }

    public void setKundeTelefon(int kundeTelefon) {
        this.kundeTelefon = kundeTelefon;
    }

    public String getKundeEmail() {
        return kundeEmail;
    }

    public void setKundeEmail(String kundeEmail) {
        this.kundeEmail = kundeEmail;
    }
}



