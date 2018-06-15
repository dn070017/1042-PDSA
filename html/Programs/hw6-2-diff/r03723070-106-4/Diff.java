import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int [] playerrank = new int[5];//[pair,Full_House,flush,stright,,bignumber]
    private int playerorder = 0;//[kind,big#]
    private Card playerbig;
    private String [] Oface = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A""};
    private int [] cardfacesize = new int [5];
        int pair = 0;
        int stright = 1;
        Card bigstright=cards[4];
        int flush = 1;
        Card bigflush = cards[4];
        
        int FullHouse =0;
        Card bigFullHouse;
        
        Card big2p;
        
        int kind = 0;//(FullHouse=5,flush=4,stright=3,2pair=2,1pair=1,HC=0)
    
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
        Arrays.sort(cards);
        this.cards = cards;
        //High Card
        //
        //System.out.println(cards[0].getFace());
        for (int i = 0;i<13;i++){
            for(int j = 0;j<5;j++){
                if(Oface[i].equals(cards[j].getFace())){
                cardfacesize[j] =i;
            }
                
            }
        }
        int pairnum = 0;
       
        //paiar
        for(int i =0;i<4;i++){
            if(cardfacesize[i] == cardfacesize[i+1]){
                big2p =cards[i];
                pairnum+=1;
                //System.out.printf(""pairnum is %d\n"",pairnum);
            }
            if(cardfacesize[i] != cardfacesize[i+1] || i==3){
                if((pairnum+1)%2==0) {
                    if(big2p.compareTo(cards[i])<0){
                        big2p =cards[i];
                    }
                    pair+=(pairnum+1)/2;
                    //System.out.printf(""pair is %d\n"",pair);
                }
                if((pairnum+1)%3==0){
                    bigFullHouse = cards[i];
                    FullHouse+=(pairnum+1)/3;
                    //System.out.printf(""flush is %d\n"",flush);
                }
                pairnum = 0;
               if(!(cards[i].getSuit()).equals(cards[i+1].getSuit())){
                   //System.out.printf(""flush is %d\n"",flush);
                   flush=0;
               }
            }
        }
        //stright
        for (int i =0;i<4;i++){
            if(cardfacesize[i+1] - cardfacesize[i] !=1){
                stright=0;
            }
        }
        if(cardfacesize[0]==0&&cardfacesize[1]==1&&cardfacesize[2]==2&&cardfacesize[3]==3&&cardfacesize[4]==12){
                bigstright=cards[0];
                stright=1;
            }

        //allocate the rank
        {   //FullHouse
            if(pair==1&&FullHouse==1){
                kind = 5;
                playerorder=kind;
                playerbig=bigFullHouse;        
            }
            //flish
            else if(flush==1){
                kind = 4;
                playerorder=kind;
                playerbig=bigflush; 
            }
            //stright
            else if(stright==1){
                kind = 3;
                playerorder=kind;
                playerbig=bigstright; 
            }
            //2pair
            else if(pair==2){
                kind = 2;
                playerorder=kind;
                playerbig=big2p; 
            }
            else if(pair==1){
                kind = 1;
                playerorder=kind;
                playerbig=big2p; 
            }
            else {
                kind = 0;
                playerorder=kind;
                playerbig=cards[4]; 
            }
            
        }
            //System.out.println(this.name);
            //System.out.printf(""FullHouse is %d\n"",FullHouse);
            //System.out.printf(""pair is %d\n"",pair);
            //System.out.printf(""flush is %d\n"",flush);
            //System.out.printf(""kind is %d\n"",kind);
       
        
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        if(this.kind>that.kind) return 1;
        else if(this.kind<that.kind) return -1;
        else {
            if(this.playerbig.compareTo(that.playerbig)>0)return 1;
            if(this.playerbig.compareTo(that.playerbig)<0)return -1;
        }
        return 0;
    }
    /*public int pairnum( that) {
.
        return 0;
    }*/
    
}


