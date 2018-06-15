import java.util.Iterator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {

        Item item;
        Node next;
        Node previous;
    }
    private Node first = null;
    private Node last = null;
    int N;

    public Deque() {
        first = null;
        last = null;

        N = 0;
    }                           // construct an empty deque

    public boolean isEmpty() {
        return first == null;
    }                 // is the deque empty?

    public int size() {
        return N;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        N++;
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        oldfirst.previous = first;

        if (N == 1) {
            last = first;
        }

    }          // add the item to the front

    public void addLast(Item item) {
        N++;
        Node prelast = last;
        first = new Node();
        last.item = item;
        last.previous = prelast;
        prelast.next = last;
        if (N == 1) {
            first = last;
        }
    }           // add the item to the end

    public Item removeFirst() {
        N--;
        Item item = first.item;
        first = first.next;
        return (item);
    }                // remove and return the item from the front

    public Item removeLast() {
        N--;
        Item item = last.item;
        last = last.previous;
        return (item);
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }       // return an iterator over items in order from front to end

    public static void main(String[] args) {
        
    }   // unit testing
}

