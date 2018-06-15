import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author 林康維
 */
public class Deque<Item> implements Iterable {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int N;               // number of elements on queue
    

    private static class Node<Item> {
        private Item item;
        private Node next;
        private Node previous;
    }
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return (first == null || last == null);
    }
    public int size() {
        return N;     
   }
    public void addFirst (Item item){
        if(item == null)throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node() ;
        first.item = item ;
        first.previous = null ;
        first.next = null ;     
        if(isEmpty()) {
            last = first ;    
        }
        else
        {
            first.next = oldfirst ;
            oldfirst.previous = first  ;
        }
        
        N++ ;
    }
    public void addLast (Item item){
        if(item == null)throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = null ;
        last.next = null;
        if (isEmpty()) {
            first = last ;    
        }
        else{
            oldlast.next = last;
            last . previous = oldlast ;
        }
        N++;
    }
    
        public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        if(N==1){
        Item item = first.item;
            first = null ;
            last = null ;
            N = 0 ;
            return item;
        }
        else{
        Item item = first.item;
        first = first.next;
        first.previous = null ;
        N--;
        //if (isEmpty()) last = null;   // to avoid loitering
        return item;
                 }
    }
    public Item removeLast() {///////////////////
        if (isEmpty()) throw new NoSuchElementException();
        if(N==1){
        Item item = last.item;
            first = null ;
            last = null ;
            N = 0 ;
            return item;
        }
        else{
        Item item = last.item;        // save item to return
        last = last . previous ;
        last.next = null ;
        N--;
        //if (isEmpty()) first = null;
        return item;                   // return the saved item
        }
        }

        
    public Iterator iterator() { 
        return new ListIterator(); 
    }
    private class ListIterator implements Iterator{
       private Node<Item> current ;
       
       public boolean hasNext() { return current != null; }
       
       public void remove() {  throw new UnsupportedOperationException();  }
       
       public Item next()
    {
        if (!hasNext()) throw new NoSuchElementException();
        
        Item item = current.item;
        current = current.next;
        return item;
        
    }
       
 }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
    }
}    



