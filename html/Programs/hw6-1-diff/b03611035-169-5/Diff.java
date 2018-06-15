import java.util.Arrays;
import java.util.Comparator;
public class Card implements Comparable<Card> {
    
    public void main(){
        Card gg=new Card(""A"",""Spades"");
    }
    
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
    public int compareTo(Card that) {//前<後-1 前>後1 
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        //先檢查face再檢查suit
        //先把face換成int
        int thisface;
        int thatface;
        if(this.face.equals(""A"")) thisface=14;
        else if(this.face.equals(""J"")) thisface=11;
        else if(this.face.equals(""Q"")) thisface=12;
        else if(this.face.equals(""K"")) thisface=13;
        else thisface=Integer.parseInt(this.face);
        if(that.face.equals(""A"")) thatface=14;
        else if(that.face.equals(""J"")) thatface=11;
        else if(that.face.equals(""Q"")) thatface=12;
        else if(that.face.equals(""K"")) thatface=13;
        else thatface=Integer.parseInt(that.face);
        //檢查face
        if(thisface>thatface) return 1;
        else if(thisface<thatface) return -1;
        else{//檢查suit            
            int face1 = 0;
            int face2 = 0;
            Card c1=this;
            Card c2=that;
            if(""Spades"".equals(c1.suit))face1=4;
            else if(""Hearts"".equals(c1.suit))face1=3;
            else if(""Diamonds"".equals(c1.suit))face1=2;
            else if(""Clubs"".equals(c1.suit))face1=1;
            if(""Spades"".equals(c2.suit))face2=4;
            else if(""Hearts"".equals(c2.suit))face2=3;
            else if(""Diamonds"".equals(c2.suit))face2=2;
            else if(""Clubs"".equals(c2.suit))face2=1;
            if(face1>face2)return 1;
            else if(face1<face2)return -1;
            else return 0;
        }
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int face1 = 0;
            int face2 = 0;
            if(""Spades"".equals(c1.suit))face1=4;
            else if(""Hearts"".equals(c1.suit))face1=3;
            else if(""Diamonds"".equals(c1.suit))face1=2;
            else if(""Clubs"".equals(c1.suit))face1=1;
            if(""Spades"".equals(c2.suit))face2=4;
            else if(""Hearts"".equals(c2.suit))face2=3;
            else if(""Diamonds"".equals(c2.suit))face2=2;
            else if(""Clubs"".equals(c2.suit))face2=1;
            if(face1>face2)return 1;
            else if(face1<face2)return -1;
            else return 0;
        }
    }   
}

