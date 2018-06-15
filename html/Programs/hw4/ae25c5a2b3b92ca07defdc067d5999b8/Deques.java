
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
          private Node<Item> pre;
    }
    public Deque()                           // construct an empty deque
    {first = null;
    last  = null;
        N = 0;}
   
    public boolean isEmpty()                 // is the deque empty?
   {return first == null;}
      public boolean isEmpty1()                 // is the deque empty?
   {return last == null;}
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
   /*if(first==null&&last==null){
        last=new Node<Item>();
        last.item = item;}*/
       Node<Item> oldfirst = first;
      // Node<Item>oldlast=last;
        //last = new Node<Item>();
        first = new Node<Item>();
        first.item = item;
        //last.item = item;
        first.next = oldfirst;
         if (isEmpty1()) last = first;
         else oldfirst.pre=first;
        //oldlast.next=last;
        N++;}
   
   public void addLast(Item item)           // add the item to the end
   {if(item==null){throw new NullPointerException();}
     Node<Item> oldlast = last;
       //Node<Item> oldfirst = first;
        last = new Node<Item>();
        //first=new Node<Item>();
        last.item = item;
        last.pre=oldlast;
        //first.item=item;
        if (isEmpty()) first = last;
        else  oldlast.next =last ;
        N++;}
   
   public Item removeFirst()                // remove and return the item from the front
   {if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        //last=last.next;
        N--;
        if(N==0){first=null;last=null;}
        return item; }
   
   public Item removeLast()                 // remove and return the item from the end
   { if (isEmpty1()) throw new NoSuchElementException();
        Item item = last.item;
       // (last.pre).next=null;
        last = last.pre;
        N--;
           if(N==0){first=null;last=null;}
        //if (isEmpty()) last = null;   // to avoid loitering
        return item;}
   
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }
    
     
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   { return new ListIterator();  }
   
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current=first;
       // public ListIterator(Node<Item> first) { }
//        current = first; 
        public boolean hasNext()  { return current != null;                     }
        
        public void remove()      { throw new UnsupportedOperationException();  }
        
        public Item next() {String str=null;Item item;
            
            if (!hasNext()) {throw new NoSuchElementException();}
            else{current = current.next; 
                 item = current.item;
            //current = current.next; 
             return item;
           // System.out.println(item);
        }
            
            
        //return null ;
        
    }
    }
   
           /**
     * @param args the command line arguments
     */
           
    public static void main(String[] args) {
            Deque deque = new Deque() ; 
            String test;
            test=""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
            String[]data=test.split("" "");
            
            deque.addLast(data[3]);
             //System.out.println(deque.iterator().next());
             deque.addLast(data[4]);
                deque.addFirst(data[6]);
               deque.addFirst(data[7]);
               System.out.println(deque.removeFirst());
             Iterator a=  deque.iterator();
               System.out.println(a);
               System.out.println(a.next());
           // System.out.println(deque.removeFirst());
           // deque.addLast(data[3]);
           
          /*  System.out.println(deque.removeLast());
               System.out.println(deque.iterator().next());
               System.out.println(deque.iterator());
             System.out.println(deque.removeFirst());
               System.out.println(deque.iterator().next());
               System.out.println(deque.iterator());
            System.out.println(deque.removeFirst());
                 System.out.println(deque.iterator().next());
                 System.out.println(deque.iterator());
            System.out.println(deque.removeFirst());     */
          //   deque.addLast(data[3]);
           //      System.out.println(deque.iterator().next());
        //    System.out.println(deque.removeLast());   
                  //    System.out.println(deque.removeLast());  
           //    System.out.println(deque.iterator());
           
             //System.out.println(deque.removeFirst());
            // System.out.println(deque.removeLast());
           //  System.out.println(deque.iterator().next());
            // deque.iterator().remove();
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

  
    


