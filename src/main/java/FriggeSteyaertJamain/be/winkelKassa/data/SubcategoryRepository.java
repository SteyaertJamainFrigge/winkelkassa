package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;

import java.util.List;

public interface SubcategoryRepository {


    /**
     * test if a category has any subcategories by asking for their id.
     *
     * @param id [Integer}  product category id
     * @return subcategoryIds {List<Integer>} all id's connected to given category
     */
    Boolean hasSubcategories(int id);

    /**
     * gets all subcategories linked to the given category.
     *
     * @param id {ProductCategory} product category
     * @return subcategories {List<ProductCategory>} all connected subcategories
     */
    List<Integer> getSubcategories(int id);

    /**
     * adds a new category to the data layer.
     *
     * @param spc {ProductCategory} product category
     */
    void addSubcategory(ProductCategory spc);

    /**
     * updates the given category in the datalayer.
     *
     * @param spc{ProductCategory} product category
     */
    void updateSubcategory(ProductCategory spc);


    /**
     * deletes the given category in the data layer
     *
     * @param spc{ProductCategory} product category
     */
    void deleteSubcategory(ProductCategory spc);
}
