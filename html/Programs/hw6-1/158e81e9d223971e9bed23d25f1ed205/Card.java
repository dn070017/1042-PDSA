
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
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

        if (that.face.equals(""A"")) {
            that.face = ""14"";
        }
        else if (that.face.equals(""J"")) {
            that.face = ""11"";
        }
        else if (that.face.equals(""Q"")) {
            that.face = ""12"";
        }
        else if (that.face.equals(""K"")) {
            that.face = ""13"";
        }
        else{}
        if (that.suit.equals(""Spades"")) {
            that.suit = ""0.4"";
        }
        else if (that.suit.equals(""Hearts"")) {
            that.suit = ""0.3"";
        }
        else if (that.suit.equals(""Diamonds"")) {
            that.suit = ""0.2"";
        }
        else {
            that.suit = ""0.1"";
        }
        if (this.face.equals(""A"")) {
            this.face = ""14"";
        }
        else if (this.face.equals(""K"")) {
            this.face = ""13"";
        }
        else if (this.face.equals(""Q"")) {
            this.face = ""12"";
        }
        else if (this.face.equals(""J"")) {
            this.face = ""11"";
        }
        else{}
        if (this.suit.equals(""Spades"")) {
            this.suit = ""0.4"";
        }
        else if (this.suit.equals(""Hearts"")) {
            this.suit = ""0.3"";
        }
        else if (this.suit.equals(""Diamonds"")) {
            this.suit = ""0.2"";
        }
        else {
            this.suit = ""0.1"";
        }

        if ((Double.parseDouble(this.face) + Double.parseDouble(this.suit) )> (Double.parseDouble(that.face) + Double.parseDouble(that.suit) )) {
            return 1;
        }
         else if ((Double.parseDouble(this.face) + Double.parseDouble(this.suit) )== (Double.parseDouble(that.face) + Double.parseDouble(that.suit) )) {
            return 0;
        }
         else {
            return -1;
        }

    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            if (c1.suit.equals(""Spades"")) {
                c1.suit = ""0.4"";
            }
            else if (c1.suit.equals(""Hearts"")) {
                c1.suit = ""0.3"";
            }
            else if (c1.suit.equals(""Diamonds"")) {
                c1.suit = ""0.2"";
            }
            else if(c1.suit.equals(""Clubs"")){
                c1.suit = ""0.1"";
            }
            if (c1.suit.equals(""Spades"")) {
                c2.suit = ""0.4"";
            }
            else if (c1.suit.equals(""Hearts"")) {
                c2.suit = ""0.3"";
            }
            else if (c1.suit.equals(""Diamonds"")) {
                c2.suit = ""0.2"";
            }
            else if (c1.suit.equals(""Clubs"")){
                c2.suit = ""0.1"";
            }
            if (Double.parseDouble(c1.suit) > Double.parseDouble(c2.suit)) {
                return 1;
            }
            else if (Double.parseDouble(c1.suit) == Double.parseDouble(c2.suit)) {
                return 0;
            }
            else {
                return -1;
            }

        }
    }
     
}
