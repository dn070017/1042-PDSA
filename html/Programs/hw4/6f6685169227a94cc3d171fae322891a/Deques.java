import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import  java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable<Item>
{
    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private Node<Item> temp;     // pre end of queue
    
   private static class Node<Item> {
        private Item item;
        private Node<Item> next;
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

   public int size()  // return the number of items on the deque
   {
        return N;
    }                      

   public void addFirst(Item item)          // add the item to the front
   {
       Deque.Node<Item> oldfirst = first;
        if(last == null){last = first;}
        first = new Deque.Node<Item>();
        first.item = item;
        first.next = oldfirst;
        
        N++;
   }

   public void addLast(Item item)           // add the item to the end
   {
        Deque.Node<Item> oldlast = last; 
        last = new Deque.Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else         oldlast.next = last;
        N++;
   
   }
   public Item removeFirst()                // remove and return the item from the front
   {
        if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        
        N--;
        return item;
   }
   public Item removeLast()                 // remove and return the item from the end
   {
       if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
       Item item = last.item;
       temp = first;
       
       for(int i=1;i<N-1;i++){
           temp = temp.next;
       }
       last = temp;
       last.next = null;
       temp = null;
       N--;
       if(N==0){first = null; last = null;}
       return item;
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
            current = current.next; 
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
            
            System.out.println(s.removeLast());
            System.out.println(s.removeLast());
            System.out.println(s.removeLast());
            System.out.println(s.removeLast());
            System.out.println(s.removeLast());
            System.out.println(s.N);
//            System.out.println(s.N);
            
   }
}

