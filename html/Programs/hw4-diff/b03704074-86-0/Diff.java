import java.util.Iterator;
public class Deques<Item> implements Iterable<Item>{
    Deques deques = new Deques();
    private class Node{
        Item item;
        Node next;
        Node pre;}
    private Node first = null;
    private Node last = null;
    public boolean isEmpty()
    {return first == null;}
    public void addfirst(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;}
    public void addlast(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.pre = oldlast; }
    public Item removeFirst(){
        Item item = first.item;
        first = first.next;
        return item;}
    public Item removeLast(){
        Item item = last.item;
        last = last.pre;
        return item;}
    public Iterator<Item> iterator() { return new ListIterator(); }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() {  }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;  }
        public void output(){
            while(hasNext()){
                Item s = next();
                System.out.println(s);}}
    }
    
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

