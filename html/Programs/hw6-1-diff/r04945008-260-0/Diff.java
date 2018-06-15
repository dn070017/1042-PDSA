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
    
    public int getValue(){
        int a=0;
        if(this.suit == ""Spades"") return a=4;
        if(this.suit == ""Hearts"") return a=3;
        if(this.suit == ""Diamonds"") return a=2;
        if(this.suit == ""Clubs"") return a=1;
        return a=0;
    }
    public int getValue2(){
        int a=0;
        if(this.face == ""A"") return a=13;
        if(this.face == ""K"") return a=12;
        if(this.face == ""Q"") return a=11;
        if(this.face == ""J"") return a=10;
        if(this.face == ""10"") return a=9;
        if(this.face == ""9"") return a=8;
        if(this.face == ""8"") return a=7;
        if(this.face == ""7"") return a=6;
        if(this.face == ""6"") return a=5;
        if(this.face == ""5"") return a=4;
        if(this.face == ""4"") return a=3;
        if(this.face == ""3"") return a=2;
        if(this.face == ""2"") return a=1;
        return a=0;
    }
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int a,b;
        a=this.getValue2();
        b=that.getValue2();
        if(a>b) return +1;
        else if(a<b) return -1;
        else if(this.getValue()>that.getValue()) return +1;
        else return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int a,b;
            a=c1.getValue();
            b=c2.getValue();
            if(a == b) return 0;
            else if(a < b) return -1;
            else return +1;
        }
    }   
    public static void main(String[] args){
        Card[] test= new Card[2];
        test[0] = new Card(""5"",""Hearts"");
        test[1] = new Card(""8"",""Spades"");
        System.out.println(test[0].compareTo(test[1]));
        System.out.println(Card.SUIT_ORDER.compare(test[0],test[1]));
    }
}
