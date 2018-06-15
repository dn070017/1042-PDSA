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
    private int num;
    private int count;
    private Node<Item> first, last;         // top of stack
    //private Node<Item> oldfirst, oldlast;
    Stack<Item> s_last = new Stack<Item>();
    Stack<Item> temp = new Stack<Item>();
    Stack<Item> s_first = new Stack<Item>();

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    public Deque() {
        first = null;
        last = null;
        //oldfirst = null;
        //oldlast = null;
        N = 0;
        num = 0;
        count = 0;
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
            temp.push(item);
            N++;
            count++;
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
            s_last.push(item);
            N++;
            num++;
        }

    }

    public Item removeFirst() {
        if (size() > 0) {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        } else {
            /*while (s_first.pop() != null) {
             s_first.pop();
             }
             while (s_last.pop() != null) {
             s_last.pop();
             }*/
            s_last = new Stack<Item>();
            s_first = new Stack<Item>();
            throw new NoSuchElementException();
        }
    }

    public Item removeLast() {
        while (count > 0) {
            s_first.push(temp.pop());
            count--;
        }
        if (size() > 0) {
            if (num > 0) {
                Item item = s_last.pop();
                N--;
                num--;
                return item;
            } else {
                Item item = s_first.pop();
                N--;
                return item;
            }

        } else {
            /*while (s_first.pop() != null) {
             s_first.pop();
             }
             while (s_last.pop() != null) {
             s_last.pop();
             }*/
            s_last = new Stack<Item>();
            s_first = new Stack<Item>();
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
//        String a = null;
//        String b = ""0"";
//        String c = ""1"";
//        String e = ""2"";
//        d.addFirst(b);
//        d.addLast(c);
//        d.addFirst(e);
//        d.removeFirst();
//        d.removeFirst();
//        d.removeLast();
//        d.removeFirst();
//        System.out.println(d.removeLast());
//
//    }
}

