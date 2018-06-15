
import java.io.BufferedReader;
import java.io.FileReader;
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

        if (that.face == ""A"") {
            that.face = ""14"";
        }
        if (that.face == ""J"") {
            that.face = ""11"";
        }
        if (that.face == ""Q"") {
            that.face = ""12"";
        }
        if (that.face == ""K"") {
            that.face = ""13"";
        }
        if (that.suit == ""Spades"") {
            that.suit = ""0.4"";
        }
        if (that.suit == ""Hearts"") {
            that.suit = ""0.3"";
        }
        if (that.suit == ""Diamonds"") {
            that.suit = ""0.2"";
        }
        if (that.suit == ""Clubs"") {
            that.suit = ""01"";
        }
        if (this.face == ""A"") {
            this.face = ""14"";
        }
        if (this.face == ""K"") {
            this.face = ""13"";
        }
        if (this.face == ""Q"") {
            this.face = ""12"";
        }
        if (this.face == ""J"") {
            this.face = ""11"";
        }
        if (this.suit == ""Spades"") {
            this.suit = ""0.4"";
        }
        if (this.suit == ""Hearts"") {
            this.suit = ""0.3"";
        }
        if (this.suit == ""Diamonds"") {
            this.suit = ""0.2"";
        }
        if (this.suit == ""Clubs"") {
            this.suit = ""0.1"";
        }

        if ((Double.parseDouble(this.face) + Double.parseDouble(this.suit) )> (Double.parseDouble(that.face) + Double.parseDouble(that.suit) )) {
            return 1;
        }
         if ((Double.parseDouble(this.face) + Double.parseDouble(this.suit) )== (Double.parseDouble(that.face) + Double.parseDouble(that.suit) )) {
            return 0;
        }
         if ((Double.parseDouble(this.face) + Double.parseDouble(this.suit) )<(Double.parseDouble(that.face) + Double.parseDouble(that.suit) )) {
            return -1;
        }

        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            if (c1.suit == ""Spades"") {
                c1.suit = ""4"";
            }
            if (c1.suit == ""Hearts"") {
                c1.suit = ""3"";
            }
            if (c1.suit == ""Diamonds"") {
                c1.suit = ""2"";
            }
            if (c1.suit == ""Clubs"") {
                c1.suit = ""1"";
            }
            if (c2.suit == ""Spades"") {
                c2.suit = ""4"";
            }
            if (c2.suit == ""Hearts"") {
                c2.suit = ""3"";
            }
            if (c2.suit == ""Diamonds"") {
                c2.suit = ""2"";
            }
            if (c2.suit == ""Clubs"") {
                c2.suit = ""1"";
            }
            if (Integer.parseInt(c1.suit) > Integer.parseInt(c2.suit)) {
                return 1;
            }
            if (Integer.parseInt(c1.suit) ==Integer.parseInt(c2.suit)) {
                return 0;
            }
            if (Integer.parseInt(c1.suit) < Integer.parseInt(c2.suit)) {
                return -1;
            }

            return 0;
        }
    }
    public static void main(String[] args) throws Exception {
      
       Card A = new Card(""2"",""Diamonds"");
       Card B = new Card(""3"",""Spades"");
       
       System.out.println(Card.SUIT_ORDER.compare(A,B));
       
            
    }
    
}
    

