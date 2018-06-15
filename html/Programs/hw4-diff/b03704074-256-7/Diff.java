import java.util.Iterator;
import java.util.*;
public class Deque<Item> implements Iterable<Item>{
private int count;
private Node first;
private Node last;
private class Node{
   Item item;
   Node next;
   Node pre;}
public Deque(){
    count = 0;
    first = null;
    last = null;}// construct an empty deque
public boolean isEmpty(){            // is the deque empty?
    return size() == 0;}
public int size(){                   // return the number of items on the deque
    return count;}
public void addFirst (Item item){     // add the item to the front
    if(item == null) throw new NullPointerException();
    else if(isEmpty()){
        first = new Node();
        last = new Node();
        last.item = item;
        first.item = item;
        first = last;
        count++;
    }
    else{
        Node newfirst = new Node();
        //newfirst.item = item;
        first.pre = newfirst;
        Node oldfirst = first;
        first = new Node();
        first = newfirst;
        first.item = item;
        first.next = oldfirst;
        count++;
    }
}
public void addLast(Item item){         // add the item to the end
    if(item == null) throw new NullPointerException();
    else if(isEmpty()){
        first = new Node();
        last = new Node();
        last.item = item;
        first.item = item;
        first = last;
        count++;
    }
    else{
        Node newlast = new Node();
        //newlast.item = item;
        last.next = newlast;
        Node oldlast = last;
        last = new Node();
        last = newlast;
        last.item = item;
        last.pre = oldlast;
        count++;
    }
}
public Item removeFirst(){                        // remove and return the item from the front
    if(isEmpty())  throw new NoSuchElementException(); 
    else {
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }
}
public Item removeLast(){                           // remove and return the item from the end
    if(isEmpty())  throw new NoSuchElementException(); 
    else{
        Item item = last.item;
        last = last.pre;
        count--;
        return item;
    }
}
public Iterator iterator(){
    return new ListIterator();
}
private class ListIterator implements Iterator<Item>{
    private Node current = first;
    public boolean hasNext(){
        return current != null;
    }
    public void remove(){
        throw new UnsupportedOperationException();
    }
    public Item next(){
        if(hasNext()){
            Item item = current.item;
            current = current.next;
            return item;
        }
        else throw new NoSuchElementException(); 
    }
}


    
    public static void main(String[] args) {
       Deque deque = new Deque();
       
       deque.addLast(4);
       deque.addFirst(5);
       deque.removeLast();
       deque.addLast(6);
       deque.removeLast();
       deque.addLast(7);
       
       deque.removeLast();
       deque.removeLast();
       deque.addFirst(99);
       //deque.removeFirst();
       //deque.removeFirst();
       //StdOut.println(deque.last.pre.pre.item);
       
       Iterator<Integer> i = deque.iterator();
        while (i.hasNext())
        {
        int s = i.next();
        StdOut.println(s);
        }
       
    }
    
}
