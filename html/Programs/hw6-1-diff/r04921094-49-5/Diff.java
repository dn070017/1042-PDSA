import java.util.Comparator;
import java.util.Arrays;

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
        int thisFace = faceToInt(this.face);
        int thatFace = faceToInt(that.face);

        if(thisFace < thatFace){
            return -1;
        }
        else if(thisFace > thatFace){
            return 1;
        }
        else{
            return SUIT_ORDER.compare(this, that);
        }
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int c1Suit = suitToInt(c1.getSuit());
            int c2Suit = suitToInt(c2.getSuit());

            if (c1Suit < c2Suit) {
                return -1;
            }
            else if (c1Suit > c2Suit){
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public static int faceToInt(String face){
        try{
            return Integer.parseInt(face);
        }
        catch(Exception e){
            switch(face){
                case ""A"": return 14;
                case ""K"": return 13;
                case ""Q"": return 12;
                case ""J"": return 11;
            }
        }

        return 0;
    }

    public static int suitToInt(String suit){
        switch(suit){
            case ""Spades"": return 3;
            case ""Hearts"": return 2;
            case ""Diamonds"": return 1;
            case ""Clubs"": return 0;
        }
        return 4;
    }   

}

