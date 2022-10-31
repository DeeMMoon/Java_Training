package day01.ex04;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransactionById(UUID uuid) throws TransactionException;
    Transaction[] toArray() throws TransactionException;
}
