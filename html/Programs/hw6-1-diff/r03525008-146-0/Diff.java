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
        int x=get_facevalue(this.face);
        int y=get_facevalue(that.suit);
        if(x>y){return 1;}
        else if(x<y){return -1;}
        else{return SUIT_ORDER.compare(this,that) ;}

    }
    private int get_facevalue(String face ){
        if(face.equals(""A"")){return 14;}
        else if(face.equals(""K"")){return 13;}
        else if(face.equals(""Q"")){return 12;}
        else if(face.equals(""J"")){return 11;}
        else {return Integer.parseInt(face);}
    }


    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int x=get_suitvalue(c1.suit);
            int y=get_suitvalue(c2.suit);
            if(x>y){return 1;}
            else if(x<y){return -1;}
            else{return 0;}

        }
        private int get_suitvalue(String suit){
            if(suit.equals(""Spades"")){return 4;}
            else if(suit.equals(""Hearts"")){return 3;}
            else if(suit.equals(""Diamonds"")){return 2;}
            else {return 1;}
        }
    }   
}


