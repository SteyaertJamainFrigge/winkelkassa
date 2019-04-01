package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MysqlCategoryRepositoryTest {


    @Test
    public void getGategory() {
        ProductCategory category = Repositories.getInstance().getCategoryRepository().getGategory(1);
        assertTrue("the category is correctly requested", category.getId() > 0);
    }

    @Test
    public void addCategory() {
        // some preperation.
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getCategories();
        ProductCategory category = new ProductCategory(4, "addTester");

        // the actual test
        Repositories.getInstance().getCategoryRepository().addCategory(category);
        List<ProductCategory> editedCategories = Repositories.getInstance().getCategoryRepository().getCategories();
        assertTrue("category is added?", categories.size() < editedCategories.size());

        // revert the added row in database
        Repositories.getInstance().getCategoryRepository().deleteCategory(category);
    }

    @Test
    public void getCategories() {
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getCategories();
        assertTrue("all categories are requested?", categories.size() > 0);
    }

    @Test
    public void updateCategory() {
        // some preparation
        ProductCategory categoryToEdit = new ProductCategory(1, "i edited it");

        // the actual test
        Repositories.getInstance().getCategoryRepository().updateCategory(categoryToEdit);
        ProductCategory editedCategory = Repositories.getInstance().getCategoryRepository().getGategory(1);
        assertEquals("name has been edited?", categoryToEdit.getName(), editedCategory.getName());

        // revert update
        Repositories.getInstance().getCategoryRepository().updateCategory(new ProductCategory(1, "testcategorie"));
    }

    @Test
    public void deleteCategory() {
        ProductCategory categoryToDelete = Repositories.getInstance().getCategoryRepository().getGategory(1);
        Repositories.getInstance().getCategoryRepository().deleteCategory(categoryToDelete);

        assertNull("the category has been deleted?",Repositories.getInstance().getCategoryRepository().getGategory(1));

        Repositories.getInstance().getCategoryRepository().addCategory(categoryToDelete);
    }
}