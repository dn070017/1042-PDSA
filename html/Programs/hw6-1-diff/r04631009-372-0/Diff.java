
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private final String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private final String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

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
        int num1 = 0;
        int num2 = 0;

        switch (this.face) {
            case ""A"":
                num1 = 13;
                break;
            case ""K"":
                num1 = 12;
                break;
            case ""Q"":
                num1 = 11;
                break;
            case ""J"":
                num1 = 10;
                break;
            default:
                num1 = Integer.parseInt(this.face);
                break;

        }
        switch (that.face) {
            case ""A"":
                num2 = 13;
                break;
            case ""K"":
                num2 = 12;
                break;
            case ""Q"":
                num2 = 11;
                break;
            case ""J"":
                num2 = 10;
                break;
            default:
                num2 = Integer.parseInt(that.face);
                break;

        }

        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        if (num1 > num2) {
            return 1;
        }
        if (num1 < num2) {
            return -1;
        }

        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            int temp1 = 0;
            int temp2 = 0;
            switch (c1.suit) {
                case ""Spades"":
                    temp1 = 4;
                    break;
                case ""Hearts"":
                    temp1 = 3;
                    break;
                case ""Diamonds"":
                    temp1 = 2;
                    break;
                case ""Clubs"":
                    temp1 = 1;
                    break;
            }
            switch (c2.suit) {
                case ""Spades"":
                    temp2 = 4;
                    break;
                case ""Hearts"":
                    temp2 = 3;
                    break;
                case ""Diamonds"":
                    temp2 = 2;
                    break;
                case ""Clubs"":
                    temp2 = 1;
                    break;
            }

            // complete this function so the Card can be sorted according to the suit
            if (temp1 > temp2) {
                return 1;
            }
            if (temp1 < temp2) {
                return -1;
            }
            return 0;
        }

    }

}

