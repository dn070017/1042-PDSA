import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of deque
    private Node<Item> last;     // end of deque
    private int N;               // number of elements on deque
    
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
    public Deque(){                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty() {                 // is the deque empty?
        return (N==0);
    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {         // add the item to the front

        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.previous = null;
        
        if (isEmpty()) {first.next = null; last = first;}
        else{
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        N++;
    }

    public void addLast(Item item) {           // add the item to the end

        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {last.previous = null; first = last;}
        else{
            last.previous = oldlast;
            oldlast.next = last;
        }
        N++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException(""Deque first underflow"");
        Item item = first.item;
        first = first.next;
        first.previous = null;
        N--;
        if (isEmpty()) {last = null; first = null;}
        return item;
    }

    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException(""Deque last underflow"");
        Item item = last.item;
        last = last.previous;
        last.next = null;
        N--;
        if (isEmpty()) {first = null; last = null;}
        return item;
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new ListIterator<Item>(first);
    }
    
    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current ;
        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext()  { return current != null;                }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
//            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public static void main(String[] args) {
      Deque<String> s = new Deque<String>();
      try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            StringBuilder everything = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                everything.append(line);
                everything.append("" "");
            }
            String[] data = everything.toString().split("" "");
            for(int i=0;i<data.length;i++){
                String item = data[i];               
                if (item.equals(""fa"")||item.equals(""fb"")||item.equals(""fc"")) s.addFirst(item); 
                else if (item.equals(""f-"")) StdOut.print(s.removeFirst() + "" "");
                else if (item.equals(""la"")||item.equals(""lb"")||item.equals(""lc"")) s.addLast(item);
                else if (item.equals(""l-"")) StdOut.print(s.removeLast() + "" "");           
                else throw new NoSuchElementException();
            }
      }
      catch(Exception ex){
          StdOut.println(""something wrong!!"");
      }
      System.out.printf(""\n"");
      Iterator<String> i = s.iterator();
      while (i.hasNext()){
            String a = i.next();
            System.out.printf(a + "" "");
      }
      System.out.printf(""\n"");
      System.out.println(""deque size = "" + s.size());
    }
}

