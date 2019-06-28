package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Btw {
    private int id;
    private int tariff;

    public Btw(int id, int tariff) {
        this.id = id;
        this.tariff = tariff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return this.tariff + " %";
    }
}
