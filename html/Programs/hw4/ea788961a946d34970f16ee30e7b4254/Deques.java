import java.util.Iterator;
import java.util.NoSuchElementException;

 public class Deque<Item> implements Iterable<Item> {
     private Node<Item> first;
     private Node<Item> last;
     private int N;

   
     
     
     
private static class Node<Item>{
     private Item item;
     private Node<Item> next, left;
     
 }

public Deque(){
    first = null;
    last = null;
    N = 0;
}
 public boolean isEmpty(){
     return first == null;
 }
 public int size(){
     return N;
 }   
 private void connect(Node a, Node b){
     a.next = b;
     b.left = a;
 }
 public void addFirst(Item item){
     if(item == null){
         throw new NullPointerException();
     }
     Node<Item> oldfirst = first;
     first = new Node<>();
     first.item = item;
     if(last == null){
         
         last = first;
     }
     else{
         connect(first, oldfirst);
     } 
         first.next = oldfirst;
         N++;
 }  

  public void addLast(Item item){
       if(item == null){
         throw new NullPointerException();
     }
      Node<Item> oldlast = last;
      last = new Node<>();
      last.item = item;
      last.next = null;
      if(first == null){
          first = last;
      }
      else {
          connect(oldlast, last);
      }
      N++;
  }     
  public Item removeFirst(){
      if(isEmpty()){
          throw new NoSuchElementException();
      }
      Item remove = first.item;
      first = first.next;
      if(first != null){
      first.left = null;
      }
      N--; 
      return remove;
  }      
  public Item removeLast(){
      if(isEmpty()){
          throw new NoSuchElementException();
      }
      Item remove = last.item;
      last = last.left;
      if(last != null){
          last.next = null;
      }
      N--;      
      return remove;
  }
 
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }
    private class ListIterator<Item> implements Iterator<Item>{
         private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
     }
    }
     public static void main(String[] args){
         
     } 
 }

