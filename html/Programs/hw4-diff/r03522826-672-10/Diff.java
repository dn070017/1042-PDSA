import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable<Item>
{
    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private Node<Item> temp;     // pre end of queue
    
   private static class Node<Item> {
        private Item item;
        public Node<Item> left, right;
        public Node(Item item) {
            // FIXME: maybe it's a bad practice to throw exception in constructor
            if (item == null) { throw new NullPointerException(); }
            this.item = item;
        }
        public void connectRight(Node<Item> other) {
            this.right = other;
            other.left = this;
        }
    }
   
   public Deque(){
        first = null;
        last  = null;
        temp  = null;
        N = 0;
    }                          // construct an empty deque

   public boolean isEmpty() // is the deque empty? 
   {
        return first == null;
    }         
   public void checkInvariants() {
    assert N >= 0;
    assert N > 0 || (first == null && last == null);
    assert (first == null && last == null) || (first != null && last != null);
  }

   public int size()  // return the number of items on the deque
   {
        return N;
    }                      

   public void addFirst(Item item)          // add the item to the front
   {
        Node<Item> prevHead = first;
        Node<Item> newHead = new Node<Item>(item);
        if (prevHead != null) {
          newHead.connectRight(prevHead);
        } else {
          last = newHead;
        }
        first = newHead;
         N++;
         checkInvariants();
   }

   public void addLast(Item item)           // add the item to the end
   {
        Node<Item> newTail = new Node<Item>(item);
        Node<Item> prevTail = last;
        if (prevTail != null) {
          prevTail.connectRight(newTail);
        } else {
          first = newTail;
        }
        last = newTail;
        N++;
        checkInvariants();
   }
   public Item removeFirst()                // remove and return the item from the front
   {
        if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
        Item item = first.item;        // save item to return
        first = first.right;            // delete first node
        
        N--;
        checkInvariants();
        if(N==0){first = null; last = null;}
        return item;
   }
   public Item removeLast()                 // remove and return the item from the end
   {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
        N--;
        Node<Item> prevTail = last;
        last = prevTail.left;
        prevTail.left = null;
        if (last != null) last.right = null;
        checkInvariants();
        if(N==0){first = null; last = null;}
        return prevTail.item;
   }
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Deque.Node<Item> current;

        public ListIterator(Deque.Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.right; 
            return item;
        }
    }
   public static void main(String[] args)   // unit testing
   {
            Deque <String> s = new Deque<String>();
            s.addFirst(""2"");
            s.addFirst(""1"");
            s.addLast(""3"");
            s.addLast(""4"");
            
            System.out.println(s.removeFirst());
            System.out.println(s.removeFirst());
            System.out.println(s.removeFirst());
            System.out.println(s.removeFirst());
            
            System.out.println(s.N);
//          System.out.println(s.N);
            
   }
}
