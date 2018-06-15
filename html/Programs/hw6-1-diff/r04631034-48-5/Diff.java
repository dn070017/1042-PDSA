
import java.util.Comparator;
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
              HashMap<String,Integer> map =new HashMap<String,Integer>();
              for(int i =2;i<11;i++){
                        map.put(String.valueOf(i),i);
              }
              map.put(""J"",11);
              map.put(""Q"",12);
              map.put(""K"",13);
              map.put(""A"",14);
              map.put(""Spades"",18);
              map.put(""Hearts"",17);
              map.put(""Diamonds"",16);
              map.put(""Clubs"",15);
              
              if(map.get(this.face)>map.get(that.face)) return +1;
              if(map.get(this.face)<map.get(that.face)) return -1;
              if(map.get(this.suit)>map.get(that.suit)) return +1;
              if(map.get(this.suit)<map.get(that.suit)) return -1;

              else return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
                  HashMap<String, Integer> map = new HashMap<String, Integer>();
                  map.put(""Spades"", 4);
                  map.put(""Hearts"", 3);
                  map.put(""Diamonds"", 2);
                  map.put(""Clubs"", 1);

                  int suit_c1 = map.get(c1.suit);
                  int suit_c2 = map.get(c2.suit);                 
                  
                  if(suit_c1<suit_c2) return -1;
                  else if(suit_c1>suit_c2)return +1;
                  else return 0;
        }
    }   
}

