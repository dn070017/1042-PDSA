import java.util.Iterator;
import java.util.NoSuchElementException;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author hung-wei
 */
public class Deque<Item> implements Iterable<Item> {
    
    private Node first = null;
    private Node last = null;
    private int size = 0; 
    
    private class Node
    {
        Item item;
        Node left;
        Node right;
    }

    public boolean isEmpty()                 // is the deque empty?
    { return size() == 0; }

    public int size()                        // return the number of items on the deque
    { return size; }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) { throw new NullPointerException(); }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.left = null;
        first.right = oldfirst;
        if (isEmpty()) last = first;
        else           oldfirst.left = first;
        size++;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) { throw new NullPointerException(); }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.right = null;
        last.left = oldlast;
        if (isEmpty()) first = last;
        else           oldlast.right = last;
        size++;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty()) { throw new java.util.NoSuchElementException(); }
        Item item = first.item;
        first = first.right;
        size--;
        if (isEmpty()) last = null;
        return item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) { throw new java.util.NoSuchElementException(); }
        Item item = last.item;
        last = last.left;
        size--;
        if (isEmpty()) first = null;
        return item;
    }
    
    public Iterator<Item> iterator() { return new DequeIterator();}        // return an iterator over items in order from front to end
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.right;
            return item;
        }
    }
    
    public static void main(String[] args)   // unit testing
    {
    }
}

