
import java.util.Iterator;

/**
 *
 * @author clint
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first; // pointer at the front
    private Node last;  // pointer at the back
    private int size;   // number of elements
    
    private class Node {
        /* the basic building block in the class */
        Node prev;
        Item item;
        Node next;
    } // end inner class Node
    
    public Deque() {
        /* construct an empty deque */
        first = null;
        last = null;
        size = 0;
    } // end constructor
    
    public boolean isEmpty() {
        /* is the deque empty? */
        return first == null;
    } // end func isEmpty
    
    public int size() {
        /* return the number of items on the deque */
        return size;
    } // end func size
    
    public void addFirst(Item item) {
        /* add the item to the front */
        if (first == null) {
            // if empty: set a new node
            first = new Node(); // create a new node
            first.prev = null;  // set previous point to null
            first.item = item;  // assign item
            first.next = null;  // set next point to null
            last = first;       // update reference last
        } else {
            // if not empty
            Node oldFirst = first; 
            first = new Node();    // create a new node
            first.prev = null;     // set previous point to null
            first.item = item;     // assign item
            first.next = oldFirst; // set next point to original first node
            oldFirst.prev = first; // original first node point back to new first node
        } // end if-else
        
        // increment size
        size += 1;
    } // end func addFirst
    
    public void addLast(Item item) {
        /* add the item to the end */
        if (last == null) {
            // if empty: set a new node
            last = new Node();  // create a new node
            last.prev = null;   // set previous point to null
            last.item = item;   // assign item
            last.next = null;   // set next point to null
            first = last;       // update reference first
        } else {
            // if empty: set a new node
            Node oldLast = last;
            last = new Node();    // create a new node
            last.prev = oldLast;  // set previous point to original last node
            last.item = item;     // assign item
            last.next = null;     // set next point to null
            oldLast.next = last;  // original last node point to new last node
        } // end if-else
        
        // increment size
        size += 1;
    } // end func addLast
    
    public Item removeFirst() {
        /* remove and return the item from the front */
        // if empty, return null
        if (first == null) { return null; } 
        
        // if not empty, 
        // get the first item and move the first pointer to the next node
        Item item = first.item;             
        first = first.next;
        
        // if the next node is not null, set previous to null
        if (first != null) { first.prev = null; } 
        
        // decrement size and return
        if (size > 0) { size -= 1; }
        return item;
    } // end func removeFirst
    
    public Item removeLast() {
        /* remove and return the item from the end */
        // if empty, return null
        if (last == null) { return null; }
        
        // if not empty, 
        // get the last item and move the first pointer to the previous node
        Item item = last.item;
        last = last.prev;
        if (last != null) { last.prev = null; }
        
        // decrement size
        if (size > 0) { size -= 1; }
        return item;
    }  // end func removeLast
    
    @Override
    public Iterator<Item> iterator() {
        /* return an iterator over items in order from front to end */
        return new DequeIterator();
    } // end func iterator
    
    private class DequeIterator implements Iterator<Item> {
        // initiation
        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        } // end func hasNext
        
        @Override
        public Item next() {
            Item item = current.item;
            current   = current.next;
            return item;
        } // end func next
    } // end inner class DequeIterator
    
    @Override
    public boolean equals(Object obj) {
        Deque<Item> that = (Deque) obj;
        Item[] arr01 = (Item[]) new Object[size];
        Item[] arr02 = (Item[]) new Object[size];
        int idx;
        
        idx = 0;
        for (Item item : this) {
            arr01[idx] = item;
            idx += 1;        
        } // end loop for
        
        idx = 0;
        for (Item item : that) {
            arr02[idx] = item;
            idx += 1;
        } // end loop for
        
        for (idx = 0; idx < size; idx += 1){
            if (arr01[idx] != arr02[idx]) { return false; }
        } // end loop for
        
        return true;
    } // end func equals
    
    public static void main(String[] args) {
        Deque<Integer> dequeInt01 = new Deque<>();
        Deque<Integer> dequeInt02 = new Deque<>();
        dequeInt01.addFirst(0);
        dequeInt01.addFirst(0);
        dequeInt02.addFirst(0);
        dequeInt02.addFirst(1);
        System.out.println(dequeInt01.equals(dequeInt02));
        //System.out.println(dequeInt.removeFirst());
        //System.out.println(dequeInt.removeFirst());
        //for (Integer num : dequeInt) {
        //    System.out.println(num);
        //}
    }
} // end class 

