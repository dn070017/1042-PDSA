

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Po-Lin
 */
public class Deques<Item> {

    private Node<Item> head;     // top of Deques
    private Node<Item> tail;      //down of Deques
    private int N;                // size of the Deques

    // helper linked list class
    private static class Node<Item> {

        private Item item;
        private Node<Item> left;
        private Node<Item> right;
    }

    /**
.
     */
    public Deques() {
        head = null;
        tail = null;
        N = 0;
    }

    /**
.
     *
     * @return true if this Deques is empty; false otherwise
     */
    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    /**
.
     *
     * @return the number of items in this Deques
     */
    public int size() {
        return N;
    }

    /**
.
     *
     * @param item the item to add
     */
    public void addFirst(Item item) {   //addFirst
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            head = new Node<Item>();
            tail = head;
            head.item = item;
            head.right = null;
            N++;
        } else {
            Node<Item> oldfirst = head;
            head = new Node<Item>();
            head.item = item;
            head.right = oldfirst;
            oldfirst.left = head;
            N++;
        }
    }

    public void addLast(Item item) {   //addLast
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            tail = new Node<Item>();
            head = tail;
            tail.item = item;
            tail.left = null;
            N++;
        } else {
            Node<Item> oldfirst = tail;
            tail = new Node<Item>();
            tail.item = item;
            tail.left = oldfirst;
            oldfirst.right = tail;
            N++;
        }
    }

    /**
.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item removeFirst() {  //removeFirst() 
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = head.item;        // save item to return
        // delete first node
        if (head.right != null) {
            head = head.right;
            head.left = null;
        } else {
            tail = null;
            head = null;
        }
        N--;
        return item;                   // return the saved item
    }

    public Item removeLast() {  //removeLast() 
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = tail.item;        // save item to return
        // delete first node
        if (tail.left != null) {
            tail = tail.left;
            tail.right = null;
        } else {
            tail = null;
            head = null;
        }

        N--;
        return item;                   // return the saved item
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO
.
     *
     * @return an iterator to this stack that iterates through the items in LIFO
     * order
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(head);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator(Node<Item> head) {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.right;
            return item;
        }
    }

    /**
.
     */
    public static void main(String[] args) {
        
    }
}

