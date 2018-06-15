
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
        s = (Item[]) new Object[1024];
        first = 1023;
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
          first++;
        for (int i = 0; i < N; i++) {
            temp[i] = s[(first + i) % s.length];
        }
        s = temp;
        first = s.length-1;
        last  = 0;  
    }


    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == s.length) resize(2*s.length);   // double size of array if necessary
        s[first] = item;  // add item
        first--;
        if (first == -1) first = s.length-1;          // wrap-around
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == s.length) resize(2*s.length);   // double size of array if necessary
        s[last] = item;                        // add item
        last++;
        if (last == s.length) last = 0;          // wrap-around
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        first++;
        if (first == s.length) first = 0;
        Item item = s[first];
        s[first] = null;                            // to avoid loitering
        N--;
        // shrink size of array if necessary
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        last--;
        if (last == -1) last = s.length-1;
        Item item = s[last];
        s[last] = null;                            // to avoid loitering
        N--;
        // shrink size of array if necessary
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private int i = (first+1);

        public boolean hasNext() {
            if(i >= last) return (i-s.length) < last;
            else return i < last;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = s[i % s.length];
            i++;
            return item;
    }
    }

}

