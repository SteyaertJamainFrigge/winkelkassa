package FriggeSteyaertJamain.be.winkelKassa.data.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.RoleRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.login.Role;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlRoleRepository implements RoleRepository {

    private static final String SQL_ADD_ROLE = "INSERT INTO login_role(idrole, rolename, weight) values (?,?,?)";
    private static final String SQL_GET_ROLE = "SELECT * from login_role where idrole=?";
    private static final String SQL_GET_ALL_ROLES = "SELECT * from login_role";
    private static final String SQL_UPDATE_ROLE = "UPDATE login_role set rolename=?, weight=? where idrole=?";
    private static final String SQL_DELETE_ROLE = "DELETE ";

    @Override
    public boolean addRole(Role r) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_ADD_ROLE)) {
            prep.setInt(1, r.getRoleId());
            prep.setString(2, r.getRoleName());
            prep.setInt(3, r.getWeight());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new KassaException("Unable to add Role to DB");
        }
    }

    @Override
    public Role getRole(int id) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_ROLE)){
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()){
                return create(rs);
            }
        } catch (SQLException e) {
            throw new KassaException("Unable to get Role from DB");
        }
    }

    private Role create(ResultSet rs) throws SQLException{
        int id = rs.getInt("idrole");
        String roleName = rs.getString("rolename");
        int weight = rs.getInt("weight");
        return new Role(id, roleName, weight);
    }

    @Override
    public List<Role> getAllRoles() {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_ALL_ROLES);
             ResultSet rs = prep.executeQuery()){
            List<Role> roles = new ArrayList<>();
            while (rs.next()){
                roles.add(create(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new KassaException("Unable to get Roles from DB");
        }
    }

    @Override
    public boolean updateRole(Role r) {
        try(Connection con = MySqlConnection.getConnection();
        PreparedStatement prep = con.prepareStatement(SQL_UPDATE_ROLE)) {
            prep.setString(1,r.getRoleName());
            prep.setInt(2, r.getWeight());
            prep.setInt(3, r.getRoleId());
            return prep.executeUpdate() >0;
        } catch (SQLException e) {
            throw new KassaException("Unable to update role in DB");
        }
    }

    @Override
    public boolean deleteRole(Role r) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_DELETE_ROLE)){
            prep.setInt(1, r.getRoleId());
            return 0<prep.executeUpdate();
        } catch (SQLException e) {
            throw new KassaException("Unable to delete Role in Dd");
        }
    }
}
