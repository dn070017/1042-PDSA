
import java.util.Arrays;





public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        return;
    }

    // TODO
    public int compareTo(Hand that) {
        if(cardtorank(this.cards)>cardtorank(that.cards))
            return 1;
        if(cardtorank(this.cards)<cardtorank(that.cards))
            return -1;
        else
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
    public static int cardtorank(Card[] cards){
        Arrays.sort(cards);
        
      int rank=0;
        //pair
        for(int i=4;i>0;i--){
            if (cards[i].getFace().equals(cards[i-1].getFace()))
                rank= 1;
        }
        //2-pair
        if(cards[0].getFace().equals(cards[1].getFace())&&cards[2].getFace().equals(cards[3].getFace()))
            rank=2;
        if(cards[0].getFace().equals(cards[1].getFace())&&cards[3].getFace().equals(cards[4].getFace()))
            rank=2;
        if(cards[1].getFace().equals(cards[2].getFace())&&cards[3].getFace().equals(cards[4].getFace()))
            rank=2;
        
        
        //順子
        boolean s=true;
        for(int i=4;i>0;i--){
                       
            int a=facetonum(cards[i]);
            int b=facetonum(cards[i-1]);
            if(a!=b+1)
                s=false;
            /*
            if(cards[i].getFace().equals(cards[i-1].getFace()));
                else s=false;    
            */
        }
        if (s== true)
            rank =3;
        //同花
        if(cards[0].getSuit().equals(cards[1].getSuit())&&cards[1].getSuit().equals(cards[2].getSuit())&&
                cards[2].getSuit().equals(cards[3].getSuit())&&cards[3].getSuit().equals(cards[4].getSuit()))
                rank=4;
        
        //葫蘆
        
            if(cards[0].getFace().equals(cards[1].getFace())&&cards[1].getFace().equals(cards[2].getFace())){
                if(cards[3].getFace().equals(cards[4].getFace()))
                    rank=5;
            }
            if(cards[2].getFace().equals(cards[3].getFace())&&cards[3].getFace().equals(cards[4].getFace())){
                if(cards[0].getFace().equals(cards[1].getFace()))
                    rank=5;
            }
        
    
        
        
        
        return rank;
    }
    
    
      
    public static int facetonum(Card c){
        switch(c.getFace()){
            case(""A""):
              return 14;
            case(""K""):
                return 13;
            case(""Q""):
                return 12;
            case(""J""):
                return 11;
            default:
                return Integer.parseInt(c.getFace());
                                      
        }
    }
}

