package FriggeSteyaertJamain.be.winkelKassa.data.db;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;

import java.util.List;

public interface CategoryRepository {

    /**
     * returns the category that has the given id.
     *
     * @param id {String} id
     * @return {Category}  category
     */
    ProductCategory getGategory(int id);

    /**
     * adds a new category to the data layer.
     *
     * @param pc {Category} product category
     */
    void addCategory(ProductCategory pc);

    /**
     * returns all categories found in data layer as a List
     *
     * @return {List<Category>} list of categories
     */
    List<ProductCategory> getAllCategories();


    /**
     * returns a list of categories that don't have a parent category
     *
     * @return @return {List<Category>} list of categories
     */
    List<ProductCategory> getbaseCategories();

    /**
     * updates the given category in the datalayer.
     *
     * @param pc {Category} product category
     */
    void updateCategory(ProductCategory pc);


    /**
     * deletes the given category in the data layer
     *
     * @param pc {Category} product category
     */
    void deleteCategory(ProductCategory pc);


    int getCategoryByName(String name);
}
