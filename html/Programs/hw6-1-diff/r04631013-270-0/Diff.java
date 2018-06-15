
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
        int a = 0;
        int b = 0;
        int ind_a = 0;
        int ind_b = 0;

        String d = ""2 3 4 5 6 7 8 9 10 J Q K A"";
        String[] dArray = d.split("" "");
        for (int i = 0; i < dArray.length; i++) {
            if (dArray[i].equals(this.face)) {
                ind_a = i;
                break;
            }
        }
        for (int i = 0; i < dArray.length; i++) {
            if (dArray[i].equals(that.face)) {
                ind_b = i;
                break;
            }
        }
        if (ind_a < ind_b) {
            return -1;
        }
        if (ind_a > ind_b) {
            return 1;
        } else {
            switch (this.suit) {
                case ""Spades"": {
                    a = 4;
                    break;
                }
                case ""Hearts"": {
                    a = 3;
                    break;
                }
                case ""Diamonds"": {
                    a = 2;
                    break;
                }
                case ""Clubs"": {
                    a = 1;
                    break;
                }
            }

            if (that.suit == ""Spades"") {
                b = 4;
            } else if (that.suit == ""Hearts"") {
                b = 3;
            } else if (that.suit == ""Diamonds"") {
                b = 2;
            } else if (that.suit == ""Clubs"") {
                b = 1;
            }
            if (a < b) {
                return -1;
            }
            if (a > b) {
                return 1;
            } else {
                return 0;
            }

        }

    }

// TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int ind_a = 0;
            int ind_b = 0;

            String d = new String(""Clubs Diamonds Hearts Spades"");
            String[] dArray = d.split("" "");
            for (int i = 0; i < dArray.length; i++) {
                if (dArray[i].equals(c1.suit)) {
                    ind_a = i;
                    break;
                }
            }

            for (int i = 0; i < dArray.length; i++) {
                if (dArray[i].equals(c2.suit)) {
                    ind_b = i;
                    break;
                }
            }
            if (ind_a < ind_b) {
                return -1;
            }
            if (ind_a > ind_b) {
                return 1;
            } else {
                return 0;
            }

        }
    }


}
