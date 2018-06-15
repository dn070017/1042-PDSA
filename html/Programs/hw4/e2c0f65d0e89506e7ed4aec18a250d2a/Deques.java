
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.soap.Node;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;     // top of stack
    private int N;                // size of the stack

    // helper linked list class
    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> pervious;
    }

    public Deque() // construct an empty deque
    {
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

    public void addFirst(Item item) // add the item to the front
    {
        Deque.Node<Item> oldfirst = first;
        first = new Deque.Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.pervious = null;
        N++;
    }

    public void addLast(Item item) // add the item to the end
    {
        Deque.Node<Item> newlast = new Deque.Node<Item>();
        newlast.item = item;
        newlast.pervious = last;
        last.next = newlast;
        N++;
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }

//    public Item removeLast() // remove and return the item from the end
//    {
//
//    }
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Deque.Node<Item> current;

        public ListIterator(Deque.Node<Item> first) {
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

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            String fund = br.readLine();
            String[] cha = fund.split("" "");
            Deque d = new Deque();
            d.addFirst(cha[0]);
            d.addFirst(cha[1]);
            d.iterator();
            Iterator e=d.iterator();
            while (e.hasNext()) {
                StdOut.println(e.next());
            }
        }

    }
}

