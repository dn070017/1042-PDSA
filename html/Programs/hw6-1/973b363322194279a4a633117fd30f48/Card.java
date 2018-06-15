
import java.util.Comparator;

public class Card{

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
        
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        //if suit of c1 > c2 return 1 equal return 0 else return -1
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int Suit1 = 0;int Suit2 = 0;int outcome = 0;
            switch(c1.suit)
            {
                case ""Spades"":
                    Suit1 = 4;
                    break;
                case ""Hearts"":
                    Suit1 = 3;
                    break;
                case ""Diamonds"":
                    Suit1 = 2;
                    break;
                case ""Clubs"":
                    Suit1 = 1;
                    break;
            }
            switch(c2.suit)
            {
                case ""Spades"":
                    Suit2 = 4;
                    break;
                case ""Hearts"":
                    Suit2 = 3;
                    break;
                case ""Diamonds"":
                    Suit2 = 2;
                    break;
                case ""Clubs"":
                    Suit2 = 1;
                    break;
            }
            if(Suit1 == Suit2)
            {
                outcome = 0;
            }
            else if(Suit1 > Suit2)
            {
                outcome = 1;
            }
            else{
                outcome = -1;
            }
                        
            return outcome;
        }
        
        public int compareFace(Card c1, Card c2)
        {
            int fa = 0,fac = 0;
              switch(c1.face)
            {
                case ""A"":
                    fa = 1;
                    break;
                case ""2"":
                    fa = 2;
                    break;
                case ""3"":
                    fa = 3;
                    break;
                case ""4"":
                    fa = 4;
                    break;
                 case ""5"":
                    fa = 5;
                    break;
                case ""6"":
                    fa = 6;
                    break;
                case ""7"":
                    fa = 7;
                    break;
                case ""8"":
                    fa = 8;
                    break;
                 case ""9"":
                    fa = 9;
                    break;
                case ""10"":
                    fa = 10;
                    break;
                case ""J"":
                    fa = 11;
                    break;
                case ""Q"":
                    fa = 12;
                    break;
                case ""K"":
                    fa = 13;
                    break;                   
            }
             switch(c2.face)
            {
                case ""A"":
                    fac = 1;
                    break;
                case ""2"":
                    fac = 2;
                    break;
                case ""3"":
                    fac = 3;
                    break;
                case ""4"":
                    fac = 4;
                    break;
                 case ""5"":
                    fac = 5;
                    break;
                case ""6"":
                    fac = 6;
                    break;
                case ""7"":
                    fac = 7;
                    break;
                case ""8"":
                    fac = 8;
                    break;
                 case ""9"":
                    fac = 9;
                    break;
                case ""10"":
                    fac = 10;
                    break;
                case ""J"":
                    fac = 11;
                    break;
                case ""Q"":
                    fac = 12;
                    break;
                case ""K"":
                    fac = 13;
                    break;                   
            }    
        if(fa > fac)
        {
            return 1;
        }
        else if(fa == fac)
        {
            return 0;
        }
        else
        {
            return -1;
        }
        }
        
         public boolean equals(Card c1,Card c2)
         {            
           return true;
         }
    }   
}


