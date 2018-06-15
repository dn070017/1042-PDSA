import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable{
	private int N;			// number of elements
	private Element<Item> first;
	private Element<Item> last;

	public Deque(){
		N = 0;
		first = null;
		last = null;
	} 

	private class Element<Item>{
		private Item item;
		private Element<Item> next;
		private Element<Item> previous;
	}

	public boolean isEmpty(){
		return N == 0;
	}

	public int size(){
		return N;
	}

	public void addFirst(Item item){
		if(item == null) throw new java.lang.NullPointerException();
		Element<Item> newFirst = new Element<Item>();
		if (isEmpty())  last = newFirst;
		else first.previous = newFirst;
		newFirst.item = item;
		newFirst.next = first;
		newFirst.previous = null;
		first = newFirst;
		++N;
	}

	public void addLast(Item item){
		if(item == null) throw new java.lang.NullPointerException();
		Element<Item> newLast = new Element<Item>();
		if (isEmpty())  first = newLast;
		else last.next = newLast;
		newLast.item = item;
		newLast.next = null;
		newLast.previous = last;
		last = newLast;
		++N;
	}

	public Item removeFirst(){			// garbage collection???
		if(isEmpty()) throw new java.util.NoSuchElementException();
		Item popItem = first.item;
		//first.next.previous = null;
		first = first.next;
		--N;
		return popItem;
	}

	public Item removeLast(){
		if(isEmpty()) throw new java.util.NoSuchElementException();
		Item popItem = last.item;
		//last.previous.next = null;
		last = last.previous;
		--N;
		return popItem;
	}

	public Iterator<Item> iterator(){
		return new DequeIterator<Item>(first);
	}

	public class DequeIterator<Item> implements Iterator<Item>{
			public Element<Item> current;

			public DequeIterator(Element<Item> first){
				current = first;
			}

			public boolean hasNext(){
				return current != null;
			}

			public void remove(){
				throw new java.lang.UnsupportedOperationException();
			}

			public Item next(){
				if(!hasNext()) throw new java.util.NoSuchElementException();
				Item item = current.item;
				current = current.next;
				return item;
			}

		};

	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		String a = null;
		deque.addFirst(""1"");
		deque.addFirst(""2"");
		deque.addFirst(a);
		System.out.println(deque.removeFirst());
		System.out.println(deque.removeFirst());
	}
}
