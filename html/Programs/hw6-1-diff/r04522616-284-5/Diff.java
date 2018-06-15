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
        int thisFaceNumber=0;
        int thatFaceNumber=0;
        //
        int thisSuitNumber=0;
        int thatSuitNumber=0;        
        //
        if(this.face.equals(""A""))thisFaceNumber=14;
        if(this.face.equals(""K""))thisFaceNumber=13;
        if(this.face.equals(""Q""))thisFaceNumber=12;
        if(this.face.equals(""J""))thisFaceNumber=11;
        if(this.face.equals(""10""))thisFaceNumber=10;
        if(this.face.equals(""9""))thisFaceNumber=9;
        if(this.face.equals(""8""))thisFaceNumber=8;
        if(this.face.equals(""7""))thisFaceNumber=7;
        if(this.face.equals(""6""))thisFaceNumber=6;
        if(this.face.equals(""5""))thisFaceNumber=5;
        if(this.face.equals(""4""))thisFaceNumber=4;
        if(this.face.equals(""3""))thisFaceNumber=3;
        if(this.face.equals(""2""))thisFaceNumber=2;
        //
        if(that.face.equals(""A""))thatFaceNumber=14;
        if(that.face.equals(""K""))thatFaceNumber=13;
        if(that.face.equals(""Q""))thatFaceNumber=12;
        if(that.face.equals(""J""))thatFaceNumber=11;
        if(that.face.equals(""10""))thatFaceNumber=10;
        if(that.face.equals(""9""))thatFaceNumber=9;
        if(that.face.equals(""8""))thatFaceNumber=8;
        if(that.face.equals(""7""))thatFaceNumber=7;
        if(that.face.equals(""6""))thatFaceNumber=6;
        if(that.face.equals(""5""))thatFaceNumber=5;
        if(that.face.equals(""4""))thatFaceNumber=4;
        if(that.face.equals(""3""))thatFaceNumber=3;
        if(that.face.equals(""2""))thatFaceNumber=2;
        //
        if(this.suit.equals(""Spades""))thisSuitNumber=3;
        if(this.suit.equals(""Hearts""))thisSuitNumber=2;
        if(this.suit.equals(""Diamonds""))thisSuitNumber=1;
        if(this.suit.equals(""Clubs""))thisSuitNumber=0;
        //
        if(that.suit.equals(""Spades""))thatSuitNumber=3;
        if(that.suit.equals(""Hearts""))thatSuitNumber=2;
        if(that.suit.equals(""Diamonds""))thatSuitNumber=1;
        if(that.suit.equals(""Clubs""))thatSuitNumber=0;
        
        if(thisFaceNumber>thatFaceNumber)return 1;
        else if(thisFaceNumber<thatFaceNumber)return -1;
        else 
        {
            if(thisSuitNumber>thatSuitNumber)return 1;
            else if(thisSuitNumber<thatSuitNumber)return -1;
        }        
        
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int thisSuitNumber=0;
            int thatSuitNumber=0;        
            //
            if(c1.suit.equals(""Spades""))thisSuitNumber=3;
            if(c1.suit.equals(""Hearts""))thisSuitNumber=2;
            if(c1.suit.equals(""Diamonds""))thisSuitNumber=1;
            if(c1.suit.equals(""Clubs""))thisSuitNumber=0;
            //
            if(c2.suit.equals(""Spades""))thatSuitNumber=3;
            if(c2.suit.equals(""Hearts""))thatSuitNumber=2;
            if(c2.suit.equals(""Diamonds""))thatSuitNumber=1;
            if(c2.suit.equals(""Clubs""))thatSuitNumber=0;
            //
            if(thisSuitNumber>thatSuitNumber)return 1;
            else if(thisSuitNumber<thatSuitNumber)return -1;
            else return 0;
            // complete this function so the Card can be sorted according to the suit
            
        }
    }   
}


