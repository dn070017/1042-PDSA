import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static final class NodeIterator<Item> implements Iterator<Item> {
        private Node<Item> next;

        public NodeIterator(final Deque<Item> deque) {
            if (deque.first != null) {
                this.next = deque.first;
            } else {
                this.next = deque.last;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Item item = next.item;
            next = next.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static final class Node<Item> {
        private Item item;
        private Node<Item> next;

        public Node(final Item i, final Node<Item> n) {
            item = i;
            next = n;
        }
    }

    private Node<Item> first = null;
    private Node<Item> last = null;
    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public int size() {
        return size;
    }
    
    public void addFirst(final Item item) {
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
        
        size++;
    }

    public void addLast(final Item item) {
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
        size++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        final Node<Item> oldFirst = first;
        if (oldFirst == last) {
            first = null;
            last = null;
        } else {
            final Node<Item> newFirst = first.next;
            first = newFirst;
        }
        size--;
        return oldFirst.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final Node<Item> oldLast = last;

        if (first == last) {
            first = null;
            last = null;
        } else if (first != null) {
            final Node<Item> newLast = findPenultimateNode();
            newLast.next = null;
            last = newLast;
        } else {
            last = null;
        }
        size--;

        if (oldLast != null) {
            return oldLast.item;
        } else {
            return first.item;
        }
    }

    public Iterator<Item> iterator() {
        return new NodeIterator<Item>(this);
    }

    private Node<Item> findPenultimateNode() {
        Node<Item> newLast = first;
        while (newLast.next != null && newLast.next != last) {
            newLast = newLast.next;
        }
        return newLast;
    }
}
