public class Kunde {

    private int kundeId;
    private String kundeNavn;
    private String kundeKode;
    private int kundeTelefon;
    private String kundeEmail;

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    public String getKundeNavn() {
        return kundeNavn;
    }

    public void setKundeNavn(String kundeNavn) {
        this.kundeNavn = kundeNavn;
    }

    public String getKundeKode() {
        return kundeKode;
    }

    public void setKundeKode(String kundeKode) {
        this.kundeKode = kundeKode;
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

    public Kunde(int kundeId, String kundeNavn, String kundeKode, int kundeTelefon, String kundeEmail) {
        this.kundeId = kundeId;
        this.kundeNavn = kundeNavn;
        this.kundeKode = kundeKode;
        this.kundeTelefon = kundeTelefon;
        this.kundeEmail = kundeEmail;

    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kundeId=" + kundeId +
                ", kundeNavn='" + kundeNavn + '\'' +
                ", kundeKode='" + kundeKode + '\'' +
                ", kundeTelefon=" + kundeTelefon +
                ", kundeEmail='" + kundeEmail + '\'' +
                '}';
    }

    public Kunde() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
