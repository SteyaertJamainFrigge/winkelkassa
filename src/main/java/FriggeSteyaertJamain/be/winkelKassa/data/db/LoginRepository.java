package FriggeSteyaertJamain.be.winkelKassa.data.db;

import FriggeSteyaertJamain.be.winkelKassa.domain.login.User;

import java.util.List;

public interface LoginRepository {

    boolean addLogin(User login);
    User getLogin(int id);
    List<User> getAllLogins();
    boolean updateLogin(User login);
    boolean deleteLogin(User login);

}
