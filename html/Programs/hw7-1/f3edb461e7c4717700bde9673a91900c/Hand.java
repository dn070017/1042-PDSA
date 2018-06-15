
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards = cards;
        return;
    }

    
  
  public int[] handcards(Card[] cards){ //回傳 Value陣列
        int[] Value = new int[6];
        for (int x =0;x<6;x++){
            Value[x]=0;
        }
        
//        int sameCards=1;
//        int groupRank=0;
        int[] ranks = new int[14];
        for (int x=0; x<=13; x++){
            ranks[x]=0; //zero the contents of the array
       }
// 交作業時，要獨立交Player.java，裡面不能用Card.java裡面自創的function(changeFace)
//        for (int x=0; x<5; x++){ 
//            if (cards[x].getFace().equals(""A""))
//                    ranks[1]++;
//            else
//            ranks[cards[x].changeFace(cards[x])]++;
//        }
          for (int x=0; x<5; x++){
              switch (cards[x].getFace()){  // '': char, """": String 記得要加break;否則會一行一行執行下去
                  case(""A""):
                      ranks[1]++;
                      break;
                  case(""J""):
                      ranks[11]++;
                      break;
                  case(""Q""):
                      ranks[12]++;
                      break;
                  case(""K""):
                      ranks[13]++;
                      break;
                  default:
                      //System.out.println(cards[x].getFace());
                      ranks[Integer.parseInt(cards[x].getFace())]++;
              }
          }
        
        
//        for (int x=13; x>=1; x--){
//            if (ranks[x]>sameCards){
//                sameCards=ranks[x]; // 找最多一樣的數字
//                groupRank=x;
//            }
//        }
        
          //flush
        boolean flush = true;  
        for (int i = 0;i<4;i++){
            if (!cards[i].getSuit().equals(cards[i+1].getSuit()))
                    flush = false;
        }
  
        
        // largeGroupRank, smallGroupRank
        int sameCards=1;
        int sameCards2=1;
        int largeGroupRank=0,smallGroupRank=0;
        for (int x=13; x>=1; x--){
            if (ranks[x]>sameCards)
            {
                if (sameCards!=1){ //if sameCards was not the default(預設) value
                    sameCards2 = sameCards; //讓原本sameCards變次要的
                    smallGroupRank = largeGroupRank; //讓原本laregeGroupRank變次要的
                }
                sameCards = ranks[x];
                largeGroupRank = x;
                
            }else if (ranks[x]>sameCards2){ //考慮2-pair
                sameCards2 = ranks[x];
                smallGroupRank = x;  //此時不知道largeGroupRank與smallGroupRank誰大誰小
            }
        }
        if (largeGroupRank==1) largeGroupRank=largeGroupRank+13; //讓A變最大
        if (smallGroupRank==1) smallGroupRank=smallGroupRank+13; //讓A變最大
        
        
        //Straight
        int topStraightValue=0;
        boolean straight=false;     
        for (int x=1; x<=9; x++){
            if (ranks[x]==1 && ranks[x+1]==1 && ranks[x+2]==1 && ranks[x+3]==1 && ranks[x+4]==1){
                straight = true;
                topStraightValue=x+4;
                break;
            }
        }
        if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[1]==1){
            straight = true;
            topStraightValue=14;
        }

        
         int[] orderedRanks = new int[5];
         int index=0;
         if (ranks[1]==1){ //if ace, run this before because ace is highest card
         orderedRanks[index]=14;
         index++;
         }
         for (int x=13; x>=2; x--){ //從大到小找
             if (ranks[x]==1){
                 orderedRanks[index]=x;
                 index++;
             }
         }
         
         if ( sameCards==1 ) {
             Value[0]=1;   // no pair
             Value[1]=orderedRanks[0]; //the first determining factor is the highest card,
             Value[2]=orderedRanks[1]; //then the next highest card,
             Value[3]=orderedRanks[2]; //and so on
             Value[4]=orderedRanks[3];
             Value[5]=orderedRanks[4];
         }
         if (sameCards==2 && sameCards2==1) {
             Value[0]=2;  // 1 pair
             Value[1]=largeGroupRank;   //rank of pair
.
             Value[3]=orderedRanks[1];
             Value[4]=orderedRanks[2];
         }
         if (sameCards==2 && sameCards2==2) { //此時largeGroup的數目 = smallGroup的數目
             Value[0]=3;//two pair
             Value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
             Value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank; //rank of smaller pair
             Value[3]=orderedRanks[0];  //extra card
         }
         if (straight) {
             Value[0]=4; // straight
             Value[1]=topStraightValue;  //if we have two straights, the one with the highest top cards wins

         }
         if (flush) {
             Value[0]=5; // flush
             Value[1]=orderedRanks[0]; //tie determined by ranks of cards
             Value[2]=orderedRanks[1];
             Value[3]=orderedRanks[2];
             Value[4]=orderedRanks[3];
             Value[5]=orderedRanks[4];
         }
         if (sameCards==3 && sameCards2==2)  {
             Value[0]=6;// full house
             Value[1]=largeGroupRank;
             Value[2]=smallGroupRank;
         }
         return Value;
    }
    
    // TODO
    public int compareTo(Hand that) {
.
        // P1,P2 分別為this,that的Value值
           int[] P1 = this.handcards(this.cards);
           //System.out.println(P1[0]+"" ""+P1[1]);
           int[] P2 = that.handcards(that.cards);
           //System.out.println(P2[0]+"" ""+P2[1]);
          
           if (P1[0]>P2[0]) return 1;
           else if (P1[0]==P2[0])
               switch(P1[0]){
                   case 1:
                       return P1[1]>P2[1]? 1:-1;
                   case 2:
                       return P1[1]>P2[1]? 1:-1;
                   case 3:
                       return P1[1]>P2[1]? 1:-1;
                   case 4:
                       return P1[1]>P2[1]? 1:-1;
                   case 5:
                       return P1[1]>P2[1]? 1:-1;
                   case 6:
                       return P1[1]>P2[1]? 1:-1;
               }
           else 
               return -1;
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

