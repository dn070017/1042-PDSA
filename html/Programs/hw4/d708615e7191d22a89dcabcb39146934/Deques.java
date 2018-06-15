import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable {

    private Node first = null;
    private Node last = first;
    private int size = 0;

    public static void main(String[] args) throws Exception {
       
    }

    private class Node {

        Node previous;
        Item item;
        Node next;
    }

    public Deque() {//產生一個空的node
        first = null;
        last = first;
    }

    public boolean isEmpty() {//回傳此node是否為空
        return first == null;
    }

    public int size() {//回傳此node的大小
        return size;
    }

    public void addFirst(Item item) {//加入一個node在前面        
        if (first == null) {
            first.item = item;
            first.next = null;
            first.previous = null;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;
            first.previous = null;
        }
        size++;
    }

    public void addLast(Item item) {//加入一個node在後面
        if (first == null) {
            first.item = item;
            first.next = null;
            first.previous = null;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
            last.previous = oldLast;
            last.next = null;
        }
        size++;
    }

    public Item removeFirst() {//回傳最前面的node,並移除他
        Item item = first.item;
        first = first.next;
        first.previous = null;
        size--;
        return item;
    }

    public Item removeLast() {//回傳最後面的node,並移除他
        Item item = last.item;
        last = last.previous;
        last.next = null;
        size--;
        return item;
    }

    public Iterator iterator() {//回傳此node裡的每一個物件
        return new ListIterator();
    }
    private class ListIterator implements Iterator{
        private Node current=first;
        public boolean hasNext(){return current !=null;}
        public void remove(){}
        public Item next(){
            Item item=current.item;
            current=current .next;
            return item;
        }
    }
   
}

