
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
        @Override
    public int compareTo(Card that) {
        int this_face = 0;
        int that_face = 0;
        int this_suit = 0;
        int that_suit = 0;
        if (this.face.equals(""A"")) this_face = 14;
        if (that.face.equals(""A"")) that_face = 14;
        if (this.face.equals(""J"")) this_face = 11;
        if (that.face.equals(""J"")) that_face = 11;
        if (this.face.equals(""Q"")) this_face = 12;
        if (that.face.equals(""Q"")) that_face = 12;
        if (this.face.equals(""K"")) this_face = 13;
        if (that.face.equals(""K"")) that_face = 13;
        else {
            this_face = Integer.parseInt(this.face);
            that_face = Integer.parseInt(that.face);
        }
        if (this.suit.equals(""Spades"")) this_suit = 4;
        if (that.suit.equals(""Spades"")) that_suit = 4;
        if (this.suit.equals(""Hearts"")) this_suit = 3;
        if (that.suit.equals(""Hearts"")) that_suit = 3;
        if (this.suit.equals(""Diamonds"")) this_suit = 2;
        if (that.suit.equals(""Diamonds"")) that_suit = 2;
        if (this.suit.equals(""Clubs"")) this_suit = 1;
        if (that.suit.equals(""Clubs"")) that_suit = 1;
        
        if (this_face < that_face) return -1;
        if (this_face > that_face) return +1;
        if (this_face == that_face){
            if (this_suit < that_suit) return -1;
            if (this_suit > that_suit) return +1;
            return 0;
        }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int c1_suit = 0;
            int c2_suit = 0;
            if (c1.suit.equals(""Spades"")) c1_suit = 4;
            if (c2.suit.equals(""Spades"")) c1_suit = 4;
            if (c1.suit.equals(""Hearts"")) c1_suit = 3;
            if (c2.suit.equals(""Hearts"")) c1_suit = 3;
            if (c1.suit.equals(""Diamonds"")) c1_suit = 2;
            if (c2.suit.equals(""Diamonds"")) c1_suit = 2;
            if (c1.suit.equals(""Clubs"")) c1_suit = 1;
            if (c2.suit.equals(""Clubs"")) c1_suit = 1;
            if (c1_suit < c2_suit) return -1;
            if (c1_suit > c2_suit) return +1;
            // complete this function so the Card can be sorted according to the suit
            return 0;
        }
    }   
}

