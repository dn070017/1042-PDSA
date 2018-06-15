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
        if (item == null) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldfirst;
            oldfirst.prev=first;
            first.prev=null;
        }
        N++;
    }

    public void addLast(Item item) {
        if(item==null)throw new NullPointerException();
        Node<Item> oldlast=last;
        last=new Node<Item>();
        last.item=item;
        if(isEmpty()){
            first=last;
        }else {
            last.prev=oldlast;
            oldlast.next=last;
            last.next=null;
        }
        N++;
    }

    public Item removeFirst() {
        if (N == 0) throw new NoSuchElementException();
        Item item = first.item;
        N--;
        if(N==0){
            first=null;
            last=null;
        }else{
            first=first.next;
            if(N==1)
                last=first;
        }
        return item;
    }

    public Item removeLast() {
        if(N==0)throw new NoSuchElementException();
        Item item=last.item;
        N--;
        if(N==0){
            first=null;
            last=null;
        }else{
            last=last.prev;
            if(N==1)
                first=last;
        }
        return item;
    }

    public boolean isEmpty() {
        return first==null||last==null;
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

