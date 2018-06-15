
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
        int thisnum = cardface(this.face);
        int thatnum = cardface(that.face);

        if (thisnum > thatnum) {
            return 1;
        } else if (thisnum < thatnum) {
            return -1;
        } else {
            thisnum = cardsuit(this.suit);
            thatnum = cardsuit(that.suit);
            
            if (thisnum > thatnum) {
                return 1;
            } else if (thisnum < thatnum) {
                return -1;
            } else {
                return 0;
            }

        }

    }
.
    // TODO

    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

            int c1num = cardsuit(c1.suit);
            int c2num = cardsuit(c2.suit);
            
            if (c1num > c2num) {
                return 1;
            } else if (c1num < c2num) {
                return -1;
            } else {
                return 0;
            }
            
        }
    }

    private static int cardface(String face) {
        int facenum;

        switch (face) {
            case ""A"":
                facenum = 14;
                break;
            case ""J"":
                facenum = 11;
                break;
            case ""Q"":
                facenum = 12;
                break;
            case ""K"":
                facenum = 13;
                break;
            default:
                facenum = Integer.parseInt(face);
                break;
        }

        return facenum;
    }

    private static int cardsuit(String suit) {
        int suitnum;

        switch (suit) {
            case ""Spades"":
                suitnum = 4;
                break;
            case ""Hearts"":
                suitnum = 3;
                break;
            case ""Diamonds"":
                suitnum = 2;
                break;
            default:
                suitnum = 1;
                break;
        }

        return suitnum;
    }
}

