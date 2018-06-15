
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
        if(c.suit==""Spades"")
        {
            value+=39;
        }
        else if(c.suit==""Hearts"")
        {
            value+=26;
        }
        else if(c.suit==""Diamonds"")
        {
            value+=13;
        }
        else if(c.suit==""Clubs"")
        {
            value+=0;
        }
        if(c.face==""2"")
        {
            value+=12;
        }
        else if(c.face==""A"")
        {
            value+=11;
        }
        else if(c.face==""K"")
        {
            value+=10;
        }
        else if(c.face==""Q"")
        {
            value+=9;
        }
        else if(c.face==""J"")
        {
            value+=8;
        }
        else if(c.face==""10"")
        {
            value+=7;
        }
        else if(c.face==""9"")
        {
            value+=6;
        }
        else if(c.face==""8"")
        {
            value+=5;
        }
        else if(c.face==""7"")
        {
            value+=4;
        }
        else if(c.face==""6"")
        {
            value+=3;
        }
        else if(c.face==""5"")
        {
            value+=2;
        }
        else if(c.face==""4"")
        {
            value+=1;
        }
        else if(c.face==""3"")
        {
            value+=0;
        }
        return value;
    }
    // TODO
    public int compareTo(Card that) {

    
        int v1=evalueIt(this);
        int v2=evalueIt(that);
        if(v1>v2)
            return 1;
        if(v1<v2)
            return -1;
        else
        return 0;
        
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            int score1 = 0;
            int score2 = 0;
            if (c1.suit == ""Spades"") {
                score1 = 4;
            } else if (c1.suit == ""Hearts"") {
                score1 = 3;
            } else if (c1.suit == ""Diamonds"") {
                score1 = 2;
            } else if (c1.suit == ""Clubs"") {
                score1 = 1;
            }
            if (c2.suit == ""Spades"") {
                score2 = 4;
            } else if (c2.suit == ""Hearts"") {
                score2 = 3;
            } else if (c2.suit == ""Diamonds"") {
                score2 = 2;
            } else if (c2.suit == ""Clubs"") {
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

