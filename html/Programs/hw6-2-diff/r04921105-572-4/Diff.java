
import java.util.Arrays;

public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
     
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     
    // TODO 
    public int compareTo(Player that) {
        if(cardtorank(this.cards)>cardtorank(that.cards))
            return 1;
        if(cardtorank(this.cards)<cardtorank(that.cards))
            return -1;
        if(cardtorank(this.cards)==cardtorank(that.cards)){
            if(cards[1].getFace().equals(cards[2].getFace())&&cards[3].getFace().equals(cards[4].getFace())){
                if(facetonum(this.cards[1])>facetonum(that.cards[1])||facetonum(this.cards[3])>facetonum(that.cards[3])||facetonum(this.cards[1])>facetonum(that.cards[3])||facetonum(this.cards[3])>facetonum(that.cards[1]))
                    return 1;   
                else 
                    return -1;
              
                
            }
               
             if(cards[0].getFace().equals(cards[1].getFace())&&cards[3].getFace().equals(cards[4].getFace())){
                if(facetonum(this.cards[1])>facetonum(that.cards[1])||facetonum(this.cards[3])>facetonum(that.cards[3])||facetonum(this.cards[1])>facetonum(that.cards[3])||facetonum(this.cards[3])>facetonum(that.cards[1]))
                    return 1;   
                else 
                    return -1;
              
                
            }
              if(cards[0].getFace().equals(cards[1].getFace())&&cards[3].getFace().equals(cards[4].getFace())){
                if(facetonum(this.cards[1])>facetonum(that.cards[1])||facetonum(this.cards[3])>facetonum(that.cards[3])||facetonum(this.cards[1])>facetonum(that.cards[3])||facetonum(this.cards[3])>facetonum(that.cards[1]))
                    return 1;   
                else 
                    return -1;
              
                
            }
         
            
        }
.
        
        return 0;
    }
    /*
    如果有手牌的等級是一樣的 (e.g 同樣拿到 1-Pair) 判斷的基準如下：
1. High Card：檢查五張牌中最大的
2. 1-Pair：檢查形成 Pair 的兩張牌中最大的
3. 2-Pair：檢查行程 2-Pair 的四張牌中最大的
4. Straight：檢查五張牌中最大張的（除了 A 2 3 4 5 是最小的以外）
5. Flush：檢查五張牌中最大張的
6. Full House：檢查 3 張數字相同的牌中最大的
    */
    
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


