import java.util.Iterator;
import java.util.NoSuchElementException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author jerry
 */
public class Deque<Item> implements Iterable<Item>{
    private int N;
    private Node header ;
    private Node tailer;
     
    public class Node{
    public Item item;
    public Node next;
    public Node prev;
    
    public Node(){
    item = null;
    next = null;
    prev = null;
    }
    
    public Node(Item item, Node n, Node p){
    item = item;
    next = n;
    prev = p; 
    }
    }    

    
     public Deque() {
       header = new Node();
       tailer = new Node();
       header.next = tailer;
       tailer.prev = header;
        N = 0;
    }
     
     public boolean isEmpty() {
         if (N == 0) return true;
         else return false;
    }   
     
    public int size() {
        return N;     
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        return header.next.item;
    }
    
    public void addFirst(Item item){
    if(item == null) throw new NullPointerException();
    Node second = header.next;
    Node first = new Node();
    first.item = item;
    first.next = second;
    first.prev = header;
    second.prev = first;
    header.next = first;
    N++;
    }
    
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Node first  = header.next;
        Item item = first.item;
        Node second = first.next;
        header.next = second;
        second.prev = header;
        return item;
    }
    
    public void addLast(Item item) {
        if(item == null) throw new NullPointerException();
        Node oldlast = tailer.prev;
        Node last = new Node();
        last.item = item;
        last.next = tailer;
        last.prev = oldlast;
        oldlast.next = last;
        tailer.prev = last;
        N++;
    }
    
        public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Node last = tailer.prev;
        Item item = last.item;
        Node secondlast = last.prev;
        tailer.prev = secondlast;
        secondlast.next = tailer;      
        return item;
    }
        
 public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = header.next;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }     
        
    
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(3);
        test.addFirst(5);
        test.addLast(7);
        test.addLast(10);
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        System.out.println(test.removeLast());
        System.out.println(test.removeLast());
    }
}

