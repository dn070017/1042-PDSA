/*
.
 * To change this template file, choose Tools | Templates
.
*/
import java.util.Iterator;
/**
 *
 * @author user
 */
public class Deque<Item> implements Iterable<Item> {
   //Deque > isEmpty > Node > last > 在做{}，所以順序有差
   public Deque(){
       last = new Node();
       first = new Node();
       first.next = last;         
   }                           // construct an empty deque
   public boolean isEmpty(){return (size()==0);}                 // is the deque empty?
   class Node{
       Item item;
       Node next;
       Node prev;
       //int yes(){return 1+5;};
   }
   Node first;
   Node last;
   int size_of_Deque = 0;
   public int size(){return size_of_Deque;}                        // return the number of items on the deque
   public void addFirst(Item item)throws Exception{// add the item to the front     
       if (item==null)
           throw new NullPointerException();
       Node oldnext = first.next;
       first.next = new Node();
       first.next.item = item;
       first.next.prev = first;
       first.next.next = oldnext;
       oldnext.prev = first.next;
       ++size_of_Deque;
      //int y= first.yes();
   }

   public void addLast(Item item){
       if (item==null)
           throw new NullPointerException();
       Node oldprev = last.prev;
       last.prev = new Node();
       last.prev.item = item;
       last.prev.next = oldprev;
       last.prev.prev = last;
       oldprev.next = last.prev;
       ++size_of_Deque;
   }           // add the item to the end

   public Item removeFirst(){// remove and return the item from the front
       if (isEmpty())
           throw new java.util.NoSuchElementException();
       Node oldfirst = first.next;
       Item a = oldfirst.item;
       first.next = oldfirst.next;
       oldfirst.next.prev = first;
       oldfirst = null;
       --size_of_Deque;
       return a;
   }

   public Item removeLast(){
       if (isEmpty())
           throw new java.util.NoSuchElementException();
       Node oldlast = last.prev;
       Item a = oldlast.item;
       last.prev = oldlast.prev;
       oldlast.prev.next = last;
       oldlast = null;
       --size_of_Deque;
       return a;
   }// remove and return the item from the end

   public Iterator<Item> iterator(){
       //public boolean hasNext() eturn (first.next==null);}
       return new InnerIterator();
   }// return an iterator over items in order from front to end
private class InnerIterator implements Iterator<Item>{
    private Node thisnode = first;
    public boolean hasNext(){return thisnode.next!=null;}
    public Item next(){
        thisnode = thisnode.next;
        if (thisnode==last)
            throw new java.util.NoSuchElementException();
        return thisnode.item;}
    public void remove() {throw new java.lang.UnsupportedOperationException();}
}
   public static void main(String[] args){
       Deque iaa = new Deque();
       iaa.removeLast();
   }
       // unit testing

}

