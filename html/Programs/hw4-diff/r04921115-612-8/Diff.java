import java.util.*;
import java.lang.*;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;                // size of the stack
    private Node<Item> first;     // top of stack
    private Node<Item> last;     // bottom of stack

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

   // construct an empty deque
   public Deque(){
       first = null;
       last = null;
       N = 0;
   }
   
   // is the deque empty?
   public boolean isEmpty(){             
	   return (first == null || last == null);
   }
   
   // return the number of items on the deque
   public int size(){
       return N;
   }

   
//add front
   // add the item to the front
   public void addFirst(Item item){
	   if(item==null) throw new NullPointerException(""null pointer"");
	   Node<Item> oldfirst = first;
       first = new Node<Item>();
       first.item = item;
       first.prev = null;
       first.next = null;	
       
       if (oldfirst == null) {
           first.next = null;
       }else {
           first.next = oldfirst;
           oldfirst.prev = first;        
       }
       
       if (last == null) {
           last = first;
       }
       
       N++;
   }
   
// V add end
   // add the item to the end
   public void addLast(Item item){
	   if(item==null) throw new NullPointerException(""null pointer"");
	   Node<Item> oldlast = last;
       last = new Node<Item>();
       if(oldlast == null){
    	   last.prev = null;
       }else{
    	   last.prev = oldlast;
    	   oldlast.next = last;
       }
       last.item = item;
       last.next = null;
       if(first == null){
    	   first= last;
       }
       N++;
   }
   
// V remove front // right!!!
   // remove and return the item from the front
   public Item removeFirst(){                
	   if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
	   Item item = first.item;        // save item to return
	   first = first.next;            // delete first node
	   N--;
       if(isEmpty()) last = null;
	   return item;                   // return the saved item
   }

//remove end
   // remove and return the item from the end
   public Item removeLast(){                
	   if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
	   Item item = last.item;        // save item to return
	   last = last.prev;	         // delete last node
	   if(last!=null && last.next!=null){
		   last.next = null;
	   }
	   if(last==null){
		   first=null;
	   }
	   N--;
       return item;                   // return the saved item
   }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() {
       return new DequeIterator<Item>(first);
   }

   // an iterator, doesn't implement remove() since it's optional
   private class DequeIterator<Item> implements Iterator<Item> {
       private Node<Item> current;

       public DequeIterator(Node<Item> first) {
           current = first;
       }
       
       public boolean hasNext()  { return (current!=null);}
       public void remove()      { throw new UnsupportedOperationException();  }

       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next; 
           return item;
       }
   }
   
   public static void main(String[] args){
	   Deque<String> d = new Deque<String>();
//	   d.addFirst(""one"");
//	   d.addFirst(""two"");
//	   d.addFirst(""three"");
//	   d.addFirst(""four"");
//
//	   d.removeFirst();
//	   d.removeFirst();
//	   d.removeFirst();
//	   d.removeFirst();
	   
//	   d.addLast(""1one"");
//	   d.addLast(""1two"");
//	   d.addLast(""1three"");
//	   d.addLast(""1four"");
	   
//	   d.removeFirst();
//	   d.removeFirst();
//	   d.removeLast();
//	   d.removeLast();
//	   d.removeLast();
//	   d.removeLast();
//	   d.removeLast();

//	   d.removeLast();
//	   d.removeLast();


//	   for (String s : d) {
//           System.out.println(s); // prints only ""four""
//       }
   }
}
