
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
        if (this.face.compareTo(that.face) < 0) {
            return -1;
        }
        if (this.face.compareTo(that.face) > 0) {
            return +1;
        } else if (this.face.compareTo(that.face) == 0) {
            if (this.suit.compareTo(that.suit) < 0) {
                return -1;
            }
            if (this.suit.compareTo(that.suit) > 0) {
                return +1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            if (c1.face.compareTo(c2.face) < 0) {
                return -1;
            }
            if (c1.face.compareTo(c2.face) > 0) {
                return +1;
            } else if (c1.face.compareTo(c2.face) == 0) {
                if (c1.suit.compareTo(c2.suit) < 0) {
                    return -1;
                }
                if (c1.suit.compareTo(c2.suit) > 0) {
                    return +1;
                } else {
                    return 0;
                }
            }
            return 0;

        }
    }

    public static void main(String[] args) {
        Card[] test = new Card[2];
        test[0] = new Card(""10"", ""Diamonds"");
        test[1] = new Card(""10"", ""Hearts"");
//        System.out.printf(""%d"", test[1].compareTo(test[0]));
//        System.out.printf(""%d"", Card.SUIT_ORDER.compare(test[1], test[0]));
    }
}
