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
    private DequeNode head;
    private DequeNode tail;
    
    public Deque() {
        size = 0;
        head = new DequeNode();
        tail = new DequeNode();
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
         if (size == 0) {
			DequeNode newNode = new DequeNode();
			newNode.item = item;
			newNode.prev = null;
			newNode.next = null;
			head = newNode;
			tail = newNode;
			size++;
		} else {
			DequeNode newNode = new DequeNode();
			newNode.item = item;
			newNode.prev = null;
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			size++;
       
         }
     }
     public void addLast(Item item) {
         if (item == null) {
            throw new NullPointerException();
        }
       if (size == 0) {
			DequeNode newNode = new DequeNode();
			newNode.item = item;
			newNode.next = null;
			newNode.prev = null;
			head = newNode;
			tail = newNode;
			size++;
		} else {
			DequeNode newNode = new DequeNode();
			newNode.item = item;
			newNode.prev = tail;
			newNode.next = null;
			tail.next = newNode;
			tail = newNode;
			size++;
		}
     }
     public Item removeFirst() {
         if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
            if (size == 1) {
			Item item = head.item;
			head = null;
			tail = null;
			size--;
			return item;
		} else {
			Item item = head.item;
			DequeNode temp = head.next;
			head.next.prev = null;
			head.next = null;
			head = temp;
			size--;
			return item;
		}
     }
     public Item removeLast(){
         if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        if (size == 1) {
			Item item = head.item;
			head = null;
			tail = null;
			size--;
			return item;
		} else {
			Item item = tail.item;
			DequeNode temp = tail.prev;
			tail.prev.next = null;
			tail.prev = null;
			tail = temp;
			size--;
			return item;
		}
     }
     
     
    private class DequeNode {

        private Item item;

        private DequeNode next;

        private DequeNode prev;
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
    private DequeNode current = head;
    public boolean hasNext() { return current != null; }
    public void remove() { 
        throw new UnsupportedOperationException();
    /* not supported */ 
    }
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

