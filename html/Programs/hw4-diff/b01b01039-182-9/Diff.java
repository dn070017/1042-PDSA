import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> pre;
	}

	// construct an empty deque
	public Deque(){
		first = null;
		last = null;
		n = 0;
	}
	
	private void first_D(Item item){
		if(item == null){throw new java.lang.NullPointerException();}
		first = new Node();
		first.item = item;
		last = first;
		n++;
	}
	
	private Item last_D(){
		Item item = first.item;
		first = null;
		last = null;
		n--;
		return(item);
	}
	
	// is the deque empty?
	public boolean isEmpty(){
		if(n == 0)return true;
		else return false;
	}

	// return the number of items on the deque
	public int size(){
		return n;
	}
	
	// add the item to the front
	public void addFirst(Item item){
		if(n == 0){
			first_D(item);
		}else{
			Node old_first = first;
			first = new Node();
			first.item = item;
			first.next = old_first;
			old_first.pre = first;
			n++;
		}
	}
	
	// add the item to the end
	public void addLast(Item item){
		if(n == 0){
			first_D(item);
		}else{
			Node old_last = last;
			last = new Node();
			last.item = item;
			last.pre = old_last;
			old_last.next = last;
			n++;
		}
	}

	// remove and return the item from the front
	public Item removeFirst(){
		Item item;
		if(n == 0){
			throw new java.util.NoSuchElementException();
		}else if(n == 1){
			item = last_D();
		}else{
			item = first.item;
			first = first.next;
			first.pre = null;
			n--;
		}
		return(item);
	}

	// remove and return the item from the end
	public Item removeLast(){
		Item item;
		if(n == 0){
			throw new java.util.NoSuchElementException();
		}else if(n == 1){
			item = last_D();
		}else{
			item = last.item;
			last = last.pre;
			last.next = null;
			n--;
		}
		return(item);
	}

	public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
	// unit testing
	public static void main(String[] args){
		/*
		String input = new String();
		if(args.length == 0){
			Scanner scanner = new Scanner(System.in);
			System.out.printf(""Input text :"");
			input = scanner.nextLine();
		}else{
			input = args[0];
		}
		String[] Input = input.split("" "");
			
		Deque<String> deque = new Deque<String>();
		
		for(String s : Input){
			System.out.println(""Add first :"" + s);
			deque.addFirst(s);
		}
		
		for(String s : deque){
			System.out.println(""deque : "" + s);
		}
		
		System.out.println(deque.isEmpty());
		while(deque.size() > 0){
			if(deque.size()%2==0){
				System.out.println(""Remove last  : "" + deque.removeLast());
			}else{
				System.out.println(""Remove first : "" + deque.removeFirst());
			}
		}
		System.out.println(deque.isEmpty());
		*/
		Deque<Integer> deque = new Deque<Integer>();
		
		Integer[] i_list = {1,2,3,4,5};
		for(Integer i : i_list){
			System.out.println(i);
			System.out.println(""Add first :"" + i);
			deque.addFirst(i);
		}
		
		for(Integer i : deque){
			System.out.println(""deque : "" + i);
		}
		
		System.out.println(deque.isEmpty());
		while(deque.size() > 0){
			if(deque.size()%2==0){
				System.out.println(""Remove last  : "" + deque.removeLast());
			}else{
				System.out.println(""Remove first : "" + deque.removeFirst());
			}
		}
		System.out.println(deque.isEmpty());
		
		
	}

}

