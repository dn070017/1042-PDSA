
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
        int thisf = 0;
        int thiss = 0;
        int thatf = 0;
        int thats = 0;
        if (this.face == ""A"") {
            thisf = 14;
        } else if (this.face == ""J"") {
            thisf = 11;
        } else if (this.face == ""Q"") {
            thisf = 12;
        } else if (this.face == ""K"") {
            thisf = 13;
        } else {
            thisf = Integer.parseInt(this.face);
        }
        if (that.face == ""A"") {
            thatf = 14;
        } else if (that.face == ""J"") {
            thatf = 11;
        } else if (that.face == ""Q"") {
            thatf = 12;
        } else if (that.face == ""K"") {
            thatf = 13;
        } else {
            thatf = Integer.parseInt(that.face);
            thatf = 4;
        }
        if (this.suit == ""Spades"") {
            thiss = 4;
        } else if (this.suit == ""Hearts"") {
            thiss = 3;
        } else if (this.suit == ""Diamonds"") {
            thiss = 2;
        } else if (this.suit == ""Clubs"") {
            thiss = 1;
        }
        if (that.suit == ""Spades"") {
            thats = 4;
        } else if (that.suit == ""Hearts"") {
            thats = 3;
        } else if (that.suit == ""Diamonds"") {
            thats = 2;
        } else if (that.suit == ""Clubs"") {
            thats = 1;
        }

        if (thisf < thatf) {
            return -1;
        }
        if (thisf > thatf) {
            return +1;
        }
        if (thiss < thats) {
            return -1;
        }
        if (thiss > thats) {
            return +1;
        }
        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int thiss = 0;
            int thats = 0;
            if (c1.suit == ""Spades"") {
                thiss = 4;
            } else if (c1.suit == ""Hearts"") {
                thiss = 3;
            } else if (c1.suit == ""Diamonds"") {
                thiss = 2;
            } else if (c1.suit == ""Clubs"") {
                thiss = 1;
            }
            if (c2.suit == ""Spades"") {
                thats = 4;
            } else if (c2.suit == ""Hearts"") {
                thats = 3;
            } else if (c2.suit == ""Diamonds"") {
                thats = 2;
            } else if (c2.suit == ""Clubs"") {
                thats = 1;
            }
            if (thiss < thats) {
                return -1;
            }
            if (thiss > thats) {
                return +1;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Card[] test = new Card[2];
        test[0] = new Card(""A"",""Clubs"");
        test[1] = new Card(""A"",""Hearts"");
        //System.out.println(test[1].compareTo(test[0]));
        //System.out.println(Card.SUIT_ORDER.compare(test[1], test[0]));
    }
}

