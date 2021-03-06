import java.util.Arrays;
import java.util.Comparator;

public class Card implements Comparable<Card> {
	private static final String[] SUIT = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
	private static final String[] FACE = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
	
	
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
		int c1_face = 0;
		int c2_face = 0;
		for(int i = 0; i < FACE.length; i++){
			if(this.face.equals(FACE[i])) c1_face = i;
			if(this.face.equals(FACE[i])) c2_face = i;
		}
		int c1_suit = 0;
		int c2_suit = 0;
		for(int i = 0; i < SUIT.length; i++){
			String s1 = this.face;
			String s2 = that.face;
			if(s1.equals(SUIT[i])) c1_suit = i;
			if(s2.equals(SUIT[i])) c2_suit = i;
		}
		
		if (c1_face > c2_face)      return  1;
		else if (c1_face < c2_face) return -1;
        else if (c1_suit > c2_suit) return  1;
		else if (c1_suit < c2_suit) return -1;
		else                        return  0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int c1_suit = 0 ;
			int c2_suit = 0;
			for(int i = 0; i < SUIT.length; i++){
				String s1 = c1.face;
				String s2 = c2.face;
				if(s1.equals(SUIT[i])) c1_suit = i;
				if(s2.equals(SUIT[i])) c2_suit = i;
			}
			if(c1_suit > c2_suit)      return  1;
			else if(c1_suit < c2_suit) return -1;
			else                       return  0;
        }
    }   
	
	public void main(){
		System.out.println(""OK"");
	}
}


