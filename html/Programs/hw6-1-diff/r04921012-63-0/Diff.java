
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
        if(SUIT_ORDER.compare(this,that)==0){
            //String faceOrder=""2345678910JQKA"";
            //if (faceOrder.indexOf(this.face)>faceOrder.indexOf(that.face))return 1;
            //else if (faceOrder.indexOf(this.face)==faceOrder.indexOf(that.face))return 0;
            String[] faceOrder={"" 2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A""};
            int thisI=0;
            int thatI=0;
            for (int i=0;i<13;i++){
                if (faceOrder[i].equals(this.face))
                    thisI=i;
                if (faceOrder[i].equals(that.face))
                    thatI=i;
            }
            if (thisI>thatI)return 1;
            else if (thisI==thatI)return 0;
            else return -1;
        }
        else
            return SUIT_ORDER.compare(this,that);
            
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            char ch1=c1.suit.charAt(0);
            char ch2=c2.suit.charAt(0);
            
            if ((int) ch1>(int) ch2)return 1;
            else if ((int) ch1==(int) ch2)return 0;
            else return -1;
            
        }
    }   
}


