
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

    Card() {
.
    }
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
    public int changeFace(Card c){
        int value1 = 0;
        switch(c.face){
            case(""A""):
                return 14;
            case(""J""):
                return 11;
            case(""Q""):
                return 12;
            case(""K""):
                return 13;
            default:
                return Integer.parseInt(c.face);
        }      
    }
    
       public int changeSuit(Card c){
        int value2 = 0;
        switch(c.suit){
            case(""Spades""):
                return 4;
            case(""Hearts""):
                return 3;
            case(""Diamonds""):
                return 2;
            case(""Clubs""):
                return 1;
        }      
        return 50;
    }
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int v1 = this.changeFace(this);
        int v2 = that.changeFace(that);
        
        int t1 = this.changeSuit(this);
        int t2 = that.changeSuit(that);
        
        if (v1>v2) return 1;
        if (v1<v2) return -1;
        if (t1>t2) return 1;
        if (t1<t2) return -1;

        return 0;
    }  

    // TODO
    // 有static的function 不用宣告新的物件就可以用了
    private static class SuitOrder implements Comparator<Card> { // 只考慮Suit 花色
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

        int s1 = c1.changeSuit(c1);
        int s2 = c2.changeSuit(c2);
        
        if (s1>s2) return 1;
        if (s1<s2) return -1;
            
            return 0; //同花色
        }
    }   
}


