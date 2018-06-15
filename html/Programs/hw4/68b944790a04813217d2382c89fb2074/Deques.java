
import java.util.*;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author philip
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;    // beginning of queue
    private Node<Item> NodNxt;     // end of queue
    private Node<Item> last;     // end of queue
    private int N;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previ;
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
.
     *
     * @return <tt>true</tt> if this queue is empty; <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }
    /**
.
     *
     * @param  item the item to add
     */
    public void addFirst(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        //first.next = oldfirst;
        if (isEmpty()) first = last;
        else{
            first.next = oldfirst;
            oldfirst.previ = first;
        }
        N++;
    }
    public void addLast(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else{
            oldlast.next = last;
            last.previ = oldlast;
            
        }
        N++;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;//A
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = last.item;
        last = last.previ;
        last.next = null;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + "" "");
        return s.toString();
    } 
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

    
    public static void main(String[] args) {

        /*Deque<String> qe = new Deque<String>();

        qe.addLast(""a"");
        qe.addLast(""b"");
        qe.addLast(""c"");
        qe.addLast(""d"");
        //qe.addFirst(""a"");
        qe.removeFirst();
        qe.removeFirst();
        qe.removeFirst();
        qe.addLast(""a"");
        //qe.removeLast();

        Iterator it=qe.iterator();

        System.out.println(""Initial Size of Queue :""+qe.size());

        while(it.hasNext())
        {
            String iteratorValue=(String)it.next();
            System.out.println(""Queue Next Value :""+iteratorValue);
        }

        System.out.println(""Final Size of Queue :""+qe.size());*/
    
    }
    
}

