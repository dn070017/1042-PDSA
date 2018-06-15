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
        if(this.face==""A"") this.face=""14"";
        if(that.face==""A"") that.face=""14"";
        if(this.face==""K"") this.face=""13"";
        if(that.face==""K"") that.face=""13"";
        if(this.face==""Q"") this.face=""12"";
        if(that.face==""Q"") that.face=""12"";
        if(this.face==""J"") this.face=""11"";
        if(that.face==""J"") that.face=""11"";
        int thif = Integer.parseInt(this.face);
        int thaf = Integer.parseInt(that.face);
        if (thif > thaf) return 1;
        if (thif < thaf) return -1;
        //在suit_order這個class下做compare這件事
        return SUIT_ORDER.compare(this, that);
        //return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            //someone spades
            if (c1.suit==""Spades""|c2.suit==""Spades"") {
                if (c1.suit==""Spades""&c2.suit!=""Spades"") return 1;
                else if (c1.suit!=""Spades""&c2.suit==""Spades"") return -1;}
            if (c1.suit==""Hearts""|c2.suit==""Hearts"") {
                if (c1.suit==""Hearts""&c2.suit!=""Hearts"") return 1;
                else if (c1.suit!=""Hearts""&c2.suit==""Hearts"") return -1;}
            if (c1.suit==""Diamonds""|c2.suit==""Diamonds"") {
                if(c1.suit==""Diamonds""&c2.suit!=""Diamonds"") return 1;
                else if(c1.suit!=""Diamonds""&c2.suit==""Diamonds"") return -1;
            }
            return 0;
        }
    }   
    
//        public static void main(String[] args){
//        //
//           Card[] test = new Card[2];
//           test[0]= new Card(""Q"",""Hearts"");
//           test[1]= new Card(""K"",""Spades"");        
//
//           System.out.println(test[0].compareTo(test[1]));
//}
}

