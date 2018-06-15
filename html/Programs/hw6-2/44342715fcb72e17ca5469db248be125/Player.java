import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private String[] copy = {""0"",""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] Suit = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""}; 
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
      public int findnum(Card card){
        
        for(int j =0;j<16;j++){
         if(card.getFace().equals(copy[j]))
             return j;
        }
        return -1;
    }
     public int findsuit(Card card){
        for(int j = 0; j<4;j++){
            if(card.getSuit().equals(Suit[j]))
                return j;
        }
        return -1;
    }
    // TODO 
    public int compareTo(Player that) {
        int a,b;
        
        this.cards = this.reang(this.cards);
        that.cards = that.reang(that.cards);
        
        a = this.findtype(this.cards);
        b = that.findtype(that.cards);
        
        if(a>b)
            return 1;
        else if(a<b)
                return -1;
        else{
            if(a==5){
                Card aa;
                Card bb;
                aa = this.confullhouse(this.cards);
                bb = that.confullhouse(that.cards);
               if(aa.findnum()>bb.findnum())
                    return 1;
                if(aa.findnum()<bb.findnum())
                    return 1;
                else{
                    if(aa.findsuit()>bb.findsuit())
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==4){
                if(this.cards[4].findnum()>that.cards[4].findnum())
                    return 1;
                if(this.cards[4].findnum()<that.cards[4].findnum())
                    return 1;
                else{
                    if(this.cards[4].findsuit()>that.cards[4].findsuit())
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==3){
                if(this.cards[4].findnum()>that.cards[4].findnum())
                    return 1;
                if(this.cards[4].findnum()<that.cards[4].findnum())
                    return 1;
                else{
                    if(this.cards[4].findsuit()>that.cards[4].findsuit())
                        return 1;
                    else 
                        return -1;
                }
                
            }
            if(a==2){
                if(this.cards[4].findnum()>that.cards[4].findnum())
                    return 1;
                if(this.cards[4].findnum()<that.cards[4].findnum())
                    return 1;
                else{
                    if(this.cards[4].findsuit()>that.cards[4].findsuit())
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==1){
                Card aa;
                Card bb;
                aa = this.contwopair(this.cards);
                bb = that.contwopair(that.cards);
               if(aa.findnum()>bb.findnum())
                    return 1;
                if(aa.findnum()<bb.findnum())
                    return 1;
                else{
                    if(aa.findsuit()>bb.findsuit())
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==0){
                Card aa;
                Card bb;
                aa = this.conpair(this.cards);
                bb = that.conpair(that.cards);
               if(aa.findnum()>bb.findnum())
                    return 1;
                if(aa.findnum()<bb.findnum())
                    return 1;
                else{
                    if(aa.findsuit()>bb.findsuit())
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==-1){
                if(this.cards[4].findnum()>that.cards[4].findnum())
                    return 1;
                if(this.cards[4].findnum()<that.cards[4].findnum())
                    return 1;
                else{
                    if(this.cards[4].findsuit()>that.cards[4].findsuit())
                        return 1;
                    else 
                        return -1;
                }
            }
        }        
        
        
.
        return 0;
    }
    public Card[] reang(Card[] cards){
       // Card[] after = new Card[5];
        for(int i = 0;i<5;i++){
            int min = i;
            for(int j = i+1;j<5;j++){
                if(cards[min].findnum()>cards[j].findnum()){
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
                else if(cards[min].findnum() == cards[j].findnum() &&
                        cards[min].findsuit()>cards[j].findsuit()){
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
                    
            }
        }
        
        return cards;
    }
    public int findtype(Card[] cards){       
        int[] fa = new int[5];
        int[] su = new int[5];       
        for(int i =0 ;i<5;i++){
            fa[i] = cards[i].findnum();            
            su[i] = cards[i].findsuit();
        }        
                
        if(fa[0] ==fa[1] && fa[0] == fa[2] && fa[3] == fa[4])
            return 5;
        else if(fa[0] ==fa[1] && fa[2] == fa[3] && fa[2] == fa[4])
            return 5;
        else if(su[0] == su[1] && su[0] == su[2] && su[0] == su[3] && su[0] == su[4])
            return 4;
        else if(fa[0]+1==fa[1]&&fa[0]+2==fa[2]&&fa[0]+3==fa[3]
                &&fa[0]+4==fa[4])
            return 3;
        else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==11&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==5&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 2;
         else if(fa[0]==fa[1]&&fa[2]==fa[3])
             return 1;
         else if(fa[0]==fa[1]&&fa[3]==fa[4])
             return 1;
         else if(fa[1]==fa[2]&&fa[3]==fa[4])
             return 1;
         else if(fa[0]==fa[1])
             return 0;
         else if(fa[1]==fa[2])
             return 0;
         else if(fa[2]==fa[3])
             return 0;
         else if(fa[3]==fa[4])
             return 0;
        else
             return -1;
    }
    
     public Card confullhouse(Card[] that){
          int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = cards[i].findnum();                     
        } 
          if(fa[0]==fa[1]&&fa[0]==fa[2])
              return that[2];
          else
         return that[4];
     }
     public Card contwopair(Card[] that){
         int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = cards[i].findnum();                     
        } 
          if(fa[2]==fa[3])
              return that[3];
          else
              return that[4];
     }
     public Card conpair(Card[] that){
          int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = cards[i].findnum();                     
        } 
          if(fa[0]==fa[1])
              return that[1];
          else if(fa[1]==fa[2])
              return that[2];
          else if(fa[2]==fa[3])
              return that[3];
          else
              return that[4];
     }
    
   public static void main(String[] args){
       
     } 
}


