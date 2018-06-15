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
       
       N++;
       if(isEmpty()) last = first;
       else first.next = oldfirst;
   }
   
// V add end
   // add the item to the end
   public void addLast(Item item){
	   if(item==null) throw new NullPointerException(""null pointer"");
	   Node<Item> oldlast = last;
       last = new Node<Item>();
       last.item = item;
       last.next = null;
       last.prev = oldlast;
       N++;
       if(isEmpty()) first = last;
       else oldlast.next = last;
   }
   
// V remove front
   // remove and return the item from the front
   public Item removeFirst(){                
	   if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
	   Item item = first.item;        // save item to return
	   first = first.next;            // delete first node
	   //first.prev = null;
	   N--;
       if(isEmpty()) last = null;
//	   if(N==0){
//		   last = null;
//		   first = null;
//	   }else{
//		   first = first.next;
//		   first.prev = null;
//	   }
	   return item;                   // return the saved item
   }

//remove end
   // remove and return the item from the end
   public Item removeLast(){                
	   if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
	   Item item = last.item;        // save item to return
	   if(last.prev!=null){last = last.prev;}	         // delete last node
	   last.next = null;
	   N--;
       if(isEmpty()) first = null;
	   return item;                   // return the saved item
   }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() {
       return new ListIterator<Item>(first);
   }

   // an iterator, doesn't implement remove() since it's optional
   private class ListIterator<Item> implements Iterator<Item> {
       private Node<Item> current;

       public ListIterator(Node<Item> first) {
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
//
//	   for (String s : d) {
//           System.out.println(s); // prints only ""four""
//       }
   }
}
