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
        int this_order = 0;
        int that_order = 0;
        switch (this.face) {
            case ""A"":
                this_order = 13;
                break;
            case ""2"":
                this_order = 1;
                break;
            case ""3"":
                this_order = 2;
                break;
            case ""4"":
                this_order = 3;
                break;
            case ""5"":
                this_order = 4;
                break;
            case ""6"":
                this_order = 5;
                break;
            case ""7"":
                this_order = 6;
                break;
            case ""8"":
                this_order = 7;
                break;
            case ""9"":
                this_order = 8;
                break;
            case ""10"":
                this_order = 9;
                break;
            case ""J"":
                this_order = 10;
                break;
            case ""Q"":
                this_order = 11;
                break;
            case ""K"":
                this_order = 12;
                break;
        }
        switch (that.face) {
            case ""A"":
                that_order = 13;
                break;
            case ""2"":
                that_order = 1;
                break;
            case ""3"":
                that_order = 2;
                break;
            case ""4"":
                that_order = 3;
                break;
            case ""5"":
                that_order = 4;
                break;
            case ""6"":
                that_order = 5;
                break;
            case ""7"":
                that_order = 6;
                break;
            case ""8"":
                that_order = 7;
                break;
            case ""9"":
                that_order = 8;
                break;
            case ""10"":
                that_order = 9;
                break;
            case ""J"":
                that_order = 10;
                break;
            case ""Q"":
                that_order = 11;
                break;
            case ""K"":
                that_order = 12;
                break;
        }
        if (this_order > that_order) {
            return 1;
        } else if (this_order < that_order) {
            return -1;
        } else {
            if (Card.SUIT_ORDER.compare(this, that) == 1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int c1_order = 0;
            int c2_order = 0;
            switch (c1.suit) {
                case ""Spades"":
                    c1_order = 4;
                    break;
                case ""Hearts"":
                    c1_order = 3;
                    break;
                case ""Diamonds"":
                    c1_order = 2;
                    break;
                case ""Clubs"":
                    c1_order = 1;
                    break;
            }
            switch (c2.suit) {
                case ""Spades"":
                    c2_order = 4;
                    break;
                case ""Hearts"":
                    c2_order = 3;
                    break;
                case ""Diamonds"":
                    c2_order = 2;
                    break;
                case ""Clubs"":
                    c2_order = 1;
                    break;
            }
            if (c1_order > c2_order) {
                return 1;
            } else if (c1_order < c2_order) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
