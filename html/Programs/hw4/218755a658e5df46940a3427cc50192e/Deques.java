/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.*;

/**
 *
 * @author user import java.util.*;
 */
public class Deque<Item> implements Iterable<Item> {

     /**
      * @param args the command line arguments
      */
     private Node first, last;
     private int N;

     private class Node {

          Item item;
          Node next;
          Node Previous;
     }

     public Deque() {
          first = null;
          last = null;
          N = 0;
     }

     public boolean isEmpty() {
          return N == 0;
     }

     public int size() {
          return N;
     }

     public void addLast(Item item) {
//                    if (item == null) {
//                              throw new java.lang.NullPointerException();
//                    } else {
          Node oldlast = last;
          last = new Node();
          last.item = item;
          last.next = null;
          last.Previous = null;
          if (isEmpty()) {
               first = last;
          } else {
               oldlast.next = last;
               last.Previous = oldlast;
          }
          N++;
//                    }
     }

     public void addFirst(Item item) {
          if (item == null) {
               throw new java.lang.NullPointerException();
          } else {
               Node oldfirst = first;
               first = new Node();
               first.item = item;
               first.next = null;
               first.Previous = null;
               if (isEmpty()) {
                    first = last;
               } else {
                    oldfirst.Previous = first;
                    first.next = oldfirst;
               }
               N++;
          }
     }

     public Item removeFirst() {
          if (isEmpty()) {
               throw new java.util.NoSuchElementException();
          } else {

               if (N == 1) {
                    Node oldfirst = first;
                    first = null;
                    last = null;
                    N--;
                    return oldfirst.item;
               } else {
                    Node oldfirst = first;
                    first = first.next;
                    first.Previous = null;
                    N--;
                    return oldfirst.item;
               }
          }
     }

     public Item removeLast() {
          if (isEmpty()) {
               throw new java.util.NoSuchElementException();
          } else {
               if (N == 1) {
                    Node oldlast = last;
                    first = null;
                    last = null;
                    N--;
                    return oldlast.item;
               } else {
                    Node oldlast = last;
                    last = oldlast.Previous;
                    last.next = null;
                    N--;
                    return oldlast.item;
               }
          }
     }

     public Iterator<Item> iterator() {
          return new ListIterator();

     }

     private class ListIterator implements Iterator<Item> {

          private Node current = first;

          public boolean hasNext() {
               return current != null;
          }

          public void remove() { /* not supported */

               throw new java.lang.UnsupportedOperationException();
          }

          public Item next() {
               Item item = current.item;
               current = current.next;
               if (item != null) {
                    return item;
               } else {
                    throw new java.util.NoSuchElementException();
               }

          }
     }

     public static void main(String[] args) {
          // TODO code application logic here
//          Deque<Integer> dq = new Deque<Integer>();
//          System.out.println(""first"" + dq.isEmpty());
//
//          dq.addLast(4);
//          dq.addLast(5);
//          dq.addFirst(1);
//          dq.addFirst(2);
//
//          dq.addFirst(3);
//          System.out.println(""sec"" + dq.isEmpty());
////
//
////          while (iter.hasNext()) {
////               System.out.println(""iteraotr ""+iter.next());
////          }
////          System.out.println(""check"");
//          System.out.println(""remove "" + dq.removeFirst());
////          Iterator iter = dq.iterator();
////          while (iter.hasNext()) {
////               System.out.println(""iteraotr"" + iter.next());
////          }
//          System.out.println(""remove "" + dq.removeLast());
//          System.out.println(""remove "" + dq.removeLast());
//
//          System.out.println(""remove "" + dq.removeLast());
//
////          System.out.println(""remove "" + dq.removeLast());
//          System.out.println(""remove "" + dq.removeLast());
//
//          System.out.println(""check"");
//
//          dq.addLast(3);
//          dq.addFirst(1);
//          Iterator iter = dq.iterator();
//          while (iter.hasNext()) {
//               System.out.println(iter.next());
//               System.out.println(""check"");
//
//          }
//
//          System.out.println("""");
     }

}

