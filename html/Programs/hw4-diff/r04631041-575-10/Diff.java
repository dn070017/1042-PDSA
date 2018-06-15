import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    public Iterator<Item> iterator() {return new ListIterator<Item>(first);}
//    private class ListIterator implements Iterator<Item>
//    {
//       private Node current=first;
//       public boolean hasNext() {return current!=null;}
//       public void remove() {}
//       public Item next()
//               {
//                   Item item=current.item;
//                   current=current.next;
//                   return item;
//               }
//    }
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

    
    private Node<Item> first;
    private Node<Item> last;
    private  int number;
    private class Node<Item>
    {
        Item item;
        Node<Item> next;
        Node<Item> previous;
    }
    public Deque()
    {
       first=null;
       last=null;
       number=0;
        assert check();
    }
    public boolean isEmpty()
    {return first==null;}
    public int size()
    {return number;}
    
    public void addFirst (Item item)//From website
    {
     if (item.equals(null)) throw new NullPointerException();
     Node<Item> newFirst=new Node<Item>();
     newFirst.item=item;
     if (first != null){
         newFirst.next=first;
         first.previous=newFirst;
     }
     first=newFirst;
     if (last==null) last=first;
     number++;
     assert check();
     }
    public void addLast (Item item)//From website
    {
        if (item.equals(null)) throw new NullPointerException();
        Node<Item> newLast=new Node<Item>();
        newLast.item=item;
        if (last!=null){
            newLast.previous=last;
            last.next=newLast;
        }
        last=newLast;
        if (first==null) first=last;
        number++;
        assert check();
    }
    public Item removeFirst()
    {   
        if(isEmpty()) throw new NoSuchElementException(""Stack underflow!"");
        Item item=first.item;
        first=first.next;
        number--;
        if (isEmpty()) last=null;
        else first.previous=null;
       assert check();
        if (number==0){
           first=null;
           last=null;
       }
        return item;
    }
    public Item removeLast() //From website
    {  
       if(isEmpty()) throw new NoSuchElementException(""Stack underflow!"");
       Item item=last.item;
       Node<Item> oldLast=last;
       last=oldLast.previous;
       if (last==null)
           first=null;
       else
           last.next=null;
       number--;
       assert check();
       if (number==0){
           first=null;
           last=null;
       }
       return item;
    }
    public static void main(String[] args){
        try{
        Deque<String> testing= new Deque<String>();
        testing.addFirst(""x"");
        testing.addLast(""e"");
        testing.addLast(""a"");
        //testing.addLast(""i"");
        //testing.addFirst(""b"");
        //testing.addLast(""j"");
        //testing.addFirst(""k"");
        //testing.addFirst(""p"");
        //testing.addLast(""q"");
        System.out.println(testing.removeLast());
        //System.out.println(testing.removeFirst());
        //System.out.println(testing.removeLast());
        //System.out.println(testing.removeFirst());
        //System.out.println(testing.removeFirst());
        //System.out.println(testing.removeFirst());
        //System.out.println(testing.removeLast());
        //System.out.println(testing.removeFirst());
        System.out.println(testing.removeFirst());
        System.out.println(testing.removeLast());
         System.out.println(testing.removeLast());
        testing.addFirst(""sd"");
        System.out.println(testing.size());
        Iterator<String> i=testing.iterator();
        while(i.hasNext()){
            String s=i.next();
            System.out.print(s+""\t"");
        }      
        //i.remove();
        }catch(NullPointerException e){System.out.println(e.getMessage());}
         
    }
    private boolean check() {
        if (number < 0) {
            return false;
        }
        else if (number == 0) {
            if (first != null) return false;
            if (last  != null) return false;
        }
        else if (number == 1) {
            if (first == null || last == null) return false;
            if (first != last)                 return false;
            if (first.next != null)            return false;
        }
        else {
            if (first == null || last == null) return false;
            if (first == last)      return false;
            if (first.next == null) return false;
            if (last.next  != null) return false;

            // check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= number; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != number) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    }  
}

