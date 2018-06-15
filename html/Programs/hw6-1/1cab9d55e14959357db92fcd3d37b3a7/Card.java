/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
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
        int ff1 = transface(this.face);
        int ff2 = transface(that.face);
        int ss1 = transsuit(this.suit);
        int ss2 = transsuit(that.suit);
        int rr = 0;
        if (ff1 == ff2){
            if (ss1 > ss2){
                rr = 1;
            }
            else{
                rr = -1;
            }
        }
        else if (ff1 > ff2){
            rr = 1;
        }
        else{
            rr = -1;
        }
        return rr;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int ct = c1.compareTo(c2);
            return ct;
        }
    }
    
    private static int transface(String ff){
        int intface = 0;
        if (ff.equals(""2"")){
            intface = 2;
        }
        else if (ff.equals(""3"")){
            intface = 3;
        }
        else if (ff.equals(""4"")){
            intface = 4;
        }
        else if (ff.equals(""5"")){
            intface = 5;
        }
        else if (ff.equals(""6"")){
            intface = 6;
        }
        else if (ff.equals(""7"")){
            intface = 7;
        }
        else if (ff.equals(""8"")){
            intface = 8;
        }
        else if (ff.equals(""9"")){
            intface = 9;
        }
        else if (ff.equals(""10"")){
            intface = 10;
        }
        else if (ff.equals(""J"")){
            intface = 11;
        }
        else if (ff.equals(""Q"")){
            intface = 12;
        }
        else if (ff.equals(""K"")){
            intface = 13;
        }
        else{
            intface = 14;
        }
        return intface;
    }
    
    private static int transsuit(String ss){
        int intsuit = 0;
        if (ss.equals(""Spades"")){
            intsuit = 4;
        }
        else if (ss.equals(""Hearts"")){
            intsuit = 3;
        } 
        else if (ss.equals(""Diamonds"")){
            intsuit = 2;
        }
        else{
            intsuit = 1;
        }
        return intsuit;
    }
    
    public static void main(String[] ars){
        Card[] test = new Card[2];
        test[0] = new Card(""A"",""Clubs"");
        test[1] = new Card(""2"",""Hearts"");
        System.out.println(test[0].compareTo(test[1]));
        System.out.println(Card.SUIT_ORDER.compare(test[0], test[1]));
        
    }
}
