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
    last = null;
    }// construct an empty deque
public boolean isEmpty(){            // is the deque empty?
    return size() == 0;}
public int size(){                   // return the number of items on the deque
    return count;}
public void addFirst (Item item){     // add the item to the front
    if(item == null) throw new NullPointerException();
    else if(first == null && last == null){
        first = new Node();
        last = new Node();
        first.item = item;
        last.item = item;
        count++;   }
    else {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        oldfirst.pre = first;
        count++;
    }
}
public void addLast(Item item){         // add the item to the end
    if(item == null) throw new NullPointerException();
    else if(first == null && last == null){
        first = new Node();
        last = new Node();
        first.item = item;
        last.item = item;
        count++;   }
    else {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.pre = oldlast;
        oldlast.next = last;
        
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
       Deque<Integer> deque = new Deque();
       //deque.addLast(1);
       //deque.addFirst(14);
       deque.addFirst(11);
       deque.addLast(78);
       //deque.addFirst(55);
       deque.addLast(8);
       //StdOut.println(deque.last.pre.pre.item);
       
       Iterator<Integer> i = deque.iterator();
        while (i.hasNext())
        {
        int s = i.next();
        StdOut.println(s);
        }
       
       
    }
    
}

