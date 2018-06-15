
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

//     DO NOT MODIFY THIS   
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

        int thisface = toface(this);
        int thatface = toface(that);
        int thissuit = tosuit(this);
        int thatsuit = tosuit(that);

        if (thisface > thatface) {
            return 1;
        } else if (thisface == thatface) {
            if (thissuit > thatsuit) {
                return 1;
            } else if(thissuit == thatsuit){
                return 0;
            }else{
                return -1;
            }
        } else {
            return -1;
        }
       
    }

    private static int toface(Card A) {
        if (""A"".equals(A.face)) {
            return 14;
        } else if (""J"".equals(A.face)) {
            return 11;
        } else if (""Q"".equals(A.face)) {
            return 12;
        } else if (""K"".equals(A.face)) {
            return 13;
        } else {
            return Integer.parseInt(A.face);
        }
    }

//    4 3 2 1 黑陶、紅心、方塊、梅花
    private static int tosuit(Card A) {
        if (""Spades"".equals(A.suit)) {
            return 4;
        } else if (""Hearts"".equals(A.suit)) {
            return 3;
        } else if (""Diamonds"".equals(A.suit)) {
            return 2;
        } else {
            return 1;
        }
    }

//     TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int thissuit = tosuit(c1);
            int thatsuit = tosuit(c2);

            if (thissuit > thatsuit) {
                return 1;
            } else if (thissuit == thatsuit) {
                return 0;
            } else {
                return -1;
            }
            
        }
    }

   
}

