import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	private int Num;
        
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();
    
    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
        if(this.face.equals(""A""))
            this.Num = 1;
        else if(this.face.equals(""K""))
             this.Num = 13;
        else if (this.face.equals(""Q""))
            this.Num = 12;
        else if(this.face.equals(""J""))
            this.Num = 11;
        else
            this.Num = Integer.parseInt(this.face);
    }
     
    public Integer getNumber(){
        return Num;
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
        if(this.Num == 1){
            if(that.Num == 1)
                return Card.SUIT_ORDER.compare(this, that);
            else
                return 1;
        }
        else if(that.Num == 1)
            return -1;
        else if(this.Num > that.Num)
            return 1;
        else if(this.Num < that.Num)
            return -1;
        else
        {
           return Card.SUIT_ORDER.compare(this, that);
        }
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if (c1.suit.equals(""Clubs"")){
                if(c2.suit.equals(""Clubs""))
                    return 0;
                else
                    return -1;
            }
            else if (c1.suit.equals(""Diamonds"")){
                if(c2.suit.equals(""Clubs""))
                    return 1;
                else if(c2.suit.equals(""Diamonds""))
                    return 0;
                else 
                    return -1;
            }
            else if(c1.suit.equals(""Hearts"")){
                if(c2.suit.equals(""Spades""))
                    return -1;
                else if(c2.suit.equals(""Hearts""))
                    return 0;
                else
                    return 1;
            }
            else{
                if(c2.suit.equals(""Spades""))
                    return 0;
                else
                    return 1;
            }
        }
    }
}

