
import java.util.Comparator;

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
        if(suittonum(this)>suittonum(that))
            return 1;
        
        else if(suittonum(this)<suittonum(that))
            return -1;
        
        else if(facetonum(this)>facetonum(that))
            return 1;
        
        else if(facetonum(this)<facetonum(that))
            return -1;
        else 
            return 0;
          
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if(suittonum(c1)>suittonum(c2))
            return 1;
        
        else if(suittonum(c1)<suittonum(c2))
            return -1; 
        else
            return 0;
        }
    }
    private static int suittonum(Card c){
        switch(c.suit){
        case(""Spades ""):
            return 4;
        case(""Hearts""):
            return 3;
        case(""Diamonds ""):
            return 2;
        case(""Clubs""):
            return 1;
        default:
            return 0;
        
        }   
    }
    private static int facetonum(Card c){
        switch(c.face){
            case(""A""):
              return 14;
            case(""K""):
                return 13;
            case(""Q""):
                return 12;
            case(""J""):
                return 11;
            default:
                return Integer.parseInt(c.face);
                                      
        }
    }
    

    
}


