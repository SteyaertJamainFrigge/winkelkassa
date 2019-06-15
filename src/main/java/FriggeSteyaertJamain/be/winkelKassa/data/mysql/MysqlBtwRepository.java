package FriggeSteyaertJamain.be.winkelKassa.data.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.BtwRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Btw;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlBtwRepository implements BtwRepository {


    private static final String SQL_GET_BTW = "SELECT * FROM btw WHERE idbtw=?";
    private static final String SQL_ADD_BTW = "INSERT INTO btw(tarief) value(?)";
    private static final String SQL_GET_BTWS = "SELECT * FROM btw";
    private static final String SQL_UPDATE_BTW = "UPDATE kassa.btw SET tarief=? WHERE idbtw=?";
    private static final String SQL_DELETE_BTW = "DELETE FROM btw where idbtw=?";

    @Override
    public int getBtw(int id) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_GET_BTW)) {
            prep.setInt(1, id);
            try(ResultSet rs = prep.executeQuery()){
                if (rs.next()){
                    return rs.getInt("tarief");
                } else {
                    return -1;
                }
            }
        }catch (SQLException e) {
            throw new KassaException("Unable to get Btw from Db");
        }
    }

    @Override
    public void addBtw(Btw btw) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_BTW)){
            prep.setInt(1, btw.getTarif());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("unable do add Btw", e);
        }
    }

    @Override
    public List<Btw> getAllBtw() {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_GET_BTWS)) {
            try(ResultSet rs = prep.executeQuery()){
                ArrayList<Btw> productCategories = new ArrayList<>();
                while (rs.next()){
                    productCategories.add(new Btw(rs.getInt("idbtw"), rs.getInt("tarief")));
                }
                return productCategories;
            }
        }catch (SQLException e) {
            throw new KassaException("Unable to get Btw from Db");
        }
    }


    @Override
    public void updateBtw(Btw btw) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_BTW)){
            prep.setInt(1, btw.getTarif());
            prep.setInt(2, btw.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("Unable to update Btw in Bd", e);
        }
    }

    @Override
    public void deleteBtw(int id) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_DELETE_BTW)){
            prep.setInt(1, id);
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("Unable to delete Btw in Bd", e);
        }
    }
}
