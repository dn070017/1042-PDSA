
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
    @Override
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int a;
        int b;
        switch (this.face) {
            case ""A"":
                a = 14;
                break;
            case ""K"":
                a = 13;
                break;
            case ""Q"":
                a = 12;
                break;
            case ""J"":
                a = 11;
                break;
            default:
                a = Integer.parseInt(this.face);
                break;
        }

        switch (that.face) {
            case ""A"":
                b = 14;
                break;
            case ""K"":
                b = 13;
                break;
            case ""Q"":
                b = 12;
                break;
            case ""J"":
                b = 11;
                break;
            default:
                b = Integer.parseInt(that.face);
                break;
        }

        if (a > b) {
            return -1;
        } else if (a < b) {
            return +1;
        } else if (this.face.compareTo(that.face) == 0) {
            if (this.suit.compareTo(that.suit) < 0) {
                return +1;
            }
            if (this.suit.compareTo(that.suit) > 0) {
                return -1;
            } else {
                return 0;
            }
        }

        return 0;
    }
//    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            int a;
            int b;
            switch (c1.face) {
                case ""A"":
                    a = 14;
                    break;
                case ""K"":
                    a = 13;
                    break;
                case ""Q"":
                    a = 12;
                    break;
                case ""J"":
                    a = 11;
                    break;
                default:
                    a = Integer.parseInt(c1.face);
                    break;
            }

            switch (c2.face) {
                case ""A"":
                    b = 14;
                    break;
                case ""K"":
                    b = 13;
                    break;
                case ""Q"":
                    b = 12;
                    break;
                case ""J"":
                    b = 11;
                    break;
                default:
                    b = Integer.parseInt(c2.face);
                    break;
            }

            if (a > b) {
                return -1;
            } else if (a < b) {
                return +1;
            } else if (c1.face.compareTo(c2.face) == 0) {
                if (c1.suit.compareTo(c2.suit) < 0) {
                    return +1;
                }
                if (c1.suit.compareTo(c2.suit) > 0) {
                    return -1;
                } else {
                    return 0;
                }
            }

            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
