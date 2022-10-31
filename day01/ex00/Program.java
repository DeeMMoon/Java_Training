package day01.ex00;

public class Program {
    public static void main(String[] args) {
        User mike = new User("Mike", 100);
        User john = new User("John", 200);
        mike.printData();
        john.printData();
        System.out.println();
        Transaction tr = Transaction.makeTransaction(john, mike, -20, Transaction.Transfer.CREDIT);
        tr.printInfo();
        System.out.println();
        mike.printData();
        john.printData();
        System.out.println();
        Transaction tr2 = Transaction.makeTransaction(john, mike, 10, Transaction.Transfer.DEBIT);
        tr2.printInfo();
        System.out.println();
        mike.printData();
        john.printData();
        System.out.println();
        Transaction tr3 = Transaction.makeTransaction(mike, john, 60, Transaction.Transfer.CREDIT);
        tr3.printInfo();
        System.out.println();
        mike.printData();
        john.printData();
        System.out.println();
        Transaction tr4 = Transaction.makeTransaction(mike, john, 1000, Transaction.Transfer.DEBIT);
    }
}
