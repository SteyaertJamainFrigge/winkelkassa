package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;

import java.util.List;

public interface SubcategoryRepository {

    /**
     * gets all subcategories linked to the given category.
     *
     * @param spc {ProductCategory} product category
     * @return subcategories {List<ProductCategory>} all connected subcategories
     */
    List<ProductCategory> getSubcategories(ProductCategory spc);

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
