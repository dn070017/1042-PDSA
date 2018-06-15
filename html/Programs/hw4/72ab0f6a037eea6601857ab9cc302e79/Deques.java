import java.lang.UnsupportedOperationException;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item>{    
    
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private Node<Item> oldlast;
    private int N;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }    
    
    public Deque(){
        first=null;
        last=null;
        oldlast=null;
        N=0;
    }
    public boolean isEmpty(){// is the deque empty?    
        return first == null;
    }
    public int size(){// return the number of items on the deque
        return N;
    }
    public void addFirst(Item item){// add the item to the front
        if(item==null) throw new NullPointerException();
        Node <Item>oldfirst=first;
        first=new Node<>();
        first.item = item;
        if(isEmpty()) {
            first=last;
            first.next=null;
        }
        else first.next = oldfirst;
        N++;
    }
    public void addLast(Item item){// add the item to the end
        if(item==null) throw new NullPointerException();
        oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else  oldlast.next = last;
        N++;
    }
    public Item removeFirst(){// remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = first.item;
        first = first.next;
        N--;
        if(first==last){
            first.next=null;
        }
        
        if (isEmpty()) {
            last = null;
            oldlast=null;
        }   // to avoid loitering
        return item;     
    }
    public Item removeLast(){// remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException(""Queue underflow"");
        Item item = last.item;
        last = oldlast;
        N--;
        if(first==last){
            first.next=null;
        }
        
        if (isEmpty()) {
            last = null;
            oldlast=null;
        }   // to avoid loitering
        return item;
    }
   public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
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


    
    public static void main(String[] args) {
        Deque<String> gg=new Deque<String>();
        gg.addLast(""統神4"");
        gg.addFirst(""統神1"");
        gg.addFirst(""統神2"");
        gg.addFirst(""統神3"");
        gg.addLast(""統神4"");
        gg.addLast(""統神5"");
        gg.addLast(""統神6"");
        System.out.println(gg.removeFirst());
        System.out.println(gg.removeFirst());
        System.out.println(gg.removeLast());
        System.out.println(gg.removeFirst());
        System.out.println(gg.removeFirst());
        System.out.println(gg.removeLast());
        System.out.println(gg.removeLast());
        System.out.println(gg.removeLast());
        System.out.println(gg.removeLast());
        System.out.println(gg.removeLast());
        System.out.println(gg.removeFirst());
       
        
    }
}

