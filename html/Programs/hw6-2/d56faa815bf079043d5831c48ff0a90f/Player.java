import java.lang.reflect.Array;
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
        /*  60 61: full house (10 10 10 4 4 > 9 9 9 A A)
            50 51: flush ( S S S S S )
            40 41: straight ( 8 7 6 5 4 )
            30 31: two pair (  )
            20 21: one pair ()
            0 1: high card */

.
        boolean[] isFullHouse={false,false};
        int FHCounter;
        String[] FHThreeFace={""-1"",""-1""};//10 //9
        String[] FHTwoFace={""-1"",""-1""};//4 //A
        String[] tempFHTwoFace={""-1"",""-1""};
        boolean[] isFlush={false,false};
        int suitCombo[]={0,0};
        boolean[] isStraight={false,false};
        int faceCombo[]={0,0};
        boolean[] isTwoPair={false,false};
        boolean[] isOnePair={false,false};
        Card[] THIS = this.cards;
        Card[] THAT = that.cards;




        for(int i=0;i<THIS.length;i++){
            if(THIS[i].getFace().equals(""A"")){
                THIS[i]=new Card(""14"",THIS[i].getSuit());
            }else if(THIS[i].getFace().equals(""J"")){
                THIS[i]=new Card(""11"",THIS[i].getSuit());
            }else if(THIS[i].getFace().equals(""Q"")){
                THIS[i]=new Card(""12"",THIS[i].getSuit());
            }else if(THIS[i].getFace().equals(""K"")){
                THIS[i]=new Card(""13"",THIS[i].getSuit());
            }
        }
        for(int i=0;i<THAT.length;i++){
            if(THAT[i].getFace().equals(""A"")){
                THAT[i]=new Card(""14"",THAT[i].getSuit());
            }else if(THAT[i].getFace().equals(""J"")){
                THAT[i]=new Card(""11"",THAT[i].getSuit());
            }else if(THAT[i].getFace().equals(""Q"")){
                THAT[i]=new Card(""12"",THAT[i].getSuit());
            }else if(THAT[i].getFace().equals(""K"")){
                THAT[i]=new Card(""13"",THAT[i].getSuit());
            }
        }

        //TODO REMOVE
        Arrays.sort(THIS);

        Arrays.sort(THAT);



        int pID=0;
        for(Card[] cs=THIS;pID<2;cs=THAT){

            Card previous=new Card(""-1"",""na"");
            FHCounter=0;
            suitCombo[pID]=0;
            faceCombo[pID]=0;
            FHThreeFace[pID]=""-1"";
            FHTwoFace[pID]=""-1"";
            tempFHTwoFace[pID]=""-1"";




            for(Card c:cs){

                // check Card c

                // isFullHouse?
                {
                    if (previous.getFace() == c.getFace()) {//若與前一張點數相等，則先計入FullHouse2
                        FHCounter++;
                    } else {
                        FHCounter = 1;
                    }

                    if (FHCounter == 3) {
                        FHThreeFace[pID] = c.getFace();
                        FHTwoFace[pID] = tempFHTwoFace[pID];
                    } else if (FHCounter == 2) {
                        tempFHTwoFace[pID] = FHTwoFace[pID];
                        FHTwoFace[pID] = c.getFace();
                    }


                }

                //is Flush
                {
                    if(previous.getSuit().equals(c.getSuit())){
                        suitCombo[pID]++;
                    }
                }

                //is Straight?
                {
                    if(Integer.parseInt(previous.getFace())-Integer.parseInt(c.getFace())==1){
                        faceCombo[pID]++;
                    }
                }



                previous=c;
            }
            //掃完第一組牌THIS了！掃第二組THAT之前：

            if(suitCombo[pID]==4) isFlush[pID]=true; hand[pID]=50;
            if(faceCombo[pID]==4) isStraight[pID]=true; hand[pID]=40;
            if (!FHThreeFace[pID].equals(""-1"") && !FHTwoFace[pID].equals(""-1"")) {
                isFullHouse[pID] = true;
                hand[pID] = 60;//+Integer.parseInt(FHThreeFace[pID]);
            }




            pID++;

        }


        //TODO DEBUG



        if(isFullHouse[0]&&isFullHouse[1]){
            if(Integer.parseInt(FHThreeFace[0])>Integer.parseInt(FHThreeFace[1])){
                hand[0]++;
            }else {hand[0]--;}
        }


        if(hand[0]-hand[1]>0){
            return 1;
        }else if(hand[0]-hand[1]<0){
            return -1;
        }else {
            return 0;    
        }
        
    }
}

