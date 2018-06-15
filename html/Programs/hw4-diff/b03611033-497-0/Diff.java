
import edu.princeton.cs.algs4.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node first, last;
    private Node oldlast, oldfirst;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
       
    }

    public boolean isEmpty() {                 // is the deque empty?
        return N == 0;
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
                //last.previous=first.previous;
            } else{
                last.previous =oldlast;
            }
            N++;
        }
    }

    public void addFirst(Item item) {           // add the item to the front
        if (item == null) {
            throw new NullPointerException();
        } else {
            oldfirst = first;
            first = new Node();
            first.item = item;
            first.previous =null;
            if (isEmpty()) {
                last = first;
            } else {
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
            first.item = null;
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
            last = last.previous;
            N--;
            return item;
        }
    }

    @Override
    public Iterator<Item>  iterator() {         // return an iterator over items in order from front to end
        return (Iterator<Item>) new ListIterator(first);
    }

    private class ListIterator implements Iterable<Item> {
        private Node current;
        public ListIterator(Node first) {
            current = first;
        }
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

        @Override
        public Iterator iterator() {
.
        }
    }

    public static void main(String[] args) throws Exception {   // unit testing
/*
        Deque deque = new Deque();
        
        //Iterator it = deque.iterator();
        
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addLast(7);
        deque.addLast(8);
        String f = null;
        //deque.addFirst(f);

       // StdOut.println(deque.size());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
/       
        Iterator it = deque.iterator();
        while(it.hasNext()){
            StdOut.println(it.next());
        }
     */   
    }
   
}


