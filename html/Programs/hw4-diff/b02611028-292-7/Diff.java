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
        if (item == null) {
            throw new java.lang.NullPointerException();
        } else {
            N++;
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.previous=null;
            if (N == 1) {
                last = first;
            } else {
                oldfirst.previous = first;
            }

        }

    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        } else {
            N++;
            Node prelast = last;
            last = new Node();
            last.item = item;
            last.previous = prelast;
            last.next=null;

            if (N == 1) {
                first = last;
            } else {
                prelast.next = last;
            }
        }
    }           // add the item to the end

    public Item removeFirst() {
        if (N == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            N--;
            Item item = first.item;
            first = first.next;
            first.previous=null;
            return (item);
        }
    }       // remove and return the item from the front

    public Item removeLast() {
        if (N == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            N--;
            Item item = last.item;
            last = last.previous;
            last.next=null;
            return (item);
        }
    }        // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }

    }       // return an iterator over items in order from front to end

    public static void main(String[] args) {
        try {
            Deque<String> q = new Deque<String>();
            q.iterator().remove();
            /*q.addFirst(""fuck"");
             q.addLast(""shit"");
             q.addLast(""ass"");
             Iterator it=q.iterator();
             System.out.print(it.next());
             System.out.print(it.next());
             System.out.print(q.removeLast());*/
        } catch (java.lang.UnsupportedOperationException exception) {
            System.out.print(1);
        }
        /*System.out.print(q.size());
         System.out.print(q.removeFirst());
         System.out.print(q.size());
         System.out.print(q.removeFirst());
         System.out.print(q.size());
         System.out.print(q.removeFirst());*/
    }   // unit testing
}

