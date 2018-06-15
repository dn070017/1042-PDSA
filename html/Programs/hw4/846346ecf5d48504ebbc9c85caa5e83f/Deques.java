
import java.util.Iterator;

public class Deque<T> implements Iterable<T> {

    private class Node {
        T value;
        Node previous;
        Node next;
    }

    int N;
    Node head;
    Node tail;

    public Deque() {
        N = 0;

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(T t) {
        if (t == null) {
            throw new NullPointerException();
        }

        N++;

        Node item = new Node();
        item.value = t;

        Node oldFirst = head.next;
        head.next = item;
        item.next = oldFirst;
        oldFirst.previous = item;
        item.previous = head;
    }

    public void addLast(T t) {
        if (t == null) {
            throw new java.lang.NullPointerException();
        }

        N++;

        Node item = new Node();
        item.value = t;

        Node oldLast = tail.previous;
        tail.previous = item;
        item.previous = oldLast;
        oldLast.next = item;
        item.next = tail;
    }

    public T removeFirst() {
        if (head.next == tail) {
            throw new java.util.NoSuchElementException();
        }

        N--;

        Node oldFirst = head.next;

        head.next = oldFirst.next;
        head.next.previous = head;

        return oldFirst.value;
    }

    public T removeLast() {
        if (head.next == tail) {
            throw new java.util.NoSuchElementException();
        }

        N--;

        Node oldLast = tail.previous;

        tail.previous = oldLast.previous;
        tail.previous.next = tail;

        return oldLast.value;
    }

    private class NodeIterator implements Iterator<T> {
        Node pointer = head;

        public boolean hasNext() {
            return pointer.next != tail;
        }

        public T next() {
            pointer = pointer.next;
            if (pointer == tail) {
                throw new java.util.NoSuchElementException();
            }

            return pointer.value;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public Iterator<T> iterator() {
        return new NodeIterator();
    }

    public static void main(String[] args) {

        /*
        java.lang.NullPointerException if the client attempts to add a null item;
        java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
        java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
        java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return
        */

        try { // add null
            Deque<Integer> dq = new Deque<>();
            dq.addFirst(null);
        } catch (Exception e) {
            System.out.println(""Expecting: java.lang.NullPointerException"");
            System.out.println(""Result is: "" + e);
        }

        try { // add null
            Deque<Integer> dq = new Deque<>();
            dq.addLast(null);
        } catch (Exception e) {
            System.out.println(""Expecting: java.lang.NullPointerException"");
            System.out.println(""Result is: "" + e);
        }

        try { // remove on empty
            Deque<Integer> dq = new Deque<>();
            Integer popped = dq.removeFirst();
            System.out.println(popped);
        } catch (Exception e) {
            System.out.println(""Expecting: java.lang.NoSuchElementException"");
            System.out.println(""Result is: "" + e);
        }

        try { // remove on empty
            Deque<Integer> dq = new Deque<>();
            Integer popped = dq.removeLast();
            System.out.println(popped);
        } catch (Exception e) {
            System.out.println(""Expecting: java.lang.NoSuchElementException"");
            System.out.println(""Result is: "" + e);
        }

        try { // call iterator remove
            Deque<Integer> dq = new Deque<>();
            Iterator<Integer> it = dq.iterator();
            it.remove();
        } catch (Exception e) {
            System.out.println(""Expecting: java.lang.UnsupportedOperationException"");
            System.out.println(""Result is: "" + e);
        }

        try { // call iterator next on null
            Deque<Integer> dq = new Deque<>();
            Iterator<Integer> it = dq.iterator();
            it.next();
        } catch (Exception e) {
            System.out.println(""Expecting: java.lang.NoSuchElementException"");
            System.out.println(""Result is: "" + e);
        }

        try { // push first, pop first
            Deque<Integer> dq = new Deque<>();
            dq.addFirst(new Integer(1));
            Integer popped = dq.removeFirst();
            System.out.println(popped);
        } catch (Exception e) {
            System.out.println(""Error!!!"");
            System.out.println(e);
        }

        try { // iterator
            Deque<Integer> dq = new Deque<>();
            for (int i=0; i<5; ++i) {
                dq.addFirst(new Integer(i));
            }
            for (int i=10; i<15; ++i) {
                dq.addLast(new Integer(i));
            }

            Iterator<Integer> it = dq.iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + "" "");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(""Error!!!"");
            System.out.println(e);
        }

        try { // push last, pop first
            Deque<Integer> dq = new Deque<>();
            dq.addLast(new Integer(1));
            Integer popped = dq.removeFirst();
            System.out.println(popped);
        } catch (Exception e) {
            System.out.println(""Error!!!"");
            System.out.println(e);
        }
    }

}
