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
        switch(this.face){
            case ""A"":
                switch(that.face){
                    case ""A"": return (this.SUIT_ORDER.compare(this,that));
                    default: return -1;
                }
            case ""K"":
                switch(that.face){
                    case ""A"": return 1;
                    case ""K"": return (this.SUIT_ORDER.compare(this,that));
                    default: return -1;
                }
            case ""Q"":
                switch(that.face){
                    case ""A"": return 1;
                    case ""K"": return 1;
                    case ""Q"": return (this.SUIT_ORDER.compare(this,that));
                    default: return -1;
                }
            case ""J"":
                switch(that.face){
                    case ""A"": return 1;
                    case ""K"": return 1;
                    case ""Q"": return 1;
                    case ""J"": return (this.SUIT_ORDER.compare(this,that));
                    default: return -1;
                }
            default:
                switch(that.face){
                    case ""A"": return 1;
                    case ""K"": return 1;
                    case ""Q"": return 1;
                    case ""J"": return 1;
                    default: 
                        if(Integer.parseInt(this.face)<Integer.parseInt(that.face))return 1;
                        else if(Integer.parseInt(this.face)==Integer.parseInt(that.face))return (this.SUIT_ORDER.compare(this,that));
                        else return -1;
                }
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            if(c1.suit==c2.suit){
                return 0;              
            }
            else{
                if(c1.suit==""Spades""){
                    return -1;
                }
                else if(c1.suit==""Hearts"" && c2.suit!=""Spades""){
                    return -1;
                }
                else if(c1.suit==""Diamonds"" && (c2.suit!=""Spades"" || c2.suit!=""Hearts"")){
                    return -1;
                }
                else return 1;
            }
        }
    }   
    public static void main(String[] args){
        Card[] test= new Card[2];
        test[0] = new Card(""A"",""Clubs"");
        test[1] = new Card(""6"",""Hearts"");
        System.out.println(test[0].compareTo(test[1]));
        System.out.println(Card.SUIT_ORDER.compare(test[0],test[1]));
    }
}
