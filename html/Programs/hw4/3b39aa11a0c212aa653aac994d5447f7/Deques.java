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
        first = new Node<Item>();
        last  = new Node<Item>();
        first.item = null;
        first.previous = null;
        first.next = last;
        last.item = null;
        last.previous = first;
        last.next = null;
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
        Node<Item> lastItem = new Node<Item>();
        lastItem.item = item;
        lastItem.next = last;
        lastItem.previous = last.previous;
        last.previous.next = lastItem;
        last.previous = lastItem;
        N++;
    }          // add the item to the end

   public void addFirst(Item item){
        if(item == null) throw new NullPointerException();
        Node<Item> firstItem = new Node<Item>();
        firstItem.item = item;
        firstItem.next = first.next;
        firstItem.previous = first;
        first.next.previous = firstItem;
        first.next = firstItem;
        N++;
    }             // add the item to the front

   public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<Item> firstItem = first.next;
        firstItem.next.previous = first;
        first.next = firstItem.next;
        N--;
        return firstItem.item;
    }                // remove and return the item from the front

   public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<Item> lastItem = last.previous;
        lastItem.previous.next = last;
        last.previous = lastItem.previous;
        N--;
        return lastItem.item;
    }                 // remove and return the item from the end

   public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);  
    }         // return an iterator over items in order from front to end
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current.next.item != null;                     }
        public void remove()      { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            //Item item = current.item;
            current = current.next; 
            return current.item;
        }
    }   
    
   public static void main(String[] args) {

    }  

}



