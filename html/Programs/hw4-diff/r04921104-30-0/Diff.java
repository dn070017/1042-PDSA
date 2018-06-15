/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author daf
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;

public class Deques<Item> implements Iterable<Item> {

    private Node<Item> first;    // beginning of deque
    private Node<Item> last;     // end of deque
    private int N = 0;               // number of elements on deque

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public Deques() {
        first = null;
        last = null;
        N = 0;
    }// construct an empty deque

    public boolean isEmpty() {
        return N == 0;
    }// is the deque empty?

    public int size() {
        return N;
    }// return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException(""uncompatible null input"");
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        N++;
    }// add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException(""uncompatible null input"");
        }
        //StdOut.println(item.getClass());
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
            last.previous = oldlast;
        }
        N++;
    }// add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Deques underflow"");
        }
        Item item = first.item;
        N--;
        if (isEmpty()) {
            last = null;   // to avoid loitering
            first = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        return item;
    }// remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Deques underflow"");
        }
        Item item = last.item;
        N--;
        if (isEmpty()) {
            first = null;   // to avoid loitering
            last = null;
        } else {
            last = last.previous;
            last.next = null;
        }
        return item;
    }// remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }         // return an iterator over items in order from front to end

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
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
            current = current.next;
            return item;
        }
    }

    //test
    public static void main(String[] args) {
        Deques<Integer> q = new Deques<Integer>();
        while (true) {
            String item = StdIn.readString();
            if (item.equals(""++"")) {
                int adfst = StdIn.readInt();
                q.addFirst(adfst);
            } else if (item.equals(""+-"")) {
                int adlst = StdIn.readInt();
                q.addLast(adlst);
            } else if (item.equals(""-+"")) {
                int rmfst = q.removeFirst();
                StdOut.println(rmfst);
            } else if (item.equals(""--"")) {
                int rmlst = q.removeLast();
                StdOut.println(rmlst);
            } else if (item.equals(""q"")) {
                break;
            } else {
                StdOut.println(""please enter '++','+-','-+','--' or'q' to break"");
            }
        }

        StdOut.println(""("" + q.size() + "" left on deque)"");

        Iterator<Integer> i = q.iterator();
        //i.remove();
        while (i.hasNext()) {
            StdOut.println(i.next().toString());
        }
    }

}

