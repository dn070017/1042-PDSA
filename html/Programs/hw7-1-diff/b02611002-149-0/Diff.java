import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards = new Card[5];

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards=cards;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }

    // TODO
    public int compareTo(Hand that) {
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
        boolean isFirstCard=true;



        Card[] NThis = this.cards;  //cards in ""numerical"" expression (A->14, J->11, Q->12, K->13)
        Card[] NThat = that.cards;
        for(int i=0;i<NThis.length;i++){

            switch (NThis[i].getFace()){
                case ""A"": NThis[i]=new Card(""14"",NThis[i].getSuit());break;
                case ""J"": NThis[i]=new Card(""11"",NThis[i].getSuit());break;
                case ""Q"": NThis[i]=new Card(""12"",NThis[i].getSuit());break;
                case ""K"": NThis[i]=new Card(""13"",NThis[i].getSuit());break;
            }

            switch (NThat[i].getFace()){
                case ""A"": NThat[i]=new Card(""14"",NThis[i].getSuit());break;
                case ""J"": NThat[i]=new Card(""11"",NThis[i].getSuit());break;
                case ""Q"": NThat[i]=new Card(""12"",NThis[i].getSuit());break;
                case ""K"": NThat[i]=new Card(""13"",NThis[i].getSuit());break;
            }
        }


        Arrays.sort(NThis);

        Arrays.sort(NThat);
 

        int hID=0;
        Card[] currentHand = NThis;
        for(int i =0;i<2;i++){  // loop: NThis, NThat
            if(hID==1) currentHand=NThat;

            Card previous = null;
            FHCounter=0;
            suitCombo[hID]=0;
            faceCombo[hID]=0;
            FHThreeFace[hID]=""-1"";
            FHTwoFace[hID]=""-1"";
            tempFHTwoFace[hID]=""-1"";

...

                //handle first card case
                if(isFirstCard){
                    FHCounter=1;
                    suitCombo[hID]=1;
                    faceCombo[hID]=1;
                    isFirstCard=false;
                    previous=c;
                    break;
                }

                // FullHouse counter
                if (previous.getFace() == c.getFace()) FHCounter++;
                else FHCounter = 1;
                if (FHCounter == 3) {
                    FHThreeFace[hID] = c.getFace();
                    FHTwoFace[hID] = tempFHTwoFace[hID];
                } else if (FHCounter == 2) {
                    tempFHTwoFace[hID] = FHTwoFace[hID];
                    FHTwoFace[hID] = c.getFace();
                }

                // Flush counter
                if(previous.getSuit().equals(c.getSuit())){
                    suitCombo[hID]++;
                }

                // Straight counter
                if(Integer.parseInt(c.getFace())-Integer.parseInt(previous.getFace())==1){
                    faceCombo[hID]++;
                }

                previous=c;
...


            scores[hID]=Integer.parseInt(currentHand[4].getFace());

            if(!FHTwoFace[hID].equals(""-1"")){
                isOnePair[hID]=true;
                scores[hID]=20;
            }
            if(!tempFHTwoFace[hID].equals(""-1"") && !FHTwoFace[hID].equals(""-1"")){
                isTwoPair[hID]=true;
                scores[hID]=30;
            }
            if(faceCombo[hID]==4){
                isStraight[hID]=true;
                scores[hID]=40;
            }
            if(suitCombo[hID]==4){
                isFlush[hID]=true;
                scores[hID]=50;
            }
            if (!FHThreeFace[hID].equals(""-1"") && !FHTwoFace[hID].equals(""-1"")) {
                isFullHouse[hID]=true;
                scores[hID]=60;
            }
            hID++;

        }// loop: NThis, NThat





        if(isFullHouse[0]&&isFullHouse[1]){
            if(Integer.parseInt(FHThreeFace[0])>Integer.parseInt(FHThreeFace[1])){
                scores[0]+=6;
            }else if(Integer.parseInt(FHThreeFace[0])<Integer.parseInt(FHThreeFace[1])){
                scores[1]+=6;
            }
        }

        if(isFlush[0]&&isFlush[1]){
            if(Integer.parseInt(NThis[4].getFace())>Integer.parseInt(NThat[4].getFace())){
                scores[0]+=5;
            }else if(Integer.parseInt(NThis[4].getFace())<Integer.parseInt(NThat[4].getFace())){
                scores[1]+=5;
            }
        }


        if(isStraight[0]&&isStraight[1]){
            if(Integer.parseInt(NThis[4].getFace())==14)scores[0]-=3;
            if(Integer.parseInt(NThat[4].getFace())==14)scores[0]-=3;
            if(Integer.parseInt(NThis[0].getFace())>Integer.parseInt(NThat[0].getFace())){
                scores[0]+=3;
            }else if(Integer.parseInt(NThis[0].getFace())<Integer.parseInt(NThat[0].getFace())) {
                scores[1]+=3;
            }
        }

        if(isTwoPair[0] && isTwoPair[1]){
            if(Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[0]+=3;
            }else if (Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[1]+=3;
            }
        }

        if(isOnePair[0] && isOnePair[1]){
            if(Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[0]+=2;
            }else if (Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[1]+=2;
            }
        }


        return scores[0]-scores[1];
    }
}

