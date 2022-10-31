package day01.ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransactionById(UUID uuid) throws TransactionException;
    Transaction [] toArray() throws TransactionException;
}
