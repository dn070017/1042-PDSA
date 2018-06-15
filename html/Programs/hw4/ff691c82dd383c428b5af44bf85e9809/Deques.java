
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first=null;
    private Node last=null;

    private int N = 0;

    private class Node {

        Item item;
        Node next=null;
        Node previous=null;

    }



    public boolean isEmpty() {
        return first == null;
    }                 // is the deque empty?

    public int size() {
        return N;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        
        if (N==0) {
            last = first;
        } else {
            oldfirst.previous = first;
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
        last.previous = oldlast;
        last.next = null;
        
        if (N==0) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
    }           // add the item to the end

    public Item removeFirst() {
        if (N==0) {
            throw new NoSuchElementException(""Queue underflow"");
        }
        
        Item item = first.item;
        first = first.next;
        
        N--;
        if (N==0) {
            last = null;
        }else{first.previous = null;}
        N--;
        return item;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (N==0) {
            throw new NoSuchElementException(""Queue underflow"");
        }
        Item item = last.item;
        last = last.previous;
        
        N--;
        if (N==0) {
            first = null;
        }else{last.next = null;}
        
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

