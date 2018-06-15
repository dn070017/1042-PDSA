import java.util.Iterator;
import java.util.NoSuchElementException;


class Deque0<T> implements Iterable<T> {
  private class Node<T> {
    public Node<T> left=null, right=null;
    private final T item;

    public Node(T item) {
      // FIXME: maybe it's a bad practice to throw exception in constructor
      if (item == null) { throw new NullPointerException(); }
      this.item = item;
    }

    public void connectRight(Node<T> other) {
      this.right = other;
      other.left = this;
    }
  }

  private class DequeIterator implements Iterator<T> {

    private Node<T> curr = head;

    public boolean hasNext() {
      return curr != null;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
    public T next() {
      if (!hasNext()) { throw new NoSuchElementException(); }
      T item = curr.item;
      curr = curr.right;
      return item;
    }
  }

  private Node<T> head, tail;
  private int size;

  public Iterator<T> iterator() {
    return new DequeIterator();
  }


  public Deque0() {
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void checkInvariants() {
    assert size >= 0;
    assert size > 0 || (head == null && tail == null);
    assert (head == null && tail == null) || (head != null && tail != null);
  }


  public void addFirst(T item) {
    Node<T> prevHead = head;
    Node<T> newHead = new Node<T>(item);
    if (prevHead != null) {
      newHead.connectRight(prevHead);
    } else {
      tail = newHead;
    }
    head = newHead;
    size++;
    checkInvariants();
  }

  public void addLast(T item) {
    Node<T> newTail = new Node<T>(item);
    Node<T> prevTail = tail;
    if (prevTail != null) {
      prevTail.connectRight(newTail);
    } else {
      head = newTail;
    }
    tail = newTail;
    size++;
    checkInvariants();
  }

  public T removeFirst() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    size--;
    Node<T> prevHead = head;
    head = prevHead.right;
    prevHead.right = null;
    if (head != null) {
      head.left = null;
    }
    if (isEmpty()) tail = null;
    checkInvariants();
    return prevHead.item;
  }

  public T removeLast() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    size--;
    Node<T> prevTail = tail;
    tail = prevTail.left;
    prevTail.left = null;
    if (tail != null) tail.right = null;
    if (isEmpty()) head = null;
    checkInvariants();
    return prevTail.item;
  }
    public static void main(String[] args) {
        Deque0 a = new Deque0();
        a.addFirst(1);
        a.addFirst(2);
        a.removeLast();
        a.removeLast();
        a.addFirst(1);
        a.addFirst(2);
        a.removeLast();
        a.removeLast();

    }
}
