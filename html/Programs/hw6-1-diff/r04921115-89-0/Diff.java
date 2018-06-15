import java.util.*;

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
    	String A = ""14"";
    	String K = ""13""; 
    	String Q = ""12""; 
    	String J = ""11"";
    	
    	
    	if(this.getFace().equals(""A"") & (!that.getFace().equals(""A"")) ){
    		return +1;
    	}else if (this.getFace().equals(""K"") & (!that.getFace().equals(""A"")) & (!that.getFace().equals(""K"")) ){
    		return +1;
    	}else if (this.getFace().equals(""Q"") & (!that.getFace().equals(""A"")) & (!that.getFace().equals(""K"")) & (!that.getFace().equals(""Q"")) ){
    		return +1;
    	}else if (this.getFace().equals(""J"") & (!that.getFace().equals(""A"")) & (!that.getFace().equals(""K"")) & (!that.getFace().equals(""Q"")) & (!that.getFace().equals(""J""))){
    		return +1;
    	}
    	//
    	if(that.getFace().equals(""A"") & (!this.getFace().equals(""A"")) ){
    		return -1;
    	}else if (that.getFace().equals(""K"") & (!this.getFace().equals(""A"")) & (!this.getFace().equals(""K"")) ){
    		return -1;
    	}else if (that.getFace().equals(""Q"") & (!this.getFace().equals(""A"")) & (!this.getFace().equals(""K"")) & (!this.getFace().equals(""Q"")) ){
    		return -1;
    	}else if (that.getFace().equals(""J"") & (!this.getFace().equals(""A"")) & (!this.getFace().equals(""K"")) & (!this.getFace().equals(""Q"")) & (!this.getFace().equals(""J""))){
    		return -1;
    	}
    	//	
    	
    	if(this.getFace().equals(that.getFace())){
	    	if(this.getSuit().equals(""Spades"") & (!that.getSuit().equals(""Spades"")) ){
	    		return +1;
	    	}else if(this.getSuit().equals(""Hearts"") & (!that.getSuit().equals(""Spades"")) & (!that.getSuit().equals(""Hearts"")) ){
	    		return +1;
	    	}else if(this.getSuit().equals(""Diamonds"") & (!that.getSuit().equals(""Spades"")) & (!that.getSuit().equals(""Hearts"")) & (!that.getSuit().equals(""Diamonds"")) ){
	    		return +1;
	    	}else if(this.getSuit().equals(""Clubs"") & (!that.getSuit().equals(""Spades"")) & (!that.getSuit().equals(""Hearts"")) & (!that.getSuit().equals(""Diamonds"")) & (!that.getSuit().equals(""Clubs"")) ){
	    		return +1;
	    	}
	    	//
	    	if(that.getSuit().equals(""Spades"") & (!this.getSuit().equals(""Spades"")) ){
	    		return -1;
	    	}else if(that.getSuit().equals(""Hearts"") & (!this.getSuit().equals(""Spades"")) & (!this.getSuit().equals(""Hearts"")) ){
	    		return -1;
	    	}else if(that.getSuit().equals(""Diamonds"") & (!this.getSuit().equals(""Spades"")) & (!this.getSuit().equals(""Hearts"")) & (!this.getSuit().equals(""Diamonds"")) ){
	    		return -1;
	    	}else if(that.getSuit().equals(""Clubs"") & (!this.getSuit().equals(""Spades"")) & (!this.getSuit().equals(""Hearts"")) & (!this.getSuit().equals(""Diamonds"")) & (!this.getSuit().equals(""Clubs"")) ){
	    		return -1;
	    	}
    	}
    	
    	String sa = this.getFace();
    	String sb = that.getFace();
    	System.out.println(""sa""+sa);
    	System.out.println(""sb""+sb);
    	int a = Integer.parseInt(sa);
    	int b = Integer.parseInt(sb);
    	if(a > b){
    		return +1;
    	}else if(a < b){
    		return -1;
    	}
    	
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
        	if(c1.getSuit().equals(""Spades"") & (!c2.getSuit().equals(""Spades"")) ){
	    		return +1;
	    	}else if(c1.getSuit().equals(""Hearts"") & (!c2.getSuit().equals(""Spades"")) & (!c2.getSuit().equals(""Hearts"")) ){
	    		return +1;
	    	}else if(c1.getSuit().equals(""Diamonds"") & (!c2.getSuit().equals(""Spades"")) & (!c2.getSuit().equals(""Hearts"")) & (!c2.getSuit().equals(""Diamonds"")) ){
	    		return +1;
	    	}else if(c1.getSuit().equals(""Clubs"") & (!c2.getSuit().equals(""Spades"")) & (!c2.getSuit().equals(""Hearts"")) & (!c2.getSuit().equals(""Diamonds"")) & (!c2.getSuit().equals(""Clubs"")) ){
	    		return +1;
	    	}
        	
        	if(c2.getSuit().equals(""Spades"") & (!c1.getSuit().equals(""Spades"")) ){
	    		return -1;
	    	}else if(c2.getSuit().equals(""Hearts"") & (!c1.getSuit().equals(""Spades"")) & (!c1.getSuit().equals(""Hearts"")) ){
	    		return -1;
	    	}else if(c2.getSuit().equals(""Diamonds"") & (!c1.getSuit().equals(""Spades"")) & (!c1.getSuit().equals(""Hearts"")) & (!c1.getSuit().equals(""Diamonds"")) ){
	    		return -1;
	    	}else if(c2.getSuit().equals(""Clubs"") & (!c1.getSuit().equals(""Spades"")) & (!c1.getSuit().equals(""Hearts"")) & (!c1.getSuit().equals(""Diamonds"")) & (!c1.getSuit().equals(""Clubs"")) ){
	    		return -1;
	    	}
        	
        	return 0;
        }
    }   
}

