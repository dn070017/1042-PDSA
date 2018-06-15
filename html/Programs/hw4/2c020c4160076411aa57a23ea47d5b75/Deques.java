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
     return size==0;
    }

    public int size()                        // return the number of items on the deque
    {
     return size;
    }

    public void addFirst(Item item)          // insert the item at the front
    {
     if(item==null){throw new java.lang.NullPointerException();}   
     else{
     Node oldbefore = before;
     before = new Node();
     before.item=item;
     if(isEmpty()){
            after = before;
     } else {
         before.next=oldbefore;
         oldbefore.previous=before;
     }
         size++;
     }
    }
    public void addLast(Item item)           // insert the item at the end
    {
      if(item==null){throw new java.lang.NullPointerException();}   
     else{
     Node oldafter = after;
     after = new Node();
     after.item=item;
     if(isEmpty()){
            before = after;
     } else {   
        oldafter.next=after;
        after.previous=oldafter;}
      size++;
      }
    }
    public Item removeFirst()                // delete and return the item at the front
    {
     if(isEmpty()){ throw new java.util.NoSuchElementException();}   
     else{
        Item it=before.item;
        if(before==after){
           before= new Node();
           after = new Node();}
         else{
            before.next.previous=null;
            before=before.next;
          }
         size--;
         return it ;
         }
    }

    public Item removeLast()                 // delete and return the item at the end
    {
         if(isEmpty()){ throw new java.util.NoSuchElementException();}   
     else{    
     Item it=after.item;
     if(before==after){
        before= new Node();
        after = new Node();}
     else{
         after.previous.next=null;
         after=after.previous;
     }
     size--;
     return it ;
     }
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
         throw new UnsupportedOperationException();
     }

     public Item next(){
      if(hasNext()){
      Item item=current.item;
      current=current.next;
      return item;  }
      else{throw new java.util.NoSuchElementException();}
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
