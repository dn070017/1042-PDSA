import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
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
        // complete this function so the Card can be sorted
        int pf[];
        pf = new int[2];
        
        String hold[];
        hold = new String[2];
        
        hold[0] = this.getFace();
        hold[1] = that.getFace();
        
        for(int i=0;i<2;i++){
            if( hold[i]==""A"")
                pf[i] = 13;
            else if(hold[i]==""2"")
                pf[i] = 1;
            else if(hold[i]==""3"")
                pf[i] = 2;
            else if(hold[i]==""4"")
                pf[i] = 3;
            else if(hold[i]==""5"")
                pf[i] = 4;
            else if(hold[i]==""6"")
                pf[i] = 5;
            else if(hold[i]==""7"")
                pf[i] = 6;
            else if(hold[i]==""8"")
                pf[i] = 7;
            else if(hold[i]==""9"")
                pf[i] = 8;
            else if(hold[i]==""10"")
                pf[i] = 9;
            else if(hold[i]==""J"")
                pf[i] = 10;
            else if(hold[i]==""Q"")
                pf[i] = 11;
            else if(hold[i]==""K"")
                pf[i] = 12; 
        }
        if(pf[0]>pf[1])
                return 1;
        else if(pf[0]<pf[1])
                return -1; 
        else if(pf[0]==pf[1]&&Card.SUIT_ORDER.compare(this,that)==1)
                return 1; 
        else if(pf[0]==pf[1]&&Card.SUIT_ORDER.compare(this,that)==-1)
                return -1;
        else 
            return 0;
        // (you must consider both face and suit)
    }  
     

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
        int p[];
        p = new int[2];
        
        if(c1.getSuit()==""Spades"")
            p[0] = 4;
        else if(c1.getSuit()==""Hearts"")
            p[0] = 3;
        else if(c1.getSuit()==""Diamonds "")
            p[0] = 2;
        else if(c1.getSuit()==""Clubs"")
            p[0] = 1;
        
        if(c2.getSuit()==""Spades"")
            p[1] = 4;
        else if(c2.getSuit()==""Hearts"")
            p[1] = 3;
        else if(c2.getSuit()==""Diamonds "")
            p[1] = 2;
        else if(c2.getSuit()==""Clubs"")
            p[1] = 1;

        if(p[0]>p[1])
            return 1;
        else if(p[0]<p[1])
            return -1;
        else 
            return 0;
        }
    }   

}


   

