import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    public Iterator<Item> iterator() {return new ListIterator();}
    private class ListIterator implements Iterator<Item>
    {
       private Node current=first;
       public boolean hasNext() {return current!=null;}
       public void remove() {}
       public Item next()
               {
                   Item item=current.item;
                   current=current.next;
                   return item;
               }
    }
    private Node first;
    private Node last;
    private  int number;
    private class Node
    {
        Item item;
        Node next;
        Node previous;
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
     Node newFirst=new Node();
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
        Node newLast=new Node();
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
        Item item=first.item;
        first=first.next;
        number--;
        if (isEmpty()) last=null;
        else first.previous=null;
        assert check();
        return item;
    }
    public Item removeLast() //From website
    {
       Item item=last.item;
       Node oldLast=last;
       last=oldLast.previous;
       if (last==null)
           first=null;
       else
           last.next=null;
       number--;
       assert check();
       return item;
    }
    public static void main(String[] args){
        Deque<String> testing= new Deque<String>();
        testing.addFirst(""x"");
        testing.addLast(""e"");
        testing.addLast(""a"");
        testing.addLast(""i"");
        testing.addFirst(""b"");
        testing.addLast(""j"");
        testing.addFirst(""k"");
        testing.addFirst(""p"");
        testing.addLast(""q"");
        //System.out.println(testing.removeLast());
        //System.out.println(testing.removeFirst());
        //System.out.println(testing.removeLast());
        //System.out.println(testing.removeFirst());
        //System.out.println(testing.removeFirst());
        System.out.println(testing.size());
        Iterator<String> i=testing.iterator();
        while(i.hasNext()){
            String s=i.next();
            System.out.print(s+""\t"");
        }
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
