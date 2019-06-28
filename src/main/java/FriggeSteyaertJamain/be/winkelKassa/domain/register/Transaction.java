package FriggeSteyaertJamain.be.winkelKassa.domain.register;


import java.util.*;

public class Transaction {

    private int id;
    private Date date;
    private String store;
    private Personnel personnel;
    private List<Product> products;
    private Map<Integer, Integer> productsFrequency;
    private String note;
    private Client client;

    public Transaction(int id, Date date, String store, Personnel personnel, Client client) {
        this.id = id;
        this.date = date;
        this.store = store;
        this.personnel = personnel;
        this.products = new ArrayList<>();
        this.note = "";
        this.client = client;
        productsFrequency = new HashMap<>();
    }

    public Transaction (int id, String store, Personnel personnel, Client client){
        this(id, new Date(), store, personnel, client);
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

    public String getStore() {
        return store;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public double getTotalPrice(){
        double totalPrice = 0.0;
        for (Product p:
             products) {
            int amount = productsFrequency.get(p.getId());
            double btw = p.getPrice() / 100 * p.getBtw().getTariff();
            totalPrice = totalPrice + (amount + btw) * amount;
        }
        return totalPrice;
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
