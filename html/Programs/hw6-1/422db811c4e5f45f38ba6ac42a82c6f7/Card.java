
import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
        public int number;
        public int suitOrder;
	
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
    private void init(){
     switch (face){
            case""A"":
                this.number = 14;
                break;
            case""2"":
                this.number = 2;
                break;            
            case""3"":
                this.number = 3;
                break;                
            case""4"":
                this.number = 4;
                break;        
            case""5"":
                this.number = 5;
                break; 
            case""6"":
                this.number = 6;
                break;
            case""7"":
                this.number = 7;
                break;
            case""8"":
                this.number = 8;
                break;
            case""9"":
                this.number = 9;
                break;
            case""10"":
                this.number = 10;
                break;
            case""J"":
                this.number = 11;
                break;
            case""Q"":
                this.number = 12;
                break;
            case""K"":
                this.number = 13;
                break;
                default:
                System.out.println(""Wrong!"");
                break;
        }
        switch(suit){
            case""Spades"":
                this.suitOrder = 4;
                break;
            case""Hearts"":
                this.suitOrder = 3;
                break;
            case""Diamonds"":
                this.suitOrder = 2;
                break;
            case""Clubs"":
                this.suitOrder = 1;
                break;
            default:
                System.out.println(""Wrong!"");
                break;
                
    }
    
    
    }
    
    static private int getsuitorder(String s){
        int flag = 0;
        switch(s){
            case""Spades"":
                flag = 4;
                break;
            case""Hearts"":
                flag = 3;
                break;
            case""Diamonds"":
                flag = 2;
                break;
            case""Clubs"":
                flag = 1;
                break;
            default:
                System.out.println(""Wrong!"");
                break;
        }
        return flag;
    }
    static private int getnumber(String s){
        int flag = 0;
        switch (s){
            case""A"":
                flag = 14;
                break;
            case""2"":
                flag = 2;
                break;            
            case""3"":
                flag = 3;
                break;                
            case""4"":
                flag = 4;
                break;        
            case""5"":
                flag = 5;
                break; 
            case""6"":
                flag = 6;
                break;
            case""7"":
                flag = 7;
                break;
            case""8"":
                flag = 8;
                break;
            case""9"":
                flag = 9;
                break;
            case""10"":
                flag = 10;
                break;
            case""J"":
                flag = 11;
                break;
            case""Q"":
                flag = 12;
                break;
            case""K"":
                flag = 13;
                break;
                default:
                System.out.println(""Wrong!"");
                break;
        }
        return flag;
    
    }
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        init();
        int ans = 0;
            if(getnumber(this.getFace()) < getnumber(that.getFace())){
                ans = -1;
            }else if(getnumber(this.getFace()) > getnumber(that.getFace())){
                ans = 1;
            }else if (getnumber(this.getFace()) == getnumber(that.getFace())){
               if(getsuitorder(this.getSuit()) < getsuitorder(that.getSuit())){
                    ans = -1;
                }else if(getsuitorder(this.getSuit()) > getsuitorder(that.getSuit())){
                    ans = 1;
                }else if(getsuitorder(this.getSuit()) == getsuitorder(that.getSuit())){
                    ans = 0;
                }
            }
        return ans;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            c1.init();
            c2.init();
            int ans = 0;
            if(getsuitorder(c1.suit) < getsuitorder(c2.suit)){
                ans = -1;
            }else if(getsuitorder(c1.suit) > getsuitorder(c2.suit)){
                ans = 1;
            }else if(getsuitorder(c1.suit) == getsuitorder(c2.suit)){
                ans = 0;
            }
            return ans;
        }
    }   
}

