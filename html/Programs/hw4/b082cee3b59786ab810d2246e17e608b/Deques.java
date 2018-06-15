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
       last=null;
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
   public void addFirst(Item item){// add the item to the front     
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.next = oldfirst;
       oldfirst.prev = first;
       ++size_of_Deque;
      //int y= first.yes();
   }

   public void addLast(Item item){
       if (last==null)
           last = first;
       last.next = new Node();
       last.next.item = item;
       last.next.prev = last;
       last = last.next;
       ++size_of_Deque;
   }           // add the item to the end

   public Item removeFirst(){// remove and return the item from the front
       Item a = first.item;
       Node oldfirst = first;
       first = first.next;
       oldfirst = null;
       --size_of_Deque;
       return a;
   }

   public Item removeLast(){
       Item a = last.item;
       Node oldlast = last;
       last= last.prev;
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
        return thisnode.item;}
    public void remove() {}
}
   /*public static void main(String[] args){
       Deque iaa = new Deque();
       iaa.addLast((double)1);
   }*/
       // unit testing

}

