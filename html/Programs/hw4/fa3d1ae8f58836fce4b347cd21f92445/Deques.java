
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;     // top of stack
    private Node<Item> last;
    private int N,M;                // size of the stack

    // helper linked list class
    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public Deque() // construct an empty deque
    {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) // add the item to the front
    {
        if(item==null)throw new NullPointerException();
        Deque.Node<Item> oldfirst = first;
        first = new Deque.Node<Item>();
        first.item = item;
        first.next = oldfirst;
        
        if(N==0){last=first;}
            else{
                oldfirst.previous = last;
            }
        N++;
        
    }

    public void addLast(Item item) // add the item to the end
    {
        if(item==null)throw new NullPointerException();
            Deque.Node<Item> oldlast = new Deque.Node<Item>();
            oldlast = last;
            last = new Deque.Node<Item>();
            last.item = item;

            if(N==0){first=last;}
            else{
                oldlast.next = last;
            }
            last.previous=oldlast;
        N++;
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;        // save item to return
        first.previous=null;
        first = first.next;            // delete first node
        N--;
        if (N == 0) {
            first = null;
            last = null;
        }
        return item;                   // return the saved item
    }

    public Item removeLast() // remove and return the item from the end
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;        // save item to return
        last.next=null;
        last = last.previous;            // delete first node
        N--;
        if(N==0){
        first=null;last=null;
        }
        return item;                   // return the saved item
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Deque.Node<Item> current;

        public ListIterator(Deque.Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            String fund = br.readLine();
            String[] cha = fund.split("" "");
            Deque d = new Deque();
            d.addFirst(cha[0]);
            d.addFirst(cha[1]);
            d.addFirst(cha[2]);
            d.addFirst(cha[3]);            
            d.addLast(cha[4]);
            d.addLast(cha[5]);
            d.addLast(cha[6]);
            d.removeLast();
            d.removeLast();
            d.removeLast();

            d.removeFirst();
            Iterator e = d.iterator();
            d.M=d.N;
            while (e.hasNext()&&d.M>0) {
                d.M--;
                System.out.println(e.next());
            }
        }
    }
}
