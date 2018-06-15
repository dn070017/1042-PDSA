
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;
    private boolean judge;
    private Node first, last;
    private Node oldlast, oldfirst,temp;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        oldlast=null;
        oldfirst=null;
        N = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return N == 0;
    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        } else {
            oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) {
                first = last;
                temp=last;
                judge=true;
            } else{
                if(oldlast!=null){
                    last.previous =oldlast;
                    oldlast.next = last;
                if(N==1) {
                    first.next=last;
                    last.previous=first;
                }
                }
            }
            N++;
        }
    }

    public void addFirst(Item item) {           // add the item to the front
        if (item == null) {
            throw new NullPointerException();
        } else {
            oldfirst = first;
            first = new Node();
            first.item = item;
            first.previous =null;
            if (isEmpty()) {
                last = first;
                temp=first;
                judge=true;
            } else {
                first.next = oldfirst;
                if(N==1) last=first.next;
            }
            N++;
        }
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item;
            item = first.item;
            first.item = null;
            first = first.next;
            N--;
            return item;
        }
    }
    
    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item;
            
            Node help=first;
            if(!last.equals(temp) && judge) {
                item = last.item;
                last = last.previous;
            } 
            else if(last.equals(temp)){
                while(first!=temp)
                {
                    last=first;
                    first=first.next;
                }
                item=first.item;
                first=help;
                judge=false;
            }
            else {
                Node temp2=last;
                while(first!=temp2)
                {
                    last=first;
                    first=first.next;
                }
                item=first.item;
                first=help;
            }
            N--;
            return item;
        }
    }

    
    @Override
    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return (Iterator<Item>) new ListIterator();
    }

    private class ListIterator implements Iterable<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item Next() {
            Item item = current.item;
            if (hasNext()) {
                current = current.next;
                return item;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public Iterator<Item> iterator() {
.
        }
    }


    public static void main(String[] args) throws Exception {   // unit testing

        Deque deque = new Deque();
        
        //Iterator it = deque.iterator();
        
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(8);
        //String f = null;
        //deque.addFirst(f);

       // StdOut.println(deque.size());
       
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        
        StdOut.println("""");
        
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(0);
        
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        
        //Iterator it = deque.iterator();
        //while(it.hasNext()){
            //StdOut.println(it.next());
        //}
        
    }
   
}


