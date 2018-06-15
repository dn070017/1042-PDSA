import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Card implements Comparable<Card> {

	private String face; 
	private String suit; 
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    private static final Comparator<Card> FACE_ORDER = new FaceOrder();


    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }
     
   
    public String getFace(){
        return this.face;
    }
    
    public String getSuit(){
        return this.suit;
    }   
    

    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)

        int result = FACE_ORDER.compare(this , that);
        if(result != 0) {
            return result;
        } else {
            return SUIT_ORDER.compare(this , that);
        }
    }


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
            int suit_1 = order.indexOf(c1.getSuit());
            int suit_2 = order.indexOf(c2.getSuit());
            if (suit_1 < suit_2) return 1;
            else if (suit_1 > suit_2) return -1;
            else return 0;
        }


    }

    private static class FaceOrder implements Comparator<Card> {
        private static List<String> order;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""1""};
            order = new ArrayList<String>();
            for (String face : faces) {
                order.add(face);
            }
        }

        @Override
        public int compare(Card c1, Card c2) {
            int face_1 = order.indexOf(c1.getFace());
            int face_2 = order.indexOf(c2.getFace());
            if (face_1 < face_2) return 1;
            else if (face_1 > face_2) return -1;
            else return 0;
        }
    }
}

