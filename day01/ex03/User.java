package day01.ex03;



public class User {
    private Integer id;
    private String name;
    private Integer balance;

    private TransactionsLinkedList transactionsLinkedList;

    public User(String name, Integer balance) {
        this.name = name;
        if (balance < 0)
            this.balance = 0;
        else
            this.balance = balance;
        this.id = UserIdsGenerator.getInstance().generateId();
        this.transactionsLinkedList = new TransactionsLinkedList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if (balance < 0)
            this.balance = 0;
        else
            this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void printData() {
        System.out.println(id + " " + name + " " + balance);
    }

    public TransactionsLinkedList getTransactionsLinkedList() {
        return transactionsLinkedList;
    }

    public void setTransactionsLinkedList(TransactionsLinkedList transactionsLinkedList) {
        this.transactionsLinkedList = transactionsLinkedList;
    }
}

