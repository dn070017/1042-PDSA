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
     return N == 0;
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
     first.left = null;
     if(N == 0){
         
         last = first;
     }
     else{
         connect(first, oldfirst);
     } 
        
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
      if(N == 0){
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
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    private class ListIterator<Item> implements Iterator<Item>{
         private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
  @Override
        public boolean hasNext() {
            return current != null;
        }
  @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
  @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next; 
            return item;
     }
    }
     public static void main(String[] args){
         
     } 
 }

