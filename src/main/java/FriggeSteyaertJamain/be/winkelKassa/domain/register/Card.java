package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Card {
    private int id;
    private int points;
    private String barcode;
    private Client owner;

    public Card(int id, int points, String barcode, Client owner) {
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

    public String  getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
