package day01.ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Node first;
    private Node last;
    private int size;

    private static class Node {
        Transaction item;
        Node next;
        Node prev;

        Node(Node prev, Transaction elem, Node next) {
            this.item = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    public TransactionsLinkedList() {}

    @Override
    public void addTransaction(Transaction transaction) {
        final Node lst = last;
        final Node newNode = new Node(lst, transaction, null);
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
        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.item != null && id.equals(tmp.item.getIdentifier())) {
                final Node next = tmp.next;
                final Node prev = tmp.prev;
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

        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            result[i++] = tmp.item;
        }

        return result;
    }
    public int getSize() {
        return size;
    }
}
