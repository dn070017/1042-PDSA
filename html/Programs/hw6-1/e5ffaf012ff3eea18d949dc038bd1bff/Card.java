
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author USER
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
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS   
    public String getSuit(){
        return this.suit;
    }   
    
    public int FacetoINT(String a){
        int facepoint = 0;
        if(a.equals(""A""))facepoint = 130;
        else if(a.equals(""2""))facepoint = 10;
        else if(a.equals(""3""))facepoint = 20;
        else if(a.equals(""4""))facepoint = 30;
        else if(a.equals(""5""))facepoint = 40;
        else if(a.equals(""6""))facepoint = 50;
        else if(a.equals(""7""))facepoint = 60;
        else if(a.equals(""8""))facepoint = 70;
        else if(a.equals(""9""))facepoint = 80;
        else if(a.equals(""10""))facepoint = 90;
        else if(a.equals(""J""))facepoint = 100;
        else if(a.equals(""Q""))facepoint = 110;
        else if(a.equals(""K""))facepoint = 120;
        return facepoint;
    } 
        public int SuittoINT(String b){
        int suitpoint = 0;
        if(b.equals(""Clubs""))suitpoint=1;
        else if(b.equals(""Diamonds""))suitpoint=2;
        else if(b.equals(""Hearts""))suitpoint=3;
        else if(b.equals(""Spades""))suitpoint=4;
        return suitpoint;
    } 
            
    
    // TODO
    public int compareTo(Card that) {
        
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        
        if(FacetoINT(this.getFace())+SuittoINT(this.getSuit()) > FacetoINT(that.getFace())+SuittoINT(that.getSuit())){
            return +1;
        }
        else if(FacetoINT(this.getFace())+SuittoINT(this.getSuit()) < FacetoINT(that.getFace())+SuittoINT(that.getSuit())){
            return -1;
        }
        else return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int suitpoint = 0;
            int a =0;
            int b =0;
            switch (c1.getSuit()) {
                case ""Clubs"":
                    a=1;
                    break;
                case ""Diamonds"":
                    a=2;
                    break;
                case ""Hearts"":
                    a=3;
                    break;
                case ""Spades"":
                    a=4;
                    break;
                default:
                    break;
            }
            switch (c2.getSuit()) {
                case ""Clubs"":
                    b=1;
                    break;
                case ""Diamonds"":
                    b=2;
                    break;
                case ""Hearts"":
                    b=3;
                    break;
                case ""Spades"":
                    b=4;
                    break;
                default:
                    break;
            }
            
            if(a>b)return+1;
            else if(a<b)return-1;
            // complete this function so the Card can be sorted according to the suit
            else return 0;
        }
    }   
}


