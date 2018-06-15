/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.util.Iterator;

/**
 *
 * @author 士齊
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int N;

    private class Node {

        Item item;
        Node next;
        Node previous;
    }

    public Deque() {          // construct an empty deque

    }

    public boolean isEmpty() {    // is the deque empty?
        return first == null;
    }

    public int size() {   // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {     // add the item to the front
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.previous = first;
        }
    }

    public void addLast(Item item) {      // add the item to the end
        if (last == null) {
            last = new Node();
            last.item = item;
            first = last;
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.previous = oldlast;
            oldlast.next = last;
        }

    }

    public Item removeFirst(){       // remove and return the item from the front
        Item A = first.item;
        first = first.next;
        return A;        
    }

    public Item removeLast(){        // remove and return the item from the end
        Item A = last.item;
        last = last.previous;
        return A;        
    }

    public Iterator iterator(){  // return an iterator over items in order from front to end
        return new Listiterator();        
    }
    
    public class Listiterator implements Iterator<Item>{
        private Node current = first;
        
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){
//            not support
        }
    }

    public static void main(String[] args) { // unit testing
        
//        Deque DEQ = new Deque<Integer>();
//        
//        int a = 1;
//        int b = 2;
//        int c = 3;
//        
//        
//        DEQ.addFirst(a);
//        DEQ.addFirst(b);
//        DEQ.addLast(c);
//        DEQ.iterator();
        
    }
}

