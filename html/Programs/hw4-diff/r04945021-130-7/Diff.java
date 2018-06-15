/*
.
 * To change this template file, choose Tools | Templates
.
 */
//remove ans static
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
private Node first=null;
private Node last=null;
private int counter = 0;
private class Node
{
    Item item;
    Node next;
    Node pre;
}
    public Deque(){}// construct an empty deque

   public boolean isEmpty()
   {return first ==null;}
// is the deque empty?

   public int size()
   {
       return counter;
   }                     // return the number of items on the deque

    public void addFirst(Item item) {

        if (item == null) {
            throw new java.lang.NullPointerException();
        }
       else if (isEmpty()) {
           first=new Node();
            first.item = item;
            first.next = null;
            first.pre = null;
            last=first;
        }
        else{
        Node oldfirst = first;
  first=new Node();
  first.item=item;
  first.next=oldfirst;
  first.pre=null;
      oldfirst.pre=first;    
        }
        counter++;
   }
// add the item to the front

   public void addLast(Item item){
       if(item==null) throw new java.lang.NullPointerException();
      if(isEmpty()){
          first=last;
          
      }
       Node oldlast =last;
       last = new Node();
       last.item=item;
       last.next=null;
       
       if(isEmpty()) first=last;
       else {
           last.pre=oldlast;
           oldlast.next=last;
       }
       counter++;
   }// add the item to the end

   public Item removeFirst(){
       if(isEmpty()) throw new java.util.NoSuchElementException();
       Item item=first.item;
       first=first.next;
       if (isEmpty()) last=null;
       counter--;
       return item;
               }// remove and return the item from the front

   public Item removeLast(){
       if(isEmpty()) throw new java.util.NoSuchElementException();
    Item item = last.item;
    last=last.pre;
    counter--;

    return item;
   }// remove and return the item from the end

   public Iterator<Item> iterator(){
      return new ListIterator();
       // return an iterator over items in order from front to end   
   }
   private class ListIterator implements Iterator<Item>
   {
       private Node current = first;
       public boolean hasNext(){
           return current !=null;
       }
       public void remove(){
           throw new UnsupportedOperationException();
       }
       public Item next()
       {
           if(!hasNext()) throw new java.util.NoSuchElementException();
         //  System.out.println(current.next);
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
  
      //static  
        
    public static void main(String[] args) throws Exception {
      
       // }
        

        
    }

}

  

    
