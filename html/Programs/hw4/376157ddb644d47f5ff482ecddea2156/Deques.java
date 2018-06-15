
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first = null;
    private Node<Item> last = null;
    private int N = 0;

    private class Node<Item> {

        private Item item;
        private Node<Item> next;

        public Node(Item i, Node<Item> j) {
            item = i;
            next = j;
        }
    }

    public Deque() {
    }// construct an empty deque

    public boolean isEmpty() {
        return first == null;
    } // is the deque empty?

    public int size() {
        return N;
    }// return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        N++;
        if (first == null) {
            first = new Node<Item>(item, last);
            last = first;
        } else {
            Node<Item> newFirst = new Node<Item>(item, first);
            if (last == first) {
                first = newFirst;
                last = first.next;
            } else {
                newFirst.next = first;
                first = newFirst;
            }
        }
    }// add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        N++;
        if (last == null) {
            last = new Node<Item>(item, null);
            first = last;
        } else {
            Node<Item> newLast = new Node<Item>(item, null);
            if (last == first) {
                last = newLast;
                first.next = last;
            } else {
                last.next = newLast;
                last = newLast;
            }
        }
    }// add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        N--;
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        return item;
    }// remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        N--;
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
            return item;
        } else {
            Node<Item> newLast = first;
            while (newLast.next != last) {
                newLast = newLast.next;
            }
            newLast.next = null;
            last = newLast;
            return item;
        }
    }// remove and return the item from the end

    public Iterator<Item> iterator() {
        return new NodeIterator<Item>(this);
    }

    private class NodeIterator<Item> implements Iterator<Item> {

        private Node<Item> next;

        public NodeIterator(Deque<Item> deque) {
                this.next = (Node<Item>) deque.first;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = next.item;
            next = next.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

