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
public class Deque implements Iterable{
    private ArrayList list;
    
   public Deque(){
       list = new ArrayList();
   }// construct an empty deque

   public boolean isEmpty(){                 
       return list.isEmpty();
   } // is the deque empty?
   
   public int size()  {
       return list.size();
   }// return the number of items on the deque
   public class Item<T>{
       private T t;
       public void set(T t){
           this.t=t;
       }
       public T get(){
           return t;
       }
   }
   public void addFirst(Item item){
       list.add(0,item);
   }// add the item to the front

   public void addLast(Item item){
       list.add(item);
   }// add the item to the end

   public Item removeFirst(){
       Item a = null;
       a.set(list.get(0));
       list.remove(0);
       return a;
       
   }// remove and return the item from the front

   public Item removeLast(){
       Item a=null;
       a.set(list.get(list.size()-1));
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

