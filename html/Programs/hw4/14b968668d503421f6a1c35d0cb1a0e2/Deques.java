/*
.
 * To change this template file, choose Tools | Templates
.
 */
package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author YuChing
 */
public class Deque implements Iterable{
    private int N;               // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }// construct an empty deque

   public boolean isEmpty()  
   {return first==null; }// is the deque empty?

   public int size()                        // return the number of items on the deque
   {return N;}
   
   public void addFirst(String item)          // add the item to the front
   {Node oldfirst;
        oldfirst = first;
        first = new Node();
        first.item = (String) item;
        first.next = oldfirst;
        if (isEmpty()) first = last;
        else oldfirst.next = last;
        N++;
       }
   public void addLast(String item)           // add the item to the end
   {   Node oldlast;
        oldlast = last;
        last = new Node();
        last.item = (String) item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
}
   public String removeFirst()                // remove and return the item from the front
   {if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        String item = first.item;
        first = first.next;
        N--;
      //  if (isEmpty()) last = null;   // to avoid loitering
        return item;}
   public String removeLast()                 // remove and return the item from the end
   {if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Node a;
        String item = last.item;
        
        a=last;
        last.next = a;
        N--;
       // if (isEmpty()) last = null;   // to avoid loitering
        return item;}
   
   public Iterator iterator()
   {
        return null;
   
   }
   
    public static void main(String[] args) {
        // TODO code application logic here
    }

        

       

      
        public void remove() {
.
        }
    
}
class Node{
String item;
Node next;

}
