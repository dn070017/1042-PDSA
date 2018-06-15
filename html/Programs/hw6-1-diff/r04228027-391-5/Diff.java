
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
    private int facecode, suitcode;

    private static void setcode(Card c) {
        switch (c.face) {
            case ""A"":
                c.facecode = 14;
                break;
            case ""K"":
                c.facecode = 13;
                break;
            case ""Q"":
                c.facecode = 12;
                break;
            case ""J"":
                c.facecode = 11;
                break;
            default:
                c.facecode = Integer.parseInt(c.face);
        }

        switch (c.suit) {
            case ""Spades"":
                c.suitcode = 4;
                break;
            case ""Hearts"":
                c.suitcode = 3;
                break;
            case ""Diamonds"":
                c.suitcode = 2;
                break;
            case ""Clubs"":
                c.suitcode = 1;
                break;
        }
    }

    public int getfacecode() {
        return this.facecode;
    }

    public int getsuitcode() {
        return this.suitcode;
    }

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
        setcode(this);
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

        if (this.facecode < that.facecode) {
            return -1;
        }
        if (this.facecode > that.facecode) {
            return +1;
        }
        if (this.suitcode < that.suitcode) {
            return -1;
        }
        if (this.suitcode > that.suitcode) {
            return +1;
        }
        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

            if (c1.suitcode < c2.suitcode) {
                return -1;
            }
            if (c1.suitcode > c2.suitcode) {
                return +1;
            }
            return 0;
        }
    }
}

