
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;

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

    public void addFirst(Item item) {    //push
        if (item != null) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            N++;
        } else {
            throw new NullPointerException(""add null"");
        }
    }

    public void addLast(Item item) {     //enquene
        if (item != null) {
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
        else {
            throw new NullPointerException(""add null"");
        }
    }

    public Item removeFirst() {  //dequene
        if (isEmpty()) {
            throw new NoSuchElementException(""Queue underflow"");
        }
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
            last = null;
        }   // to avoid loitering

        return item;
    }

    public Item removeLast() {   //pop
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;

        return item;
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

    public static void main(String[] args) {
//        Queue<String> q = new Queue<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals(""-"")) {
//                q.enqueue(item);
//            }
//            else if (!q.isEmpty()) {
//                StdOut.print(q.dequeue() + "" "");
//            }
//        }
//        StdOut.println(""("" + q.size() + "" left on queue)"");       
//        
        Deque<String> d = new Deque<String>();

        d.addLast(""1"");
        //d.addFirst("""");
        if (d.isEmpty()) {
            System.out.printf(""is Empty"");
        } else {
            System.out.printf(""Not empty"");
        }

        System.out.printf(d.removeFirst());
        System.out.printf(d.removeFirst());
        
    }

}

