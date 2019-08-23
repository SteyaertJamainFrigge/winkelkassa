package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import org.omg.PortableInterceptor.INACTIVE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private Btw btw;
    private String description;
    private String location;
    private String store;
    private String barcode;
    private Integer category;

    public Product(int id, String name, double price, Btw btw, String description, String location, String store, String barcode, Integer category) {
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

    public Product(int id, String name, double price, Btw btw, String description, String location, String store, Integer category) {
        this(id, name, price, btw, description, location, store, null, category);
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
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


    /**
     *  generates a unique code that will be used for making the barcode
     *  uses EAN standard
     *
     * @return {String} 13 digits between 0 and 9
     */
    public String generateInternalUniqueCode(){
        String prefix = "029";
        String manufacturer = "80156";
        StringBuilder code = new StringBuilder(prefix + manufacturer);

        code.append(getProductCode());

        //checksum
        code.append(getChecksum(code.toString()));

        return code.toString();
    }

    /**
     *  add's the id that the product has to the barcode number as a product code
     *
     * @return {String} 4 digits between 0 and 9
     */
    private String getProductCode(){
        StringBuilder product = new StringBuilder();
        if(this.id == 0){
            this.id = Repositories.getInstance().getProductRepository().getHighestId();
        }
        // add 0's
        for(int i = Integer.toString(this.id).length() ; i <= 4; i++){
            product.append('0');
        }
        product.append(this.id);
        return product.toString();
    }

    /**
     * compute modulo 10, where the weights in the checksum calculation alternate 3 and 1
     *
     * @return {String} a single digit between 0 and 9
     */
    private String getChecksum(String code){
        String[] strArray = code.split("");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        int total = 0;
        for(int i = 0; i < strArray.length; i+=2) {
            total += (intArray[i] * 3) + intArray[i+1];
        }
        int rest = 10 - (total % 10);
        return Integer.toString(rest);
    }

    public boolean Equals(Product p){
        return this.id == p.getId();
    }
}
