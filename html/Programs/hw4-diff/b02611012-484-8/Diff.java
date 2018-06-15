
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;// top of stack
    private Node<Item> last;
    private int N;                // size of the stack

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    private void elseif(boolean b) {
.
    }

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> pre;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            first = new Node<Item>();
            first.item = item;
            last = first;
        } else {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            first.pre = null;
            oldfirst.pre = first;
        }
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            last = new Node<Item>();
            last.item = item;
            first = last;
        } else {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = null;
            oldlast.next = last;
            last.pre = oldlast;
        }

        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = first.item;        // save item to return
        if (N==1) {
            first = null;
            last = null;
        } else if (N==2) {
            first = last;
            first.next = null;
            first.pre = null;
        } else {
            first = first.next; 
            first.pre = null;// delete first node
        }
        
        N--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = last.item;        // save item to return
        if (N==1) {
            last = null;
        } else if (N==2) {
            first = last;
            last.pre = null;
            last.next = null; 
        } else {
            last = last.pre; 
            last.next = null; 
        }
        
                 // delete first node
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        d.addLast(""a"");
        d.addFirst(""c"");
        d.addLast(""b"");
        d.addFirst(""c"");
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.addFirst(""d"");
        System.out.printf(d.removeFirst());

        // TODO code application logic here
    }
}

