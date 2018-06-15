/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author YuChing
 */
public class Deque<Item> implements Iterable<Item>{
    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
.
     */
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
.
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }

    /**
.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        return first.item;
    }

    /**
.
     * @param item the item to add
     */
    public void addFirst(Item item) {
        if(item==null) throw new NullPointerException("""");
        first.item = item;
        Node<Item> oldfirst = first;
        first = new Node<Item>();
      
        if (isEmpty()) first = last;
        else           first.next = oldfirst;
        N++;
    }
public void addLast(Item item) {
    if(item==null) throw new NullPointerException("""");
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }
    /**
.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = last.item;
        Node<Item> a= last;
        last = a.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + "" "");
        return s.toString();
    } 

    /**
.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
.
     */
    public static void main(String[] args) {
       
        }
       
    }


