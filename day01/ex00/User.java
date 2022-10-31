package day01.ex00;

import java.util.UUID;

public class User {
    private UUID identifier;
    private String name;
    private Integer balance;

    public User(String name, Integer balance)
    {
        this.name = name;
        if (balance < 0)
            this.balance = 0;
        else
            this.balance = balance;
        this.identifier = UUID.randomUUID();
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

    public UUID getIdentifier() {
        return identifier;
    }

    public void printData() {
        System.out.println(identifier + " "  + name + " " + balance);
    }
}