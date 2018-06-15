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
        String a = this.face;
        String b = that.face;
        if (a==""Q"") a=""JQ"";
        if (a==""A"") a=""L"";
        if (b==""Q"") b=""JQ"";
        if (b==""A"") b=""L"";
        if (a.compareTo(b)>0)  return 1;
        if (a.compareTo(b)<0) return -1;
        if (a.compareTo(b)==0)  {
            if (this.suit.compareTo(that.suit)>0)   return +1;
            if (this.suit.compareTo(that.suit)<0)    return -1;
            return 0;
        }
                
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if (c1.getSuit().compareTo(c2.getSuit())>0)   return +1;
            if (c1.getSuit().compareTo(c2.getSuit())<0)    return -1;
            return 0;
        }
    }   
}

