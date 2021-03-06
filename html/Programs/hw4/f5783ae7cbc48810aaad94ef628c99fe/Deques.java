
import java.util.Iterator;
import java.util.NoSuchElementException;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 許志鵬
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    public Deque() {

        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == last;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public void addLast(Item item) {
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        oldLast.next = last;
        N++;

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Queue underflow"");
        }
        Item item = first.item;
        first = first.next;
        N--;
        return item;

    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Queue underflow"");
        }
        Item item = last.item;
        if (N > 1) {
            Node<Item> oldLast = null;
            oldLast = last;

        } else {
            last = null;
        }
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

   

}

