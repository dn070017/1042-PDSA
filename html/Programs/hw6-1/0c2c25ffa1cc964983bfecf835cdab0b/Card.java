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
        int iFace1;
        int iFace2;

        if (this.face.equals(""A"")) iFace1 = 14;
        else if (this.face.equals(""J"")) iFace1 = 11;
        else if (this.face.equals(""Q"")) iFace1 = 12;
        else if (this.face.equals(""K"")) iFace1 = 13;
        else iFace1 = Integer.parseInt(this.face);

        if (that.face.equals(""A"")) iFace2 = 14;
        else if (that.face.equals(""J"")) iFace2 = 11;
        else if (that.face.equals(""Q"")) iFace2 = 12;
        else if (that.face.equals(""K"")) iFace2 = 13;
        else iFace2 = Integer.parseInt(that.face);

        if (iFace1 > iFace2) {
            return 1;
        } else if (iFace1 == iFace2) {
            return Card.SUIT_ORDER.compare(this, that);
        } else {
            return -1;
        }

    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
                if(c1.suit.compareTo(c2.suit)>0){
                    return 1;
                }else if (c1.suit.compareTo(c2.suit)==0){
                    return 0;
                }
                else{
                    return -1;
                }
            // complete this function so the Card can be sorted according to the suit
        }
    }   
}

