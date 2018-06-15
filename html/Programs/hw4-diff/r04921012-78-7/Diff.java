/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package deque;

import java.util.ArrayList;
import java.util.Iterator;

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
       list.add(0,item);
   }// add the item to the front

   public void addLast(T item){
       list.add(item);
   }// add the item to the end

   public T removeFirst(){
       T a = null;
       a=list.get(0);
       list.remove(0);
       return a;
       
   }// remove and return the item from the front

   public T removeLast(){
       T a=null;
       a=list.get(list.size()-1);
       list.remove(list.size()-1);
       return a;
   }// remove and return the item from the end

   public Iterator iterator(){
       return list.iterator();
   }// return an iterator over items in order from front to end

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

