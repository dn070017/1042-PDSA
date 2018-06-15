
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;              // size of the Deque
    private Item[] data;        // new datas of the Deque
    private Item[] olddata;     // for saving datas
//    construct an empty deque

    public Deque() {
        data = (Item[])(new Object[100]);
        olddata = (Item[])(new Object[100]);
        data[0] = null;
        N = 0;
    }
//    is the deque empty?
    public boolean isEmpty() {
        return data[0] == null;
    }
//    return the number of items on the deque

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == 0) {
            data[0] = item;
            N++;
        } else {
//            olddata = (Item[]) (new Object[N]);
            olddata = data;
//            data = (Item[]) (new Object[N + 1]);
            data[0] = item;
            for (int i = 0; i < N; i++) {
                data[i + 1] = olddata[i];
            }
            N++;
        }

    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == 0) {
            data[0] = item;
            N++;
        } else {
//            olddata = (Item[]) (new Object[N]);
//            olddata = data;
//            data = (Item[]) (new Object[N + 1]);
            data[N] = item;
//            for (int i = 0; i < N; i++) {
//                data[i] = olddata[i];
//            }
            N++;
        }

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (N == 1) {
            Item item = data[0];
            data[0] = null;
            N = 0;
            return item;
        } else {
            Item item = data[0];
//            olddata = (Item[]) (new Object[N]);
            olddata = data;
//            data = (Item[]) (new Object[N - 1]);
            for (int i = 0; i < N - 1; i++) {
                data[i] = olddata[i + 1];
            }
            N--;
            return item;
        }
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (N == 1) {
            Item item = data[0];
            data[0] = null;
            N = 0;
            return item;
        } else {
            Item item = data[N - 1];
//            olddata = (Item[]) (new Object[N]);
//            olddata = data;
//            data = (Item[]) (new Object[N - 1]);
//            for (int i = 0; i < N - 1; i++) {
//                data[i] = olddata[i];
//            }
            data[N-1]=null;
            N--;
            return item;
        }

    }

    public Iterator iterator() {
        return new ListIterator<Item>(data);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Item[] current;
        private Item[] saved;
        private int N;

        public ListIterator(Item[] data) {
            current = data;
            N = data.length;
        }

        public boolean hasNext() {
            return current[0] != null;
        }

        public void remove() {

            throw new UnsupportedOperationException();

        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (N == 1) {
                Item item = current[0];
                data[0] = null;
                N = 0;
                return item;
            } else {
                Item item = current[0];
//                saved = (Item[]) (new Object[N]);
                saved = current;
//                current = (Item[]) (new Object[N - 1]);
                for (int i = 0; i < N - 1; i++) {
                    current[i] = saved[i + 1];
                }
                N--;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Deque dq = new Deque();

        dq.addFirst(5);

        dq.addFirst(2);

        System.out.print(dq.iterator().next() + ""\n"");

    }

}

