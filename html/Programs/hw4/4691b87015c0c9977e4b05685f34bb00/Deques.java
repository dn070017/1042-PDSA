import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node first, last;
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return N == 0;
    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node temp=new Node();
            if (isEmpty()) {
                temp.next=null;
                temp.previous=null;
                first=temp;
                last=temp;
            } else {
                last.next=temp;
                temp.previous=last;
                temp.next=null;
                last=temp;
            }
            last.item=item;
            N++;
        }
    }

    public void addFirst(Item item) {           // add the item to the front
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node newNode=new Node();
            if (isEmpty()) {
                newNode.next=null;
                newNode.previous=null;
                first=newNode;
                last=newNode;
            } else {
                first.previous=newNode;
                newNode.next=first;
                newNode.previous=null;
                first=newNode;
                
            }
            first.item=item;
            N++;
        }
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item=first.item;
            if (first.next == null) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.previous = null;
            }
            N--;
            return item;
        }
    }
    
    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item=last.item;
            if (first.next == null) {
                first = null;
                last = null;
            } else {
                last = last.previous;
                last.next = null;
            }
        
            N--;
            return item;
        }
    }

    
    @Override
    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return (Iterator<Item>) new ListIterator(first);
    }

    private class ListIterator implements Iterable<Item> {
        private Node current ;
        public ListIterator(Node first) {
            current=first;
        }
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item Next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public Iterator<Item> iterator() {
.
        }
    }


    public static void main(String[] args) throws Exception {   // unit testing
 /*
        Deque deque = new Deque();
       
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(8);
        //String f = null;
        //deque.addFirst(f);

       // StdOut.println(deque.size());
       
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        
        StdOut.println("""");
        
        for(int i=0;i<8;i++)
        deque.addLast(i);
        
        for(int j=0;j<8;j++){
            StdOut.println(deque.removeFirst());
            //deque.addFirst(j);
        }
        
        Iterator it = deque.iterator();
        while(it.hasNext()){
            StdOut.println(it.next());
        }
        */
    }
   
}


