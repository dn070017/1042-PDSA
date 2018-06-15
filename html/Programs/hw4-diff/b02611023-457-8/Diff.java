/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;                // size of the stack
    private Node<Item> first, last;         // top of stack

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
        return first == null;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = null;
            if (size() == 0) {
                last = first;
            } else {
                first.next = oldfirst;
            }
            N++;
        }

    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) {
                first = last;
            } else {
                oldlast.next = last;
            }
            N++;
        }

    }

    public Item removeFirst() {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        } else {
            throw new NoSuchElementException();
        }
    }

     public Item removeLast() {
        if (!isEmpty()) {
            Node<Item> find = first;
            if (size() == 1) {
                Item item = find.item;
                first = null;
                N--;
                return item;
            } else {
                while (find.next != null) {
                    if (find.next.next == null) {
                        break;
                    }
                    find = find.next;
                }
                Item item = find.next.item;
                find.next = null;
                N--;
                return item;
            }

        } else {
            throw new NoSuchElementException();
        }
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

//    public static void main(String[] args) {
//
//        Deque<String> d = new Deque<String>();
//        String b = ""5"";
//        String c = ""1"";
//        String e = ""2"";
//        String f = ""3"";
//        d.addFirst(b);
//        d.addLast(c);
//        d.addLast(e);
//        d.removeLast();
//        d.removeFirst();
//        d.removeLast();
//        System.out.println(d.removeLast());
        //System.out.println(d.removeLast());
//        Iterator a = d.iterator();
//        System.out.println(a.next());
//        System.out.println(a.next());
//        d.removeFirst();
//        d.removeLast();
//        System.out.println(a.next());
//    }
}

