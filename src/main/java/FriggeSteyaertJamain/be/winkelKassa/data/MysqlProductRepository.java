package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Btw;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlProductRepository implements ProductRepository {

    private static final String SQL_ADD_PRODUCT =   "insert into product(naam, prijs, btw, omschrijving, locatie, winkel, barcode, idcategorie) " +
                                                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_PRODUCT =   "SELECT p.*, b.tarief FROM product p where idproduct=? LEFT JOIN btw b on p.btw = b.idbtw";
    private static final String SQL_GET_PRODUCTS =  "select p.*, b.tarief from product p LEFT JOIN btw b on p.btw = b.idbtw";
    private static final String SQL_DELETE_PRODUCT= "delete from product p where p.idproduct = ?";
    private static final String SQL_UPDATE_PRODUCT ="UPDATE product" +
                                                    "set naam=?, prijs=?, btw=?, omschrijving=?, locatie=?, winkel=?, barcode=?, idcategorie=?" +
                                                    "where idproduct=?";

    @Override
    public void addProduct(Product product) {
        try(
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_ADD_PRODUCT)
                ){
            fillPreparedStatement(product, prep);
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new KassaException("Unable to add product to db", ex);
        }
    }

    private void fillPreparedStatement(Product p, PreparedStatement prep) throws SQLException {
        prep.setString(1, p.getName());
        prep.setDouble(2, p.getPrice());
        prep.setInt(3, p.getBtw().getId());
        prep.setString(4, p.getDescription());
        prep.setString(5, p.getLocation());
        prep.setString(6, p.getStore());
        prep.setString(7, p.getBarcode());
        prep.setInt(8, p.getCategory().getId());
    }

    private Product createProduct(ResultSet rs) throws SQLException {
        int id = rs.getInt("idproduct");
        String name = rs.getString("naam");
        double price = rs.getDouble("prijs");
        int btwId = rs.getInt("Btw");
        String description = rs.getString("omschrijving");
        String location = rs.getString("locatie");
        String store = rs.getString("winkel");
        String barcode = rs.getString("barcode");
        int categoryId = rs.getInt("idcategorie");
        int tarif = rs.getInt("tarief");

        ProductCategory category =  Repositories.getInstance().getCategoryRepository().getGategory(categoryId);
        return new Product(id, name, price, new Btw(btwId, tarif), description, location, store, barcode, category);
    }

    @Override
    public Product getProduct(int id) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_PRODUCT)
                ){
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()){
                if(rs.next()){
                    return createProduct(rs);
                } else {
                    return null;
                }
            }
        }catch (SQLException ex){
            throw new KassaException("Unable to get product with id: "+ id, ex);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_PRODUCTS)
                ){
            try(ResultSet rs = prep.executeQuery()){
                List<Product> products = new ArrayList<>();
                while (rs.next()){
                    products.add(createProduct(rs));
                }
                return products;
            }
        } catch (SQLException ex) {
            throw new KassaException("Unable to get products from DB");
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_UPDATE_PRODUCT)
        ){
            fillPreparedStatement(product, prep);
            prep.setInt(9, product.getId());
            prep.executeUpdate();
        } catch(SQLException ex){
            throw new KassaException("Unable to update product in DB.", ex);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_DELETE_PRODUCT)
        )
        {
            prep.setInt(1, product.getId());
            prep.executeUpdate();
        } catch(SQLException ex){
            throw new KassaException("Unable to delete product from DB.", ex);
        }
    }
}
