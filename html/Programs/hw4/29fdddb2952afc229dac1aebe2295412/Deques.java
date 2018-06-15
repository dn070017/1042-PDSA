import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
 private int size;// number of elements
 private Node before;//sentinel marking the beginning of a list
 private Node after;//sentinel marking the end of a list


    public Deque()                           // construct an empty deque
    {
     before= new Node();
     after = new Node();
     before.next=after;
     after.previous=before;
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
     if(oldbefore==null){
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
     if(oldafter==null){
            before = after;
     } else {   oldafter.next=before; }
    }
    public Item removeFirst()                // delete and return the item at the front
    {
     size--;
.
     Node second=first.next;//gets the second element in the list
     second.previous=before;//sets second's previous to before
     before.next=second;//sets before's next to second
     return first.item;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
     size--;
     Node last = after.previous;//sets last node
     Node second2Last= after.previous.previous;//sets second2Last
     second2Last.next=after;//sets second2Last's next to after
     after.previous=second2Last;//sets after's previous to second2last
     return last.item;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
     return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
     Node current=before;
     public boolean hasNext(){
      return current.next!=after;

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
  // TODO Auto-generated method stub
  Deque d = new Deque();

  d.addFirst(1);
  d.addFirst(2);

  Iterator i = d.iterator();
  System.out.println(i.hasNext());
  while(i.hasNext()){
   System.out.println(i.next());
  }


 }

}
