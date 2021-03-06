
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1042 PDSA hw04_Deque
 *
 * @author Robert
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int N;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> pre;
    }

    public Deque() {
        // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        // is the deque empty?
        return first == null || last == null;
        //return size()==0;
    }

    public int size() {
        // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.pre = null;
        if (last == null) {
            last = first;
        }
        else {
            oldfirst.pre = first;
        }
        N++;
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node<Item> oldlast = last;
        // new a memory to store
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        if (first == null) {
            first = last;
        }
        else {
            oldlast.next = last;    
        }
        N++;
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        if (first == last){
            last = null;
            first.next = null;
        }
        first = first.next;
        N--;
        return item;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        if (first == last){
            first = null;
            last.pre = null;
        }
        last = last.pre;
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
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

    public static void main(String[] args) {
        // Test first in first out
        Deque<Integer> q = new Deque<Integer> ();
        q.addLast(2);
        q.addLast(3);
//        q.addLast(4);
//        q.addFirst(4);
//        q.addFirst(3);

        StdOut.println(q.removeFirst());
        StdOut.println(q.removeLast());
        q.addLast(5);
        q.addLast(6);
        StdOut.println(q.removeLast());
        StdOut.println(q.removeFirst());
//        StdOut.println(q.removeFirst());
        StdOut.println(""("" + q.size() + "" left on queue)"");
    }

}

