package day01.ex03;

public class Program {
    public static void main(String[] args) throws TransactionException {
        User user1 = new User("Mike", 100);
        User user2 = new User("John", 200);
        Transaction transaction1 = Transaction.makeTransaction(user1, user2, -300, Transaction.Transfer.CREDIT);
        Transaction transaction2 = Transaction.makeTransaction(user1, user2, -500, Transaction.Transfer.CREDIT);
        Transaction transaction3 = Transaction.makeTransaction(user1, user2, 300, Transaction.Transfer.DEBIT);
        Transaction transaction4 = Transaction.makeTransaction(user1, user2, 500, Transaction.Transfer.DEBIT);
        TransactionsLinkedList list = user1.getTransactionsLinkedList();
        TransactionsLinkedList list1 = user2.getTransactionsLinkedList();
        list.addTransaction(transaction1);
        list.addTransaction(transaction2);
        list.addTransaction(transaction3);
        list.addTransaction(transaction4);

        System.out.println("Count of transactions: " + list.getSize());

        list1.addTransaction(transaction1);
        list1.addTransaction(transaction2);
        list1.addTransaction(transaction3);
        list1.addTransaction(transaction4);

        list.removeTransactionById(transaction1.getIdentifier());
        list1.removeTransactionById(transaction3.getIdentifier());

        Transaction [] array = list.toArray();
        Transaction [] array1 = list1.toArray();

        System.out.println();

        for (Transaction item : array) {
            item.printInfo();
        }
        System.out.println();

        for (Transaction item : array1) {
            item.printInfo();
        }

        user1.printData();
        user2.printData();
    }
}
