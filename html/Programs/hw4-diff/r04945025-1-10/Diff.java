import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

   	private class Node{
        Item item;
        Node prev;
        Node next;
   	}
    
   	private class DequeIterator implements Iterator<Item>{
   		private Node current = first;
   		public boolean hasNext(){
   		    return current != null;	
   		}
   		public Item next(){
   		    if(!this.hasNext()){
                throw new NoSuchElementException();
   		    }
   			Item item = current.item;
   			current = current.next;
   			return item;
   		}
   		public void remove(){
   		    throw new UnsupportedOperationException();  
   		}
   	}

    private Node first, last;

 	public Deque(){
 		this.first = null;
 		this.last  = null;
 	}

	public boolean isEmpty(){
		return first == null;
	}       

	public int size(){
        int count = 0;
        Node node = this.first;
        while(node != null){
        	node = node.next;
        	count++;
        }
        return count;
	}                        
   	public void addFirst(Item item){
   	    if(item == null){
   			throw new NullPointerException();
   		}
        Node node = new Node();
        node.item = item;
        if(this.isEmpty()){
            node.prev  = null;
       	    node.next  = null;
       	    this.first = node;  
       	    this.last  = node;
        }
        else{
            node.prev = null;
            node.next = this.first;
            this.first.prev = node;
       	    this.first = node;
        }
   	}

   	public void addLast(Item item){
   	    if(item == null){
   			throw new NullPointerException();
   	    }
        Node node = new Node();
        node.item = item;
        if(this.isEmpty()){
       		node.prev  = null;
       		node.next  = null;
        	this.first = node;
        	this.last  = node;
        }
        else{
           	node.prev = this.last;
        	node.next = null;
        	this.last.next = node;
          	this.last = node;
        }
   	}         
   	
   	public Item removeFirst(){
   		if(this.isEmpty()){
   		    throw new NoSuchElementException();
   		}
   		Item item  = this.first.item;
   		if(this.first == this.last){
   		    this.first = null;
   		  	this.last  = null;
   		}
   		else{
   		    this.first = this.first.next;
   		    this.first.prev = null;
   		}
   		return item;
   	}                
   	
   	public Item removeLast(){
        if(this.isEmpty()){
   		    throw new NoSuchElementException();
   		}
   		Item item = this.last.item;
  	    if(this.first == this.last){
  			this.first = null;
  			this.last = null;
   		}
   		else{
        	this.last = this.last.prev;
        	this.last.next = null;
        }
   		return item;
   	}                  
   	
   	public Iterator<Item> iterator(){
        return new DequeIterator();
   	}
   	
   	public void print(){
   		Node node = this.first;
   	    while(node != null){
   	    	System.out.print(node.item);
   	    	System.out.printf(""\t"");
            if(node.prev != null){
            	System.out.printf(""prev:"");
            	System.out.print(node.prev.item);
            }
            System.out.printf(""\t"");
            if(node.next != null){
            	System.out.printf(""next:"");
            	System.out.print(node.next.item);
            }
            System.out.printf(""\n"");
        	node = node.next;
        }	
   	}

    public static void main(String[] argv){
        System.out.println(""Test"");
    }
}

