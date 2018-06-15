
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;     // top of stack
    private Node<Item> last;
    private Node<Item> origin;
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
        M=0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) // add the item to the front
    {
        Deque.Node<Item> oldfirst = first;
        first = new Deque.Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if(N==0){origin = new Deque.Node<Item>();origin=first;}
        N++;
        
    }

    public void addLast(Item item) // add the item to the end
    {
        if (M == 0 && N != 0) {
            last = new Deque.Node<Item>();
            last.item = item;
            origin.next = last;
            last.previous=origin;
        }else if(M==0&&N==0){//都還沒
            origin =new Deque.Node<Item>();
            last = new Deque.Node<Item>();
            last.item = item;
            origin = last;
        }else{
        Deque.Node<Item> oldlast = new Deque.Node<Item>();
        oldlast=last;
        last = new Deque.Node<Item>();
        last.item=item;
        last.previous=oldlast;
        oldlast.next=last;
        }
        N++;
        M++;
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }

    public Item removeLast() // remove and return the item from the end
    {
        if (isEmpty()) {
            throw new NoSuchElementException(""Stack underflow"");
        }
        Item item = last.item;        // save item to return
        last = last.previous;            // delete first node
        N--;
        return item;                   // return the saved item
    }
    
    @Override
    public Iterator<Item> iterator() {
        Deque.Node<Item> current;
        current = first;
        while(current!=null&&N>0){
            Item item = current.item;
            current = current.next;
            N--;
            StdOut.println(item);
        } 
        current = last;
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
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
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
            d.removeFirst();
            d.removeLast();
            Iterator e = d.iterator();
        }
    }
}

