/*
.
 * To change this template file, choose Tools | Templates
.
 */
//remove ans static
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
private Node<Item> first;
private Node<Item> last;
private int counter;

private class Node<Item>
{
    Item item;
    Node<Item> next;
    Node<Item> pre;
}
    public Deque(){
    first=null;
    last=null;
    counter=0;
    }// construct an empty deque

   public boolean isEmpty()
   {return first ==null;}
// is the deque empty?

   public int size()
   {
       return counter;
   }                     // return the number of items on the deque

    public void addFirst(Item item) {

        if (item == null) {
            throw new java.lang.NullPointerException();
        }
       else if (isEmpty()) {
            first = new Node<Item>();
            first.item = item;
            first.next = null;
            first.pre = null;
            last = first;
        } else {
            Node<Item> oldfirst = first;
            first = new <Item>Node();
            first.item = item;
            first.next = oldfirst;
            first.pre = null;
            oldfirst.pre = first;
        }
        counter++;
    }
// add the item to the front

   public void addLast(Item item){
       if(item==null) throw new java.lang.NullPointerException();
     else if(isEmpty()){
         last = new Node<Item>();
         last.item = item;
            last.next = null;
            last.pre = null;
          first=last;
          
      }
     else{
       Node<Item> oldlast =last;
       last = new <Item>Node();
       last.item=item;
       last.next=null;
       last.pre=oldlast;
       oldlast.next=last;
     }
           

       counter++;
   }// add the item to the end

   public Item removeFirst(){
       if(isEmpty()) throw new java.util.NoSuchElementException();
       Item item=first.item;
       first=first.next;
       if (isEmpty()) last=null;
       counter--;
       return item;
               }// remove and return the item from the front

   public Item removeLast(){
       if(isEmpty()) throw new java.util.NoSuchElementException();
    Item item = last.item;
    last.pre.next=null;
    last=last.pre;
    if(isEmpty()) first=null;
    counter--;
    return item;
   }// remove and return the item from the end

   public Iterator<Item> iterator(){          
       return new ListIterator<Item>(first);      
       // return an iterator over items in order from front to end   
   }
   
   private class ListIterator<Item> implements Iterator<Item>
   {
            
       private Node<Item> current;
       public ListIterator(Node first) {
            current = first;
        }
   
       public boolean hasNext(){
           return current !=null;
       }
       public void remove(){
           throw new UnsupportedOperationException();
       }
       public Item next()
       {
           if(!hasNext()) throw new java.util.NoSuchElementException();
           
           Item item = current.item;
           current = current.next;          
           return item;
       }
   }
  
      //static  
        
    public static void main(String[] args) throws Exception {
        Deque<Integer> de=new <Integer>Deque();
       
        int a=5;
        de.addFirst(5);
       de.addLast(6);
       de.addLast(4);
        
//                while(ii.hasNext()){
//          System.out.println(ii.next());     
//        }
        de.removeLast();
        System.out.println();

      Iterator ii=de.iterator();
        
        while(ii.hasNext()){
          System.out.println(ii.next());     
        }
        

        
    }

}

  

    
