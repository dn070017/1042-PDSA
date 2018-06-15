
import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.InputMismatchException;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Lab304
 */
public class Deque<Item> implements Iterable<Item> {

     private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    
     private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public Deque()                           // construct an empty deque
    {first = null;
        N = 0;}
   
    public boolean isEmpty()                 // is the deque empty?
   {return first == null;}
    
   public int size()                        // return the number of items on the deque
   { return N;}
   
     @Override
   public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + "" "");
        return s.toString();
    } 
   
   public void addFirst(Item item)          // add the item to the front
   {Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;}
   
   public void addLast(Item item)           // add the item to the end
   {Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;}
   
   public Item removeFirst()                // remove and return the item from the front
   {if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item; }
   
   public Item removeLast()                 // remove and return the item from the end
   { if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;}
   
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }
    
     @Override
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {return new ListIterator<Item>(first);  }
   
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public ListIterator(Node<Item> first) {
            current = first;
        }
        @Override
        public boolean hasNext()  { return current != null;                     }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }
        @Override
        public Item next() {
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
        Deque<String> D = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals(""-"")) D.addFirst(item);
            else if (!D.isEmpty()) StdOut.print(D.removeFirst() + "" "");
        }
       // StdOut.println(""("" + D.size() + "" left on deque)"");
       //throw new NullPointerException();
    }
        // TODO code application logic here
    }
    

