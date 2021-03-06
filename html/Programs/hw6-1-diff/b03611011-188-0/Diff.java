
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
        public Comparator<Card> suitOrder() {
        return new SuitOrder();
    }

    // TODO
    @Override
    public int compareTo(Card that) {
        int i, o;
        switch (this.face) {
            case ""A"":
                i = 1;
                break;
            case ""J"":
                i = 11;
                break;
            case ""Q"":
                i = 12;
                break;
            case ""K"":
                i = 13;
                break;
            default:
                i = Integer.parseInt(this.face);
        }
        switch (that.face) {
            case ""A"":
                o = 1;
                break;
            case ""J"":
                o = 11;
                break;
            case ""Q"":
                o = 12;
                break;
            case ""K"":
                o = 13;
                break;
            default:
                o = Integer.parseInt(that.face);
        }
        if (i < o) {
            return -1;
        }
        if (i > o) {
            return 1;
        }
        switch (this.suit) {
            case ""Spades"":
                i = 1;
                break;
            case ""Hearts"":
                i = 2;
                break;
            case ""Diamonds"":
                i = 3;
                break;
            case ""Clubs"":
                i = 4;
                break;
            default:
                return 0;
        }
        switch (that.suit) {
            case ""Spades"":
                o = 1;
                break;
            case ""Hearts"":
                o = 2;
                break;
            case ""Diamonds"":
                o = 3;
                break;
            case ""Clubs"":
                o = 4;
                break;
            default:
                return 0;
        }
        if (i < o) {
            return -1;
        }
        if (i > o) {
            return 1;
        }
        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        @Override
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int i, j;
            switch (c1.suit) {
                case ""Spades"":
                    i = 1;
                    break;
                case ""Hearts"":
                    i = 2;
                    break;
                case ""Diamonds"":
                    i = 3;
                    break;
                case ""Clubs"":
                    i = 4;
                    break;
                default:
                    return 0;
            }
            switch (c2.suit) {
                case ""Spades"":
                    j = 1;
                    break;
                case ""Hearts"":
                    j = 2;
                    break;
                case ""Diamonds"":
                    j = 3;
                    break;
                case ""Clubs"":
                    j = 4;
                    break;
                default:
                    return 0;
            }
            if (i < j) {
                return -1;
            }
            if (i > j) {
                return 1;
            }
            return 0;
        }
    }
}


