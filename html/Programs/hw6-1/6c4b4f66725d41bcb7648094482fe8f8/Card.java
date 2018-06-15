import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    private static final Comparator<String> FACE_ORDER = new FaceOrder();

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

        String face1 = this.getFace();
        String face2 = that.getFace();
        int result = FACE_ORDER.compare(face1 , face2);
        if(result != 0) {
            return result;
        } else {
            return SUIT_ORDER.compare(this , that);
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        private static List<String> order;
        SuitOrder() {
            String[] suits = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
            order = new ArrayList<String>();
            for (String suit : suits) {
                order.add(suit);
            }
        }

        @Override
        public int compare(Card c1, Card c2) {
            int suit_1 = order.indexOf(c1);
            int suit_2 = order.indexOf(c2);
            if (suit_1 < suit_2) return 1;
            else if (suit_1 > suit_2) return -1;
            else return 0;
        }

    }

    private static class FaceOrder implements Comparator<String> {
        private static List<String> order;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""1""};
            order = new ArrayList<String>();
            for (String face : faces) {
                order.add(face);
            }
        }

        @Override
        public int compare(String face1, String face2) {
            int face_1 = order.indexOf(face1);
            int face_2 = order.indexOf(face2);
            if (face_1 < face_2) return 1;
            else if (face_1 > face_2) return -1;
            else return 0;
        }
    }
}


