import java.io.FileReader;
import java.util.Arrays;
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
        int thif = 0;
        int thaf = 0;
        if(this.face.equals(""A"")) thif=14;
          else if(this.face.equals(""K"")) thif=13;
          else if(this.face.equals(""Q"")) thif=12;
          else if(this.face.equals(""J"")) thif=11;
          else thif = Integer.parseInt(this.face);
        if(that.face.equals(""A"")) thaf=14;
          else if(that.face.equals(""K"")) thaf = 13;
          else if(that.face.equals(""Q"")) thaf = 12;
          else if(that.face.equals(""J"")) thaf = 11;
          else thaf = Integer.parseInt(that.face);
        if (thif > thaf) return +1;
        if (thif < thaf) return -1;
        //在suit_order這個class下做compare這件事
        return SUIT_ORDER.compare(this, that);
        //return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            //someone spades Diamonds   Spades > Hearts > Diamonds > Clubs
            if (c1.suit==""Spades""|c2.suit==""Spades"") {
                if (c1.suit==""Spades""&c2.suit!=""Spades"") return +1;
                else if (c1.suit!=""Spades""&c2.suit==""Spades"") return -1;}
            else if (c1.suit==""Hearts""|c2.suit==""Hearts"") {
                if (c1.suit==""Hearts""&c2.suit!=""Hearts"") return +1;
                else if (c1.suit!=""Hearts""&c2.suit==""Hearts"") return -1;}
            else if (c1.suit==""Diamonds""|c2.suit==""Diamonds"") {
                if(c1.suit==""Diamonds""&c2.suit!=""Diamonds"") return +1;
                else if(c1.suit!=""Diamonds""&c2.suit==""Diamonds"") return -1;
            }
            return 0;
        }
    }   
//    
//        public static void main(String[] args){
//        //
//           Card[] test = new Card[2];
//           test[0]= new Card(""10"",""Spades"");
//           test[1]= new Card(""Q"",""Clubs"");        
//           //Arrays.sort(test);
//           System.out.println(test[0].face);
//           System.out.println(test[1].face);
//           System.out.println(test[0].compareTo(test[1]));
//}
}

