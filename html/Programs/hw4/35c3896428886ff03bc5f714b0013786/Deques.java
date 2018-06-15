import javax.xml.soap.Node;
import java.util.*;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable {      // Deque實作Iterable，故需包含方法iterator()

    private int n;
    private Node<Item> first;
    private Node<Item> last;
    private Node<Item> current=first;

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
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast(){
        Item item = last.item;
        last = last.prev;
        n--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{      //DequeIterator實作Iterator<>
                                                                // ，故需包含hesNext()及next()
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) throws Exception {

//        Deque DequeTest = new Deque();
//        System.out.println(DequeTest.iterator());
//        Iterator it = DequeTest.iterator();
//
//        for (Object o : DequeTest) System.out.println(o);

        // read file from args[0] in Java 7 style
//        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//
//            // read a line and split by ','
//            String[] data = br.readLine().split("","");
//
//            // store the first integer in variable stringCount (number of announced strings)
//            int stringCount = Integer.parseInt(data[0]);
//
//            // store the second integer in variable num (dimension of matrix: num * num)
//            int num = Integer.parseInt(data[1]);
//
//            // initilization of a String array in Java
//            String[] announce = new String[stringCount];
//            String[][] matrix = new String[num][num];
//
//            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

    }
}

