
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
        int thisface = face(this);
        int thatface = face(that);
        double thissuit = suit(this);
        double thatsuit = suit(that);
        
        if((thisface+thissuit)>(thatface+thatsuit))
            return 1;
        else if((thisface+thissuit)==(thatface+thatsuit))
            return 0;
        else
            return -1;
       
    }

    public static int face(Card that) {
        if (""A"".equals(that.face)) {
            return 14;
        } else if (""J"".equals(that.face)) {
            return 11;
        } else if (""Q"".equals(that.face)) {
            return 12;
        } else if (""K"".equals(that.face)) {
            return 13;
        } else {
            return Integer.parseInt(that.face);
        }

    }

    public static double suit(Card A) {
        if (""Spades"".equals(A.suit)) {
            return 0.4;
        } else if (""Hearts"".equals(A.suit)) {
            return 0.3;
        } else if (""Diamonds"".equals(A.suit)) {
            return 0.2;
        } else {
            return 0.1;
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
          double C1 =suit(c1);
          double C2 =suit(c2);
          
          if(C1>C2)
              return 1;
          else if(C1==C2)
              return 0;
          else
              return -1;
        }
    }

//    public static void main(String[] args) throws Exception {
//
//        Card A = new Card(""A"", ""Diamonds"");
//        Card B = new Card(""A"", ""Spades"");
//
//        System.out.println(Card.SUIT_ORDER.compare(A,B));
//
//    }

}

