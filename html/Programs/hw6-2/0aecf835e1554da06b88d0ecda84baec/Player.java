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
.
        String[] comsuit = { ""Clubs"" , ""Diamonds"", ""Hearts"", ""Spades""};
        String[] comface = { ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        
        
        int [] thissamecaed = {0,0,0,0};
        int [] thatsamecaed = {0,0,0,0};
        
        int thissamesuit = 0;
        int thatsamesuit = 0;
        
        int thisstraight = 0; 
        int thatstraight = 0;
        
        int thisMVP = 0; 
        int thatMVP = 0;
        
        int [] thishold = {0,0,0,0,0,0};
        int [] thathold = {0,0,0,0,0,0};
        
        int thiscard = 0;
        int thatcard = 0;
        //======================================
        for(int i=0;i<5;i++){
            for(int j=0;j<13;j++){
                   if((this.cards[0].getFace()).equals(comface[j])){
                     thishold[i]=j;
                   }
                }
        }
        
        for(int i=0;i<4;i++){
            if((this.cards[i].getFace()).equals(this.cards[i+1].getFace())){
              thissamecaed[i]++;
            }
            //flush
            if((this.cards[i].getSuit()).equals(this.cards[i+1].getSuit())){
              thissamesuit++;
            }
            //straight 
            if((thishold[i]+1)%13==(thishold[i+1])%13){
              thisstraight++;
            }
        }
        //=============================================
        for(int i=0;i<5;i++){
            for(int j=0;j<13;j++){
                   if((that.cards[i].getFace()).equals(comface[j])){
                     thathold[i]=j;
                   }
                }
        }
        
        for(int i=0;i<4;i++){
            if((that.cards[i].getFace()).equals(that.cards[i+1].getFace())){
              thatsamecaed[i]++;
            }
            //flush
            if((that.cards[i].getSuit()).equals(that.cards[i+1].getSuit())){
              thatsamesuit++;
            }
            //straight 
            
            if((thathold[i]+1)%13==(thathold[i+1])%13){
              thatstraight++;
            }
        }
        
        
        
        
        //FULL HOUSE
        if((thissamecaed[0]==1&&thissamecaed[1]==1&&thissamecaed[3]==1))
        {thisMVP = 2; thiscard=0;}
        if((thissamecaed[0]==1&&thissamecaed[2]==1&&thissamecaed[3]==1))
        {thisMVP = 4; thiscard=0;}
        //flush
        else if(thissamesuit==4)
        {thisMVP = 4; thiscard=1;}
        //straight
        else if(thisstraight==4)
        {
            thiscard=2;
            if(thishold[4]==13&&thishold[0]==0)
                thisMVP = 3;
            else 
                thisMVP = 4; 
        }
        //two pair
        else if((thissamecaed[0]==1&&thissamecaed[3]==1))
        {thisMVP=4; thiscard=3;}
        else if((thissamecaed[1]==1&&thissamecaed[3]==1))
        {thisMVP=4; thiscard=3;}
        else if((thissamecaed[0]==1&&thissamecaed[2]==1))
        {thisMVP=3; thiscard=3;}
        
        //one pair
        else if((thissamecaed[0]==1))
        {thisMVP=1; thiscard=4;}
        else if((thissamecaed[1]==1))
        {thisMVP=2; thiscard=4;}
        else if((thissamecaed[2]==1))
        {thisMVP=3; thiscard=4;}
        else if((thissamecaed[3]==1))
        {thisMVP=4; thiscard=4;}
        //high card
        else
        {thisMVP = 4; thiscard=5;}
        //=================================================
        //FULL HOUSE
        if((thatsamecaed[0]==1&&thatsamecaed[1]==1&&thatsamecaed[3]==1))
        {thatMVP = 2; thatcard=0;}
        if((thatsamecaed[0]==1&&thatsamecaed[2]==1&&thatsamecaed[3]==1))
        {thatMVP = 4; thatcard=0;}
        //flush
        else if(thatsamesuit==4)
        {thatMVP = 4; thatcard=1;}
        //straight
        else if(thatstraight==4)
        {
            thatcard=2;
            if(thathold[4]==13&&thathold[0]==0)
                thatMVP = 3;
            else 
                thatMVP = 4; 
        }
        //two pair
        else if((thatsamecaed[0]==1&&thatsamecaed[3]==1))
        {thatMVP=4; thatcard=3;}
        else if((thatsamecaed[1]==1&&thatsamecaed[3]==1))
        {thatMVP=4; thatcard=3;}
        else if((thatsamecaed[0]==1&&thatsamecaed[2]==1))
        {thatMVP=3; thatcard=3;}
        
        //one pair
        else if((thatsamecaed[0]==1))
        {thatMVP=1; thatcard=4;}
        else if((thatsamecaed[1]==1))
        {thatMVP=2; thatcard=4;}
        else if((thatsamecaed[2]==1))
        {thatMVP=3; thatcard=4;}
        else if((thatsamecaed[3]==1))
        {thatMVP=4; thatcard=4;}
        //high card
        else
        {thatMVP = 4; thatcard=5;}

//        System.out.println(this.getName()); 
//        //System.out.println(thisstraight);
//        System.out.println(thiscard);    
//        
//        
//        System.out.println(that.getName()); 
//        System.out.println(thatcard);
        if(thiscard<thatcard)
            return 1;
        else if(thiscard>thatcard)
            return -1;
        else if((thiscard==thatcard)&&thiscard==0)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==1)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==2)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==3)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==4)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==5)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else
            return 0;
    }
}


