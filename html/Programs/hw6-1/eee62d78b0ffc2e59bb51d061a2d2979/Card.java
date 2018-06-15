
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
            return 1;
        else if (thisFaceInt< faceInt)
            return -1;
        else if (thisSuitInt> suitInt)
            return 1;
        else if (thisSuitInt< suitInt)
            return -1;
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

