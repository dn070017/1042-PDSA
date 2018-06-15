import java.util.*;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable {      // Deque實作Iterable，故需包含方法iterator()

    private int n, index;
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return n;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (!(n==0)){
            oldfirst.prev=first;
        }else {
            last=first;
        }
        n++;
    }

    // add the item to the end
    public void addLast(Item item){
        if (item == null) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        if (isEmpty()) first = last;
        else {
            oldlast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) {throw new NoSuchElementException();}
        else if (n==1) {first = last;}
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast(){
        if (isEmpty()) {throw new NoSuchElementException();}
        else if (n==1) {first = last;}
        Item item = last.item;
        last = last.prev;
        n--;
        return item;
    }



    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator(){
        return new DequeIterator<Item>(first);
    }


    private class DequeIterator<Item> implements Iterator<Item> {      //DequeIterator實作Iterator<>
                                                                       // ，故需包含hesNext()及next()
        private Node<Item> current;

        public DequeIterator(Node<Item> first){current=first;}

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) throws Exception {
    }
}

