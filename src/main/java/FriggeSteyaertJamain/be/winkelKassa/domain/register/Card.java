package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Card {
    private int id;
    private int points;
    private int barcode;
    private Client owner;

    public Card(int id, int points, int barcode, Client owner) {
        this.id = id;
        this.points = points;
        this.barcode = barcode;
        this.owner = owner;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public int getPoints() {
        return points;
    }

    public void setPunten(int points) {
        this.points = points;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
