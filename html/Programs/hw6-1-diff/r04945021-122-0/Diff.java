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
    
   public int CardID(String CC) {
 int CardID=0;
       switch(CC){
           case ""A"":
               CardID= 14;
               break;
           case ""K"":
               CardID= 13;
               break;
           case ""Q"":
               CardID= 12;
               break;
           case ""J"":
               CardID= 11;
               break;
           case ""Spades"":
               CardID= 4;
               break;
           case ""Hearts"":
               CardID=3;
               break;
           case ""Diamonds"":
               CardID=2;
               break;
           case ""Clubs"":
               CardID=1;
               break;
           default:
               CardID=Integer.parseInt(CC);
               break;                
       }
       return CardID;   
   }
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int CardSuitThis = CardID(this.suit);
        int CardSuitThat = CardID(that.suit);
        int CardFaceThis = CardID(this.face);
        int CardFaceThat = CardID(that.face);
        
        if (CardFaceThat > CardFaceThis) {
            return -1;
        } else if (CardFaceThat < CardFaceThis) {
            return 1;
        } else {
            if (CardSuitThat > CardSuitThis) {
                return -1;
            } else if (CardSuitThat < CardSuitThis) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int CardI(String CC) {
 int CardID=0;
       switch(CC){
           case ""A"":
               CardID= 14;
               break;
           case ""K"":
               CardID= 13;
               break;
           case ""Q"":
               CardID= 12;
               break;
           case ""J"":
               CardID= 11;
               break;
           case ""Spades"":
               CardID= 4;
               break;
           case ""Hearts"":
               CardID=3;
               break;
           case ""Diamonds"":
               CardID=2;
               break;
           case ""Clubs"":
               CardID=1;
               break;
           default:
               CardID=Integer.parseInt(CC);            
               break;                
       }
       return CardID;   
   }
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

            int C1Suit=CardI(c1.suit);
            int C2Suit=CardI(c2.suit);
            if(C1Suit>C2Suit)
                return -1;
            else if (C1Suit<C2Suit)
                return 1;
            else
                    return 0;
        }
    }   
    
//    public static void main(String[] args) {
//        Card[] test = new Card[2];
//        test[0] = new Card(""A"",""Clubs"");
//        test[1] = new Card(""A"",""Hearts"");
//        System.out.println(test[1].compareTo(test[0]));
//        System.out.println(Card.SUIT_ORDER.compare(test[1], test[0]));
//    }
}


