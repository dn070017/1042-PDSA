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
        boolean[] isStraight={false,false};
        boolean[] isTwoPair={false,false};
        boolean[] isOnePair={false,false};
        Card[] THIS = this.cards;
        Card[] THAT = that.cards;


        //TODO REMOVE
        Arrays.sort(THIS);
        System.out.println(""\n* this.cards sorted: "");for (Card c:THIS){System.out.println(c.getSuit()+c.getFace());}
        Arrays.sort(THAT);
        System.out.println(""\n* that.cards sorted: "");for (Card c:THAT){System.out.println(c.getSuit()+c.getFace());}


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


        int pID=0;
        for(Card[] cs=this.cards;pID<2;cs=that.cards){
            System.out.println(""\n========CARD========"");
            Card previous=new Card(""na"",""-1"");
            FHCounter=0;



            for(Card c:cs){

            // check Card c

                {// isFullHouse?
                    if (previous.getFace() == c.getFace()) {//若與前一張點數相等，則先計入FullHouse2
                        FHCounter++;
                    } else {
                        FHCounter = 1;
                    }

                    if (FHCounter == 3) {
                        FHThreeFace[pID] = c.getFace();
                        isFullHouse[pID]=true;
                        hand[pID]=60;
                        FHTwoFace = tempFHTwoFace;
                    } else if (FHCounter == 2) {
                        tempFHTwoFace[pID] = FHTwoFace[pID];
                        FHTwoFace[pID] = c.getFace();
                    }

                }




                previous=c;
            }
            pID++;
            System.out.println(""====================\n"");
        }


        //TODO DEBUG
        System.out.println(""\n*FHThreeFace: ""+FHThreeFace[0]+""\n*FHTwoFace: ""+FHTwoFace[0]);
        System.out.println(""\n*FHThreeFace: ""+FHThreeFace[1]+""\n*FHTwoFace: ""+FHTwoFace[1]);


        if(isFullHouse[0]&&isFullHouse[1]){
            if(Integer.parseInt(FHThreeFace[0])>Integer.parseInt(FHThreeFace[1])){
                hand[0]++;
            }else {hand[0]--;}
        }


        System.out.printf(""\nhand[]: {%d,%d}"",hand[0],hand[1]);
        return hand[0]-hand[1];
    }
}

