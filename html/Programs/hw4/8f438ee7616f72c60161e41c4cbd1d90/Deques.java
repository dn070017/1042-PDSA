
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Deque<Item> implements Iterable<Item>  {
    
    private node<Item> first;
    private node<Item> last;
    private int size;
    
    private static class node<Item> {
        private Item item;
        private node<Item> next;
        private node<Item> prev;
    }
    
   public Deque(){                    // construct an empty deque
       first = null;
       last = null;
       size = 0;
   }
   public boolean isEmpty(){                 // is the deque empty?
       return first == null || last == null;
   }

   public int size(){                        // return the number of items on the deque
       return size;
   }

   public void addFirst(Item item){          // add the item to the front
       if(item==null) throw new java.lang.NullPointerException();    
       node<Item> temp = new node();
           temp.item = item;
           if(isEmpty()){
               temp.next = null;
               temp.prev = null;
               last = temp;
               first = temp;
           }else{
               temp.next = first;
               first.prev = temp;
               first = temp;
           }
           size ++;
           
   }

   public void addLast(Item item){           // add the item to the end
       if(item==null) throw new java.lang.NullPointerException();     
       node<Item> temp = new node();
        temp.item = item;
        temp.next = null;
        if(isEmpty()){
            temp.prev = null;
            first = temp;
            last = temp;
        }else{
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        size ++;
   }

   public Item removeFirst(){                // remove and return the item from the front
       if(isEmpty()) throw new NoSuchElementException(""Queue underflow"");
       node<Item> temp = first;
       first = first.next;
       if(!isEmpty()) first.prev = null;
       size --;
       return temp.item;
   }

   public Item removeLast(){                 // remove and return the item from the end
       if(isEmpty()) throw new NoSuchElementException(""Queue underflow"");
       node<Item> temp = last;
       last = last.prev;
       if(!isEmpty())last.next = null;
       size --;
       return temp.item;
       
   }
   
   public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
      return new ListIterator<Item>(first);
   }
   
   private class ListIterator<Item> implements Iterator<Item>{
       private node<Item> current;
       public ListIterator(node<Item> first) {
            current = first;
        }
       public void remove(){
           throw new UnsupportedOperationException();
       }
       public boolean hasNext(){
           return current != null;
       }
       public Item next(){
           if(!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
   
    

    public static void main(String[] args) {
        // TODO code application logic here
         Deque a = new Deque();
        a.addFirst(""aa"");
        a.addLast(""bb"");
        StdOut.print(a.removeFirst());
        StdOut.print(a.removeLast());
        a.addLast(""bb"");
    }    
}

