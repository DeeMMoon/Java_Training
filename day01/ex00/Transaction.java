package day01.ex00;

import java.util.UUID;

public class Transaction {
    protected enum Transfer {
        DEBIT,
        CREDIT
    }
    private UUID identifier;
    private User recipient;
    private User sender;
    private Transfer transferCategory;
    private Integer transferAmount;

    public Transaction(){}

    public static Transaction makeTransaction(User recipient, User sender,Integer transferAmount, Transfer transfer)
    {
        if (sender.getBalance() < 0 || sender.getBalance() < transferAmount)
            System.out.println("Transaction failed!");
        else{
            Transaction transaction = new Transaction();
            transaction.sender = sender;
            transaction.recipient = recipient;
            transaction.identifier = UUID.randomUUID();
            transaction.transferAmount = transferAmount;
            transaction.transferCategory = transfer;
            transaction.setTransferAmount(transferAmount);
            sender.setBalance(sender.getBalance() - transferAmount);
            recipient.setBalance(recipient.getBalance() + transferAmount);
            return (transaction);
        }
        return (null);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Transfer getTransferCategory() {
        return transferCategory;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        if (this.transferCategory == Transaction.Transfer.CREDIT && transferAmount > 0){
            this.transferAmount = 0;
        } else if (this.transferCategory == Transaction.Transfer.DEBIT && transferAmount < 0){
            this .transferAmount = 0;
        } else {
            this.transferAmount = transferAmount;
        }
    }

    public void printInfo(){
        System.out.printf("From: " + sender.getName() + " Identifier: " + sender.getIdentifier() + "\nTo: " + recipient.getName() + " Identifier: " + recipient.getIdentifier() + "\n" + transferCategory + " " + transferAmount + " " + identifier + "\n");
    }
}
