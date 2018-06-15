
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
        if (first == last) {
            first.next = null;
        } else {
            first = first.next;            // delete first node
        }
        first.pre = null;
        N--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = last.item;        // save item to return

        if (first == last) {
            last.pre = null;
        } else {
            last = last.pre;
        }
        last.next = null;          // delete first node
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
        d.addFirst(""fuck"");
        d.removeLast();

        // TODO code application logic here
    }

}

