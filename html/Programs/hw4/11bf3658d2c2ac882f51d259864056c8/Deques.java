import java.util.Iterator;
import java.util.*;
public class Deque<Item> implements Iterable<Item>{
private int count = 0;
private Node first = null;
private Node last = null;
private class Node{
   Item item;
   Node next;
   Node pre;}
public Deque(){
    Deque deques = new Deque();}// construct an empty deque
public boolean isEmpty(){            // is the deque empty?
    return count == 0;}
public int size(){                   // return the number of items on the deque
    return count;}
public void addFirst (Item item){     // add the item to the front
    if(item == null) throw new NullPointerException();
    else{
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        count++;
    }
}
public void addLast(Item item){         // add the item to the end
    if(item == null) throw new NullPointerException();
    else{
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.pre = oldlast;
        count++;
    }
}
public Item removeFirst(){                        // remove and return the item from the front
    if(size()==0)  throw new NoSuchElementException(); 
    else {
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }
}
public Item removeLast(){                           // remove and return the item from the end
    if(size()==0)  throw new NoSuchElementException(); 
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
       
    }
    
}

