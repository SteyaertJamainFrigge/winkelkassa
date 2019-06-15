package FriggeSteyaertJamain.be.winkelKassa.data.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.SubcategoryRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlSubcategoryRepository implements SubcategoryRepository {

    private static final String SQL_GET_SUBCATEGORYID = "SELECT idsubcategorie FROM categorie_subcategorie WHERE idcategorie=?";
    private static final String SQL_GET_All_SUBCATEGORYID = "";
    private static final String SQL_ADD_SUBCATEGORY = "";
    private static final String SQL_UPDATE_SUBCATEGORY = "";
    private static final String SQL_DELETE_SUBCATEGORY = "";

    private ProductCategory create(ResultSet rs){
        return null;
    }

    @Override
    public Boolean hasSubcategories(int id) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_SUBCATEGORYID)) {
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new KassaException("failed to get Id's from database");
        }
    }

    @Override
    public List<Integer> getSubcategories(int id) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_All_SUBCATEGORYID)){
            prep.setInt(1, id);
            ArrayList<ProductCategory> subCategories = new ArrayList<>();
            try (ResultSet rs = prep.executeQuery()){
                while (rs.next()){
                    subCategories.add(create(rs));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new KassaException("failed to get subcategories");
        }
    }

    @Override
    public void addSubcategory(ProductCategory spc) {

    }

    @Override
    public void updateSubcategory(ProductCategory spc) {

    }

    @Override
    public void deleteSubcategory(ProductCategory spc) {

    }
}
