package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Product {
    private int id;
    private String name;
    private double price;
    private Btw btw;
    private String description;
    private String location;
    private String store;
    private String barcode;
    private ProductCategory category;

    public Product(int id, String name, double price, Btw btw, String description, String location, String store, String barcode, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.btw = btw;
        this.description = description;
        this.location = location;
        this.store = store;
        this.barcode = barcode;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public Btw getBtw() {
        return btw;
    }

    public void setBtw(Btw btw) {
        this.btw = btw;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
