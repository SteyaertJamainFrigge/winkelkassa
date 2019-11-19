package FriggeSteyaertJamain.be.winkelKassa.data.db.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.db.AfbeeldingRepository;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MysqlAfbeeldingRepository implements AfbeeldingRepository {

    private static final String SQL_GET_IMAGE = "select naam, afbeelding from afbeeldingen where idafbeeldingen=?";
    private static final String SQL_ADD_IMAGE = "insert into afbeeldingen(naam, afbeelding) values(?,?)";
    private static final String SQL_GET_ID_BY_NAME = "select idafbeeldingen from afbeeldingen where naam= ?";

    @Override
    public void addImage(String name, File image) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_ADD_IMAGE)) {
            FileInputStream imageInputStream = new FileInputStream(image);
            prep.setString(1, name);
            prep.setBinaryStream(2, imageInputStream, image.length());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new KassaException("couldn't add image to bd" + ex);
        } catch (FileNotFoundException e) {
            throw new KassaException("couldn't find file");
        }
    }

    @Override
    public Map<String, Image> getImage(int id) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_IMAGE)) {
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                InputStream is = rs.getBinaryStream("afbeelding");
                Image image = new Image(is);
                Map<String, Image> map = new HashMap<>();
                map.put(rs.getString("naam"), image);
                return map;
            }
        } catch (SQLException e) {
            throw new KassaException("couldn't get image from db");
        }
    }

    @Override
    public void updateImage(int id, String name, File image) {

    }

    @Override
    public int getImageIdByName(String name) {
        try(Connection con = MySqlConnection.getConnection();
        PreparedStatement prep = con.prepareStatement(SQL_GET_ID_BY_NAME)){
            prep.setString(1, name);
            try(ResultSet rs = prep.executeQuery()){
                if(rs.next()){
                    return rs.getInt("idafbeeldingen");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
