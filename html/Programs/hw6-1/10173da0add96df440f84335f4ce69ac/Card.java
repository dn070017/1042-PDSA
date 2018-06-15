
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public int compareTo(Card that) 
    {
        int faceInt1 = 0;// faceToMap(that.getFace());
        int faceInt2 = 0;
        String thisFace = this.getFace();
        String thatFace = that.getFace();
        
        if(thisFace.equals(""2""))
            faceInt1 = 2;
        if(thisFace.equals(""3""))
            faceInt1 = 3;
        if(thisFace.equals(""4""))
            faceInt1 = 4;
        if(thisFace.equals(""5""))
            faceInt1 = 5;
        if(thisFace.equals(""6""))
            faceInt1 = 6;
        if(thisFace.equals(""7""))
            faceInt1 = 7;
        if(thisFace.equals(""8""))
            faceInt1 = 8;
        if(thisFace.equals(""9""))
            faceInt1 = 9;
        if(thisFace.equals(""10""))
            faceInt1 = 10;
        if(thisFace.equals(""J""))
            faceInt1 = 11;
        if(thisFace.equals(""Q""))
            faceInt1 = 12;
        if(thisFace.equals(""K""))
            faceInt1 = 13;
        if(thisFace.equals(""A""))
            faceInt1 = 14;

              
        if(thatFace.equals(""2""))
            faceInt2 = 2;
        if(thatFace.equals(""3""))
            faceInt2 = 3;
        if(thatFace.equals(""4""))
            faceInt2 = 4;
        if(thatFace.equals(""5""))
            faceInt2 = 5;
        if(thatFace.equals(""6""))
            faceInt2 = 6;
        if(thatFace.equals(""7""))
            faceInt2 = 7;
        if(thatFace.equals(""8""))
            faceInt2 = 8;
        if(thatFace.equals(""9""))
            faceInt2 = 9;
        if(thatFace.equals(""10""))
            faceInt2 = 10;
        if(thatFace.equals(""J""))
            faceInt2 = 11;
        if(thatFace.equals(""Q""))
            faceInt2 = 12;
        if(thatFace.equals(""K""))
            faceInt2 = 13;
        if(thatFace.equals(""A""))
            faceInt2 = 14;
        

       
        if (faceInt1> faceInt2)
            return 1;
        else if (faceInt1< faceInt2)
            return -1;
        else 
            return SuitOrder.compare(this, that);

       // else return 0;
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
    }  

    


    // TODO
    
    private static class SuitOrder implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) 
        {
            int num1 = 0, num2 = 0;
        String suitStr = c1.getSuit();
        if (suitStr.equals(""Spades""))
            num1=  3;
        else if (suitStr.equals(""Hearts""))
            num1 =2;
        else if (suitStr.equals(""Diamonds""))
            num1=1;
        else if(suitStr.equals(""Clubs""))
            num1 = 0;
        
          String suitStr2 = c2.getSuit();
        if (suitStr2.equals(""Spades""))
            num2=  3;
        else if (suitStr2.equals(""Hearts""))
            num2 =2;
        else if (suitStr2.equals(""Diamonds""))
            num2=1;
        else if(suitStr2.equals(""Clubs""))
            num2 = 0;

            return num1 - num2;
            // complete this function so the Card can be sorted according to the suit
          //  return 0;
        }
    }   
}

