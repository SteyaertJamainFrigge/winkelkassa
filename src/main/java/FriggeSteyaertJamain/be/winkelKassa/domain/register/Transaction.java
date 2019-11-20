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
                productsFrequency.computeIfPresent(productId, (k, v) -> v -1);
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

    private Transaction(TransactionBuilder builder){
        this.id = builder.id;
        this.timestamp = builder.timestamp;
        this.store = builder.store;
        this.personnel = builder.personnel;
        this.products = builder.products;
        this.productsFrequency = builder.productsFrequency;
        this.note = builder.note;
        this.client = builder.client;
        this.totalPrice = builder.totalPrice;
    }

    public static class TransactionBuilder{
        private int id;
        private String store;
        private Personnel personnel;
        private List<Product> products;
        private Map<Integer, Integer> productsFrequency;
        private String note;
        private Client client;
        private double totalPrice;
        private Date timestamp;

        public TransactionBuilder setId(int id) {
            this.id = id;
            this.timestamp = new Date();
            return this;
        }

        public TransactionBuilder setStore(String store) {
            this.store = store;
            return this;
        }

        public TransactionBuilder setPersonnel(Personnel personnel) {
            this.personnel = personnel;
            return this;
        }

        public TransactionBuilder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public TransactionBuilder setProductsFrequency(Map<Integer, Integer> productsFrequency) {
            this.productsFrequency = productsFrequency;
            return this;
        }

        public TransactionBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public TransactionBuilder setClient(Client client) {
            this.client = client;
            return this;
        }

        public TransactionBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public TransactionBuilder setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Transaction build(){
            return new Transaction(this);
        }
    }
}
