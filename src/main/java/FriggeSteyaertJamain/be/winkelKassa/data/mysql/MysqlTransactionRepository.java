package FriggeSteyaertJamain.be.winkelKassa.data.mysql;

import FriggeSteyaertJamain.be.winkelKassa.data.TransactionRepository;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MysqlTransactionRepository implements TransactionRepository {

    private static final String SQL_ADD_PRODUCT_TRANSACTIONS = "INSERT INTO product_verrichting(idverrichting, idproduct, aantal) VALUES(?,?,?)";
    private static final String SQL_ADD_TRANSACTIONS = "INSERT INTO verrichting(timeStamp, winkel, idpersoneel, totaalPrijs, opmerking, idklant) " +
            "VALUES(?,?,?,?,?,?)";

    @Override
    public void addTransaction(Transaction transaction) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_TRANSACTIONS)) {
            Timestamp param = new java.sql.Timestamp(transaction.getTimestamp().getTime());
            prep.setTimestamp(1, param);
            prep.setString(2,transaction.getStore());
            prep.setInt(3, transaction.getPersonnel().getId());
            prep.setDouble(4, transaction.getTotalPrice());
            prep.setString(5, transaction.getNote());
            prep.setInt(6, transaction.getClient().getId());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //addProductTransaction(transaction);
    }

    /*private void addProductTransaction(Transaction transaction) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_PRODUCT_TRANSACTIONS)){
            for (Product product :
                    transaction.getProducts()) {
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public Transaction getTransaction(int id) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }
}
