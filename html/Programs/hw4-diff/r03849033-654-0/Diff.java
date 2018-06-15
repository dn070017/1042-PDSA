import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
 private int size=0;// number of elements
 private Node before;//sentinel marking the beginning of a list
 private Node after;//sentinel marking the end of a list


    public Deque()                           // construct an empty deque
    {
     before= new Node();
     after = new Node();
    }

    public boolean isEmpty()                 // is the deque empty?
    {
     return size>0;
    }

    public int size()                        // return the number of items on the deque
    {
     return size;
    }

    public void addFirst(Item item)          // insert the item at the front
    {
     size++;
     Node oldbefore = before;
     before = new Node();
     before.item=item;
     before.next=oldbefore;
     if(size==0){
            after = before;
     } else {   oldbefore.previous=before; }
    }
    public void addLast(Item item)           // insert the item at the end
    {
     size++;
     Node oldafter = after;
     after = new Node();
     after.item=item;
     after.previous=oldafter;
     if(size==0){
            before = after;
     } else {   oldafter.next=after; }
    }
    public Item removeFirst()                // delete and return the item at the front
    {
     size--;
     Item it=before.item;
     if(before.next==null){after=null;}
     else{
         before.next.previous=null;
         before=before.next;
     }
     return it ;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
     size--;
     Item it=after.item;
     if(before.next==null){before=null;}
     else{
         after.previous.next=null;
         after=after.previous;
     }
     return it ;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
     return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
     Node current=before;
     public boolean hasNext(){
      return current.next!=null;

     }
     public void remove(){

     }

     public Item next(){
      Item item=current.item;
      current=current.next;
      return item;   
     }

    }//end class DequeIterator

    private class Node{
     private Item item;
     private Node next;
     private Node previous;
    }

 public static void main(String[] args) {

 }

}
