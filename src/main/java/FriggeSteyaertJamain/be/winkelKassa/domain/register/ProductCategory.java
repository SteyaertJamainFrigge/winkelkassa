package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class ProductCategory {
    private int id;
    private String name;
    private List<ProductCategory> subCategories;
    private List<Product> products;

    public ProductCategory(int id, String name,List<Product> products, List<ProductCategory> subCategories) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.subCategories = subCategories;
    }
    public ProductCategory(int id, String name){
        this(id, name, null, null);
    }

    public ProductCategory(int id, String name, List<Product> products){
        this(id, name, products, null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductCategory> getSubCategories() {
         return subCategories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addSubCategory(ProductCategory productCategory){
        subCategories.add(productCategory);
    }

    public void removeSubCategory(ProductCategory productCategory){
        subCategories.remove(productCategory);
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
