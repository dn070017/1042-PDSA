
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
        int faceInt = faceToMap(that.face);
        int suitInt = faceToMap(that.suit);
        int thisFaceInt = faceToMap(this.face);
        int thisSuitInt = faceToMap(this.suit);
       
        if (thisFaceInt> faceInt)
            return -1;
        else if (thisFaceInt< faceInt)
            return 1;
        else if (thisSuitInt> suitInt)
            return -1;
        else if (thisSuitInt< suitInt)
            return 1;
        else return 0;
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
    }  
    public static int faceToMap(String faceStr)
    {
        if(isInteger(faceStr))
        {
            int key = Integer.parseInt(faceStr) ;
            return key;
        }
        
        else if (faceStr.equals(""A""))
            return 14;
        else if (faceStr.equals(""K""))
            return 13;
        else if (faceStr.equals(""Q""))
            return 12;
        else if(faceStr.equals(""J""))
            return 11;
            else 
            return 0;
    }
    
        public static int suitToMap(String suitStr)
    {

        if (suitStr.equals(""Spades""))
            return 3;
        else if (suitStr.equals(""Hearts""))
            return 2;
        else if (suitStr.equals(""Diamonds""))
            return 1;
        else if(suitStr.equals(""Clubs""))
            return 0;
            else 
            return 0;
    }
    
    public static boolean isInteger(String s) 
    {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return true;
}
    

    // TODO
    
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) 
        {
            return suitToMap(c1.suit) - suitToMap(c2.suit); 
            // complete this function so the Card can be sorted according to the suit
          //  return 0;
        }
    }   
}

