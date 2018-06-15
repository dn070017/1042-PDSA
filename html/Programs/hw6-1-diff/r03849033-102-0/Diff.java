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
        String[] ranks  = { ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A"" };
        String[] suitss = { ""clubs"", ""diamonds"", ""hearts"",""spades"" };
        int thisf=15;
        int thatf=15;
        int thiss=15;
        int thats=15;      
        for (int k = 0; k < 13; k++) {
            if(this.face.equals(ranks[k])){ thisf = k;}
            if(that.face.equals(ranks[k])){ thatf = k;}            
        }
        for (int k = 0; k < 4; k++) {
            if(this.suit.equals(suitss[k])){ thiss = k;}
            if(that.suit.equals(suitss[k])){ thats = k;}    
        }          
        if(thisf > thatf) {return 1;}
        else if(thisf < thatf) {return -1;}
        else if(thiss > thats) {return 1;}        
        else if(thiss < thats) {return -1;}
        return 0;
        
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
        String[] suits = { ""Clubs"", ""Diamonds"", ""Hearts"",""Spades"" };
        int c1s=15;
        int c2s=15;  
        for (int k = 0; k < 4; k++) {
            if(c1.getSuit().equals(suits[k])){ c1s = k;}
            if(c2.getSuit().equals(suits[k])){ c2s = k;}        
        }  
        if(c1s>c2s){return 1;}  
        else if(c1s < c2s) {return -1;}        
        return 0;
        }
    }   
}

