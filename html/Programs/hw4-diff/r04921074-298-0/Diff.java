import java.util.Iterator;
import java.util.NoSuchElementException;

class Deque<Item> implements Iterable<Item> {
  private class Node<Item> {
    public Node<Item> left, right;
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

  private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        private ListIterator() {
.
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.right; 
            return item;
        }
    }

  private Node<Item> head, tail;
  private int size;

  public Iterator<Item> iterator() {
    return new ListIterator();
  }


  public Deque() {
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


  public void addFirst(Item item) {
    Node<Item> prevHead = head;
    Node<Item> newHead = new Node<Item>(item);
    if (prevHead != null) {
      newHead.connectRight(prevHead);
    } else {
      tail = newHead;
    }
    head = newHead;
    size++;
    checkInvariants();
  }

  public void addLast(Item item) {
    Node<Item> newTail = new Node<Item>(item);
    Node<Item> prevTail = tail;
    if (prevTail != null) {
      prevTail.connectRight(newTail);
    } else {
      head = newTail;
    }
    tail = newTail;
    size++;
    checkInvariants();
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    size--;
    Node<Item> prevHead = head;
    head = prevHead.right;
    prevHead.right = null;
    if (head != null) {
      head.left = null;
    }
    checkInvariants();
    return prevHead.item;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    size--;
    Node<Item> prevTail = tail;
    tail = prevTail.left;
    prevTail.left = null;
    if (tail != null) tail.right = null;
    checkInvariants();
    return prevTail.item;
  }
  
  public static void main(String[] args){}   // unit testing
  
}
