
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> pre;
    }

    /**
.
     */
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
.
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }

    /**
.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
//    public Item peek() {
//        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
//        return first.item;
//    }

    
    public void addFirst(Item item)          // add the item to the front
    {
        if(item == null)  throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.pre = null;
        if (isEmpty()) last = first;
        else           oldfirst.pre = first;
        N++;
    }
    public void addLast(Item item)           // add the item to the end
    {   
        if(item == null)  throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }
    public Item removeFirst()                // remove and return the item from the front
    {   
        
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        
        N--;
        if (isEmpty()) 
        {
            first = null;
            last = null;
        }           // to avoid loitering
        else first.pre = null;
        return item;
    
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.pre;
        
        N--;
        if (isEmpty()) 
        {
            first = null;
            last = null;
        }           
        else last.next = null;
        return item;
    }
            /**
.
     * @param item the item to add
     */
   /* public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }*/

    /**
.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    /*public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }*/

    /**
.
     * @return the sequence of items in FIFO order, separated by spaces
     */
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (Item item : this)
//            s.append(item + "" "");
//        return s.toString();
//    } 

    /**
.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    @Override
    public Iterator<Item> iterator()  {
        return new ListIterator<>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext()  { return current != null;                     }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
.
     */
    public static void main(String[] args) {
//        Deque<String> q = new Deque<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals(""-"")) q.enqueue(item);
//            else if (!q.isEmpty()) StdOut.print(q.dequeue() + "" "");
//        }
//        StdOut.println(""("" + q.size() + "" left on queue)"");
    }
}

