
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
    public int face_int;
    public int suit_int;

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
        
        if(face.equals(""A""))      this.face_int = 14;
        else if(face.equals(""J"")) this.face_int = 11;
        else if(face.equals(""Q"")) this.face_int = 12;
        else if(face.equals(""K"")) this.face_int = 13;
        else this.face_int = Integer.parseInt(face);
        
        switch(suit){
            case ""Clubs"":    this.suit_int = 1; break;
            case ""Diamonds"": this.suit_int = 2; break;    
            case ""Hearts"":   this.suit_int = 3; break;
            case ""Spades"":   this.suit_int = 4; break;
            default: System.out.printf(""suit_input wrong\n"");
        }
    }

    // DO NOT MODIFY THIS   
    public String getFace() {
        return this.face;
    }

    // DO NOT MODIFY THIS    
    public String getSuit() {
        return this.suit;
    }

    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        if (this.face_int < that.face_int) return -1;
        if (this.face_int > that.face_int) return +1;
        if (this.suit_int < that.suit_int) return -1;
        if (this.suit_int > that.suit_int) return +1;
        return 0;
    }
    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if (c1.suit_int < c2.suit_int) return -1;
            if (c1.suit_int > c2.suit_int) return +1;
            return 0;
        }
    }
}

