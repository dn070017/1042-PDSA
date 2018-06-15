/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

/**
 *
 * @author steven
 */
public class Deque<T> implements Iterable{
    private ArrayList<T> list;

    
   public Deque(){
       list = new ArrayList();
   }// construct an empty deque

   public boolean isEmpty(){                 
       return list.isEmpty();
   } // is the deque empty?
   
   public int size()  {
       return list.size();
   }// return the number of items on the deque

   public void addFirst(T item){
       if(item==null)
           throw new NullPointerException();
       list.add(0,item);
   }// add the item to the front

   public void addLast(T item){
       if(item==null)
           throw new NullPointerException();
       list.add(item);
   }// add the item to the end

   public T removeFirst(){
       if (list.isEmpty())
           throw new NoSuchElementException();
       else{T a = null;
       a=list.get(0);
       list.remove(0);
       return a;}
       
   }// remove and return the item from the front

   public T removeLast(){
       if (list.isEmpty())
           throw new NoSuchElementException();
       else{T a=null;
       a=list.get(list.size()-1);
       list.remove(list.size()-1);
       return a;}
   }// remove and return the item from the end

   public Iterator iterator(){
       return list.iterator();
   }
   /*public class Iter implements Iterator<T>{
       public boolean hasNext(){
           return list.iterator().hasNext();
       }
       public void remove(){
           throws new UnsupportedOperationException();
       }
   }*/
// return an iterator over items in order from front to end
  /*private class Listiterator() implements Iterator<T>{
       public void remove(){throws new UnsupportedOperationException();}
       public T next(){
           
       }
   }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Deque<String> a=new Deque<String>();
        String b=""b"";
        String c =""fdf"";
        a.addLast(b);
        //a.removeFirst();
        a.addFirst(c);
        Iterator it=a.iterator();
        it.remove();
        while (it.hasNext())
            System.out.println(it.next());*/
    }
    
}

