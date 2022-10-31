package day01.ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws UserNotFoundException, TransactionException {
        TransactionsService facade = new TransactionsService();
        User user1 = new User("Mark", 400);
        User user2 = new User( "John", 500);
        user1.printData();
        user2.printData();
        facade.addUser(user1);
        facade.addUser(user2);
        facade.getTransaction(user1.getId(), user2.getId(), 50);
        facade.getTransaction(user1.getId(), user2.getId(), 100);
        facade.getTransaction(user1.getId(), user2.getId(), 150);
        System.out.println("\nMark's transactions");
        Transaction[] arrayTransactionUser1 = facade.getTransactionList(user1.getId());
        for (int i = 0; i < arrayTransactionUser1.length; i++) {
            arrayTransactionUser1[i].printInfo();
        }
        UUID transactionId = arrayTransactionUser1[1].getIdentifier();
        facade.removeTransaction(transactionId, user1.getId());
        arrayTransactionUser1 = facade.getTransactionList(user1.getId());
        System.out.println("\nRemove transaction");
        for (int i = 0; i < arrayTransactionUser1.length; i++) {
            arrayTransactionUser1[i].printInfo();
        }
        System.out.println("\nUnpair transactions");
        Transaction[] unpairedTransactions = facade.getInvalidTransaction();
        for (int i = 0; i < unpairedTransactions.length; i++) {
            unpairedTransactions[i].printInfo();
        }
    }
}
