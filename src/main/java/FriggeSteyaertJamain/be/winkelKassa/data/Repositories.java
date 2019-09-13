package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.data.mysql.*;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Transaction;

import java.util.List;

public class Repositories {
    private static Repositories ourInstance = new Repositories();

    public static Repositories getInstance() {
        return ourInstance;
    }

    private Repositories() {
    }

    public BtwRepository getBtwRepository(){return new MysqlBtwRepository();}

    public CategoryRepository getCategoryRepository(){return new MysqlCategoryRepository();}

    public ClientCardRepository getClientCardRepository(){return new MysqlClientCardRepository();}

    public ClientRepository getClientRepository() {return new MySqlClientRepository();}

    public LoginRepository getLoginRepository(){return new MysqlLoginRepository();}

    public ProductRepository getProductRepository(){return  new MysqlProductRepository();}

    public RoleRepository getRoleRepository(){return new MysqlRoleRepository();}

    public SubcategoryRepository getSubCategoryRepository(){return new MysqlSubcategoryRepository();}

    public SupplierRepository getSupplierRepository(){return new MysqlSupplierRepository();}

    public TransactionRepository getTransactionRepository(){return new MysqlTransactionRepository();}
}
