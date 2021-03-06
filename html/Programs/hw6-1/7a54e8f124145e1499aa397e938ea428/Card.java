
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

        if (""A"".equals(that.face)) {
            that.face = ""14"";
        }
        else if (""J"".equals(that.face)) {
            that.face = ""11"";
        }
        else if (""Q"".equals(that.face)) {
            that.face = ""12"";
        }
        else if (""K"".equals(that.face)) {
            that.face = ""13"";
        }
        else{}
        if (""Spades"".equals(that.suit)) {
            that.suit = ""0.4"";
        }
        else if (""Hearts"".equals(that.suit)) {
            that.suit = ""0.3"";
        }
        else if (""Diamonds"".equals(that.suit)) {
            that.suit = ""0.2"";
        }
        else {
            that.suit = ""0.1"";
        }
        if (""A"".equals(this.face)) {
            this.face = ""14"";
        }
        else if (""K"".equals(this.face)) {
            this.face = ""13"";
        }
        else if (""Q"".equals(this.face)) {
            this.face = ""12"";
        }
        else if (""J"".equals(this.face)) {
            this.face = ""11"";
        }
        else{}
        if (""Spades"".equals(this.suit)) {
            this.suit = ""0.4"";
        }
        else if (""Hearts"".equals(this.suit)) {
            this.suit = ""0.3"";
        }
        else if (""Diamonds"".equals(this.suit)) {
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
            if (""Spades"".equals(c1.suit)) {
                c1.suit = ""0.4"";
            }
            else if (""Hearts"".equals(c1.suit)) {
                c1.suit = ""0.3"";
            }
            else if (""Diamonds"".equals(c1.suit)) {
                c1.suit = ""0.2"";
            }
            else{
                c1.suit = ""0.1"";
            }
            if (""Spades"".equals(c2.suit)) {
                c2.suit = ""0.4"";
            }
            else if (""Hearts"".equals(c2.suit)) {
                c2.suit = ""0.3"";
            }
            else if (""Diamonds"".equals(c2.suit)) {
                c2.suit = ""0.2"";
            }
            else{
                c2.suit = ""0.1"";
            }
            if (Double.parseDouble(c1.suit) > Double.parseDouble(c2.suit)) {
                return 1;
            }
            else if (Double.parseDouble(c1.suit) ==Double.parseDouble(c2.suit)) {
                return 0;
            }
            else {
                return -1;
            }

            
        }
    }
//    public static void main(String[] args) throws Exception {
//      
//       Card A = new Card(""2"",""Diamonds"");
//       Card B = new Card(""3"",""Spades"");
//       
//       System.out.println(Card.SUIT_ORDER.compare(A,B));
//       
//            
//    }
    
}
