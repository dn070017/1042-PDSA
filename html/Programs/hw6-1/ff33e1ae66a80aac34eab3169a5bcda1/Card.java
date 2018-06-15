import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Card implements Comparable<Card> {

	private String face; // 13 of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // 4 suits [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    private static final Comparator<Card> FACE_ORDER = new FaceOrder();

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
        // compare faces first if r=0 then compare suits 
        int result = FACE_ORDER.compare(this , that);
        if(result != 0) {
            return result;
        } else {
            return SUIT_ORDER.compare(this , that);
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        private static List<String> orders;
        SuitOrder() {
            String[] suits = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
            orders = new ArrayList<String>();
            for (String suit : suits) {
                orders.add(suit);
            }
        }

        @Override
        public int compare(Card c1, Card c2) {
            int suitc1 = orders.indexOf(c1.getSuit());
            int suitc2 = orders.indexOf(c2.getSuit());
            if (suitc1 < suitc2) return 1;
            else if (suitc1 > suitc2) return -1;
            else return 0;
        }

    }

    private static class FaceOrder implements Comparator<Card> {
        private static List<String> orders;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2""};
            orders = new ArrayList<String>();
            for (String face : faces) {
                orders.add(face);
            }
        }

        @Override
        public int compare(Card c1, Card c2) {
            int facec1 = orders.indexOf(c1.getFace());
            int facec2 = orders.indexOf(c2.getFace());
            if (facec1 < facec2) return 1;
            else if (facec1 > facec2) return -1;
            else return 0;
        }
    }
}
