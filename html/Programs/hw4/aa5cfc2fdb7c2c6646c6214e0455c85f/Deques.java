
import java.util.Iterator;
import java.util.*;//NoSuchElementException;
import java.util.Scanner;

public class Deque<Item> implements Iterable {

    private Node first, last;
    static int num;

    private class Node {

        Item item;
        Node next;
        Node pre;
    }

    public Deque() {
        this.first = null;
        this.last = null;

    }// construct an empty deque

    public boolean isEmpty() {// is the deque empty?
        return num == 0;
    }

    public int size() {                        // return the number of items on the deque
        return num;

    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new NullPointerException();
        } else {
            Node newnode = new Node();
            newnode.item = item;
            if (isEmpty()) {
                newnode.pre = null;
                newnode.next = null;
                first = newnode;
                last = newnode;
            } else {
                newnode.next = first;
                newnode.pre = null;
                first.pre = newnode;
                first = newnode;
            }

            num++;
        }
    }// add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node newnode = new Node();
            newnode.item = item;
            if (isEmpty()) {
                newnode.pre = null;
                newnode.next = null;
                first = newnode;
                last = newnode;
            } else {
                last.next = newnode;
                newnode.pre = last;
                newnode.next = null;
                last = newnode;
            }
            num++;
        }

    }// add the item to the end

    public Item removeFirst() {               // remove and return the item from the front
        if (first == null) {
            throw new NoSuchElementException();
        } else {
            Item item = first.item;
            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.pre = null;
            }
            num--;
            return item;
        }
    }

    public Item removeLast() {                 // remove and return the item from the end

        if (last == null || num == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = last.item;
            if (first == last) {
                first = null;
                last = null;
            } else {
                last = last.pre;
                last.next=null;
            }
            num--;
            return item;
        }
    }

    public Iterator iterator() {      // return an iterator over items in order from front to end

        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { /* not supported */

            throw new UnsupportedOperationException();

        }

        public Item next() {
            if (current.item == null) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {   // unit testing

        Deque aa = new Deque();

        Scanner scanner = new Scanner(System.in);
        String ss = ""aa"";
        try {
//            aa.addFirst(""ss"");

            aa.addFirst(scanner.nextLine());
            aa.addFirst(scanner.nextLine());
            aa.addFirst(scanner.nextLine());

//            StdOut.print(aa.removeLast());
            StdOut.print(aa.removeLast());
            StdOut.print(aa.removeLast());
            StdOut.print(aa.removeLast());
//            Iterator iterator = aa.iterator();
//            while (iterator.hasNext()) {
//                StdOut.print(iterator.next());
//            }
        } catch (NoSuchElementException exx) {
            StdOut.print(""remove 為 null"");
        } catch (NullPointerException ex) {
            StdOut.print(""輸入為NULL"");
        }

    }

}

