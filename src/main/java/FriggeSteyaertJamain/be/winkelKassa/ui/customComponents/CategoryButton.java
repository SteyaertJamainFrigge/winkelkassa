package FriggeSteyaertJamain.be.winkelKassa.ui.customComponents;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CategoryButton extends Button{

    private ProductCategory category;

    public CategoryButton(){
        super();
        this.category = null;
        this.setDisable(true);
    }

    public CategoryButton(@NotNull ProductCategory category) {
        super(category.getName());
        this.category = category;
    }

    public List<ProductCategory> getSubCategories(){
        return this.category.getSubCategories();
    }

    public List<Product> getProducts(){
        return category.getProducts();
    }

    public ProductCategory getCategory() {
        return this.category;
    }
    public String getName(){
        return this.category.getName();
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
        this.setDisable(false);
    }
}
