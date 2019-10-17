package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.data.db.CategoryRepository;
import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MysqlCategoryRepositoryTest {

    private static CategoryRepository repo;

    @BeforeClass
    public static void setUp() {
        repo = Repositories.getInstance().getCategoryRepository();
    }

    @Test
    public void getGategory() {
        ProductCategory category = repo.getGategory(1);
        assertTrue("the category is correctly requested", category.getId() > 0);
    }

    @Test
    public void addCategory() {
        // some preperation.
        List<ProductCategory> categories = repo.getAllCategories();
        ProductCategory category = new ProductCategory(5, "addTester");

        // the actual test
        repo.addCategory(category);
        List<ProductCategory> editedCategories = repo.getAllCategories();
        assertTrue("category is added?", categories.size() < editedCategories.size());

        // revert the added row in database
        repo.deleteCategory(category);
    }

    @Test
    public void getCategories() {
        List<ProductCategory> categories = repo.getAllCategories();
        assertTrue("all categories are requested?", categories.size() > 0);
    }

    @Test
    public void updateCategory() {
        // some preparation
        ProductCategory categoryToEdit = new ProductCategory(1, "i edited it");

        // the actual test
        repo.updateCategory(categoryToEdit);
        ProductCategory editedCategory = repo.getGategory(1);
        assertEquals("name has been edited?", categoryToEdit.getName(), editedCategory.getName());

        // revert update
        repo.updateCategory(new ProductCategory(1, "testcategorie"));
    }

    @Test
    public void deleteCategory() {
        ProductCategory categoryToDelete = repo.getGategory(4);
        repo.deleteCategory(categoryToDelete);

        assertNull("the category has been deleted?",repo.getGategory(4));

        repo.addCategory(categoryToDelete);
    }

    @Test
    public void hasParentCategory(){
        repo.getbaseCategories();
    }
}