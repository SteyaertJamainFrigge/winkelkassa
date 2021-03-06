package FriggeSteyaertJamain.be.winkelKassa.data.db.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.db.SubcategoryRepository;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlSubcategoryRepository implements SubcategoryRepository {

    private static final String SQL_GET_SUBCATEGORYID = "SELECT child FROM categorie_subcategorie WHERE parent=?";
    private static final String SQL_GET_All_SUBCATEGORYID = "SELECT child FROM categorie_subcategorie WHERE parent=?";
    private static final String SQL_ADD_SUBCATEGORY = "INSERT into categorie_subcategorie(parent, child) values(?,?)";
    private static final String SQL_DELETE_SUBCATEGORY = "delete from categorie_subcategorie where parent=? AND child= ?";

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
            ArrayList<Integer> subCategoryIds = new ArrayList<>();
            try (ResultSet rs = prep.executeQuery()){
                while (rs.next()){
                    subCategoryIds.add(rs.getInt("child"));
                }
            }
            return subCategoryIds;
        } catch (SQLException e) {
            throw new KassaException("failed to get subcategories");
        }
    }

    @Override
    public void addSubcategory(int parentCategoryId, int subcategoryID) {
        try(Connection con = MySqlConnection.getConnection();
        PreparedStatement prep = con.prepareStatement(SQL_ADD_SUBCATEGORY)){
            prep.setInt(1, parentCategoryId);
            prep.setInt(2, subcategoryID);
            prep.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubcategory(int parentCategoryId, int subcategoryID) {
        try(Connection con = MySqlConnection.getConnection();
        PreparedStatement prep = con.prepareStatement(SQL_DELETE_SUBCATEGORY)){
            prep.setInt(1, parentCategoryId);
            prep.setInt(2, subcategoryID);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
