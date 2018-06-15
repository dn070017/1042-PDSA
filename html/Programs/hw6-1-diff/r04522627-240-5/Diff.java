import java.io.BufferedReader;
import java.io.FileReader;
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
        int v1 = 0, v2 = 0;
        //<face to value>
        //c1
        if(tryParseInt(this.face))
        {
            v1 = Integer.parseInt(this.face);
        }
        else if(this.face.equals(""A""))
        {
            v1 = 14;
        }
        else if(this.face.equals(""K""))
        {
            v1 = 13;
        }
        else if(this.face.equals(""Q""))
        {
            v1 = 12;
        }
        else if(this.face.equals(""J""))
        {
            v1 = 11;
        }
        //c2
        if(tryParseInt(that.face))
        {
            v2 = Integer.parseInt(that.face);
        }
        else if(that.face.equals(""A""))
        {
            v2 = 14;
        }
        else if(that.face.equals(""K""))
        {
            v2 = 13;
        }
        else if(that.face.equals(""Q""))
        {
            v2 = 12;
        }
        else if(that.face.equals(""J""))
        {
            v2 = 11;
        }
        
        
        
        
        if(v1 > v2)
        {
            return 1;
        }
        else if(v1 < v2)
        {
            return -1;
        }
        else
        {
            return Card.SUIT_ORDER.compare(this, that);
        }
    }
    
    boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
}

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int v1 = 0, v2 = 0;
            //c1
            if(c1.suit.equals(""Spades""))
            {
                v1 = 4;
            }
            else if(c1.suit.equals(""Hearts""))
            {
                v1 = 3;
            }
            else if(c1.suit.equals(""Diamonds""))
            {
                v1 = 2;
            }
            else if(c1.suit.equals(""Clubs""))
            {
                v1 = 1;
            }
            //c2
            if(c2.suit.equals(""Spades""))
            {
                v2 = 4;
            }
            else if(c2.suit.equals(""Hearts""))
            {
                v2 = 3;
            }
            else if(c2.suit.equals(""Diamonds""))
            {
                v2 = 2;
            }
            else if(c2.suit.equals(""Clubs""))
            {
                v2 = 1;
            }
            
            if(v1 > v2)
            {
                return 1;
            }
            else if(v1 < v2)
            {
                return -1;
            }
            else
            {
                return 0;
            }
            
        }
    }
    
    /*public static void main(String[] args) {
        // TODO code application logic here
        Card c1 = new Card(""A"",""Spades"");
        Card c2 = new Card(""A"",""Hearts"");
        
        System.out.println(c1.compareTo(c2));
    }
    */
}


