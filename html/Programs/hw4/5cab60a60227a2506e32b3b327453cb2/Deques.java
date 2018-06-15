import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private int N; 
    private Node<Item> first; 
    private Node<Item> last;
    
    private class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
    public Deque() {
        first = null;
        last  = null;
        N = 0;        
    }

   public boolean isEmpty(){
        return (N == 0);
    }                 

         
   public int size(){
        return N;     
    }                        // return the number of items on the deque

   public void addFirst(Item item){
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }          // add the item to the front

   public void addLast(Item item){
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = null;
        if (isEmpty()) first = last;
        else           oldfirst.previous = first;
        N++;
    }             // add the item to the end

   public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }                // remove and return the item from the front

   public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = last.item;
        last = last.previous;
        N--;
        if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }                 // remove and return the item from the end

   public Iterator iterator(){
        return new ListIterator<Item>(first);  
    }         // return an iterator over items in order from front to end
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
    
   public static void main(String[] args) {

    }  

}



