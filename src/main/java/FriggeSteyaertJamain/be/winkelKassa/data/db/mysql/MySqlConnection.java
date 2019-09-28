package FriggeSteyaertJamain.be.winkelKassa.data.db.mysql;


import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySqlConnection {

    private static final String URL = "jdbc:mysql://localhost/kassa?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String UID = "kassa_usr";
    private static final String PWD = "Kassa1_pwd*";

    static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, UID, PWD);
        } catch (SQLException e) {
            throw new KassaException("Cannot connect to DB", e);
        }
    }
}
