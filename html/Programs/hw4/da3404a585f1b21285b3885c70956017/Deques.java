
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;

    private int N = 0;

    private class Node {

        Item item;
        Node next;
        Node previous;

    }

    public Deque() {
    }// construct an empty deque

    public boolean isEmpty() {
        return first == null;
    }                 // is the deque empty?

    public int size() {
        return N;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        oldfirst.previous = first;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldfirst;
        }
        N++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
    }           // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = last.item;
        last = last.previous;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;

    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator();
    }        // return an iterator over items in order from front to end

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

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
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }   // unit testing

}

