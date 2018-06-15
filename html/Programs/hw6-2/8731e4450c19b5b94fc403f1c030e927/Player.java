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



    public int compareTo(Player that) {
.


.

        boolean[] isFullHouse={false,false};
        int FHCounter=0;
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





        Arrays.sort(THIS);
        Arrays.sort(THAT);







        int pID=0;
        Card[] now = THIS;

        for(int i =0;i<2;i++){
            if(pID==1) now=THAT;

            Card previous=new Card(""1"",""Clubs"");
                FHCounter=0;
                suitCombo[pID]=0;
                faceCombo[pID]=0;
                FHThreeFace[pID]=""-1"";
                FHTwoFace[pID]=""-1"";
                tempFHTwoFace[pID]=""-1"";

            for(Card c:now){

                // check Card c

                // isFullHouse?
                {
                    if (previous.getFace() == c.getFace()) {
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
            hand[pID]=Integer.parseInt(now[4].getFace());

            if(!FHTwoFace[pID].equals(""-1"")){
                isOnePair[pID]=true;
                hand[pID]=20;
            }
            if(!tempFHTwoFace[pID].equals(""-1"") && !FHTwoFace[pID].equals(""-1"")){
                isTwoPair[pID]=true;
                hand[pID]=30;
            }
            if(faceCombo[pID]==4){
                isStraight[pID]=true;
                hand[pID]=40;
            }
            if(suitCombo[pID]==4){
                isFlush[pID]=true;
                hand[pID]=50;
            }
            if (!FHThreeFace[pID].equals(""-1"") && !FHTwoFace[pID].equals(""-1"")) {
                isFullHouse[pID]=true;
                hand[pID]=60;
            }
            pID++;
        }





        if(isFullHouse[0]&&isFullHouse[1]){
            if(Integer.parseInt(FHThreeFace[0])>Integer.parseInt(FHThreeFace[1])){
                hand[0]+=6;
            }else if(Integer.parseInt(FHThreeFace[0])<Integer.parseInt(FHThreeFace[1])){
                hand[1]+=6;
            }
        }

        if(isFlush[0]&&isFlush[1]){
            if(Integer.parseInt(THIS[4].getFace())>Integer.parseInt(THAT[4].getFace())){
                hand[0]+=5;
            }else if(Integer.parseInt(THIS[4].getFace())<Integer.parseInt(THAT[4].getFace())){
                hand[1]+=5;
            }
        }


        if(isStraight[0]&&isStraight[1]){
            if(Integer.parseInt(THIS[4].getFace())==14)hand[0]-=3;
            if(Integer.parseInt(THAT[4].getFace())==14)hand[0]-=3;
            if(Integer.parseInt(THIS[0].getFace())>Integer.parseInt(THAT[0].getFace())){
                hand[0]+=3;
            }else if(Integer.parseInt(THIS[0].getFace())<Integer.parseInt(THAT[0].getFace())) {
                hand[1]+=3;
            }
        }

        if(isTwoPair[0] && isTwoPair[1]){
            if(Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                hand[0]+=3;
            }else if (Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                hand[1]+=3;
            }
        }

        if(isOnePair[0] && isOnePair[1]){
            if(Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                hand[0]+=2;
            }else if (Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                hand[1]+=2;
            }
        }


        return hand[0]-hand[1];
    }
}
