import java.util.Comparator;
import java.util.Arrays;
public class Card implements Comparable<Card>{

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
        int facevalue1;
        int facevalue2;
        int suitvalue1 = 0;
        int suitvalue2 = 0;
        
        if(this.face.equalsIgnoreCase(""A"")) facevalue1 = 14;
        else if(this.face.equalsIgnoreCase(""K"")) facevalue1 = 13;
        else if(this.face.equalsIgnoreCase(""Q"")) facevalue1 = 12;
        else if(this.face.equalsIgnoreCase(""J"")) facevalue1 = 11;
        else facevalue1 = Integer.parseInt(this.face);
        
        if(that.face.equalsIgnoreCase(""A"")) facevalue2 = 14;
        else if(that.face.equalsIgnoreCase(""K"")) facevalue2 = 13;
        else if(that.face.equalsIgnoreCase(""Q"")) facevalue2 = 12;
        else if(that.face.equalsIgnoreCase(""J"")) facevalue2 = 11;       
        else facevalue2 = Integer.parseInt(that.face);
        
        if(this.suit.equalsIgnoreCase(""Spades"")) suitvalue1 = 4;
        else if(this.suit.equalsIgnoreCase(""Hearts"")) suitvalue1 = 3;
        else if(this.suit.equalsIgnoreCase(""Diamonds"")) suitvalue1 = 2;
        else if(this.suit.equalsIgnoreCase(""Clubs"")) suitvalue1 = 1;
        
        if(that.suit.equalsIgnoreCase(""Spades"")) suitvalue2 = 4;
        else if(that.suit.equalsIgnoreCase(""Hearts"")) suitvalue2 = 3;
        else if(that.suit.equalsIgnoreCase(""Diamonds"")) suitvalue2 = 2;
        else if(that.suit.equalsIgnoreCase(""Clubs"")) suitvalue2 = 1;       

        if(facevalue1 > facevalue2) return 1;
        if(facevalue1 < facevalue2) return -1;
        if(facevalue1 == facevalue2){
            if(suitvalue1 > suitvalue2) return 1;
            if(suitvalue1 < suitvalue2) return -1;
        }
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
        int suitvalue1 = 0;
        int suitvalue2 = 0;   
        if(c1.suit.equalsIgnoreCase(""Spades"")) suitvalue1 = 4;
        else if(c1.suit.equalsIgnoreCase(""Hearts"")) suitvalue1 = 3;
        else if(c1.suit.equalsIgnoreCase(""Diamonds"")) suitvalue1 = 2;
        else if(c1.suit.equalsIgnoreCase(""Clubs"")) suitvalue1 = 1;
        
        if(c2.suit.equalsIgnoreCase(""Spades"")) suitvalue2 = 4;
        else if(c2.suit.equalsIgnoreCase(""Hearts"")) suitvalue2 = 3;
        else if(c2.suit.equalsIgnoreCase(""Diamonds"")) suitvalue2 = 2;
        else if(c2.suit.equalsIgnoreCase(""Clubs"")) suitvalue2 = 1;
            // complete this function so the Card can be sorted according to the suit
        if(suitvalue1 > suitvalue2) return 1;
        else if(suitvalue1 < suitvalue2) return -1;
        else return 0;
        }
    }

}

