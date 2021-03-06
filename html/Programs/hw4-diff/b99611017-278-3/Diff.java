
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;

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

        if (item == null) {
            throw new NullPointerException(""There is an exception"");
        }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;

        first.next = oldFirst;
        N++;

    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException(""There is an exception"");
        }
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

        Node<Item> starter = null;
        Node<Item> buff = null;
        starter = first;
        Item item = last.item;
        buff = first;
        while (first.next != last && first.next != null) {
            first = first.next;
            buff = first;
        }
        first = starter;
        last = buff;
        if (first.next == null || last.next == null) {
            first = null;
            last = null;
        }
        N--;
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
        // TODO code application logic here
    }

}

