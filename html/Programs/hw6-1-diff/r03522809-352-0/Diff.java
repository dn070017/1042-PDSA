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
        int thisvalue,thatvalue,value=0;

        if(that.face.equals(""A"")){thatvalue = 14;} //get that card value
        else if(that.face.equals(""J"")){thatvalue = 11;}
        else if(that.face.equals(""Q"")){thatvalue = 12;}          
        else if(that.face.equals(""K"")){thatvalue = 13;}
        else{thatvalue=Integer.parseInt(that.face);}
        
        if(this.face.equals(""A"")){thisvalue = 14;} //get this card value
        else if(this.face.equals(""J"")){thisvalue = 11;}
        else if(this.face.equals(""Q"")){thisvalue = 12;}          
        else if(this.face.equals(""K"")){thisvalue = 13;}
        else{thisvalue=Integer.parseInt(this.face);}
        
        if(thisvalue>thatvalue){value=1;}
        else if(thisvalue<thatvalue){value=-1;}
        else if(thisvalue==thatvalue){
            int C1suitvalue=0,C2suitvalue=0,suitvalue=0;
            
            if(this.suit.equals(""Spades"") ){C1suitvalue=4;}
            else if(this.suit.equals(""Hearts"") ){C1suitvalue=3;}
            else if(this.suit.equals(""Diamonds"") ){C1suitvalue=2;}
            else if(this.suit.equals(""Clubs"") ){C1suitvalue=1;}
            
            if(that.suit.equals(""Spades"") ){C2suitvalue=4;}
            else if(that.suit.equals(""Hearts"") ){C2suitvalue=3;}
            else if(that.suit.equals(""Diamonds"") ){C2suitvalue=2;}
            else if(that.suit.equals(""Clubs"") ){C2suitvalue=1;}
            
            if(C1suitvalue>C2suitvalue){value=1;}
            else if(C1suitvalue<C2suitvalue){value=-1;}
            else{value=0;}
            }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return value;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        
        
        
        public int compare(Card c1, Card c2) {
            int C1suitvalue=0,C2suitvalue=0,suitvalue=0;
            if(c1.suit.equals(""Spades"") ){C1suitvalue=4;}
            else if(c1.suit.equals(""Hearts"") ){C1suitvalue=3;}
            else if(c1.suit.equals(""Diamonds"") ){C1suitvalue=2;}
            else if(c1.suit.equals(""Clubs"") ){C1suitvalue=1;}
            
            if(c2.suit.equals(""Spades"") ){C2suitvalue=4;}
            else if(c2.suit.equals(""Hearts"") ){C2suitvalue=3;}
            else if(c2.suit.equals(""Diamonds"") ){C2suitvalue=2;}
            else if(c2.suit.equals(""Clubs"") ){C2suitvalue=1;}
            
            if(C1suitvalue>C2suitvalue){suitvalue=1;}
            else if(C1suitvalue<C2suitvalue){suitvalue=-1;}
            else{suitvalue=0;}

            // complete this function so the Card can be sorted according to the suit
            return suitvalue;
        }

    }
    public interface Comparator<T extends Object> {

        public int compare(T t, T t1);

        public boolean equals(Object o);
}
    
}
