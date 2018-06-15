
import java.util.Comparator;
import java.util.Arrays;

public class Card implements Comparable<Card> {

	private String face; // should be one of should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
        protected int facesize;
        protected int suitsize;
	private String [] Osuit = {""Clubs"",""Diamonds"",""Hearts"",""Spades""};
        private String [] Oface = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A""};

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;//give card's face
        this.suit = suit;//give card's number
        for (int i = 0;i<4;i++){
            if(Osuit[i].equals(this.suit)){
                 this.suitsize =i;
            }
        } 
        for (int i = 0;i<13;i++){
            if(Oface[i].equals(this.face)){
                 this.facesize =i;
            }
        }
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
        //give the number
        if(this.facesize> that.facesize){
            return 1;
        }
        if(this.facesize< that.facesize){
            return -1;
        }
        else{
            return SUIT_ORDER.compare(this, that);
        }
        // (you must consider both face and suit)
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if(c1.suitsize< c2.suitsize){
                return -1;
            }
            if(c1.suitsize> c2.suitsize){
                return 1;
            }
            else{
                return 0;
            }
        }
    }   
}

