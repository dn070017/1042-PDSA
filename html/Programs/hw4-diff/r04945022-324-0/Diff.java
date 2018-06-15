import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;

/**
 *
 * @author Daniel
 */
public class Deque<Item> implements Iterable<Item>{
    private int size;
    private final DequeNode<Item> head;
    private final DequeNode<Item> tail;
    
    public Deque() {
        size = 0;
        head = new DequeNode<Item>();
        tail = new DequeNode<Item>();
        head.next = tail;
        tail.prev = head;
    }
     public boolean isEmpty(){
         return size == 0;
     }
     public int size() {
         return size;
     }
     public void addFirst(Item item) {
         if (item == null) {
            throw new NullPointerException();
        }
        final DequeNode<Item> first = head.next;
        final DequeNode<Item> node = new DequeNode<Item>();
        node.item = item;
        node.next = first;
        node.prev = head;
        head.next = node;
        first.prev = node;
        size++;
     }
     public void addLast(Item item) {
         if (item == null) {
            throw new NullPointerException();
        }
        final DequeNode<Item> last = tail.prev;
        final DequeNode<Item> node = new DequeNode<Item>();
        node.item = item;
        node.next = tail;
        node.prev = last;
        tail.prev = node;
        last.next = node;
        size++;
     }
     public Item removeFirst() {
         if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        final DequeNode<Item> first = head.next;
        final DequeNode<Item> next = first.next;
        next.prev = head;
        head.next = next;
        size--;

        return first.item;
     }
     public Item removeLast(){
         if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        final DequeNode<Item> last = tail.prev;
        final DequeNode<Item> prev = last.prev;
        tail.prev = prev;
        prev.next = tail;
        size--;

        return last.item;
     }
     
     
    private static class DequeNode<Item> {

        private Item item;

        private DequeNode<Item> next;

        private DequeNode<Item> prev;
    }
    
    
    public static void main(String[] args) throws IOException{
       
        // TODO code application logic here
    }

    @Override
    public Iterator iterator() {
      return new ListIterator();
//        throw new UnsupportedOperationException(""Not supported yet.""); 
    }
    private class ListIterator implements Iterator<Item>{
    private DequeNode<Item> current = head;
    public boolean hasNext() { return current != null; }
    public void remove() { /* not supported */ }
    public Item next()
    {
    Item item = current.item;
    current = current.next;
    return item;
    }
    }
//    public class Node{
//        Item item;
//        Node next;
//    }
    
}

