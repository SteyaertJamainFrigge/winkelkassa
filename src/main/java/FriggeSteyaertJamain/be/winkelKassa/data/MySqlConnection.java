package FriggeSteyaertJamain.be.winkelKassa.data;


import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String URL = "jdbc:mysql://localhost/kassa";
    private static final String UID = "kassa_usr";
    private static final String PWD = "kassa_pwd";

    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(URL, UID, PWD);
            return con;
        } catch (SQLException e) {
            throw new KassaException("Cannot connect to DB", e);
        }
    }
}
