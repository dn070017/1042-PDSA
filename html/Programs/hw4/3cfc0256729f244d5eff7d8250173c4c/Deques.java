import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
//import java.lang.NullPointerException;
//import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
   
    private int N;          // size of the stack
    private Node first;     // Position 0 of stack
    private Node last;      // Position N-1 of Stack
    private Node debug;

    // helper linked list class
    private class Node {
        private Item item;
        private Node next = null;
        private Node previous = null;
    }
    
    public Deque(){
        N = 0;
        first = null;
        last = null;
        debug = new Node();
        debug.item = (Item) ""Empty stack"";
    }
    
    public int size(){
        System.out.print(""size=""+N);
        return N;
    }
    
    public boolean isEmpty(){
        boolean empty = (first == null)? true : false;
         System.out.println(""Is Empty: "" + empty);
        
        return first == null;
        
            
        
    }
    
    public void addFirst(Item item){
        System.out.println(""Add First"");
        if(first == null) {
            first = new Node();
            first.item = item;
            last = first;
            N++;
            //System.out.println(""Message: First was equal to NULL."");
            test();
            return;
        }
        
        Node oldFirst = first; 
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        oldFirst.previous = first;
        N++;
        test();  
    }
    
    public Item removeFirst(){
        System.out.println(""Remove First"");       
        if (isEmpty()){
            // new NoSuchElementException(""The Queue is empty you big dummy"");
            return debug.item;
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
        test();
        return item;
    }
    
    public void addLast(Item item){
        System.out.println(""add last"");        
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
        test();
    }
    
    public Item removeLast() {
        System.out.println(""remove last"");
        if (isEmpty()){
            new NoSuchElementException(""The Queue is empty you big dummy"");
            return debug.item;
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
        test();
        return lastItem;
    }    
    
    /**
.
     */
    public Iterator<Item> iterator()  { return new ListIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        //change to private not
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

     public void test() {
         boolean empty = (first == null)? true : false;
         //System.out.println(""Is Empty: "" + empty);
         //System.out.println(""Stack size: "" +N);
         System.out.print(""Stack: First > "");
         if (N >0){
             Iterator iter =  iterator();
             while(iter.hasNext() ){
                 System.out.print(iter.next());
                 System.out.print("" | "");
             }
         }
         if(last != null){
             System.out.print(last.item);
         }
         System.out.println("" < Last"");           
    }
    
    public static void main(String[] args) {
        
        Deque deq = new Deque();
        deq.iterator();
        //deq.size();
        //deq.isEmpty();
        deq.addFirst(3);
        //deq.addLast(4);
        //deq.isEmpty();
        //deq.addLast(5);
        //deq.isEmpty();
        //deq.size();
        //deq.removeFirst();
        //deq.removeFirst();
        //deq.isEmpty();
        //deq.removeLast();
        //deq.isEmpty();
        
        
        
        
        
    }
    
}

