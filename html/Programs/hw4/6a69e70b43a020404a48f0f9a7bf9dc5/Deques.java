import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author stonebreaker
 */
public class Deque<Item> implements Iterable<Item>{
    
    private int N;
    private Node<Item> first;
    private Node<Item> last;
    
    
    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
       
    }
    
    public Deque(){
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
        if(1 == N){last = first;}
        else if(2 == N){last.previous = first;}
        else{oldfirst.previous = first;}
        
    }
    
    public void addLast(Item item){
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = oldlast;
        N++;
        if(1 == N){first = last;}
        else if(2 == N){first.next = last;}
        else{oldlast.next = last;}
    }
    
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if(1 != N){first.previous = null;}
        N--;
        if(isEmpty()){
            last = null;
        }
        return item;
    }
    
    public Item removeLast(){
        if(isEmpty())throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if(1 != N){last.next = null;}
        N--;
       if(isEmpty()){
           first = null;
       }
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
            Deque<String> s = new Deque<String>();
            s.addFirst(data[0]);
            s.addFirst(data[1]);
            s.addFirst(data[2]);
            
//            s.addLast(data[0]);
//            s.addLast(data[1]);
//            s.addLast(data[2]);
//            System.out.print(s.size()+""\n"");
            System.out.println(s.removeFirst());
            System.out.println(s.removeLast());
            System.out.println(s.removeLast());
//           System.out.println(s.removeLast());
//            System.out.print(s.size()+""\n"");
//            System.out.println(s.removeLast());
//            System.out.print(s.size()+""\n"");
//            System.out.println(s.removeLast());
//            System.out.print(s.isEmpty()+""\n"");
//            System.out.print(s.size()+""\n"");
        }
    }
    
}
