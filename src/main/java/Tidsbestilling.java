public class Tidsbestilling {

private int tidsbestilling;
private int dato;
private int tidspunkt;
private boolean status;
private boolean anuller;

    public int getTidsbestilling() {
        return tidsbestilling;
    }

    public void setTidsbestilling(int tidsbestilling) {
        this.tidsbestilling = tidsbestilling;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(int tidspunkt) {
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

    public Tidsbestilling(int tidsbestilling, int dato, int tidspunkt, boolean status, boolean anuller) {
        this.tidsbestilling = tidsbestilling;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.status = status;
        this.anuller = anuller;
    }

    public void setAnuller(boolean anuller) {
        this.anuller = anuller;

    }
}
