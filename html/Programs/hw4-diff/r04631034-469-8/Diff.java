
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 *
 */
public class Deque<Item> implements Iterable<Item> {

          public LinkedList<Item> queue;

          public Deque() {
                    queue = new LinkedList<Item>();
          }

          public boolean isEmpty() {
                    if (queue.size() == 0) {
                              return true;
                    } else {
                              return false;
                    }
          }

          public int size() {
                    return queue.size();
          }

          public void addFirst(Item item) {
                    if (item == null) {
                              throw new java.lang.NullPointerException();
                    } else {
                              queue.addFirst(item);
                    }
          }

          public void addLast(Item item) {
                    //Item item = null;
                    if (item == null) {
                              throw new java.lang.NullPointerException();
                    } else {
                              queue.addLast(item);
                    }
          }

          public Item removeFirst() {
                    if (queue.isEmpty()) {
                              throw new java.util.NoSuchElementException();
                    } else {
                              Item first;
                              first = queue.getFirst();
                              queue.removeFirst();
                              return first;
                    }

          }

          public Item removeLast() {
                    if (queue.isEmpty()) {
                              throw new java.util.NoSuchElementException();
                    } else {
                              Item last;
                              last = queue.getLast();
                              queue.removeLast();
                              return last;
                    }
          }

          public Iterator iterator() {
                    Iterator iterator;
                    iterator = queue.iterator();
                    return iterator;

          }

          public static void main(String[] args) {

          }

}


