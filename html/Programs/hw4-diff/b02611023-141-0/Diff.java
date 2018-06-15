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

public class Deques<Item> implements Iterable<Item> {

    private int N;                // size of the stack
    private Node<Item> first, last;         // top of stack
    private Node<Item> oldfirst, oldlast;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    public Deques() {
        first = null;
        last = null;
        //oldfirst = null;
        //oldlast = null;
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
            oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = null;
            if (size()==0) {
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
            oldlast = last;
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
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = first.item;;
            first = first.next;
            N--;
            return item;
        }
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = last.item;
            last = oldlast;
            N--;
            return item;
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

    /*public static void main(String[] args) {

        Deques d = new Deques();
        String a = null;
        String b = ""0"";
        String c = ""1"";
        d.addFirst(b);
        d.addFirst(c);
        d.removeLast();
        d.removeFirst();
        System.out.println(d.removeLast());

    }*/
}
