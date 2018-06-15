import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;    // beginning of deque
    private Node<Item> last;     // end of deque
    private int N;               // number of elements on deque
    
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
    public Deque(){                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty() {                 // is the deque empty?
        return (first == null || last == null);
    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {         // add the item to the front
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.previous = null;
        if (isEmpty()){last = first;}
        else{first.next = oldfirst;}
        N++;
    }

    public void addLast(Item item) {           // add the item to the end
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()){first = last;}
        else {last.previous = oldlast;}
        N++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");
        Item item = last.item;
        last = last.previous;
        N--;
        if (isEmpty()) first = null;
        return item;
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new ListIterator<Item>(first);
    }
    
    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current ;
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
    
//    public static void main(String[] args) {
//
//    }

}
