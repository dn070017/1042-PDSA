public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first; // beginning of queue
    private Node<Item> last; // end of queue
    private int N; // number of elements on queue
// helper linked list class

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
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
        return N == 0;
    }

    /**
.
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }
    
    
     public void addFirst(Item item)    {      // add the item to the front
         if (item==null) throw new NullPointerException(""Deque underflow"");  
          Deque.Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        N++;
     }
     
     public void addLast(Item item)  {         // add the item to the end
          if (item==null) throw new NullPointerException(""Deque underflow"");  
     Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
     }
     
     public Item removeFirst() {               // remove and return the item from the front
       if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");  
       Item item = first.item;
       first = first.next;            // delete first node
        N--;
        if (isEmpty()) last = null; 
        return item;   
     }
     
     
     public Item removeLast() {                // remove and return the item from the end
       if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");  
       Item item = last.item;
      last = last.previous;
      N--;
       if (isEmpty()) last = null; 
       return item;
     }
     
     
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
// an iterator, doesn't implement remove() since it's optional

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
    
    public static void main(String[] args) {  // unit testing
Deque<String> d = new Deque<String>();
d.addLast(""3"");
 String item = ""1 "";
d.addLast(item);
System.out.println(""("" + d.size() + "" left on queue)"");
System.out.println(""("" + d.removeFirst() + "" is the answer"");

    }
}

