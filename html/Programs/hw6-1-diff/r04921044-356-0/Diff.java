
import java.util.*;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }

    // DO NOT MODIFY THIS
    public String getFace(){
        return this.face;
    }

    // DO NOT MODIFY THIS
    public String getSuit(){
        return this.suit;
    }

    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)

        Map<String, Integer> faceMap = new HashMap<String, Integer> ();
        faceMap.put(""A"", 14);
        faceMap.put(""2"", 2);
        faceMap.put(""3"", 3);
        faceMap.put(""4"", 4);
        faceMap.put(""5"", 5);
        faceMap.put(""6"", 6);
        faceMap.put(""7"", 7);
        faceMap.put(""8"", 8);
        faceMap.put(""9"", 9);
        faceMap.put(""10"", 10);
        faceMap.put(""J"", 11);
        faceMap.put(""Q"", 12);
        faceMap.put(""K"", 13);

        Map<String, Integer> suitMap = new HashMap<String, Integer> ();
        suitMap.put(""Spades"", 0);
        suitMap.put(""Hearts"", 1);
        suitMap.put(""Diamonds"", 2);
        suitMap.put(""Clubs"", 3);

        if (faceMap.get(that.face) != faceMap.get(this.face))
            return faceMap.get(that.face) - faceMap.get(this.face);

        return suitMap.get(that.suit) - suitMap.get(this.suit);
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

            Map<String, Integer> suitMap = new HashMap<String, Integer> ();
            suitMap.put(""Spades"", 0);
            suitMap.put(""Hearts"", 1);
            suitMap.put(""Diamonds"", 2);
            suitMap.put(""Clubs"", 3);

            return suitMap.get(c1.suit) - suitMap.get(c2.suit);
        }
    }
}

