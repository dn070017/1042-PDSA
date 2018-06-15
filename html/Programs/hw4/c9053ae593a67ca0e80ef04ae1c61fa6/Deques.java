import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class Deque<Item> implements Iterable<Item> {
    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> pre;
    }

    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null || last == null;
    }

    public int size() {
        return N;
    }
    
    public void addFirst(Item item) {
        if (item.equals(null)) throw new NoSuchElementException(""item is null"");
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.pre = null;
        if (isEmpty()) last = first;
        else           oldfirst.pre = first;
        N++;
    }
        public void addLast(Item item) {
        if (item.equals(null)) throw new NoSuchElementException(""item is null"");
            Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = last.item;
        last = last.pre;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
        

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException(""Stack underflow"");
        return first.item;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + "" "");
        return s.toString();
    }
       

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }


    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    public static void main(String[] args)throws Exception {
     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//        String[] words = br.readLine().split("" ""); 
        String ws =""A,B,C,D,E,-,F,-,-,G,-,-,-,H,-,-,"";
        String ans;
        String[] word =ws.split("","");
        Deque stack=new Deque();
        int N = word.length;
        int i =0,j=0;

        while ( j != N )
        {
            if(word[j].equals(""-"")) System.out.println(stack.removeFirst());
            else{stack.addLast(word[j]);

            }
                j++;
        }        
        

     }
    }
}
