
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> front;
    }

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {

        return N == 0;

    }

    public int size() {
        return N;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
            last.front = oldlast;
        }
        N++;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.front = first;

        }
        N++;

    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last.next=null;
        last = last.front;

        N--;
        if (N == 1) {
            first = last;
        }
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return item;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first.front=null;
        first = first.next;

        N--;
        if (N == 1) {
            last = first;
        }

        if (isEmpty()) {
            last = null;
            first = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);

    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }
}
