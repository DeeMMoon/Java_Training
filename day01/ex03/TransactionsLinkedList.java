package day01.ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private TransactionsLinkedList.Node first;
    private TransactionsLinkedList.Node last;
    private int size;

    private static class Node {
        Transaction item;
        TransactionsLinkedList.Node next;
        TransactionsLinkedList.Node prev;

        Node(TransactionsLinkedList.Node prev, Transaction elem, TransactionsLinkedList.Node next) {
            this.item = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    public TransactionsLinkedList() {}

    @Override
    public void addTransaction(Transaction transaction) {
        final TransactionsLinkedList.Node lst = last;
        final TransactionsLinkedList.Node newNode = new TransactionsLinkedList.Node(lst, transaction, null);
        last = newNode;

        if (lst == null) {
            first = newNode;
        } else {
            lst.next = newNode;
        }
        size++;
    }

    @Override
    public void removeTransactionById(UUID id) throws TransactionException {
        if (id == null) {
            throw new TransactionException("Wrong UUID");
        }
        for (TransactionsLinkedList.Node tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.item != null && id.equals(tmp.item.getIdentifier())) {
                final TransactionsLinkedList.Node next = tmp.next;
                final TransactionsLinkedList.Node prev = tmp.prev;
                if (prev == null) {
                    this.first = next;
                }
                else {
                    prev.next = next;
                    tmp.prev = null;
                }
                if (next == null)
                    this.last = prev;
                else {
                    next.prev = prev;
                    tmp.next = null;
                }
                tmp.item = null;
                size--;
                return;
            }
        }
        throw new TransactionException("Transaction with UUID: " + id + " not found.");
    }

    @Override
    public Transaction[] toArray() {
        if (this.size == 0) {
            return null;
        }
        Transaction[] result = new Transaction[this.size];
        int i = 0;

        for (TransactionsLinkedList.Node tmp = first; tmp != null; tmp = tmp.next) {
            result[i++] = tmp.item;
        }

        return result;
    }
    public int getSize() {
        return size;
    }
}

