import java.io.BufferedReader;
import java.io.FileReader;


public class Deque<Item> implements Iterable{
    
    private Node<Item> first;
    private Node<Item> last;
    private int N;
    
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
        
    public Deque(){ 
        first = null;
        last  = null;
        N = 0;
    }
    
    public boolean isEmpty()
    { return first == null; }
    
    public int size(){
        return N;
    }
    
    public void addFirst(Item item) {
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = null;
        if (isEmpty()) last = first;
        else           oldfirst.next = first;
        N++;
    }       
    
    public void addLast(Item item){
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }

    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    
    public Item removeLast(){
        Item item = last.item;
        last = last.next;
        N--;
        if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }
         
    public Iterator<Item> iterator(){
        return new ListIterator<Item>(first) {};
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
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {//args[0]
            
        }
    }
    
}
