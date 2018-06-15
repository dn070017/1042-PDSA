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
        if(this.getfacerank(this)<that.getfacerank(that))
            return 1;
        else if(this.getfacerank(this)>that.getfacerank(that))
            return -1;
        
        else if(this.getfacerank(this)==that.getfacerank(that))
        {
            if(this.getsuitrank(this)<that.getsuitrank(that))
                return 1;
            else
                return -1;
        }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return 0;
    }
    private int getsuitrank(Card c){
        if(c.getSuit().equalsIgnoreCase(""Spades""))
            return 1;
        else if(c.getSuit().equalsIgnoreCase(""Hearts""))
            return 2;
        else if(c.getSuit().equalsIgnoreCase(""Diamonds""))
            return 3;
        else if(c.getSuit().equalsIgnoreCase(""Clubs""))
            return 4;
        
        
        
        return 0;
    }
    private int getfacerank(Card c){
        if(c.getFace().equalsIgnoreCase(""A""))
            return 1;
        else if(c.getFace().equalsIgnoreCase(""K""))
            return 2;
        else if(c.getFace().equalsIgnoreCase(""Q""))
            return 3;
        else if(c.getFace().equalsIgnoreCase(""J""))
            return 4;
        else if(c.getFace().equalsIgnoreCase(""10""))
            return 5;
        else if(c.getFace().equalsIgnoreCase(""9""))
            return 6;
        else if(c.getFace().equalsIgnoreCase(""8""))
            return 7;
        else if(c.getFace().equalsIgnoreCase(""7""))
            return 8;
        else if(c.getFace().equalsIgnoreCase(""6""))
            return 9;
        else if(c.getFace().equalsIgnoreCase(""5""))
            return 10;
        else if(c.getFace().equalsIgnoreCase(""4""))
            return 11;
        else if(c.getFace().equalsIgnoreCase(""3""))
            return 12;
        else if(c.getFace().equalsIgnoreCase(""2""))
            return 13;
        
        return 0;
    }
    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if(c1.getSuit().equalsIgnoreCase(c2.getSuit()))
                return 0;
            else if(c1.getSuit().equalsIgnoreCase(""Spades"")&&!c1.getSuit().equalsIgnoreCase(c2.getSuit()))
                return 1;
            else if(c1.getSuit().equalsIgnoreCase(""Clubs"")&&!c1.getSuit().equalsIgnoreCase(c2.getSuit()))
                return -1;
            else if(c1.getSuit().equalsIgnoreCase(""Hearts"")&&(c2.getSuit().equalsIgnoreCase(""Diamonds"")||c2.getSuit().equalsIgnoreCase(""Clubs"")))
                return 1;
            else if(c1.getSuit().equalsIgnoreCase(""Hearts"")&&c2.getSuit().equalsIgnoreCase(""Spades""))
                return -1;
            else if(c1.getSuit().equalsIgnoreCase(""Diamonds"")&&c2.getSuit().equalsIgnoreCase(""Clubs""))
                return 1;
            else if(c1.getSuit().equalsIgnoreCase(""Diamonds"")&&(c2.getSuit().equalsIgnoreCase(""Spades"")||c2.getSuit().equalsIgnoreCase(""Hearts"")))
                return -1;
            // complete this function so the Card can be sorted according to the suit
           return 0;
        }
    }   
}


