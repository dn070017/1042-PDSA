import java.util.Comparator;
import java.util.Objects;

public class Card implements Comparable<Card> {

	private final String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private final String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
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
        Integer[] first_face =new Integer[1];
        Integer[] second_face =new Integer[1];
        if(this.getFace().equalsIgnoreCase(""A""))
            first_face[0] = 14 ;
        else if(this.getFace().equalsIgnoreCase(""K""))
            first_face[0] = 13 ;
        else if(this.getFace().equalsIgnoreCase(""Q""))
            first_face[0] = 12 ;
        else if(this.getFace().equalsIgnoreCase(""J""))
            first_face[0] = 11 ;
        else 
            first_face[0] = Integer.parseInt(this.getFace());
        
        if(that.getFace().equalsIgnoreCase(""A""))
            second_face[0]  = 14 ;
        else if(that.getFace().equalsIgnoreCase(""K""))
            second_face[0]  = 13 ;
        else if(that.getFace().equalsIgnoreCase(""Q""))
            second_face[0] = 12 ;
        else if(that.getFace().equalsIgnoreCase(""J""))
            second_face[0] = 11 ;
        else
            second_face[0] = Integer.parseInt(that.getFace());
        
        if(first_face[0]>second_face[0])
            return +1 ;
        else if(first_face[0]<second_face[0])
            return -1 ;
        
        else if(Objects.equals(first_face[0],second_face[0])){
        if(SUIT_ORDER.compare(this,that)==-1)
           return -1 ;
        else if(SUIT_ORDER.compare(this, that)==1)
           return +1 ;
        else 
           return 0;
        }
           return 0;
        
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            String a = c1.getSuit();
            String b = c2.getSuit();
            int first=0,second=0;
            if (a.equalsIgnoreCase(""Spades"")){
                first=4;
             }
            else if (a.equalsIgnoreCase(""Hearts"")){
                first=3;
            }
            else if(a.equalsIgnoreCase(""Diamonds"")){
                first=2;
            }
            else if(a.equalsIgnoreCase(""Clubs"")){
                first=1;
            }
            
            if (b.equalsIgnoreCase(""Spades"")){
                second=4;
             }
            else if (b.equalsIgnoreCase(""Hearts"")){
                second=3;
            }
            else if(b.equalsIgnoreCase(""Diamonds"")){
                second=2;
            }
            else if(b.equalsIgnoreCase(""Clubs"")){
                second=1;
            }
            
            if(first>second) {return +1;}
            else if (first<second) {return -1;}
            else if(first==second){return 0;}
                        return 0;
        }
    }   
}


