
import java.util.Comparator;


/**
 * 1042 PDSA
 * hw06_Card
 * @author Robert
 */


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
        String Number = ""2,3,4,5,6,7,8,9,10,J,Q,K,A"";
        String Color = ""Clubs, Diamonds, Hearts, Spades"";
        int face_compare;
        face_compare = Number.indexOf(this.face)-Number.indexOf(that.face);
        if (face_compare>0){
            return 1;
        }
        else if (face_compare<0){
            return -1;
        }
        else{
            int suit_compare;
            suit_compare = Color.indexOf(this.suit)-Color.indexOf(that.suit);
            if (suit_compare>0){
                return 1;
            }
            else if (suit_compare<0){
                return -1;
            }
            else{
                return 0;
            }
        }
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            String Color = ""Clubs, Diamonds, Hearts, Spades"";
            int suit_compare;
            suit_compare = Color.indexOf(c1.suit)-Color.indexOf(c2.suit);
            if (suit_compare>0){
                return 1;
            }
            else if (suit_compare<0){
                return -1;
            }
            else{
                return 0;
            }
        }
    }   
}


