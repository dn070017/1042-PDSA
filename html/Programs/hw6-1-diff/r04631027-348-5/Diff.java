/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.util.Comparator;
import java.util.Objects;
import java.util.*;

/**
 *
 * @author YuChing
 */
public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }

    private Card() {
.
    }
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int a1_face = 0;
        int a2_face = 0;
        String b1 = this.face;
        String b2 = that.face;
        switch (b1) {
            case ""A"":
                a1_face = 14;
                break;
            case ""J"":
                a1_face = 11;
                break;
            case ""Q"":
                a1_face = 12;
                break;
            case ""K"":
                a1_face = 13;
                break;
            default:
                a1_face = Integer.parseInt(b1);
        }
        switch (b2) {
            case ""A"":
                a2_face = 14;
                break;
            case ""J"":
                a2_face = 11;
                break;
            case ""Q"":
                a2_face = 12;
                break;
            case ""K"":
                a2_face = 13;
                break;
            default:
                a2_face = Integer.parseInt(b2);
        }

        if (a1_face>a2_face)
            return 1;
        else if (a1_face<a2_face)
            return -1;
        else if (a1_face==a1_face)
            return(SUIT_ORDER.compare(this,that));
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit         
            String a1_suit =c1.suit;
            String a2_suit = c2.suit;
            int b1 = 0;
            int b2 = 0;
            switch(a1_suit)
            {
                 case ""Spades"":
                    b1 = 1;
                    break;
                case ""Hearts"":
                    b1 = 2;
                    break;
                case ""Diamonds"":
                    b1 = 3;
                    break;
                case ""Clubs"":
                    b1 = 4;
                    break; 
            }
            switch(a2_suit)
            {
                 case ""Spades"":
                    b2 = 1;
                    break;
                case ""Hearts"":
                    b2 = 2;
                    break;
                case ""Diamonds"":
                    b2 = 3;
                    break;
                case ""Clubs"":
                    b2 = 4;
                    break; 
             
            }
            if (b1<b2)
                    return 1;
            else if(b1==b2)
                return 0;
            else if(b1>b2)
                return -1;
            
            return 0;
        }
    }   
//    public static void main(String[] args) {
//        // TODO code application logic here
//      //  In in = new In(args[0]);
//        //System.out.println(in.readLine());
//        Card[] test = new Card[2];
//     test[0] = new Card(""J"",""Heafarts"") ;
//     test[1] = new Card(""J"",""Diamonds"") ;
//     int b2;
//     System.out.println(test[1].compareTo(test[0]));
//     System.out.println(SUIT_ORDER.compare(test[1],test[0]));
//     //為什麼SUIT_ORDER不能打SUITORDER
//      int a1_face =0;
//       int a2_face =0;
//       String b1 = ""1"";
//      // String b2 = that.face;
//       
//    
//    }
         
}


