package day01.ex04;


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

    public static Transaction makeTransaction(User recipient, User sender, Integer transferAmount, Transfer transfer)
    {
            Transaction transaction = new Transaction();
            transaction.sender = sender;
            transaction.recipient = recipient;
            transaction.identifier = UUID.randomUUID();
            transaction.setTransferAmount(transferAmount);
            transaction.transferCategory = transfer;
            return (transaction);
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
        if (this.transferCategory == Transfer.CREDIT &&  transferAmount > 0){
            this.transferAmount = 0;
        } else if (this.transferCategory == Transfer.DEBIT && transferAmount < 0){
            this .transferAmount = 0;
        } else {
            this.transferAmount = transferAmount;
        }
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void printInfo(){
        System.out.printf("From: " + sender.getName() + " Identifier: " + sender.getId() + "\nTo: " + recipient.getName() + " Identifier: " + recipient.getId() + "\n" + transferCategory + " " + transferAmount + " " + identifier + "\n");
    }

}
