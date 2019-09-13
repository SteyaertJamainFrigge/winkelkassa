package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Transaction;

import java.util.List;

public interface TransactionRepository {

    void addTransaction(Transaction transaction);
    Transaction getTransaction(int id);
    List<Transaction>getAllTransactions();
}
