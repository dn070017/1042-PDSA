
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author jerry
 */
public class Deque<Item> implements Iterable<Item> {

    private Item[] s;
    private int first;
    private int last;
    private int N;

    public Deque() {
        s = (Item[]) new Object[2];
        first = 0;
        last = 0;
        N = 0;
    }

    public boolean isEmpty() {
        if (N == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return N;
    }
    
    private void resize(int max){
          Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = s[(first + i) % s.length];
        }
        s = temp;
        first = 0;
        last  = N;  
    }


    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == s.length) resize(2*s.length);   // double size of array if necessary
        s[first++] = item;                        // add item
        if (first == s.length) first = 0;          // wrap-around
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == s.length) resize(2*s.length);   // double size of array if necessary
        s[last++] = item;                        // add item
        if (last == s.length) last = 0;          // wrap-around
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = s[first];
        s[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == s.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == s.length/4) resize(s.length/2); 
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = s[last];
        s[last] = null;                            // to avoid loitering
        N--;
        last--;
        if (last == 0) last = s.length;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == s.length/4) resize(s.length/2); 
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = s[(i + first) % s.length];
            i++;
            return item;
    }
    }

}

