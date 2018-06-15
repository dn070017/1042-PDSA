
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node first, last;
    private Node oldlast, oldfirst;

    private class Node {

        Item item;
        Node next;
    }

    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return first == null;
    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        } else {
            oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) {
                first = last;
            } else {
                oldlast.next = last;
            }
            N++;
        }
    }

    public void addFirst(Item item) {           // add the item to the front
        if (item == null) {
            throw new NullPointerException();
        } else {
            oldfirst = first;
            if (isEmpty()) {
                first = new Node();
                first.item = item;
                last = first;
            } else {
                first = new Node();
                first.item = item;
                first.next = oldfirst;
            }
            N++;
        }
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item;
            item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item;
            item = last.item;
            last = oldlast;
            N--;
            return item;
        }
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return (Iterator<Item>) new ListIterator();
    }

    private class ListIterator implements Iterable<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item Next() {
            Item item = current.item;
            if (hasNext()) {
                current = current.next;
                return item;
            } else {
                throw new NoSuchElementException();
            }
        }

        
    }

    public static void main(String[] args) throws Exception {   // unit testing
        


    }
}

