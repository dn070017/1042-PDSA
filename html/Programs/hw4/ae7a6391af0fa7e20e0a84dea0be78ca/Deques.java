
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 余軒
 */
public class Deque<Item> implements Iterable<Item>{

private Node first;
private Node last;
private int size;

    

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
.
    
// construct empty deque    
public Deque(){
    first = null;
    last = null;
    size = 0;
    
}    


    
private class Node<Item>{
    private Item item;
    public Node next;
    public Node previous;
    public Node(Item item, Node next,Node previous){
     this.item = item;
     this.next = next;
     this.previous = previous;
    }
//    Item item;
//    Node next=null;
//    Node previous=null;
}

public boolean isEmpty(){
    return first ==null;
}

public int size(){
    return size;
}

public void addFirst(Item item){
    if (item == null){
    throw new NullPointerException();}
    else{
    if(first==null)
    {
      first = new Node(item,null,null);
      last = first;
      size++;
    }
    else {
        Node temp = new Node(item,first,null);
        first.previous = temp;
        first = temp;
        size++;
    }
    }
}

public void addLast(Item item){
    if (item == null){
    throw new NullPointerException();}
    else{
    if(first==null){
        first = new Node(item,null,null);
        last = first;
        size++;
    }
    else{
        Node temp = new Node(item,null,last);
        last.next = temp;
        last = temp;
        size++;
        
                }
    }
}

public Item removeFirst(){
    if(first==null) throw new NoSuchElementException(""list is empty"");
    Item item = (Item) first.item;
    first = first.next;
    if (first!=null){
        first.previous=null;
    }
    else{
         //移除後變empty
        last = first;
    }
    size--;
    return item;
}

public Item removeLast(){
    if(last==null) throw new NoSuchElementException(""list is empty"");
    Item item = (Item)last.item;
    last = last.previous;
    if (last!=null){
        last.next =null;
    }
    else{
        //移除後變empty
        first = last;
    }
    size--;
    return item;
}

   public static void main(String[] args) throws IOException {
        // TODO code application logic here
          
   }
}

