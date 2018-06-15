import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque <Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int numberOfItems;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        public boolean hasNext() { return current != null;                      }
        public void remove()     { throw new UnsupportedOperationException();   }

        public Item next() {
            if (!this.hasNext()) { throw new NoSuchElementException();          }
            else {
                Node node = current;
                current = current.next;
                return node.item;
            }
        }
    }

    public Deque() {
        this.first = null;
        this.last = null;
        this.numberOfItems = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.numberOfItems;
    }

    public void addFirst(Item item) {
        if (item == null) { throw new NullPointerException();   }

        if (this.isEmpty()) {
            this.first = new Node(item);
            this.last = first;
        } else {
            Node node = new Node(item);
            node.next = this.first;
            this.first.prev = node;
            this.first = node;
        }
        this.numberOfItems++;
    }

    public void addLast(Item item) {
        if (item == null) { throw new NullPointerException();   }

        if (this.isEmpty()) {
            this.last = new Node(item);
            this.first = last;
        } else {
            Node node = new Node(item);
            this.last.next = node;
            node.prev = this.last;
            this.last = node;
        }
        this.numberOfItems++;
    }

    public Item removeFirst() {
        if (this.isEmpty()) { throw new NoSuchElementException(); }

        Node node = this.first;
        if (this.size() == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.first.next.prev = null;
            this.first = this.first.next;
        }
        this.numberOfItems--;
        node.next = null;
        return node.item;
    }

    public Item removeLast() {
        if (this.isEmpty()) { throw new NoSuchElementException(); }

        Node node = this.last;
        if (this.size() == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.last.prev.next = null;
            //this.last.prev = null;
            this.last = this.last.prev;
        }
        this.numberOfItems--;
        node.next = null;
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        //Deque<String> deq1 = new Deque<String>();
        Deque<Integer> deq2 = new Deque<Integer>();

        System.out.println(""deq2: "" + deq2.toString());
        System.out.println(""size: "" + deq2.size());

        deq2.addFirst(1);
        deq2.addFirst(2);
        deq2.addFirst(3);
        deq2.addFirst(4);
        deq2.addFirst(5);


        System.out.println(""deq2: "" + deq2.toString());

        deq2.removeLast();
        System.out.println(""deq2: "" + deq2.toString());

        deq2.removeFirst();
        deq2.removeFirst();
        System.out.println(""deq2: "" + deq2.toString());
        System.out.println(""size: "" + deq2.size());

        deq2.removeLast();
        deq2.removeLast();
        System.out.println(""deq2: "" + deq2.toString());

        deq2.addFirst(1);
        deq2.addLast(2);
        System.out.println(""deq2: "" + deq2.toString());

        deq2.addFirst(3);
        deq2.addLast(4);
        System.out.println(""deq2: "" + deq2.toString());

        System.out.println(""size: "" + deq2.size());



        Iterator itr = deq2.iterator();

        //System.out.println(itr.);
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        //System.out.println(itr.next());

    }
}
