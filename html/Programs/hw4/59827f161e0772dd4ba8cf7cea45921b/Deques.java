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
        if(item.equals(null)) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if(isEmpty()) last = first;
        else oldfirst.previous = first;
        N++;
//        if(1 == N){last = first;}
//        else if(2 == N){last.previous = first;}
//        else{oldfirst.previous = first;}
        
    }
    
    public void addLast(Item item){
        if(item.equals(null)) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.previous = oldlast;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        N++;
//        if(1 == N){first = last;}
//        else if(2 == N){first.next = last;}
        //else{oldlast.next = last;}
    }
    
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        //if(1 != N){first.previous = null;}
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
        //if(1 != N){last.next = null;}
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
            //String [] data = new String [9];
            Deque<String> s = new Deque<String>();
            s.addFirst(data[0]);
            s.addFirst(data[1]);
            s.addFirst(data[2]);
            s.addFirst(data[3]);
            s.addFirst(data[4]);
            s.addFirst(data[5]);
            s.addFirst(data[6]);
            s.addFirst(data[7]);
            s.addFirst(data[8]);
//            s.addLast(data[0]);
//            s.addLast(data[1]);
//            s.addLast(data[2]);
//            System.out.print(s.size()+""\n"");
            int N = s.size();
            for(int i = 0; i < N; i++)
            System.out.println(s.removeFirst());
            
            
            System.out.print(s.isEmpty());
             System.out.println(s.removeLast());
//            System.out.println(s.removeFirst());
//            System.out.println(s.removeLast());
//            System.out.println(s.removeFirst());
//            System.out.println(s.removeLast());
//            System.out.println(s.removeFirst());
//            System.out.println(s.removeLast());
//            System.out.println(s.removeFirst());
//            System.out.println(s.removeLast());
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
