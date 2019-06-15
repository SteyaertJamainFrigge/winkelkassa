package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.data.mysql.*;

public class Repositories {
    private static Repositories ourInstance = new Repositories();

    public static Repositories getInstance() {
        return ourInstance;
    }

    private Repositories() {
    }

    public ClientRepository getClientRepository() {return new MySqlClientRepository();}

    public ClientCardRepository getClientCardRepository(){return new MysqlClientCardRepository();}

    public ProductRepository getProductRepository(){return  new MysqlProductRepository();}

    public CategoryRepository getCategoryRepository(){return new MysqlCategoryRepository();}

    public BtwRepository getBtwRepository(){return new MysqlBtwRepository();}

    public SubcategoryRepository getSubCategoryRepository(){return new MysqlSubcategoryRepository();}
}
