
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
    private int intsuit;
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
//        switch (suit) {
//            case ""Spades"":
//                this.intsuit = 3;
//                break;
//            case ""Hearts"":
//                this.intsuit = 2;
//                break;
//            case ""Diamonds"":
//                this.intsuit = 1;
//                break;
//            case ""Clubs"":
//                this.intsuit = 0;
//                break;
//        }
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
        String a = this.face;
        String b = that.face;
        if (a.equals( ""Q"")) {
            a = ""JQ"";
        }
        if (a.equals(""A"")) {
            a = ""L"";
        }
        if (a == ""10"") {
            a = ""99"";
        }
        if (b.equals( ""Q"")) {
            b = ""JQ"";
        }
        if (b.equals(""A"")) {
            b = ""L"";
        }
        if (b.equals( ""10"")) {
            b = ""99"";
        }
        if (a.compareTo(b) > 0) {
            return 1;
        }
        if (a.compareTo(b) < 0) {
            return -1;
        }
        if (this.getSuit().compareTo(that.getSuit()) > 0) {
            return +1;
        }
        if (this.getSuit().compareTo(that.getSuit()) < 0) {
            return -1;
        }
        return 0;

    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if (c1.getSuit().compareTo(c2.getSuit()) > 0) {
                return +1;
            }
            if (c1.getSuit().compareTo(c2.getSuit()) < 0) {
                return -1;
            }
            return 0;
        }
    }
}

