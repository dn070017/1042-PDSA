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

            Card previous=new Card(""-100"",""na"");
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
                    if((Integer.parseInt(previous.getFace())-Integer.parseInt(c.getFace())==-1)
                            ||(Integer.parseInt(previous.getFace())-Integer.parseInt(c.getFace())==-9)){
                        faceCombo[pID]++;
                    }
                }



                previous=c;
            }







            //掃完第一組牌THIS了！掃第二組THAT之前：


            hand[pID]=Integer.parseInt(cs[4].getFace()); //1~14

            if(faceCombo[pID]==4){
                isStraight[pID]=true;
                hand[pID]=40;
            }
            if(suitCombo[pID]==4){
                isFlush[pID]=true;
                hand[pID]=50;
            }
            if (!FHThreeFace[pID].equals(""-1"") && !FHTwoFace[pID].equals(""-1"")) {
                isFullHouse[pID] = true;
                hand[pID] = 60;//+Integer.parseInt(FHThreeFace[pID]);
            }




            pID++;

        }












        //處理兩個都是Full House
        if(isFullHouse[0]&&isFullHouse[1]){
            if(Integer.parseInt(FHThreeFace[0])>Integer.parseInt(FHThreeFace[1])){
                hand[0]+=6;
            }else if(Integer.parseInt(FHThreeFace[0])<Integer.parseInt(FHThreeFace[1])){
                hand[1]+=6;
            }
        }
        //處理兩個都是Flush
        if(isFlush[0]&&isFlush[1]){
            if(Integer.parseInt(THIS[4].getFace())>Integer.parseInt(THAT[4].getFace())){
                hand[0]+=5;
            }else if(Integer.parseInt(THIS[4].getFace())<Integer.parseInt(THAT[4].getFace())){
                hand[1]+=5;
            }
        }
        //處理兩個都是Straight
        // TODO 2 3 4 11 12  vs  2 3 11 12 13
        if(isStraight[0]&&isStraight[1]){
            if(Integer.parseInt(THIS[4].getFace())==14)hand[0]-=3;//排除A在Flush中最小
            if(Integer.parseInt(THAT[4].getFace())==14)hand[0]-=3;
            if(Integer.parseInt(THIS[0].getFace())>Integer.parseInt(THAT[0].getFace())){
                hand[0]+=3;
            }else if(Integer.parseInt(THIS[0].getFace())<Integer.parseInt(THAT[0].getFace())) {
                hand[1]+=3;
            }
        }



        return hand[0]-hand[1];
    }
}

