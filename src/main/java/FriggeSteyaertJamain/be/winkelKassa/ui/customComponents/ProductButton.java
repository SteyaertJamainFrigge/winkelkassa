package FriggeSteyaertJamain.be.winkelKassa.ui.customComponents;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import javafx.scene.control.Button;

public class ProductButton extends Button {

    private Product product;

    public ProductButton(Product product) {
        super(product.getName());
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
