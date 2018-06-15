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
    
    public int face2int() {
        if (this.face.equals(""A"")) return 14;
        if (this.face.equals(""K"")) return 13;
        if (this.face.equals(""Q"")) return 12;
        if (this.face.equals(""J"")) return 11;
        if (this.face.equals(""10"")) return 10;
        if (this.face.equals(""9"")) return 9;
        if (this.face.equals(""8"")) return 8;
        if (this.face.equals(""7"")) return 7;
        if (this.face.equals(""6"")) return 6;
        if (this.face.equals(""5"")) return 5;
        if (this.face.equals(""4"")) return 4;
        if (this.face.equals(""3"")) return 3;
        if (this.face.equals(""2"")) return 2;
        return 0;
    }
    
    public int suit2int() {
        if (this.suit.equals(""Spades"")) return 4;
        if (this.suit.equals(""Hearts"")) return 3;
        if (this.suit.equals(""Diamonds"")) return 2;
        if (this.suit.equals(""Clubs"")) return 1;
        return 0;
    }
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int face1 = this.face2int();
        int face2 = that.face2int();
        int suit1 = this.suit2int();
        int suit2 = that.suit2int();
        if (face1 < face2) return -1;
        if (face1 > face2) return +1;
        if (suit1 < suit2) return -1;
        if (suit1 > suit2) return +1;
        return 0;
    }  
    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int suit1 = c1.suit2int();
            int suit2 = c2.suit2int();
            if (suit1 < suit2) return -1;
            if (suit1 > suit2) return +1;
            return 0;
        }
    }   
    public static void main(String[] args){
        Card[] test= new Card[2];
        test[0] = new Card(""A"",""Spades"");
        test[1] = new Card(""8"",""Spades"");
        System.out.println(test[0].compareTo(test[1]));
        System.out.println(Card.SUIT_ORDER.compare(test[0],test[1]));
    }
}
    

