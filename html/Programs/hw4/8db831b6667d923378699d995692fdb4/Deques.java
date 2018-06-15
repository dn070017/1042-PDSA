import java.util.Iterator;
import java.util.NoSuchElementException;

/**
.
 */
public class Deque <Item> implements Iterable {
//public class Deque <Item> {

    private Node first;
    private Node last;

    class Node{
        Item item;
        Node previous;
        Node next;
        Node (Item item){
            this.item=item;
        }

    }
    public Deque(){

    }
    public boolean isEmpty(){
        return first==null&&last==null;
    }
    public int size(){
        int num=0;
        Node temp=first;
        while(temp!=null){
            temp=temp.next;
            num+=1;
        }
        return num;
    }
    public void addFirst(Item item){
        if(item==null){
            throw new NullPointerException();
        }
        else {
            if (first == null && last == null) {
                first = new Node(item);
                last = first;
            } else {
                Node temp = first;
                //first.next.previous=temp;
                first = new Node(item);
                first.next = temp;
                temp.previous = first;
            }
        }
    }
    public void addLast(Item item){
        if(item==null){
            throw new NullPointerException();
        }
        else {
            if (first == null && last == null) {
                first = new Node(item);
                last = first;
            } else {
                Node temp = last;
                //last.previous.next=temp;
                last = new Node(item);
                last.previous = temp;
                temp.next = last;
            }
        }
    }
    public Item removeFirst(){
        if(size()==0){
            throw new NoSuchElementException();
        }
        else {
            Item item = first.item;
            if (size() == 1) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.previous = null;
            }
            return item;
        }
    }
    public Item removeLast(){
        if(size()==0){
            throw new NoSuchElementException();
        }
        else {
            Item item = last.item;
            if (size() == 1) {
                first = null;
                last = null;
            } else {
                last = last.previous;
                last.next = null;
            }
            return item;
        }

    }
/*    public void showDeque(){
        Node temp = first;
        while(temp!=null){
            System.out.println(temp.item);
            temp=temp.next;
        }
    }*/
    public Iterator iterator(){
        Iterator it = new Iterator(){
          Node temp = first;
            @Override
            public boolean hasNext(){
                return temp!=null;
            }
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
            @Override
            public Item next(){
                if(temp==null)
                    throw new NoSuchElementException();
                else {
                    Item item = temp.item;
                    temp=temp.next;
                    return item;
                }
            }


        };
        return it;
    }

   /* public static void main(String[] args) {

        Deque<String> deque = new Deque<String>();
        String s = null;
        deque.addLast(""This"");
        deque.addLast(""is"");
        deque.addLast(""a"");
        deque.addLast(""book"");
*//*        System.out.println(""size ""+deque.size());
        deque.showDeque();
        System.out.println(""remove ""+deque.removeFirst());
        System.out.println(""size ""+deque.size());
        deque.showDeque();
        System.out.println(""remove ""+deque.removeLast());
        System.out.println(""size ""+deque.size());
        deque.showDeque();
        System.out.println(""isEmpty ""+deque.isEmpty());
        deque.removeFirst();
        System.out.println(""size ""+deque.size());
        deque.removeLast();
        System.out.println(""size ""+deque.size());
        System.out.println(""isEmpty ""+deque.isEmpty());*//*
       // deque.addFirst(s);
       // deque.addLast(s);
       // deque.removeLast();
       // deque.removeFirst();
        Iterator it = deque.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        //it.next();
        //it.remove();
    }*/
}

