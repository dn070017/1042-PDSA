import java.util.Iterator;
import java.util.NoSuchElementException;


/**
.
 */
public class Deque<Item> implements Iterable<Item> {

    private Object[] s;
    private int size;

    private void resize() {
        if (size == s.length) {
            Object[] s2 = new Object[2 * s.length];
            for (int i = 0; i < size; i++) {
                s2[i] = s[i];
            }
            s = s2;
        } else if (size < s.length / 4) {
            int sizeTemp = 0;
            if (s.length % 2 != 0) {
                sizeTemp = s.length + 1;
            }
            Object[] s2 = new Object[sizeTemp / 2];
            for (int i = 0; i < size; i++) {
                s2[i] = s[i];
            }
            s = s2;
        }
    }

    // construct an empty deque
    public Deque() {
        s = new Object[10];
        size = 0;
    }


    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            for (int i = size - 1; i >= 0; i--) {
                s[i + 1] = s[i];
            }
            s[0] = item;
            size++;
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            s[size] = item;
            size++;
        }
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            Object item = s[0];
            for (int i = 1; i < size; i++) {
                s[i - 1] = s[i];
            }
            s[size - 1] = null;
            size--;
            return (Item) item;
        }
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            size--;
            Object item = s[size];
            s[size] = null;
            return (Item) item;
        }
    }


    public void print() {
        for (int i = 0; i < 10; i++) {
            System.out.print(s[i] + "" , "");
        }
        System.out.println("""");
    }


    @Override
    public Iterator<Item> iterator() {

        class qwert implements Iterator<Item> {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    return (Item) s[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        return new qwert();
    }
}

