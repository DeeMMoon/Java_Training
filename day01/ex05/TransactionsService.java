package day01.ex05;

import java.util.UUID;

public class TransactionsService{
    UserList userList = new UsersArrayList();

    public void addUser (User user)
    {
        this.userList.addUser(user);
    }

    public Integer getBalance(Integer id) throws UserNotFoundException {
        return (userList.getUserById(id).getBalance());
    }
    public Integer getBalance(User user) throws UserNotFoundException {
        return (userList.getUserById(user.getId()).getBalance());
    }
    public void getTransaction(Integer senderId, Integer recipientId, Integer amount) throws UserNotFoundException, TransactionException {
        User sender = userList.getUserById(senderId);
        User recipient = userList.getUserById(recipientId);

        if (senderId == recipientId || amount < 0 || sender.getBalance() < amount)
            throw new TransactionException("Transaction error");
        if (sender.getBalance() < amount)
            throw new IllegalArgumentException();
        Transaction credit = Transaction.makeTransaction(recipient, sender, -amount, Transaction.Transfer.CREDIT);
        Transaction debit = Transaction.makeTransaction(recipient, sender, amount, Transaction.Transfer.DEBIT);
        credit.setIdentifier(debit.getIdentifier());
        recipient.getTransactionsLinkedList().addTransaction(debit);
        sender.getTransactionsLinkedList().addTransaction(credit);
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }
    public Transaction[] getTransactionList(Integer userId) throws UserNotFoundException, TransactionException {
        return userList.getUserById(userId).getTransactionsLinkedList().toArray();
    }

    public void removeTransaction(UUID uuid, Integer id) throws UserNotFoundException, TransactionException {
        userList.getUserById(id).getTransactionsLinkedList().removeTransactionById(uuid);
    }

    public Transaction [] getInvalidTransaction() throws UserNotFoundException, TransactionException {
        TransactionsLinkedList all = new TransactionsLinkedList();
        all = getAllTransactions();
        Transaction [] arrays = all.toArray();
        TransactionsLinkedList list = new TransactionsLinkedList();
        int size = 0;
        int count;
        if (arrays != null)
        {
            size = arrays.length;
            for (int i = 0; i < size; i++)
            {
                count = 0;
                for (int j = 0; j < size; j++)
                {
                    if(arrays[i].getIdentifier().equals(arrays[j].getIdentifier()))
                        count++;
                }
                if (count != 2)
                    list.addTransaction(arrays[i]);
            }
        }
    return (list.toArray());
    }

    private TransactionsLinkedList getAllTransactions() throws UserNotFoundException, TransactionException {
        TransactionsLinkedList transactionsLinkedListAllUsers = new TransactionsLinkedList();
        int size;
        for(int i = 0; i < userList.getNumberOfUsers(); i++)
        {
            User user = userList.getUserByIndex(i);
            if (user != null)
            {
                size = user.getTransactionsLinkedList().getSize();
                for (int j = 0; j < size; j++)
                {
                    transactionsLinkedListAllUsers.addTransaction(user.getTransactionsLinkedList().toArray()[j]);
                }
            }

        }
        return (transactionsLinkedListAllUsers);
    }

   public UsersArrayList getUserList() throws UserNotFoundException {
        UsersArrayList list = new UsersArrayList();
        for (int i = 0; i < userList.getNumberOfUsers(); i++)
        {
            list.addUser(userList.getUserByIndex(i));
        }
        return list;
   }

}
