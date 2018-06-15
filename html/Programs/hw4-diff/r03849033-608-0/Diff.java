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
     Node node = new Node();
     node.item=item;//sets new node equal to the item
     node.previous=before;//sets new node's previous to before which is the beginning sentinel
.
.
.

    }
    public void addLast(Item item)           // insert the item at the end
    {
     size++;
     Node node = new Node();
     node.item=item;//sets node item to item
     node.next=after;//sets new node's next to after 
     after.previous.next=node;//sets old last node's next to the new node 
     node.previous=after.previous;//sets new node's previous to old last node
.


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



 }

}
