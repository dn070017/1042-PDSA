
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; 
    private String suit; 

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();


    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }


    public String getFace() {
        return this.face;
    }

  
    public String getSuit() {
        return this.suit;
    }


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

    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {

            if (c1.suit.compareTo(c2.suit) < 0) {
                return +1;
            }
            if (c1.suit.compareTo(c2.suit) > 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
    }
}

