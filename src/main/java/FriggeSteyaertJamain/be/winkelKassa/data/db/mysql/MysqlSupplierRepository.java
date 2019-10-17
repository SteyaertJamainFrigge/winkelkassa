package FriggeSteyaertJamain.be.winkelKassa.data.db.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.db.SupplierRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Supplier;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlSupplierRepository implements SupplierRepository {

    private static final String SQL_ADD_SUPPLIER = "INSERT INTO leverancier(idleverancier, bedrijfNaam, adres, email, telNr, contactpersoon) values (?,?,?,?,?,?)";
    private static final String SQL_GET_SUPPLIER = "SELECT * from leverancier where idleverancier=?";
    private static final String SQL_GET_ALL_SUPPLIERS = "SELECT * from login_role";
    private static final String SQL_UPDATE_SUPPLIER = "UPDATE login_role set rolename=?, weight=? where idrole=?";
    private static final String SQL_DELETE_SUPPLIER = "DELETE from login_role where idrole=?";

    @Override
    public boolean addSupplier(Supplier supplier) {
        try(
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_ADD_SUPPLIER)
        ){
            fillPreparedStatement(supplier, prep);
           return prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new KassaException("Unable to add product to db", ex);
        }
    }

    private void fillPreparedStatement(Supplier s, PreparedStatement prep) throws SQLException{
        prep.setInt(1, s.getId());
        prep.setString(2, s.getName());
        prep.setString(3, s.getAddress());
        prep.setString(4, s.getEmail());
        prep.setString(5, s.getTelNr());
        prep.setString(6, s.getContact());
    }

    @Override
    public Supplier getSupplier(int id) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_SUPPLIER)){
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()){
                return create(rs);
            }
        } catch (SQLException e) {
            throw new KassaException("Unable to get Role from DB");
        }
    }

    private Supplier create(ResultSet rs) throws SQLException{
        int id = rs.getInt("idleverancier");
        String name = rs.getString("bedrijfsnaam");
        String addresss = rs.getString("adres");
        String email = rs.getString("email");
        String telNr = rs.getString("telNr");
        String contact = rs.getString("contact");
        return new Supplier(id, name, addresss, email, telNr, contact);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return null;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return false;
    }

    @Override
    public boolean deleteSupplier(Supplier supplier) {
        return false;
    }
}
