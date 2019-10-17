package FriggeSteyaertJamain.be.winkelKassa.data.db.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.db.ClientRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlClientRepository implements ClientRepository {

    private static final String SQL_ADD_CLIENT = "insert into klant(idklant, voornaam, famillienaam, email, telNummer, adres, postNummer) " +
            "values(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_CLIENTS = "select * from klant";
    private static final String SQL_GET_CLIENT = "select * from klant " +
            "where idklant=?";
    private static final String SQL_UPDATE_CLIENT = "UPDATE klant " +
            "SET voornaam=?, famillienaam=?, email=?, telNummer=?, adres=?, postNummer=? " +
            "WHERE idklant=?";
    private static final String SQL_DELETE_CLIENT = "DELETE FROM klant " +
            "WHERE idklant=?;";

    @Override
    public boolean addClient(Client c) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_ADD_CLIENT)
        ) {
            prep.setInt(1, c.getId());
            prep.setString(2, c.getName());
            prep.setString(3, c.getFamilyName());
            prep.setString(4, c.getEmail());
            prep.setString(5, c.getTelNumber());
            prep.setString(6, c.getAddress());
            prep.setString(7, c.getPostAddress());
            return prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new KassaException("Unable to add client to DB.", ex);
        }
    }

    @Override
    public List<Client> getAllClients() {

        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement prep = con.prepareStatement(SQL_GET_CLIENTS);
             ResultSet rs = prep.executeQuery()) {
            List<Client> clients = new ArrayList<>();

            while (rs.next()) {
                clients.add(createClient(rs));
            }
            return clients;

        } catch (SQLException ex) {
            throw new KassaException("Unable to get clients from DB.", ex);
        }

    }

    private Client createClient(ResultSet rs) throws SQLException {
        int id = rs.getInt("idklant");
        String name = rs.getString("voornaam");
        String familyName = rs.getString("famillienaam");
        String email = rs.getString("email");
        String telNumber = rs.getString("telNummer");
        String address = rs.getString("adres");
        String postNumber = rs.getString("postNummer");
        return new Client(id, name, familyName, email, telNumber, address, postNumber);
    }

    @Override
    public Client getClient(int id) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_CLIENT)
        ) {
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()) {
                    return createClient(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new KassaException("Unable to get clients from DB.", ex);
        }
    }

    @Override
    public boolean updateClient(Client c) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_UPDATE_CLIENT)
        ) {
            prep.setString(1, c.getName());
            prep.setString(2, c.getFamilyName());
            prep.setString(3, c.getEmail());
            prep.setString(4, c.getTelNumber());
            prep.setString(5, c.getAddress());
            prep.setString(6, c.getPostAddress());
            prep.setInt(7, c.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new KassaException("Unable to update client in DB.", ex);
        }
    }


    @Override
    public boolean deleteClient(Client c) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_DELETE_CLIENT)
        ) {
            prep.setInt(1, c.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new KassaException("Unable to delete client from DB.", ex);
        }
    }
}
