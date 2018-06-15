import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int inisize;	 // initial size of deque
	private Node first;      // index of first item
    private Node last;       // index of last item

	// construct an empty deque
	public Deque() {}
	   

	// is the deque empty?
	public boolean isEmpty() {
		return first == null;
	}                

	// return the number of items on the deque
	public int size() {
		return inisize;
	}                       


	// add the item to the front
	public void addFirst(Item item) {
		throwIfNull(item);
		Node newFirst = new Node();
		newFirst.item = item;
		if(!isEmpty()){
			newFirst.next = first;
			first.previous = newFirst;
		}
		first = newFirst;
		if(last == null) last = first;

		inisize++;
	}         



	// add the item to the end
	public void addLast(Item item) {
		throwIfNull(item);
		Node newLast = new Node();
		newLast.item = item;
		if(last!=null){
		last.next = newLast;
		newLast.previous = last;
		}
		
		if(isEmpty()) first = last;
		inisize++;
	}          

	// remove and return the item from the front
	public Item removeFirst() {
		throwIfEmpty();
		Node oldFirst = first;
		first = first.next;

		if(isEmpty())last=null;
		else first.previous=null;

		inisize--;
		return oldFirst.item;
		}              


	// remove and return the item from the end
	public Item removeLast() {
		throwIfEmpty();
		Node oldLast = last;
		last.previous = last;
		if (last==null) first=null;
		else last.next=null;

		inisize--;
		return oldLast.item;

	}                


	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() {return new ListIterator();}
	private class ListIterator implements Iterator<Item>{
		private Node current = first;

		
		
		public boolean hasNext(){
			return current != null;
		}

		public Item next(){
			if (current == null) throw new NoSuchElementException();

			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove(){
		throw new UnsupportedOperationException();
		}


	}        

	
private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private void throwIfEmpty() {
        if (isEmpty())
            throw new NoSuchElementException();
    }

    private void throwIfNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

	public static void main(String[] args) {
		
		
	} 



}




