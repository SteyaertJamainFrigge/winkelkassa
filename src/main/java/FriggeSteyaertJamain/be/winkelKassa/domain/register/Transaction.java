package FriggeSteyaertJamain.be.winkelKassa.domain.register;


import javax.swing.text.Style;
import java.time.Instant;
import java.util.*;

public class Transaction {

    private int id;
    private String store;
    private Personnel personnel;
    private List<Product> products;
    private Map<Integer, Integer> productsFrequency;
    private String note;
    private Client client;
    private double totalPrice;
    private Date timestamp;

    public Transaction(int id, String store, Personnel personnel, List<Product> products, Map<Integer, Integer> productsFrequency, String note, Client client, double totalPrice) {
        this.id = id;
        this.timestamp = new Date();
        this.store = store;
        this.personnel = personnel;
        this.products = products;
        this.productsFrequency = productsFrequency;
        this.note = note;
        this.client = client;
        this.totalPrice = totalPrice;
    }

    public Transaction(int id, Date date, String store, Personnel personnel, Client client){
        this(id, store, personnel, null, null, "", client, 0);
    }

    public int getId() {
        return id;
    }

    public String getStore() {
        return store;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Map<Integer, Integer> getProductsFrequency() {
        return productsFrequency;
    }

    public String getNote() {
        return note;
    }

    public Client getClient() {
        return client;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void addProduct(Product p){
        if(!hasProduct(p)) {
            products.add(p);
        }
        updateProductFrequency(p.getId(), "add");
    }

    public void removeProduct(Product p){
        if(getProductsFrequency().get(p.getId()) == 1){
            products.remove(p);
        }
        updateProductFrequency(p.getId(), "remove");
    }

    public void removeAllProducts(){
        products.clear();
        productsFrequency.clear();
    }

    private void updateProductFrequency(int productId, String action){
        if(action.equals("add")){
            productsFrequency.computeIfPresent(productId, (k, v) -> v + 1);
            productsFrequency.putIfAbsent(productId, 1);
        }
        if(action.equals("remove")){
            int amount = productsFrequency.get(productId);
            if(amount == 1){
                productsFrequency.remove(productId);
            }else{
                productsFrequency.put(productId, amount -1);
            }
        }
    }
    private boolean hasProduct(Product p){
        for (Product product:
             products) {
            if(product.getId() == p.getId()){
                return true;
            }
        }
        return false;
    }
}
