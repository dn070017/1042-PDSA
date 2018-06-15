import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable {

    private Node first;
    private Node last = first;
    private int size = 0;

    public static void main(String[] args) throws Exception {
        Deque<Double> abc = new Deque<Double>();
//        abc.removeLast();
        abc.addFirst(null);
        abc.addFirst(0.1);
        abc.addLast(0.2);
        abc.addFirst(0.3);
        Iterator iterator = abc.iterator();

        double a = abc.removeLast();
        abc.addFirst(2.0);

    }

    private class Node {

        Node previous;
        Item item;
        Node next;
    }

    public Deque() {//產生一個空的node
        first = null;
        last = first;
        size = 0;
    }

    public boolean isEmpty() {//回傳此node是否為空
        return first == null;
    }

    public int size() {//回傳此node的大小
        return size;
    }

    public void addFirst(Item item) {//加入一個node在前面     
        if (item != null) {
            if (first == null) {
                first = new Node();
                last = first;
                first.item = item;
                first.next = null;
                first.previous = null;
            } else {
                Node oldFirst = first;
                first = new Node();
                first.item = item;
                first.next = oldFirst;
                oldFirst.previous = first;
                first.previous = null;
            }
            size++;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public void addLast(Item item) {//加入一個node在後面        
        if (item != null) {
            if (first == null) {
                first = new Node();
                last = first;
                first.item = item;
                first.next = null;
                first.previous = null;
            } else {
                Node oldLast = last;
                last = new Node();
                last.item = item;
                oldLast.next = last;
                last.previous = oldLast;
                last.next = null;
            }
            size++;
        }
        else{            
            throw new NoSuchElementException();
        }
    }

    public Item removeFirst() {//回傳最前面的node,並移除他
        try {
            Item item = first.item;
            first = first.next;
            if (first != null) {
                first.previous = null;
            }
            size--;
            return item;
        } catch (NullPointerException e) {
            throw e;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public Item removeLast() throws Exception {//回傳最後面的node,並移除他
        try {
            if (last == null) {
                throw new NullPointerException();
            }
            Item item = last.item;
            last = last.previous;
            if (last != null) {
                last.next = null;
            }
            size--;
            return item;
        } catch (NullPointerException e) {
            throw e;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public Iterator iterator() {//回傳此node裡的每一個物件
        try {
            return new ListIterator();
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    private class ListIterator implements Iterator {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            current.previous = null;
            size--;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

