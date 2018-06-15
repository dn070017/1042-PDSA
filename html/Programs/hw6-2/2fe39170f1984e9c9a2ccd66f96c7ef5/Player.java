
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
/*
關於作業 6-2，如果有手牌的等級是一樣的 (e.g 同樣拿到 1-Pair) 判斷的基準如下：
1. High Card：檢查五張牌中最大的
2. 1-Pair：檢查形成 Pair 的兩張牌中最大的
3. 2-Pair：檢查行程 2-Pair 的四張牌中最大的
4. Straight：檢查五張牌中最大張的（除了 A 2 3 4 5 是最小的以外）
5. Flush：檢查五張牌中最大張的
6. Full House：檢查 3 張數字相同的牌中最大的
不需要考慮同花順，如果有同花順的情況發生，當作同花判斷就好。
*/
    
    // TODO 
    public int compareTo(Player that) {
.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(play2rank(this) > play2rank(that)) return 1;
        else if(play2rank(this) < play2rank(that)) return -1;
        else return 0;             

    }
    
    public static int play2rank(Player c){
    int result = 0;
//================ Check Pairs =====================    

    int pi = 5;
    for(int i=4;i > 1;i--){
        int a = face2num(c.cards[i]);
        int b = face2num(c.cards[i-1]);        
        if(a == b){
            result = 1;
            pi = i;
        }    
    }

    if(result == 1){
        for(int i=0;i < 4;i++){
        int a = face2num(c.cards[i]);
        int b = face2num(c.cards[i+1]);        
        if(a == b && !(i==pi) && !(i+1 == pi)){
            result = 2;
        }    
        }        
    }    
 


//================ Check Strait ==================== 
    int strait = 1;
    for(int i=0;i < 4;i++){
        int a = face2num(c.cards[i]);
        int b = face2num(c.cards[i+1]);
        if(a + 1 == b);
        else{
            strait = 0;
        }
    }
    if(strait == 1) result = 4;
//================ Check Flush =====================    
    int flush = 1;
    for(int i=1;i < 5;i++){
        if(c.cards[0].getSuit().equals(c.cards[i].getSuit())==false){
            flush = 0;
        }
    }
    
    if(flush == 1) result = 5;

//================ Check Full House =================      
    
    if(c.cards[0].getFace().equals(c.cards[1].getFace()) && c.cards[1].getFace().equals(c.cards[2].getFace()) && c.cards[3].getFace().equals(c.cards[4].getFace())) result = 6;
    
    if(c.cards[4].getFace().equals(c.cards[3].getFace()) && c.cards[3].getFace().equals(c.cards[2].getFace()) && c.cards[1].getFace().equals(c.cards[0].getFace())) result = 6;   
  
      
    

    
    
    return result;
    }
    
        public static int face2num(Card c){
        switch(c.getFace()){
            case(""A""):
                return 13;
            case(""J""):
                return 10;
            case(""Q""):
                return 11;
            case(""K""):
                return 12;
            default:
                return (Integer.parseInt(c.getFace())-1);
        }
    }
        
    public static int suit2num(Card c){
        switch(c.getSuit()){
            case(""Spades""):
                return 4;
            case(""Hearts""):
                return 3;
            case(""Diamonds""):
                return 2;
            case(""Clubs""):
                return 1;
            default:
                return 0;
        }   
    } 
    
}

