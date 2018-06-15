
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
        int num1 = 0;
        int num2 = 0;
        int temp1 = 0;
        int temp2 = 0;
        switch (this.face){
            case ""A"":
                num1=13;
                break;
            case ""K"":
                num1=12;
                break;
            case ""Q"":
                num1=11;
                break;
            case ""J"":
                num1=10;
                break;
            case ""10"":
                num1=9;
                break;
            case ""9"":
                num1=8;
                break;
            case ""8"":
                num1=7;
                break;
            case ""7"":
                num1=6;
                break;
            case ""6"":
                num1=5;
                break;
            case ""5"":
                num1=4;
                break;
            case ""4"":
                num1=3;
                break;
            case ""3"":
                num1=2;
                break;
            case ""2"":
                num1=1;
                break;
        
        }
        switch (that.face){
            case ""A"":
                num2=13;
                break;
            case ""K"":
                num2=12;
                break;
            case ""Q"":
                num2=11;
                break;
            case ""J"":
                num2=10;
                break;
            case ""10"":
                num2=9;
                break;
            case ""9"":
                num2=8;
                break;
            case ""8"":
                num2=7;
                break;
            case ""7"":
                num2=6;
                break;
            case ""6"":
                num2=5;
                break;
            case ""5"":
                num2=4;
                break;
            case ""4"":
                num2=3;
                break;
            case ""3"":
                num2=2;
                break;
            case ""2"":
                num2=1;
                break;
        
        }
        
        switch (this.suit) {
            case ""Spades"":
                temp1 = 4;
                break;
            case ""Hearts"":
                temp1 = 3;
                break;
            case ""Diamonds"":
                temp1 = 2;
                break;
            case ""Clubs"":
                temp1 = 1;
                break;
        }
        switch (that.suit) {
            case ""Spades"":
                temp2 = 4;
                break;
            case ""Hearts"":
                temp2 = 3;
                break;
            case ""Diamonds"":
                temp2 = 2;
                break;
            case ""Clubs"":
                temp2 = 1;
                break;
        }
        if(num1>num2) return 1;
        if(num1<num2) return -1;
        if(temp1>temp2) return 1;
        if(temp1<temp2) return -1;   
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int temp1 = 0;
            int temp2 = 0;
            switch (c1.suit) {
                case ""Spades"":
                    temp1 = 4;
                    break;
                case ""Hearts"":
                    temp1 = 3;
                    break;
                case ""Diamonds"":
                    temp1 = 2;
                    break;
                case ""Clubs"":
                    temp1 = 1;
                    break;
            }
            switch (c2.suit) {
                case ""Spades"":
                    temp2 = 4;
                    break;
                case ""Hearts"":
                    temp2 = 3;
                    break;
                case ""Diamonds"":
                    temp2 = 2;
                    break;
                case ""Clubs"":
                    temp2 = 1;
                    break;
            }

            // complete this function so the Card can be sorted according to the suit
            if(temp1>temp2) return 1;
            if(temp1<temp2) return -1;
            return 0;
        }
    }
    
    public static void main(String[] args) {
        Card test1 = new Card(""2"", ""Diamond"");
        Card test2 = new Card(""3"", ""Spades"");
        
        System.out.println(Card.SUIT_ORDER.compare(test2, test1));
    }
}


