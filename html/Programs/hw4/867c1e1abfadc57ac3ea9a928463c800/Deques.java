
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;



public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;    //first of queue
    private Node<Item> last;     // end of queue
    private int N;               // number of elements on queue

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
        if(item==null) throw new NullPointerException("""");
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;       
        N++;
    }

    public void addLast(Item item) {
        if(item==null) throw new NullPointerException("""");
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }
    
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = last.item;
        last = null;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public static void main(String[] args) {
       
    }
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

}

