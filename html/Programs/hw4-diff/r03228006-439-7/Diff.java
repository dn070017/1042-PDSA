import java.util.Iterator;
import java.util.NoSuchElementException;

    
    public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private Node oldlast;
    private int N;
    
    private class Node{
          Item item;
          Node next;}
    
   public Deque()                           // construct an empty deque
       { 
       first = null;
       last = null;
       N = 0;
       }            
           
   public boolean isEmpty()                 // is the deque empty?
   {
     return ((first==null)|(last==null));    
   }
   public int size()                        // return the number of items on the deque
   {
       return N;
   } 
   public void addFirst(Item item)          // add the item to the front
   {
       if(item==null){throw new NullPointerException();}
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       if (isEmpty()) last = first;//if last is empty
       else           first.next =oldfirst;
       N++;
   }
   public void addLast(Item item)          // add the item to the end
   {
       if(item==null){throw new NullPointerException();}
       oldlast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       if (isEmpty()) first = last;//if first is empty
       else           oldlast.next=last;
       N++;
   } 
   public Item removeFirst()                // remove and return the item from the front
   {
        if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // 
        return item;
   }

   public Item removeLast()                 // remove and return the item from the 
   {
        if (isEmpty()) throw new NoSuchElementException(""Deque underflow"");
        Item item = last.item;
        last = oldlast;
        N--;
        if (isEmpty()) {last = null;} // to avoid loitering
        return item;   
   }

   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
     {return new ListIterator();}
   private class ListIterator implements Iterator<Item>
     {
       private Node current = first;
       public boolean hasNext(){ return current !=null;}
       public void remove()    { throw new UnsupportedOperationException(); }
       public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
     }
   
   public static void main(String[] args)   // unit testing
   {
       Deque<String> s = new Deque<String>();
       String a = ""2"";
       String b = ""3"";
       s.addFirst(a);
       s.addLast(b);
       System.out.println(s.removeLast());
       System.out.println(s.removeFirst());
       s.addFirst(a);
       System.out.println(s.size());
   }
    
    
}

