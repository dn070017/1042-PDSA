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
    	String[] faceString = {""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"",""J"",""Q"",""K"",""A""};
    	String[] suitString = {""Clubs"",""Diamonds"",""Hearts"",""Spades""};

    	int faceindex1=0;
    	int faceindex2=0;
    	for(int i = 0; i<faceString.length;i++){
    		if(this.getFace().equals(faceString[i])){
    			faceindex1=i+1;
//    			System.out.println(""this: :""+ this.getFace());
//    			System.out.println(""thisi: :""+ faceindex1);
    		}
    		if(that.getFace().equals(faceString[i])){
    			faceindex2=i+1;
//    			System.out.println(""that: :""+ that.getFace());
//    			System.out.println(""thati: :""+ faceindex2);
    		}
    	}
    	
    	int suitindex1=0;
    	int suitindex2=0;
    	for(int j = 0; j<suitString.length;j++){
    		if(this.getSuit().equals(suitString[j])){
    			suitindex1=j;
//    			System.out.println(""this: :""+ this.getSuit());
//    			System.out.println(""suit1: ""+ suitindex1);
    		}
    		if(that.getSuit().equals(suitString[j])){
    			suitindex2=j;
//    			System.out.println(""that: :""+ that.getSuit());
//    			System.out.println(""suit2: ""+ suitindex2);
    		}
    	}
    	
    	if(faceindex1 > faceindex2){
    		return +1;
    	}else if(faceindex1 < faceindex2){
    		return -1;
    	}
    	
    	if(faceindex1 == faceindex2){
    		if(suitindex1 > suitindex2){
    			return +1;
    		}else if(suitindex1 < suitindex2){
    			return -1;
    		}
    		else{
    			return 0;
    		}
    	}
    	
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
        	String[] suitString = {""Clubs"",""Diamonds"",""Hearts"",""Spades""};
        	int suitindex1=0;
        	int suitindex2=0;
        	for(int j = 0; j<suitString.length;j++){
        		if(c1.getSuit().equals(suitString[j])){
        			suitindex1=j;
//        			System.out.println(""this: :""+ this.getSuit());
//        			System.out.println(""suit1: ""+ suitindex1);
        		}
        		if(c2.getSuit().equals(suitString[j])){
        			suitindex2=j;
//        			System.out.println(""that: :""+ that.getSuit());
//        			System.out.println(""suit2: ""+ suitindex2);
        		}
        	}
    		if(suitindex1 > suitindex2){
    			return +1;
    		}else if(suitindex1 < suitindex2){
    			return -1;
    		}
    		else{
    			return 0;
    		}
        }
    }   
}

