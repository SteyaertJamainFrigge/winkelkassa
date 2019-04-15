package FriggeSteyaertJamain.be.winkelKassa.domain.register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductCategory {
    private int id;
    private String name;
    private List<ProductCategory> subCategories;

    public ProductCategory(int id, String name, ProductCategory... subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = new ArrayList<>();
        this.subCategories.addAll(Arrays.asList(subCategories));
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

    @Override
    public String toString() {
        return this.name;
    }

}
