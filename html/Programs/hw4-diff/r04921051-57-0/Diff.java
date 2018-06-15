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

   public void addLast(Item item){
        if(item == null) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }          // add the item to the end

   public void addFirst(Item item){
        if(item == null) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.previous = null;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        else           oldfirst.previous = first;
        N++;
    }             // add the item to the front

   public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        first.previous = null;
        N--;
        return item;
    }                // remove and return the item from the front

   public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        last.next = null;
        N--;
        return item;
    }                 // remove and return the item from the end

   public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);  
    }         // return an iterator over items in order from front to end
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current.next != null;                     }
        public void remove()      { throw new UnsupportedOperationException(); }

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



