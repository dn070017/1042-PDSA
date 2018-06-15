
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
   private Node<Item> first1;
   private Node<Item> first2;
    private Node<Item> last;     // end of queue
    
    
     private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public Deque()                           // construct an empty deque
    {first = null;
    last  = null;
    first1=null;
    first2=null;
        N = 0;}
   
    public boolean isEmpty()                 // is the deque empty?
   {return first2 == null;}
     public boolean isEmpty1()                 // is the deque empty?
   {return first1 == null;}
   public int size()                        // return the number of items on the deque
   { return N;}
   
    
   public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + "" "");
        return s.toString();
    } 
   
   public void addFirst(Item item)          // add the item to the front
   { if(item==null){throw new NullPointerException();}
       Node<Item> oldfirst = first;
       Node<Item> oldlast=last;
        last = new Node<Item>();
        first = new Node<Item>();
        first.item = item;
        last.item = item;
        first.next = oldfirst;
        first2=first;
        if (isEmpty1()) first1 = last;
        else oldlast.next=last;
        N++;}
   
   public void addLast(Item item)           // add the item to the end
   {if(item==null){throw new NullPointerException();}
       Node<Item> oldlast = last;
       Node<Item> oldfirst = first;
        last = new Node<Item>();
        first=new Node<Item>();
        last.item = item;
        first.item=item;
        last.next=oldlast;
        first1=last;
        if (isEmpty()) first2 = first;
         else oldfirst.next =first;
      //if (isEmpty1()) first1 = last;
        
        N++;}
   
   public Item removeFirst()                // remove and return the item from the front
   {if (isEmpty1()) throw new NoSuchElementException();
        Item item = first1.item;        // save item to return
        first1 = first1.next;            // delete first node
        N--;
        return item; }
   
   public Item removeLast()                 // remove and return the item from the end
   { if (isEmpty()) throw new NoSuchElementException();
        Item item = first2.item;
       first2 = first2.next;
        N--;
        if (isEmpty1()) last = null;   // to avoid loitering
        return item;}
   
    public Item peek() {
        if (isEmpty()||isEmpty()) throw new NoSuchElementException();
        return first1.item;
    }
    
     
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {return new ListIterator<Item>(first2);  }
   
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public ListIterator(Node<Item> first2) {
            current = first2;
        }
        
        public boolean hasNext()  { return current != null;                     }
        
        public void remove()      { throw new UnsupportedOperationException();  }
        
        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            else{Item item = current.item;
            current = current.next; 
            return item;}
        }
    }
   
           /**
     * @param args the command line arguments
     */
           
    public static void main(String[] args) {
          /*  Deque deque = new Deque() ; 
            String test;
            test=""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
            String[]data=test.split("" "");*/
            /*for(int i=0;i<24;i++){
            deque.addLast(data[i]);
             System.out.println(deque.peek());
            }
             System.out.println(deque.removeLast());*/
         /*   deque.addLast(data[3]);
             deque.addLast(data[4]);
              deque.addLast(data[6]);
               deque.addLast(data[7]);
           // System.out.println(deque.removeFirst());
           // deque.addLast(data[3]);
             System.out.println(deque.removeFirst());
             System.out.println(deque.removeFirst());
             System.out.println(deque.removeFirst());
             System.out.println(deque.removeFirst());
             System.out.println(deque.iterator());*/
       /* Deque<String> D = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals(""-"")) D.addFirst(item);
            else if (!D.isEmpty()) StdOut.print(D.removeFirst() + "" "");
        }
       // StdOut.println(""("" + D.size() + "" left on deque)"");
       throw new NullPointerException();*/
    }

    
    
}
        // TODO code application logic here

  
    

