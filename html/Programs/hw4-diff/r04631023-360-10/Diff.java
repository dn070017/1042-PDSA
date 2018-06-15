import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node<Item> first = null;
    private Node<Item> last = null;
    private int N = 0;
    
    private class Node<Item> {
        private Item item;
        private Node<Item> next;

        public Node(Item i, Node<Item> n) {
            item = i;
            next = n;
        }
    }

    public Deque() {
    }// construct an empty deque

    public boolean isEmpty() {
        return first == null && last == null;
    } // is the deque empty?


    public int size() {
        return N ;
    }// return the number of items on the deque


    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (first == null) {
            first = new Node<Item>(item, last);
        } else {
            final Node<Item> newFirst = new Node<Item>(item, first);
            first = newFirst;
            if (last == null) {
                last = first.next;
            }
        }
        if (last == null) {
            last = first;
        }
        N++;
    }// add the item to the front


    public void addLast(Item item) {
    if (item == null) {
            throw new NullPointerException();
        }
        if (last == null) {
            last = new Node<Item>(item, null);
            if (first != null) {
                first.next = last;
            }
        } else {
            final Node<Item> newLast = new Node<Item>(item, null);
            final Node<Item> oldLast = last;
            oldLast.next = newLast;
            last = newLast;
            if (first == null) {
                first = oldLast;
            }
        }
        if (first == null) {
            first = last;
        }
        N++;
    }// add the item to the end


    public Item removeFirst() {
    if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<Item> oldFirst = first;
        if (oldFirst == last) {
            first = null;
            last = null;
        } else {
            Node<Item> newFirst = first.next;
            first = newFirst;
        }
        N--;
        return oldFirst.item;
    }// remove and return the item from the front


    public Item removeLast() {
    if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final Node<Item> oldLast = last;
        if (first == last) {
            first = null;
            last = null;
        } else if (first != null) {
            Node<Item> newLast = first;
            while (newLast.next != null && newLast.next != last) {
            newLast = newLast.next;
        }
            newLast.next = null;
            last = newLast;
        } else {
            last = null;
        }
        N--;

        if (oldLast != null) {
            return oldLast.item;
        } else {
            return first.item;
        }
    }// remove and return the item from the end

    public Iterator<Item> iterator() {
        return new NodeIterator<Item>(this);
    }

    private class NodeIterator<Item> implements Iterator<Item> {
        private Node<Item> next;

        public NodeIterator(final Deque<Item> deque) {
            if (deque.first != null) {
                this.next = (Node<Item>) deque.first;
            } else {
                this.next = (Node<Item>) deque.last;
            }
        }
        public boolean hasNext() {
            return next != null;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Item item = next.item;
            next = next.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
