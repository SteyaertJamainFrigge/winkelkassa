package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import java.util.*;

public class Transaction {

    private int id;
    private Date date;
    private String store;
    private Personnel personnel;
    private List<Product> products;
    private Map<Integer, Integer> productsFrequency;
    private float totalPrice;
    private String note;
    private Client client;

    public Transaction(int id, Date date, String store, Personnel personnel, Client client) {
        this.id = id;
        this.date = date;
        this.store = store;
        this.personnel = personnel;
        this.products = new ArrayList<>();
        this.totalPrice = 0;
        this.note = "";
        this.client = client;
        productsFrequency = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<Integer, Integer> getProductsFrequency() {
        return productsFrequency;
    }

    public void setProductsFrequency(Map<Integer, Integer> productsFrequency) {
        this.productsFrequency = productsFrequency;
    }
}
