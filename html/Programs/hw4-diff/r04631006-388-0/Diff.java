public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    public Deque() { // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return first == null;

    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {          // add the item to the front
        if (item == null) {
            throw new  java.lang.NullPointerException();
        }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null) {
            throw new  java.lang.NullPointerException();
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;

    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.next;
        N--;
        if (isEmpty()) {
            first = null;   // to avoid loitering
        }
        return item;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
            last = null;   // to avoid loitering
        }
        return item;
    }

    public Iterator<Item> iterator() {      // return an iterator over items in order from front to end
        return new ListIterator<Item>(first);

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

    public static void main(String[] args) {   // unit testing

    }
}

