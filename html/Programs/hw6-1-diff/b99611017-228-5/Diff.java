
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    // DO NOT MODIFY THIS   
    public String getFace() {
        return this.face;
    }

    // DO NOT MODIFY THIS    
    public String getSuit() {
        return this.suit;
    }

    public int evalueIt(Card c){
    
        int value=0;
       
        if(""A"".equals(c.face))
        {
            value+=12;
        }
        else if(""K"".equals(c.face))
        {
            value+=11;
        }
        else if(""Q"".equals(c.face))
        {
            value+=10;
        }
        else if(""J"".equals(c.face))
        {
            value+=9;
        }
        else if(""10"".equals(c.face))
        {
            value+=8;
        }
        else if(""9"".equals(c.face))
        {
            value+=7;
        }
        else if(""8"".equals(c.face))
        {
            value+=6;
        }
        else if(""7"".equals(c.face))
        {
            value+=5;
        }
        else if(""6"".equals(c.face))
        {
            value+=4;
        }
        else if(""5"".equals(c.face))
        {
            value+=3;
        }
        else if(""4"".equals(c.face))
        {
            value+=2;
        }
        else if(""3"".equals(c.face))
        {
            value+=1;
        }
        else if(""2"".equals(c.face))
        {
            value+=0;
        }
        if(""Spades"".equals(c.suit))
        {
            value=value*4+3;
        }
        else if(""Hearts"".equals(c.suit))
        {
            value=value*4+2;
        }
        else if(""Diamonds"".equals(c.suit))
        {
            value=value*4+1;
        }
        else if(""Clubs"".equals(c.suit))
        {
            value=value*4;
        }
        return value;
    }
 
    // TODO
    @Override
    public int compareTo(Card that) {

    
        int v1=evalueIt(this);
        int v2=evalueIt(that);
 
        int ans=0;
        if(v1>v2)
            ans= 1;
        if(v1<v2)
            ans= -1;
        
        if(v1==v2)
            ans=0;
        return ans;
        
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            int score1 = 0;
            int score2 = 0;
            if (""Spades"".equals(c1.suit)) {
                score1 = 4;
            } else if (""Hearts"".equals(c1.suit)) {
                score1 = 3;
            } else if (""Diamonds"".equals(c1.suit)) {
                score1 = 2;
            } else if (""Clubs"".equals(c1.suit)) {
                score1 = 1;
            }
            if (""Spades"".equals(c2.suit)) {
                score2 = 4;
            } else if (""Hearts"".equals(c2.suit)) {
                score2 = 3;
            } else if (""Diamonds"".equals(c2.suit)) {
                score2 = 2;
            } else if (""Clubs"".equals(c2.suit)) {
                score2 = 1;
            }
            if (score1 > score2) {
                return 1;
            } else if (score1 < score2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

