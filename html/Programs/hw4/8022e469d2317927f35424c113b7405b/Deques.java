import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable{
	private Node<T> dummyNode;
	private int N;

	public Deque(){
		dummyNode = new Node<T>();
		N = 0;
	}

	private class Node<T>{
		private Node<T> prev;
		private Node<T> next;
		private T content;

		public Node() {
			prev = next = this;
		}

		private Node(T content, Node<T> prev, Node<T> next){
			this.content = content;
			this.prev = prev;
			this.next = next;
		}
	}

	public boolean isEmpty(){
		return N == 0;
	}

	public int size(){
		return N;
	}

	public void addFirst (T Item){
		if(Item == null) throw new java.lang.NullPointerException();
		dummyNode.next.prev = dummyNode.next = new Node<T>(Item, dummyNode, dummyNode.next);
		++N;
	}

	public void addLast(T Item){
		if(Item == null) throw new java.lang.NullPointerException();
		dummyNode.prev.next = dummyNode.prev = new Node<T>(Item, dummyNode.prev, dummyNode);
		++N;
	}

	public T removeFirst(){
		return remove(dummyNode.next);
	}

	public T removeLast() {
		return remove(dummyNode.prev);
	}

	protected T remove(Node<T> target) {
		T rtn = target.content;
		target.prev.next = target.next;
		target.next.prev = target.prev;
		--N;
		return rtn;
	}

	public Iterator<T> iterator(){
		return new DequeIterator<T>(dummyNode.next);
	}

	public class DequeIterator<T> implements Iterator<T>{
			public Node<T> current;

			public DequeIterator(Node<T> first){
				current = first;
			}

			public boolean hasNext(){
				return current != dummyNode;
			}

			public void remove(){
				throw new java.lang.UnsupportedOperationException();
			}

			public T next(){
				if(!hasNext()) throw new java.util.NoSuchElementException();
				T Item = current.content;
				current = current.next;
				return Item;
			}

		}

	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		deque.addFirst(""1"");
		deque.addLast(""2"");
		deque.addLast(""3"");
		deque.addLast(""4"");
		deque.addFirst(""0"");
		// System.out.println(deque.removeLast());
		// System.out.println(deque.removeLast());
		// System.out.println(deque.removeLast());
		// System.out.println(deque.removeLast());
		// System.out.println(deque.removeFirst());
		// System.out.println(deque.removeFirst());
		Iterator<String> i = deque.iterator();
		 while(i.hasNext()){
			String s = i.next();
			System.out.print(s);
		}
		System.out.println();
	}
}
