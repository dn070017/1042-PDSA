/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private class Node<Item>{
        public Node<Item> left,right;
        private final Item item;
        public Node(Item item) {
      // FIXME: maybe it's a bad practice to throw exception in constructor
      if (item == null) { throw new NullPointerException(); }
      this.item = item;
    }
          public void connectRight(Node<Item> other) {
      this.right = other;
      other.left = this;
    }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
     private final Node<Item> head = new Node<>(null);
  private final Node<Item> tail = new Node<>(null);
  private int size = 0;
    private class DequeIterator implements Iterator<Item> {
        
    private Node<Item> curr = head.right;
    public boolean hasNext() {
      return curr != tail;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
    public Item next() {
      if (!hasNext()) { throw new NoSuchElementException (); }
      Item item = curr.item;
      curr = curr.right;
      return item;
    }
  }

  //private Node<Item> head, tail;
  //private int size;

  public Iterator<Item> iterator() {      
    return new DequeIterator();    
  }

  public Deque() {
       head.right = tail;
    tail.left  = head;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }
  public void addFirst(Item item) {
    Node<Item> toadd = new Node<Item>(item);
    Node<Item> before = head.right;
    head.right = toadd;
    toadd.left = head;
    before.left = toadd;
    toadd.right = before;
    size++;
  }

  public void addLast(Item item) {
    Node<Item> newTail = new Node<Item>(item);
    Node<Item> prevTail = tail;
//        if(item ==null)
//         throw new NullPointerException ();
    if (prevTail != null) {
      prevTail.connectRight(newTail);
    } else {
      
        //head = newTail;
    }
    //tail = newTail;
    size++;
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;
    Node<Item> togo = head.right;
    head.right = togo.right;
    head.right.left = head;
    togo.left = null;
    togo.right = null;
    return togo.item;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;
    Node<Item> prevTail = tail;
    //tail = prevTail.left;
    prevTail.left = null;
    if (tail != null) tail.right = null;
    return prevTail.item;
  }
}
