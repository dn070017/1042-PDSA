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
        int c1face=-1;//隨便給個初始值而已
        int c2face=-1;
        if(this.face.equals(""A""))      c1face = 14;
        else if(this.face.equals(""K"")) c1face = 13;
        else if(this.face.equals(""Q"")) c1face = 12;
        else if(this.face.equals(""J"")) c1face = 11;
        else if(this.face.equals(""10""))c1face = 10;
        else if(this.face.equals(""9"")) c1face = 9;
        else if(this.face.equals(""8"")) c1face = 8;
        else if(this.face.equals(""7"")) c1face = 7;
        else if(this.face.equals(""6"")) c1face = 6;
        else if(this.face.equals(""5"")) c1face = 5;
        else if(this.face.equals(""4"")) c1face = 4;
        else if(this.face.equals(""3"")) c1face = 3;
        else if(this.face.equals(""2"")) c1face = 2;
        if(that.face.equals(""A""))      c2face = 14;
        else if(that.face.equals(""K"")) c2face = 13;
        else if(that.face.equals(""Q"")) c2face = 12;
        else if(that.face.equals(""J"")) c2face = 11;
        else if(that.face.equals(""10""))c2face = 10;
        else if(that.face.equals(""9"")) c2face = 9;
        else if(that.face.equals(""8"")) c2face = 8;
        else if(that.face.equals(""7"")) c2face = 7;
        else if(that.face.equals(""6"")) c2face = 6;
        else if(that.face.equals(""5"")) c2face = 5;
        else if(that.face.equals(""4"")) c2face = 4;
        else if(that.face.equals(""3"")) c2face = 3;
        else if(that.face.equals(""2"")) c2face = 2;
        if(c1face<c2face)return -1;
        if(c1face>c2face)return +1;
        //c1face == c2face點數一樣的情況
        return this.suit.compareTo(that.suit);        
        
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            String c1suit = ((Card)c1).getSuit();
            String c2suit = ((Card)c2).getSuit();
            return c1suit.compareTo(c2suit);//依照花色排序
        }
    }   
    
    public static void main(String[] args){
        Card[] test = new Card[2];
        test[0] = new Card(""A"",""Clubs"");
        test[1] = new Card(""2"",""Hearts"");
        System.out.println(test[1].compareTo(test[0]));
        System.out.println(Card.SUIT_ORDER.compare(test[1], test[0]));
        
        
        
}
}

