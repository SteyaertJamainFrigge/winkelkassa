package FriggeSteyaertJamain.be.winkelKassa.data.db;

import FriggeSteyaertJamain.be.winkelKassa.domain.login.Role;
import java.util.List;

public interface RoleRepository {

    /**
     *
     * @param login the User that is going to be added
     * @return returns true if adding the login was successful
     */
    boolean addRole(Role login);
    Role getRole(int id);
    List<Role> getAllRoles();
    boolean updateRole(Role login);
    boolean deleteRole(Role login);
}
