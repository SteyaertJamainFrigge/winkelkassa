package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.data.db.SubcategoryRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import org.junit.*;

import java.util.ArrayList;

public class MysqlSubcategoryRepositoryTest {

    private static ProductCategory pc1;
    private static ProductCategory pc2;

    @BeforeClass
    public static void setUp(){
        pc1 = Repositories.getInstance().getCategoryRepository().getGategory(1);
        pc2 = Repositories.getInstance().getCategoryRepository().getGategory(2);
    }
    
    @Test
    public void hasSubcategories() {
        System.out.println(pc1.getId());
        Assert.assertTrue(Repositories.getInstance().getSubCategoryRepository().hasSubcategories(pc1.getId()));
    }

    @Test
    public void hasNoSubcategories(){
        Assert.assertFalse(Repositories.getInstance().getSubCategoryRepository().hasSubcategories(pc2.getId()));
    }


    @Test
    public void getSubcategories() {
        Assert.assertTrue(Repositories.getInstance().getSubCategoryRepository().getSubcategories(pc1.getId()).size() > 0 );
    }

    @Test
    public void addSubcategory() {
        SubcategoryRepository repo = Repositories.getInstance().getSubCategoryRepository();
        ArrayList baseList = (ArrayList) repo.getSubcategories(pc1.getId());
        repo.addSubcategory(pc1.getId(), 4);
        ArrayList changedList = (ArrayList) repo.getSubcategories(pc1.getId());

        Assert.assertTrue(baseList.size() < changedList.size());
    }

    @Test
    public void deleteSubcategory() {
        SubcategoryRepository repo = Repositories.getInstance().getSubCategoryRepository();
        ArrayList baseList = (ArrayList) repo.getSubcategories(pc1.getId());
        repo.deleteSubcategory(pc1.getId(), 4);
        ArrayList changedList = (ArrayList) repo.getSubcategories(pc1.getId());

        Assert.assertTrue(baseList.size() > changedList.size());
    }
}