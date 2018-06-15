import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private int n; 
    private Node<Item> first; 
    private Node<Item> last;
    
    private class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> pre;
    }
    public Deque() {
        first = new Node<Item>();
        last  = new Node<Item>();
        first.item = null;
        first.pre = null;
        first.next = last;
        last.item = null;
        last.pre = first;
        last.next = null;
        n = 0;        
    }

   public boolean isEmpty(){
        return (n == 0);
    }                 
         
   public int size(){
        return n;     
    }                      

   public void addLast(Item item){
        if(item == null) throw new NullPointerException();
        Node<Item> lastItem = new Node<Item>();
        lastItem.item = item;
        lastItem.next = last;
        lastItem.pre = last.pre;
        last.pre.next = lastItem;
        last.pre = lastItem;
        n++;
    }       

   public void addFirst(Item item){
        if(item == null) throw new NullPointerException();
        Node<Item> firstItem = new Node<Item>();
        firstItem.item = item;
        firstItem.next = first.next;
        firstItem.pre = first;
        first.next.pre = firstItem;
        first.next = firstItem;
        n++;
    }             

   public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<Item> firstItem = first.next;
        firstItem.next.pre = first;
        first.next = firstItem.next;
        n--;
        return firstItem.item;
    }               

   public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<Item> lastItem = last.pre;
        lastItem.pre.next = last;
        last.pre = lastItem.pre;
        n--;
        return lastItem.item;
    }                 

   public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);  
    }         
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current.next.item != null;                     }
        public void remove()      { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!isEmpty()) throw new NoSuchElementException();
 
            current = current.next; 
            return current.item;
        }
    }   
    
   public static void main(String[] args) {
        
    }  

}



