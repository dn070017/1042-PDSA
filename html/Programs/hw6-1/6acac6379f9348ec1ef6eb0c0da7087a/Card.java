import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
    private String iFace1;
    private String iFace2;

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
        return Card.SUIT_ORDER.compare(this,that);
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if(c1.face==""A"")c1.iFace1=""14"";
            else if(c1.face==""J"")c1.iFace1=""11"";
            else if (c1.face==""Q"")c1.iFace1=""12"";
            else if (c1.face==""K"")c1.iFace1=""13"";
            else c1.iFace1=c1.face;

            if(c2.face==""A"")c2.iFace1=""14"";
            else if(c2.face==""J"")c2.iFace1=""11"";
            else if (c2.face==""Q"")c2.iFace1=""12"";
            else if (c2.face==""K"")c2.iFace1=""13"";
            else c2.iFace1=c2.face;

            if(c1.face.compareTo(c2.face)>0){
                return 1;
            }else if(c1.face.compareTo(c2.face)==0){
                if(c1.suit.compareTo(c2.suit)>0){
                    return 1;
                }else if (c1.suit.compareTo(c2.suit)==0)return 0;
                else return -1;
            }else return -1;

            // complete this function so the Card can be sorted according to the suit
        }
    }   
}

