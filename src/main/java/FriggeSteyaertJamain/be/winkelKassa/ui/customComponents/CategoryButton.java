package FriggeSteyaertJamain.be.winkelKassa.ui.customComponents;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import javafx.scene.control.Button;

import java.util.List;

public class CategoryButton extends Button {

    private ProductCategory category;

    public CategoryButton(ProductCategory category) {
        super(category.getName());
        this.category = category;
    }

    public List<ProductCategory> getSubCategories(){
        return category.getSubCategories();
    }

    public List<Product> getProducts(){
        return category.getProducts();
    }
}
