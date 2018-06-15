import java.util.Iterator;
import java.util.NoSuchElementException;

/**
.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int N;

    private class Node<Item> {
        private Node<Item> next;
        private Item item;
    }

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if(item==null)throw new NullPointerException();
        Node<Item> oldfirst=first;
        first=new Node<Item>();
        first.item=item;
        first.next=oldfirst;
        N++;
    }

    public void addLast(Item item) {
        if(item==null)throw new NullPointerException();
        Node<Item> oldlast=last;
        last=new Node<Item>();
        last.item=item;
        last.next=oldlast;
        N++;
    }

    public Item removeFirst() {
        if(N==0)throw new NoSuchElementException();
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }

    public Item removeLast() {
        if(N==0)throw new NoSuchElementException();
        Item item=last.item;
        last=last.next;
        N--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if(!hasNext())throw new NoSuchElementException();
            Item item=current.item;
            current=current.next;
            return null;
        }

        public ListIterator(Node<Item> first){
            current=first;
        }
    }

    public static void main(String[] args) {
    }

}

