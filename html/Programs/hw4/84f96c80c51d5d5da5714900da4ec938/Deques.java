import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Sophia
 */
public class Deque<Item> implements Iterable<Item>{
    private int N;
    private Node first;
    private Node last;
    
    private class Node{
        private Item item;
        private Node next;
        private Node previous;
    
    }
    
    
    public Deque(){
        first = null;
        last = null;
        N = 0;
    
    }
    public boolean isEmpty(){
        return first == null;
    }
   // return the number of items on the deque
   public int size(){
       return N;
   }
   // add the item to the front
   public void addFirst(Item item){
       if(item == null){
           throw new java.lang.NullPointerException();
       }
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.previous = null;
       if(last == null){
           first.next = null;
           last = first;
       }else{
           first.next = oldfirst;
           oldfirst.previous = first;
       }
       N++;
   }
   // add the item to the end
   public void addLast(Item item){
       if(item == null){
           throw new java.lang.NullPointerException();
       }
       Node oldlast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       if(first == null){
           last.previous = null;
           last = first;
       }else{
           last.previous = oldlast;
           oldlast.next = last;
       }
       N++;
   }
   // remove and return the item from the front
   public Item removeFirst(){
       if(first == null){
           throw new java.util.NoSuchElementException();
       }
       Item item = first.item;
       first = first.next;

       N--;
       if(first == null){
           last = null;
       }else{
           first.previous = null;
       }
       return item;
   }
   
// remove and return the item from the end
   public Item removeLast(){
   if(last == null){
           throw new java.util.NoSuchElementException();
       }
       Item item = last.item;
       last = last.previous;
       N--;
       if(last == null){
           first = null;
       }else{
           last.next = null;
       }
       return item;
   
   }
   // unit testing


    @Override
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public Iterator remove() {
        throw new UnsupportedOperationException();
    }
       public static void main(String[] args){
           Deque<String> d = new Deque<String>();
           d.addFirst(""3"");
           d.addFirst(""5"");
           //d.addFirst(""5"");
           System.out.println(d.removeFirst());
           System.out.println(d.removeLast());
           
   
   }  
    
    
}

