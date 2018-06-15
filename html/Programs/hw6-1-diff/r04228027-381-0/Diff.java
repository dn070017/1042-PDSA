
import java.util.Comparator;
import java.util.Arrays;

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
        String[] sortedFace ={""A"","" 2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"","" J"", ""Q"", ""K""};
        int c1Face = Arrays.binarySearch(sortedFace,this.getFace());
        int c2Face = Arrays.binarySearch(sortedFace, that.getFace());
        if(c1Face < c2Face)
            return 1;
        else if (c1Face > c2Face)
            return -1;
        else{
            if( Card.SUIT_ORDER.compare(this, that) == 1)
                return 1;
            else if (Card.SUIT_ORDER.compare(this, that) == -1)
                return -1;
            else
                return 0;
            }
        }
    }  

    // TODO
    class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            String[] sortedSuit = {""Spades"","" Hearts"",""Diamonds"",""Clubs""};
            int c1Suit = Arrays.binarySearch(sortedSuit,c1.getSuit());
            int c2Suit = Arrays.binarySearch(sortedSuit,c2.getSuit());
            if(c1Suit < c2Suit)
                return 1;
            else if (c1Suit > c2Suit)
                return -1;
            else
                return 0;
        }
    }   
}


