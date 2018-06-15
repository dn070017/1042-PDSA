
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
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
         int a=0;
         int b=0;
        if(!this.face.equals(""J"")&&!this.face.equals(""Q"")&&!this.face.equals(""K"")&&!this.face.equals(""A"")){
            a=Integer.parseInt(this.face);
        }
        else{
            if(this.face.equals(""J""))a=11;
            if(this.face.equals(""Q""))a=12;
            if(this.face.equals(""K""))a=13;
            if(this.face.equals(""A""))a=14;
        }
        if(!that.face.equals(""J"")&&!that.face.equals(""Q"")&&!that.face.equals(""K"")&&!that.face.equals(""A"")){
            b=Integer.parseInt(that.face);
        }
        else{
            if(that.face.equals(""J""))b=11;
            if(that.face.equals(""Q""))b=12;
            if(that.face.equals(""K""))b=13;
            if(that.face.equals(""A""))b=14;
        }
        if(a<b)return -1;
        if(a>b)return 1;
        if(a==b){
            int e=0,f=0;
            if (this.suit.equals(""Spades"")){
                e=4;
            }
            if (this.suit.equals(""Hearts"")){
                e=3;
            }
            if (this.suit.equals(""Diamonds"")){
                e=2;
            }
            if (this.suit.equals(""Clubs"")){
                e=1;
            }if (that.suit.equals(""Spades"")){
                f=4;
            }
            if (that.suit.equals(""Hearts"")){
                f=3;
            }
            if (that.suit.equals(""Diamonds"")){
                f=2;
            }
            if (that.suit.equals(""Clubs"")){
                f=1;
            }
            
            // complete this function so the Card can be sorted according to the suit
            if(e>f)return 1;
            if(e<f)return -1;
            return 0;
        }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int e=0,f=0;
            if (c1.suit.equals(""Spades"")){
                e=4;
            }
            if (c1.suit.equals(""Hearts"")){
                e=3;
            }
            if (c1.suit.equals(""Diamonds"")){
                e=2;
            }
            if (c1.suit.equals(""Clubs"")){
                e=1;
            }if (c2.suit.equals(""Spades"")){
                f=4;
            }
            if (c2.suit.equals(""Hearts"")){
                f=3;
            }
            if (c2.suit.equals(""Diamonds"")){
                f=2;
            }
            if (c2.suit.equals(""Clubs"")){
                f=1;
            }
            
            // complete this function so the Card can be sorted according to the suit
            if(e>f)return 1;
            if(e<f)return -1;
            return 0;
        }
    }   
}


