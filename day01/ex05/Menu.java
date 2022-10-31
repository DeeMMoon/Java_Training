package day01.ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private Scanner scanner;
    private TransactionsService facade;

    public Menu()
    {
        scanner = new Scanner(System.in);
        facade = new TransactionsService();
    }

    public void run(String mode)
    {
        System.out.println();
        while (true) {
            if (mode.equals("dev_mode"))
                devMode();
            else
                standardMode();
        }
    }
    private void devMode()
    {
        welcomeMessageDev();
        answerForDev(getAnswer());
    }
    private void standardMode()
    {
        welcomeMessage();
        answerForStandard(getAnswer());
    }
    private void welcomeMessageDev()
    {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID ");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
    }
    private void welcomeMessage()
    {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. Finish execution");
    }

    private int getAnswer()
    {
        String answer = scanner.nextLine().toLowerCase().trim();
        int answerInt;
        try {
            answerInt = Integer.parseInt(answer);
            if(answerInt > 7 || answerInt < 1)
                throw new RuntimeException("Invalid action, put correct answer:");
        }catch (RuntimeException e)
        {
            System.out.println(e);
            answerInt = getAnswer();
        }
        return (answerInt);
    }

    private void answerForDev(int answer)
    {
        switch (answer){
            case(1):
                addUser();
                break;
            case (2):
                getBalance();
                break;
            case (3):
                makeTransfer();
                break;
            case (4):
                showTransactions();
                break;
            case (5):
                removeTransferById();
                break;
            case (6):
                checkTransfers();
                break;
            case (7):
                System.exit(0);
        }
    }
    private void answerForStandard(int answer)
    {
        switch (answer){
            case(1):
                addUser();
            case (2):
                getBalance();
            case (3):
                makeTransfer();
            case (4):
                showTransactions();
            case (5):
                System.exit(0);
        }
    }

    private void addUser()
    {
        System.out.println("Enter a user name and a balance");
        String input = scanner.nextLine().trim();
        String [] array;
        try {
            array = input.split("\\s+");
            if (array.length != 2)
                throw new RuntimeException("Invalid values");
            String name = array[0];
            int balance = Integer.parseInt(array[1]);
            User user = new User(name, balance);
            facade.addUser(user);
            System.out.println("User with id = " + user.getId() + " is added");
            System.out.println("---------------------------------------------------------");
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
            addUser();
        }
    }
    private void getBalance() {
        System.out.println("Enter a user ID");
        try {
            String input = scanner.nextLine().trim();
            int id = Integer.parseInt(input);
            int balance = facade.getBalance(id);
            String name = facade.userList.getUserById(id).getName();
            System.out.println(name + " - " + balance);
            System.out.println("---------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void makeTransfer()
    {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        try{
            String input = scanner.nextLine().trim();
            String [] array = input.split("\\s+");
            if (array.length != 3)
                throw new RuntimeException("Invalid values");
            int senderId = Integer.parseInt(array[0]);
            int recipientId = Integer.parseInt(array[1]);
            int amount = Integer.parseInt(array[2]);
            facade.getTransaction(senderId, recipientId, amount);
            System.out.println("The transfer is completed");
            System.out.println("---------------------------------------------------------");
        }
        catch (RuntimeException | UserNotFoundException | TransactionException e) {
            System.out.println(e);
        }
    }
    private void showTransactions() {
        System.out.println("Enter a user ID");
        try {
            String input = scanner.nextLine().trim();
            int id = Integer.parseInt(input);
            Transaction[] transactions = facade.getTransactionList(id);
            if (transactions == null)
                throw new TransactionException("User with id = " + id + " hasn't transactions.");
            for (int i = 0; i < transactions.length; i++)
            {
                String  userName = (transactions[i].getTransferCategory() == Transaction.Transfer.DEBIT) ? transactions[i].getSender().getName() : transactions[i].getRecipient().getName();
                Integer userId  = (transactions[i].getTransferCategory() == Transaction.Transfer.DEBIT) ? transactions[i].getSender().getId() : transactions[i].getRecipient().getId();
                String userCategory = (transactions[i].getTransferCategory() == Transaction.Transfer.DEBIT) ? "From " : "To ";
                System.out.print(userCategory + userName + "(id = " + userId + ") ");
                System.out.print(transactions[i].getTransferAmount());
                System.out.println(" with id = " + transactions[i].getIdentifier());
            }
            System.out.println("---------------------------------------------------------");
        } catch (RuntimeException | UserNotFoundException | TransactionException e) {
            System.out.println(e);
        }
    }
    private void removeTransferById()
    {
        System.out.println("Enter a user ID and a transfer ID");
        String input = scanner.nextLine().trim();
        try{
            String [] array = input.split("\\s+");
            if (array.length != 2)
                throw new RuntimeException("Invalid values");
            int userId = Integer.parseInt(array[0]);
            UUID uuid = UUID.fromString(array[1]);
            Transaction transaction = getTransaction(facade.getTransactionList(userId), uuid);
            if (transaction == null)
                throw new TransactionException("Transaction with id = " + uuid + "not found. User id = " + userId);
            facade.removeTransaction(uuid, userId);
            String name = (transaction.getTransferCategory() == Transaction.Transfer.DEBIT) ? transaction.getSender().getName() : transaction.getRecipient().getName();
            String category = (transaction.getTransferCategory() == Transaction.Transfer.DEBIT) ? "From " : "To ";
            Integer userIdTransaction  = (transaction.getTransferCategory() == Transaction.Transfer.DEBIT) ? transaction.getSender().getId() : transaction.getRecipient().getId();
            System.out.print("Transfer " + category + name);
            System.out.println("(id = " + userIdTransaction + ") " + (transaction.getTransferAmount() < 0 ? (-transaction.getTransferAmount()) : transaction.getTransferAmount())  + " removed");
            System.out.println("---------------------------------------------------------");
        }
        catch (RuntimeException | TransactionException | UserNotFoundException e) {
            System.out.println(e);
        }
    }

    private Transaction getTransaction(Transaction [] transactions, UUID uuid) throws TransactionException {
        if(transactions == null)
            throw new TransactionException("Transaction with id = " + uuid + " not found.");
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i].getIdentifier().equals(uuid))
                return transactions[i];
        }
        return (null);
    }

    private void checkTransfers() {
        System.out.println("Check results: ");
        try {
            Transaction[] unpairedTransaction = facade.getInvalidTransaction();
            if(unpairedTransaction != null)
            {
                for (int i = 0; i < unpairedTransaction.length; i++)
                {
                    User userTransactions = getUserTransaction(unpairedTransaction[i]);
                    String senderName = (unpairedTransaction[i].getTransferCategory() == Transaction.Transfer.DEBIT) ? unpairedTransaction[i].getSender().getName() : unpairedTransaction[i].getRecipient().getName();
                    int senderId = (unpairedTransaction[i].getTransferCategory() == Transaction.Transfer.DEBIT) ? unpairedTransaction[i].getSender().getId() : unpairedTransaction[i].getRecipient().getId();
                    UUID transferId = unpairedTransaction[i].getIdentifier();
                    int amount = unpairedTransaction[i].getTransferAmount();
                    String category = (unpairedTransaction[i].getTransferCategory() == Transaction.Transfer.DEBIT) ? "from " : "to ";
                    System.out.print(userTransactions.getName() + "(id = " + userTransactions.getId() + ")");
                    System.out.print(" has an unacknowledged transfer id = " + transferId);
                    System.out.println(" " + category + senderName + "(id = " + senderId + ") for " + amount);
                    System.out.println("---------------------------------------------------------");
                }
                return;
            }
        } catch (TransactionException | UserNotFoundException e) {
            System.out.println(e);
        }
    }

    private User getUserTransaction(Transaction transaction)
    {
        try {
            UsersArrayList list = facade.getUserList();
            for (int i = 0; i < list.getNumberOfUsers(); i++)
            {
                Transaction [] listTransaction = list.getUserByIndex(i).getTransactionsLinkedList().toArray();
                for (int j = 0; listTransaction != null && j < listTransaction.length; j++)
                {
                    if (listTransaction[j].getIdentifier().equals(transaction.getIdentifier()))
                        return list.getUserByIndex(i);
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println("User not found");
        }
        return null;
    }

}
