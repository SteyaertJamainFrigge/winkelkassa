package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    private int id;
    private String name;
    private ProductCategory parent;
    private ArrayList<ProductCategory> subCategories = new ArrayList<>();
    private List<Product> products;

    public ProductCategory(int id, String name, ProductCategory parent, List<Product> products) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.products = products;
    }

    public ProductCategory(int id, String name) {
        this(id, name, null, null);
    }

    public ProductCategory(int id, String name, List<Product> products) {
        this(id, name, null, products);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public ArrayList<ProductCategory> getSubCategories() {
        return subCategories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public void addSubCategory(int id, String name, List<Product> products) {
        ProductCategory child = new ProductCategory(id, name, products);
        child.setParent(this);
        this.subCategories.add(child);
    }

    private void setParent(ProductCategory category) {
        this.parent = category;
    }

    public void addSubCategory(ProductCategory child) {
        child.setParent(this);
        this.subCategories.add(child);
    }

    public void removeSubCategory(ProductCategory productCategory) {
        subCategories.remove(productCategory);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
