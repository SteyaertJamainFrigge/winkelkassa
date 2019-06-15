package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import org.junit.*;

import static org.junit.Assert.*;

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

}