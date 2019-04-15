package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Btw {
    private int id;
    private int tarif;

    public Btw(int id, int tarif) {
        this.id = id;
        this.tarif = tarif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return this.tarif + " %";
    }
}
