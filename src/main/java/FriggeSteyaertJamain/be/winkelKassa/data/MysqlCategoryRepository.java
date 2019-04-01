package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import com.mysql.cj.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlCategoryRepository implements CategoryRepository {

    private static final String SQL_GET_CATEGORIES = "SELECT * FROM categorie";
    private static final String SQL_GET_CATEGORY = "SELECT * FROM categorie c where c.idcategorie = ?";
    private static final String SQL_ADD_CATEGORY = "insert into categorie(idcategorie, naam) values (?,?)";
    private static final String SQL_UPDATE_CATEGORY = "UPDATE `kassa`.`categorie` SET `naam` =? WHERE (`idcategorie` = ?)";
    private static final String SQL_DELETE_CATEGORY = "DELETE FROM categorie WHERE (idcategorie = ?);";

    private ProductCategory create(ResultSet resultSet) throws SQLException{
        String name = resultSet.getString("naam");
        int id = resultSet.getInt("idcategorie");
        return new ProductCategory(id, name);
    }

    @Override
    public ProductCategory getGategory(int id) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_GET_CATEGORY)) {
            prep.setInt(1, id);
            try(ResultSet rs = prep.executeQuery()){
                if (rs.next()){
                   return create(rs);
                } else {
                    return null;
                }
            }
        }catch (SQLException e) {
            throw new KassaException("Unable to get Category from Db");
        }
    }

    @Override
    public void addCategory(ProductCategory pc) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_CATEGORY)){
            prep.setInt(1, pc.getId());
            prep.setString(2, pc.getName());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("unable do add category", e);
        }
    }

    @Override
    public List<ProductCategory> getCategories() {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_GET_CATEGORIES)) {
            try(ResultSet rs = prep.executeQuery()){
                ArrayList<ProductCategory> productCategories = new ArrayList<>();
                while (rs.next()){
                    productCategories.add(create(rs));
                }
                return productCategories;
            }
        }catch (SQLException e) {
            throw new KassaException("Unable to get Category from Db");
        }
    }

    @Override
    public void updateCategory(ProductCategory pc) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_CATEGORY)){
            prep.setString(1, pc.getName());
            prep.setInt(2, pc.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("Unable to update Category in Bd", e);
        }
    }

    @Override
    public void deleteCategory(ProductCategory pc) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_DELETE_CATEGORY)){
            prep.setInt(1, pc.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("Unable to delete Category in Bd", e);
        }
    }
}
