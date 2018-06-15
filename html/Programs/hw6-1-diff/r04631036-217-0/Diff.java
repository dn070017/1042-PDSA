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
        if(getfacerank(this)<getfacerank(that))
            return 1;
        if(getfacerank(this)>getfacerank(that))
            return -1;
        
        if(getfacerank(this)==getfacerank(that))
        {
            if(getsuitrank(this)>getsuitrank(that))
                return -1;
            else if(getsuitrank(that)>getsuitrank(this))
                return 1;
        }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return 0;
    }
    private int getsuitrank(Card c){
        if(c.getSuit()==""Spades"")
            return 1;
        else if(c.getSuit()==""Hearts"")
            return 2;
        else if(c.getSuit()==""Diamonds"")
            return 3;
        else if(c.getSuit()==""Clubs"")
            return 4;
        return 0;
    }
    private int getfacerank(Card c){
        if(c.getFace()==""A"")
            return 1;
        if(c.getFace()==""K"")
            return 2;
        if(c.getFace()==""Q"")
            return 3;
        if(c.getFace()==""J"")
            return 4;
        if(c.getFace()==""10"")
            return 5;
        if(c.getFace()==""9"")
            return 6;
        if(c.getFace()==""8"")
            return 7;
        if(c.getFace()==""7"")
            return 8;
        if(c.getFace()==""6"")
            return 9;
        if(c.getFace()==""5"")
            return 10;
        if(c.getFace()==""4"")
            return 11;
        if(c.getFace()==""3"")
            return 12;
        if(c.getFace()==""2"")
            return 13;
        
        return 0;
    }
    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if(c1.getSuit()==c2.getSuit())
                return 0;
            else if(c1.getSuit()==""Spades""&&c1.getFace()!=c2.getSuit())
                return 1;
            else if(c1.getSuit()==""Clubs""&&c1.getSuit()!=c2.getSuit())
                return -1;
            else if(c1.getSuit()==""Hearts""&&(c2.getSuit()==""Diamonds""||c2.getSuit()==""clubs""))
                return 1;
            else if(c1.getSuit()==""Diamonds""&&c2.getSuit()==""Clubs"")
                return 1;
            else if(c1.getSuit()==""Hearts""&&c2.getSuit()==""Spades"")
                return -1;
            else if(c1.getSuit()==""Diamonds""&&(c2.getSuit()==""Spades""||c2.getSuit()==""Hearts""))
                return -1;
            // complete this function so the Card can be sorted according to the suit
           return 0;
        }
    }   
}


