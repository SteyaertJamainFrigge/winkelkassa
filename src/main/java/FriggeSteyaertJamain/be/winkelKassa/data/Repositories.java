package FriggeSteyaertJamain.be.winkelKassa.data;

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
}
