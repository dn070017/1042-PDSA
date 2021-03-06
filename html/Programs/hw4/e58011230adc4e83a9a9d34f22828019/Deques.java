
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
        last = new Node<Item>();
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
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        oldfirst.front = first;
//        oldfirst.front = first;
//        if (isEmpty()) {
//            last = first;
//        } else {
//            oldfirst.front = first;
//            first = oldfirst.front;
//        }
        N++;

    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
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
            throw new java.lang.UnsupportedOperationException();
        }

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
        try {
            Deque de = new Deque();
            de.addLast(""you"");
            de.addFirst(""are"");
            de.addLast(""good"");
            de.addFirst(""!"");
            System.out.printf(""%s"", de.removeFirst());
            System.out.printf(""%s"", de.removeFirst());
            System.out.printf(""%s"", de.removeFirst());
            System.out.printf(""%s"", de.removeFirst());
            de.removeLast();

        } catch (java.util.NoSuchElementException exception) {
            System.out.printf(""%d"", 1);
        }

    }
}
