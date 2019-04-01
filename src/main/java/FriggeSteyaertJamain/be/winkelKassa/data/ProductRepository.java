package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;

import java.util.List;

public interface ProductRepository {

    /**
     * adds a new product to the data layer.
     * @param product{Product} product to add.
     */
    void addProduct(Product product);

    /**
     * search the data layer for a product with a specific id and return the product
     * @param id {String} id
     * @return {Product} product
     */
    Product getProduct(int id);

    /**
     * returns all the products it can find in the datalayer
     * @return {List<Product>}  A list of products.
     */
    List<Product> getAllProducts();

    /**
     *  searches for a product with a specific id in the datalayer and compares that product to the given product.
     * @param product {Product} product to update.
     */
    void updateProduct(Product product);

    /**
     * searches for a product in the datalayer that correlates with the given product and deletes it.
     * @param product{Prodcut} product to delete.
     */
    void deleteProduct(Product product);
}
