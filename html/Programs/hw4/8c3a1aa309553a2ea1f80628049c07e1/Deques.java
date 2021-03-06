
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author 林康維
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int N;               // number of elements on queue
    

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return N;     
   }
    public void addFirst (Item item){
        if(item == null)throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>() ;
        first.item = item ;
        if(isEmpty()) {
            first = last ;      
        }
        else 
        {
            first.next = oldfirst ;
            oldfirst.previous = first ;
        }
        
        N++ ;
    }
    public void addLast (Item item){
        if(item == null)throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else{
            oldlast.next = last;
            last . previous = oldlast ;
        }
        N++;
    }
    
        public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    public Item removeLast() {///////////////////
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;        // save item to return
        last = last . previous ;
        N--;
        if (isEmpty()) first = null;
        return item;                   // return the saved item
    }
    public Iterator<Item> iterator() { 
        return new ListIterator<Item>(); 
    }
    private class ListIterator<Item> implements Iterator<Item>{
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
       /*public Item previous(){
           Item item = current.item ;
           Item item1 = current.previous.item ;
           current = pre.next ;
           return item1 ;
       }*/
 }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deque test = new Deque( ) ;
        test.addLast(""z"");
        test.addLast(""o"");
        test.addLast(""o"");
        test.addLast(""t"");
        test.addLast(""o"");
        test.addLast(""p"");
        test.addLast(""i"");
        test.addLast(""a"");
        System.out.println(test.first.item) ;
        test.removeLast() ;
        System.out.println(test.last.item) ;
        test.removeFirst() ;
        System.out.println(test.first.item) ;
        test.removeFirst() ;
        System.out.println(test.first.item) ;
        test.removeFirst() ;
        System.out.println(test.first.item) ;
        test.removeFirst() ;
        System.out.println(test.first.item) ;
        test.removeFirst() ;
        System.out.println(test.first.item) ;
        test.removeFirst() ;
        System.out.println(test.first.item) ;
        /*Deque<String> D = new Deque<String>() ;
        while (!StdIn.isEmpty()){
            String item = StdIn.readString() ;
.
        }*/
    }
    
}


