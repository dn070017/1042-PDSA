import java.util.Comparator;
import java.util.Arrays;
public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]       
	private String[] copy = {""0"",""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"", ""J"", ""Q"", ""K"", ""A""};
        private String[] Suit = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};        
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
       
        int a ;
        int b ;
        a = this.findnum();
        b = that.findnum();
        
        if(a>b) 
            return 1;
        else if(a<b)
            return -1;
        else{
            int c;
            c = this.suit.compareTo(that.suit);
            if(c>0)
                return 1;
            else if(c<0)
                return -1;
        }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int k;
// complete this function so the Card can be sorted according to the suit
            k = c1.suit.compareTo(c2.suit);
            if(k>0)
                return 1;
            if(k<0)
                return -1;
            else
                 return 0;
        }
    }   
    public int findnum(){
        
        for(int j =0;j<16;j++){
            if(this.face.equals(copy[j])){
                return j;
            }
        }
        return -1;
    }
    public int findsuit(){
        for(int j = 0; j<4;j++){
            if(this.suit.equals(Suit[j]))
                return j;
        }
        return -1;
    }
     public static void main(String[] args){
     }
}


