package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import javafx.scene.image.Image;

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
    private String imageLocation;
    private String imageName;
    private int imageId;
    private Image image;

    /*public Product(int id, String name, double price, Btw btw, String description, String location, String store, String barcode, Integer category, String imageLocation, String imageName, int imageId, Image image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.btw = btw;
        this.description = description;
        this.location = location;
        this.store = store;
        this.barcode = barcode;
        this.category = category;
        this.imageLocation = imageLocation;
        this.imageName = imageName;
        this.imageId = imageId;
        this.image = image;
    }

    public Product(int id, String name, double price, Btw btw, String description, String location, String store, String barcode, Integer category, String imageLocation) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.btw = btw;
        this.description = description;
        this.location = location;
        this.store = store;
        this.barcode = barcode;
        this.category = category;
        this.imageLocation = imageLocation;
    }

     */

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public double getPrice() {
        return price;
    }

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

    private Product(ProdcutBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.btw = builder.btw;
        this.description = builder.description;
        this.location = builder.location;
        this.store = builder.store;
        this.barcode = builder.barcode;
        this.category = builder.category;
        this.imageLocation = builder.imageLocation;
        this.imageName = builder.imageName;
        this.imageId = builder.imageId;
        this.image = builder.image;
    }

    public static class ProdcutBuilder {
        private int id;
        private String name;
        private double price;
        private Btw btw;
        private String description;
        private String location;
        private String store;
        private String barcode;
        private Integer category;
        private String imageLocation;
        private String imageName;
        private int imageId;
        private Image image;

        public ProdcutBuilder(int id) {
            this.id = id;
        }

        public ProdcutBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProdcutBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProdcutBuilder setBtw(Btw btw) {
            this.btw = btw;
            return this;
        }

        public ProdcutBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProdcutBuilder setLocation(String location){
            this.location = location;
            return this;
        }

        public ProdcutBuilder setStore(String store) {
            this.store = store;
            return this;
        }

        public ProdcutBuilder setBarcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public ProdcutBuilder setCategory(Integer category) {
            this.category = category;
            return this;
        }

        public ProdcutBuilder setImageLocation(String imageLocation) {
            this.imageLocation = imageLocation;
            return this;
        }

        public ProdcutBuilder setImageName(String imageName) {
            this.imageName = imageName;
            return this;
        }

        public ProdcutBuilder setImageId(int imageId) {
            this.imageId = imageId;
            return this;
        }

        public ProdcutBuilder setImage(Image image) {
            this.image = image;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }

    /**
     * generates a unique code that will be used for making the barcode
     * uses EAN standard
     *
     * @return {String} 13 digits between 0 and 9
     */
    public String generateInternalUniqueCode() {
        String prefix = "029";
        String manufacturer = "80156";
        StringBuilder code = new StringBuilder(prefix + manufacturer);

        code.append(getProductCode());

        //checksum
        code.append(getChecksum(code.toString()));

        return code.toString();
    }

    /**
     * add's the id that the product has to the barcode number as a product code
     *
     * @return {String} 4 digits between 0 and 9
     */
    private String getProductCode() {
        StringBuilder product = new StringBuilder();
        if (this.id == 0) {
            this.id = Repositories.getInstance().getProductRepository().getHighestId();
        }
        // add 0's
        product.append("0".repeat(Math.max(0, 4 - Integer.toString(this.id).length() + 1)));
        product.append(this.id);
        return product.toString();
    }

    /**
     * compute modulo 10, where the weights in the checksum calculation alternate 3 and 1
     *
     * @return {String} a single digit between 0 and 9
     */
    private String getChecksum(String code) {
        String[] strArray = code.split("");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        int total = 0;
        for (int i = 0; i < strArray.length; i += 2) {
            total += (intArray[i] * 3) + intArray[i + 1];
        }
        int rest = 10 - (total % 10);
        return Integer.toString(rest);
    }

    public boolean Equals(Product p) {
        return this.id == p.getId();
    }
}
