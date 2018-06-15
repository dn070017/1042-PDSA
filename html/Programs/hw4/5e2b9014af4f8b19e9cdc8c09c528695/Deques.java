/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Lenovo
 */
public class Deque<Item> implements Iterable<Item>{

    /**
     * @param args the command line arguments
     */
    private Node<Item> first;     // first of deque
    private Node<Item> last;      // last of deque
    private int N;                // size of deque
    
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        //private Node<Item> up;
    }
    
    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        //first.next=last;
        N = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty(){
        return (first == null);
    }     
    
    // return the number of items on the deque
    public int size(){
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        N++;
        if (N==1){
            last=first;
        }
        else{
            first.next = oldfirst;
        }
    }
    
    // add the item to the end
    public void addLast(Item item){
        if (item == null) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        N++;
        if (N==1){
            first=last;
        }
        else {
           last.next = oldlast;
           oldlast.next = last; 
        }
    }
    
    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");
        Item item = first.item;
        N--;
        if (N==0){
            first=null;
            last=null;
        }
        else if (first.next==last){
            first = last;
        }
        else{
            first = first.next;
        }
        
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");
        Item item = last.item;
        N--;
        if (N==0){
            first=null;
            last=null;
        }
        else if (last.next==first){
            last=first;
        }
        else{
            last=last.next;
        }
        N--;
        return item; 
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);
    }  
    
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    
    // unit testing        
    public static void main(String[] args) {
        
    }
}

