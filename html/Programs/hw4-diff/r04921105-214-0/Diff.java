
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  

    private int N;          // size of the stack
    private Node first;     // Position 0 of stack
    private Node last;      // Position N-1 of Stack

    private class Node {
        private Item item;
        private Node next = null;
        private Node previous = null;
    }
    
    public Deque(){
        N = 0;
        first = null;
        last = null;
    }
    
    public int size(){
        return N;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public void addFirst(Item item){
        
        if(first == null) {
            first = new Node();
            first.item = item;
            last = first;
            N++;
            return;
        }
        
        Node oldFirst = first; 
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        oldFirst.previous = first;
        N++; 
    }
    
    public Item removeFirst(){
       
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item =first.item;
        if (first.next != null){
            first = first.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        N--;
        return item;
    }
    
    public void addLast(Item item){  
        if(first == null || last == null) {
            first = new Node();
            first.item = item;
            last = first;
            N++;
            return;
        }
        Node newLast = new Node();
        newLast.item = item;
        newLast.previous = last;
        last.next = newLast;
        last = newLast;
        last.next = null;
        N++;
    }
    
    public Item removeLast() {
        
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item lastItem = last.item;
        if(last.previous != null && first.next != null){
            last = last.previous;
            last.next = null;
        } else{
            last = null;
            first = null;
        }
        N--;
        return lastItem;
    }    
    

    public Iterator<Item> iterator()  { return new ListIterator();  }

    private class ListIterator implements Iterator<Item> {
        public Node current = first;
        public boolean hasNext()  { return current.next != null;                     }
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
