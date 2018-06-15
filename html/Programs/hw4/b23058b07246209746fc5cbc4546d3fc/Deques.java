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
        private Node<Item> prev;
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
        if(oldfirst!=null)oldfirst.prev=first;
        N++;
        if(N==1)last=first;
    }

    public void addLast(Item item) {
        if(item==null)throw new NullPointerException();
        Node<Item> oldlast=last;
        last=new Node<Item>();
        last.item=item;
        last.prev=oldlast;
        if(oldlast!=null)oldlast.next=last;
        N++;
        if(N==1)first=last;
    }

    public Item removeFirst() {
        if(N==0)throw new NoSuchElementException();
        Item item=first.item;
        first.next.prev=null;
        first=first.next;
        N--;
        if(N==1)last=first;
        if(N==0){
            first=null;
            last=null;
        }
        return item;
    }

    public Item removeLast() {
        if(N==0)throw new NoSuchElementException();
        Item item=last.item;
        last.prev.next=null;
        last=last.prev;
        N--;
        if(N==1)first=last;
        if(N==0){
            first=null;
            last=null;
        }
        return item;
    }

    public boolean isEmpty() {
        if (N == 0) return true;
        else return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> first){
            current=first;
        }

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
            return item;
        }

    }

    public static void main(String[] args) {
    }

}

