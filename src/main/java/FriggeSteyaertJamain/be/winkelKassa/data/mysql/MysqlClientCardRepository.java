package FriggeSteyaertJamain.be.winkelKassa.data.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.ClientCardRepository;
import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Card;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlClientCardRepository implements ClientCardRepository {

    private static final String SQL_ADD_CLIENTCARD = "insert into klantenkaart(punten, barcode, eigenaar) values(?, ?, ?)";
    private static final String SQL_GET_CLIENTCARDS = "select * from klantenkaart";
    private static final String SQL_GET_CLIENTCARD = "SELECT * from klantenkaart where idklantenkaart=?";
    private static final String SQL_UPDATE_CLIENTCARD = "UPDATE klantenkaart SET punten=?, barcode=?, eigenaar=? WHERE idklantenkaart=?";
    private static final String SQL_DELETE_CLIENTCARD = "DELETE FROM klantenkaart WHERE idklantenkaart=?;";

    private void fillPreparedStatement(Card c, PreparedStatement prep) throws SQLException {
        prep.setInt(1, c.getId());
        prep.setString(2, c.getBarcode());
        prep.setInt(3, c.getPoints());
        prep.setInt(4, c.getOwner().getId());

        prep.executeUpdate();
    }

    private Card createClientCard(ResultSet rs) throws SQLException {
        int id = rs.getInt("idklantenkaart");
        int points = rs.getInt("punten");
        String barcode = rs.getString("barcode");
        int ownerId = rs.getInt("eigenaar");

        Client owner = Repositories.getInstance().getClientRepository().getClient(ownerId);

        return new Card(id, points, barcode, owner);
    }

    @Override
    public void addClientCard(Card c) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_ADD_CLIENTCARD)
        ) {
            fillPreparedStatement(c, prep);
        } catch (SQLException ex) {
            throw new KassaException("Unable to add client card to DB.", ex);
        }
    }

    @Override
    public Card getClientCard(int id) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_CLIENTCARD)
        ){
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()) {
                    return createClientCard(rs);
                } else {
                    return null;
                }
            }
        } catch(SQLException ex){
            throw new KassaException("Unable to get client card from DB.", ex);
        }
    }

    @Override
    public List<Card> getAllClientCards() {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_CLIENTCARDS)
        ){
            try (ResultSet rs = prep.executeQuery()) {
                List<Card> cards = new ArrayList<>();

                while (rs.next()) {
                    cards.add(createClientCard(rs));
                }
                return cards;
            }
        } catch(SQLException ex){
            throw new KassaException("Unable to get client cards from DB.", ex);
        }
    }

    @Override
    public void updateClientCards(Card c) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_UPDATE_CLIENTCARD)
        ){
            fillPreparedStatement(c, prep);
        } catch(SQLException ex){
            throw new KassaException("Unable to update client card in DB.", ex);
        }

    }

    @Override
    public void deleteClientCards(Card c) {
        try (
                Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_DELETE_CLIENTCARD)
        )
        {
            prep.setInt(1, c.getId());
            prep.executeUpdate();
        } catch(SQLException ex){
            throw new KassaException("Unable to delete client card from DB.", ex);
        }

    }
}
