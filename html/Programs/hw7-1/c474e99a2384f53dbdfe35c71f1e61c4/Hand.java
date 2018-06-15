
import java.util.Arrays;


public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 
    
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards=cards;
        return;
    }

    // TODO
    public int compareTo(Hand that) {
                
        Arrays.sort(this.getCards());
        Arrays.sort(that.getCards());
        
        int thisSET = GetOrder(this.cards);
        int thatSET = GetOrder(that.cards);
        
        if(thisSET>thatSET)return +1;
        else if(thatSET>thisSET)return -1;
        else if(thisSET == thatSET){
                if(thisSET==0){ 
                    if(GetPoint(this.cards[4])>GetPoint(that.cards[4]))return+1;
                    else return-1;
                }
                else if(thisSET==1){
                    if(OnePair(this.cards)[1]>OnePair(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==2){
                    if(TwoPair(this.cards)[1]>TwoPair(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==3){
                    if(Straight(this.cards)[1]>Straight(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==4){
                    if(Flush(this.cards)[1]>Flush(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==5){
                    if(FullHouse(this.cards)[1]>FullHouse(that.cards)[1])return+1;
                    else return-1;
                }
                else return 0;
               
        }
.
        else  return 0;
       
    }
//***************************** Function for sort*****************************\\
//----------------------------------------------------------------------------\\
    public int FacetoINT(String a){
    int facepoint = 0;
    if(a.equals(""A""))facepoint = 130;
    else if(a.equals(""2""))facepoint = 10;
    else if(a.equals(""3""))facepoint = 20;
    else if(a.equals(""4""))facepoint = 30;
    else if(a.equals(""5""))facepoint = 40;
    else if(a.equals(""6""))facepoint = 50;
    else if(a.equals(""7""))facepoint = 60;
    else if(a.equals(""8""))facepoint = 70;
    else if(a.equals(""9""))facepoint = 80;
    else if(a.equals(""10""))facepoint = 90;
    else if(a.equals(""J""))facepoint = 100;
    else if(a.equals(""Q""))facepoint = 110;
    else if(a.equals(""K""))facepoint = 120;
    return facepoint;
} 
    public int SuittoINT(String b){
    int suitpoint = 0;
    if(b.equals(""Clubs""))suitpoint=1;
    else if(b.equals(""Diamonds""))suitpoint=2;
    else if(b.equals(""Hearts""))suitpoint=3;
    else if(b.equals(""Spades""))suitpoint=4;
    return suitpoint;
} 
    public int GetPoint(Card aa) {
        int GP = 0;
        GP = FacetoINT(aa.getFace())+SuittoINT(aa.getSuit());
        return GP;
    }
        public int[] FullHouse(Card[] bb) {     //FULLHOUSE
        Arrays.sort(bb);
        int[] GP = new int[2];
        int[] temp = new int[5];
        int[] tempC = new int[5];
        for(int i=0; i<5; i++){
            temp[i]=GetPoint(bb[i])/10;
            tempC[i]=GetPoint(bb[i]);
        }
        if(temp[0]==temp[1]&&temp[2]==temp[3]&&temp[3]==temp[4]){
            GP[0]=1;
            GP[1]=tempC[4];
        }
        else if(temp[0]==temp[1]&&temp[1]==temp[2]&&temp[3]==temp[4]){
            GP[0]=1;
            GP[1]=tempC[2];
        }
        else{GP[0]=0; GP[1]=0;}
        return GP;
    }
    public int[] Flush(Card[] cc) {                ///FLUSH
        Arrays.sort(cc);
         int[] GP = new int[2];
        for(int i=0; i<4; i++){
           if((GetPoint(cc[0])%10)==(GetPoint(cc[i+1])%10))GP[0] = GP[0]+1;
        } 
        GP[1] = GetPoint(cc[4]);//save max card
        GP[0] = GP[0]/4;//save whether set is establish?
        return GP;
    }
    public int[] Straight(Card[] aa) {              //STRAIGHT
        Arrays.sort(aa);
        int[] GP = new int[2];
        for(int i=0; i<4; i++){
           if((GetPoint(aa[i+1])/10)-(GetPoint(aa[i])/10)==1)GP[0] = GP[0]+1;
        } 
        GP[1] = GetPoint(aa[4]);//save max card
        GP[0] = GP[0]/4;//save whether set is establish?
        if((GetPoint(aa[0])/10)==1&&(GetPoint(aa[1])/10)==2&&(GetPoint(aa[2])/10)==3&&(GetPoint(aa[3])/10)==4&&(GetPoint(aa[4])/10)==13){
            GP[0]=1; GP[1]=GetPoint(aa[3]);
        }
        return GP;
    }
    public int[] TwoPair(Card[] dd) {         ///TWO PAIR
        Arrays.sort(dd);
        int[] GP = new int[2];
        int count = 0;
        int temp = 0;
        for(int i=0; i<4; i++){
            for(int j=i+1; j<5; j++){
                if((GetPoint(dd[i])/10)==(GetPoint(dd[j])/10)){
                    temp=j;
                    count++;
                }
            }            
        }
        if(count==2){
            GP[0]=1;GP[1]=GetPoint(dd[temp]);}
        else if(count==3){
            GP[0]=1;GP[1]=GetPoint(dd[temp]);}
        else if(count==6){
            GP[0]=1;GP[1]=GetPoint(dd[temp]);}
        else{ GP[0]=0;GP[1]=0;
        }
        return GP;
    }
    public int[] OnePair(Card[] ee) {         //ONE PAIR
        Arrays.sort(ee);
        int[] GP = new int[2];
        int temp = 0;
        int count = 0;
        for(int i=0; i<4; i++){
            for(int j=i+1; j<5; j++){
                if((GetPoint(ee[i])/10)==(GetPoint(ee[j])/10)){
                    temp=j;
                    count++;
                }
            }            
        }
        if(count==1){
            GP[0]=1; GP[1]=GetPoint(ee[temp]);}
        else{GP[0]=0; GP[1]=0;            
        }
        return GP;
    }
    
    public int GetOrder(Card[] O){
        Arrays.sort(O);
        int Order = 0;
        if(FullHouse(O)[0]==1) Order = 5;
        else{
            if(Flush(O)[0]==1) Order = 4;
            else{
                if(Straight(O)[0]==1)Order = 3;
                else{
                    if(TwoPair(O)[0]==1)Order = 2;
                    else{
                        if(OnePair(O)[0]==1)Order = 1;
                        else Order = 0;
                    }
                }
            }//2-else
        }//1-else
        return Order;
    }
//----------------------------------------------------------------------------\\    
//***********************************Sort end*********************************\\    
    
      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

