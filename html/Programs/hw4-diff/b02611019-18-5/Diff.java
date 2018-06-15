import java.util.Iterator;
import java.util.*;//NoSuchElementException;
import java.util.Scanner;


public class Deque <Item> implements Iterable {
    private Node first, last;
    static int num;
    private class Node 
    {
        Item item;
        Node next;
    }

   public Deque()   {
       first=null;
       last=null;
       
   }// construct an empty deque

   public boolean isEmpty()  {// is the deque empty?
       return first == null;
   }

   public int size(){                        // return the number of items on the deque
       return num;
      
   }

   public void addFirst(Item item){

         if(item==null){
            throw new NullPointerException() ;
        }
        else{
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            num++;
            
            if(last == null)
                last = first;
        }
   }// add the item to the front

   public void addLast(Item item){
       if(item==null){
           throw new NullPointerException();
       }
       else{
           Node oldlast = last;
           last = new Node();
           last.item = item;
           last.next = null;
           if (isEmpty()) first = last;
           else oldlast.next = last;
           num++;
           
           if(first == null)
               first = last;
       }
       
   }// add the item to the end

   public Item removeFirst() {               // remove and return the item from the front
       
       if(first == null){
           throw new NoSuchElementException();
       }
       else{
            Item item = first.item;
            first = first.next;
            num--;
            return item;
       }
       
   }

   public Item removeLast(){                 // remove and return the item from the end
      
       if(last==null||num==0){
           
           throw new NoSuchElementException();
       }
       else{
           Item item = last.item;
           last=null;
           Node newlast=first;
           for(int i=0;i<=(num-3);i++)newlast=newlast.next;

           last=newlast;

           if (isEmpty()) last = null;
           num--;
           return item;
       }
   }

   public Iterator iterator()   {      // return an iterator over items in order from front to end
       
       return new ListIterator();
   }
   private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { /* not supported */
            throw new UnsupportedOperationException(); 
        
        }
        public Item next()
        {
            if(current.item==null ){
                throw new NoSuchElementException(); 
            }
            else{
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

   public static void main(String[] args){   // unit testing
       
       Deque aa= new Deque();
       
       Scanner scanner = new Scanner(System.in);
       String ss=""aa"";
       try{
//            aa.addFirst(""ss"");
           
            aa.addFirst(scanner.nextLine());
            aa.addFirst(scanner.nextLine());
            aa.addFirst(scanner.nextLine());
            
            StdOut.print(aa.removeLast());
            StdOut.print(aa.removeLast());
            StdOut.print(aa.removeLast());
//            StdOut.print(aa.removeLast());
//            StdOut.print(aa.removeLast());
            
//            StdOut.print(aa.removeFirst());
//            StdOut.print(aa.removeFirst());
//            StdOut.print(aa.removeFirst());
//            StdOut.print(aa.removeFirst());
//            StdOut.print(aa.removeFirst());
       }
       catch(NoSuchElementException exx){
           StdOut.print(""remove 為 null"");
       }
       catch(NullPointerException ex){
           StdOut.print(""輸入為NULL"");
       }
       
       

               
   }

}
