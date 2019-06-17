package FriggeSteyaertJamain.be.winkelKassa.data.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.LoginRepository;
import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.login.Role;
import FriggeSteyaertJamain.be.winkelKassa.domain.login.User;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlLoginRepository implements LoginRepository {

    private static final String SQL_ADD_LOGIN = "INSERT INTO login(idlogin, username, password, idrole) values (?,?,?,3)";
    private static final String SQL_GET_LOGIN = "SELECT * from login where idlogin=?";
    private static final String SQL_GET_ALL_LOGIN = "SELECT * from login";
    private static final String SQL_UPDATE_LOGIN = "UPDATE login set username=?, password=?, idrole=? where idlogin=?";
    private static final String SQL_DELETE_LOGIN = "DELETE from login where idlogin=?";

    @Override
    public boolean addLogin(User l) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_ADD_LOGIN)) {
            prep.setInt(1, l.getLoginId());
            prep.setString(2, l.getUsername());
            prep.setString(3, l.getHashedPassword());
            prep.setInt(4, l.getRole().getRoleId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new KassaException("Unable to add Login to DB");
        }
    }

    @Override
    public User getLogin(int id) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_LOGIN)){
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()){
                return create(rs);
            }
        } catch (SQLException e) {
            throw new KassaException("Unable to get Role from DB");
        }
    }

    private User create(ResultSet rs) throws SQLException{
        int id = rs.getInt("idlogin");
        String username = rs.getString("username");
        String password = rs.getString("password");
        Role role = Repositories.getInstance().getRoleRepository().getRole(rs.getInt("idrole"));
        return new User(id, username, password, role);
    }

    @Override
    public List<User> getAllLogins() {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_ALL_LOGIN);
             ResultSet rs = prep.executeQuery()){
            List<User> users = new ArrayList<>();
            while (rs.next()){
                users.add(create(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new KassaException("Unable to get Logins from DB");
        }
    }

    @Override
    public boolean updateLogin(User l) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_LOGIN)) {
            prep.setString(1, l.getUsername());
            prep.setString(2, l.getHashedPassword());
            prep.setInt(3, l.getRole().getRoleId());
            prep.setInt(4, l.getLoginId());
            return prep.executeUpdate() >0;
        } catch (SQLException e) {
            throw new KassaException("Unable to update Login in DB");
        }
    }

    @Override
    public boolean deleteLogin(User l) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_DELETE_LOGIN)){
            prep.setInt(1, l.getLoginId());
            return 0<prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("Unable to delete Role in Dd");
        }
    }
}
