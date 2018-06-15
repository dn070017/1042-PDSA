
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author clint
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
        Integer thisFace = this.mapFace(true);
        Integer thisSuit = this.mapSuit();
        Integer thatFace = that.mapFace(true);
        Integer thatSuit = that.mapSuit();
        
        int compareFace = thisFace.compareTo(thatFace);
        int compareSuit = thisSuit.compareTo(thatSuit);
        
        // compare two card
        if (compareFace != 0) {
            // if two card have diff faces (numbers), compare the face
            return compareFace;
        } else {
            // if two card have same faces (numbers), compare the suit
            return compareSuit;
        } // end if-else
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            Integer c1Suit = c1.mapSuit();
            Integer c2Suit = c2.mapSuit();
            
            return c1Suit.compareTo(c2Suit);
        }
    }
    
    // my implementation
    /**
     * method: mapFace
     * convert the face into integer and return
     * note that A can be 1 or 14
     * @param isAce
     * @return integer
     */
    public Integer mapFace(boolean isAce) {
        // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
        String cardFace = getFace();
            switch (cardFace) {
                case ""A"": if (isAce) return 14;
                          else       return 1;
                case ""K"": return 13;
                case ""Q"": return 12;
                case ""J"": return 11;
                default:
                    return Integer.valueOf(cardFace); // end if-else
            }
    } // end func mapFace
    
    /**
     * method: mapSuit
     * return integer that corresponds to the suit
     * rules Spades > Hearts > Diamonds > Clubs
     * @return 
     */
    public Integer mapSuit() {
        String cardSuit;
        cardSuit = getSuit();
            switch (cardSuit) {
                case ""Spades"":   return 4; // Spades
                case ""Hearts"":   return 3; // Hearts
                case ""Diamonds"": return 2; // Diamonds
                default:         return 1; // Clubs
            }
    } // end func mapFace
}

