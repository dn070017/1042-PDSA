
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Lab304
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
        // (you must consider both face and suit)
        int  this_point=0;
        int  that_point=0;
        
        if(this.face.equals(""A"")){this_point=14;}
        else if(this.face.equals(""K"")){this_point=13;}
        else if(this.face.equals(""Q"")){this_point=12;}
        else if(this.face.equals(""J"")){this_point=11;}
        else{ this_point=Integer.parseInt(this.face);}
        
         if(that.face.equals(""A"")){that_point=14;}
         else if(that.face.equals(""K"")){that_point=13;}
         else if(that.face.equals(""Q"")){that_point=12;}
         else if(that.face.equals(""J"")){that_point=11;}
         else{
         that_point=Integer.parseInt(that.face);}
        
        if(this_point>that_point){return +1;}
        if(this_point<that_point){return -1;}
        if(this_point==that_point){
        int this_suit=0;
        int that_suit=0;
         if(this.suit.equals(""Spades"")){this_suit=4;}
        else if(this.suit.equals(""Hearts"")){this_suit=3;}
        else if(this.suit.equals(""Diamonds"")){this_suit=2;}
        else if(this.suit.equals(""Clubs"")){this_suit=1;}
         
         if(that.suit.equals(""Spades"")){that_suit=4;}
         else if(that.suit.equals(""Hearts"")){that_suit=3;}
         else if(that.suit.equals(""Diamonds"")){that_suit=2;}
         else if(that.suit.equals(""Clubs"")){that_suit=1;}
         
         if(this_suit>that_suit){return +1;}
         if(this_suit<that_suit){return -1;}
        }
        return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
        int this_suit=0;
        int that_suit=0;
         if(c1.suit.equals(""Spades"")){this_suit=4;}
        else if(c1.suit.equals(""Hearts"")){this_suit=3;}
        else if(c1.suit.equals(""Diamonds"")){this_suit=2;}
        else if(c1.suit.equals(""Clubs"")){this_suit=1;}
         
         if(c2.suit.equals(""Spades"")){that_suit=4;}
         else if(c2.suit.equals(""Hearts"")){that_suit=3;}
         else if(c2.suit.equals(""Diamonds"")){that_suit=2;}
         else if(c2.suit.equals(""Clubs"")){that_suit=1;}
         
         if(this_suit>that_suit){return +1;}
         if(this_suit<that_suit){return -1;}
            
            return 0;
        }
    }   
    
    
     public static void main(String[] args) throws Exception{

     /*   try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                
                player.setCards(cardsArray);
                // Arrays.sort(cardsArray, cardsArray[2]SuitOrder);
              System.out.println( cardsArray[4].compareTo(cardsArray[3]));
               
            }

            Arrays.sort(playerArray);
            System.out.println(playerArray[playerCount-3].getName());
        }*/
    }
      
}


