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

        if (N == 1) {
            last = first;
        } else {
            oldfirst.previous = first;
        }
        
        if(item==null)throw new java.lang.NullPointerException();

    }          // add the item to the front

    public void addLast(Item item) {
        N++;
        Node prelast = last;
        last = new Node();
        last.item = item;
        last.previous = prelast;

        if (N == 1) {
            first = last;
        } else {
            prelast.next = last;
        }
        if(item==null)throw new java.lang.NullPointerException();
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
        /*public Item previous() {
         Item item = current.item;
         current = current.previous;
         return item;
         }*/
    }       // return an iterator over items in order from front to end

    public static void main(String[] args) {
        /*Deque<String> q = new Deque<String>();
         q.addFirst(""fuck"");
         q.addFirst(null);
         System.out.print(q.removeFirst());*/
    }   // unit testing
}

