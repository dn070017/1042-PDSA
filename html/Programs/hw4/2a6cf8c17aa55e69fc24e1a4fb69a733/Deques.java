import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author stonebreaker
 */
public class Deques<Item> implements Iterable<Item>{
    
    private int N;
    private Node<Item> first;
    private Node<Item> last;
    
    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }
    
    public Deques(){
        first = null;
        last = null;
        N = 0;
    }
    
    public boolean isEmpty(){
        return 0 == N;
    }
    
    public int size(){
        return N;
    }
    
    public void addFirst(Item item){
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public void addLast(Item item){
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = oldlast;
        N++;
    }
    
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    public Item removeLast(){
        if(isEmpty())throw new NoSuchElementException();
        Item item = last.item;
        last = last.next;
        N--;
        return item;
    }
    
    public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);
    }
    
    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;
        
        public ListIterator(Node<Item> first){
            current = first;
        } 
        
        public boolean hasNext(){return current != null;}
        
        public void remove(){throw new UnsupportedOperationException();}
        
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String [] data = br.readLine().split("" "");
            Deques<String> s = new Deques<String>();
            s.addFirst(data[0]);
            s.addFirst(data[1]);
            s.addLast(data[2]);
            System.out.print(s.size()+""\n"");
            System.out.println(s.removeFirst());
            System.out.println(s.removeLast());
            System.out.println(s.removeFirst());
            System.out.print(s.isEmpty());
        }
    }
    
}
