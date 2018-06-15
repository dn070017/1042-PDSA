import java.util.Arrays;
//import sun.org.mozilla.javascript.internal.Token;

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
        Arrays.sort(that.cards);
        Arrays.sort(this.cards);
        
        int[] thisCardNumber=new int[5];
        int[] thatCardNumber=new int[5];
        
        for(int i=0;i<5;i++)
        {
            if(this.cards[i].getFace().equals(""A""))thisCardNumber[i]=14;
            if(this.cards[i].getFace().equals(""K""))thisCardNumber[i]=13;
            if(this.cards[i].getFace().equals(""Q""))thisCardNumber[i]=12;
            if(this.cards[i].getFace().equals(""J""))thisCardNumber[i]=11;
            if(this.cards[i].getFace().equals(""10""))thisCardNumber[i]=10;
            if(this.cards[i].getFace().equals(""9""))thisCardNumber[i]=9;
            if(this.cards[i].getFace().equals(""8""))thisCardNumber[i]=8;
            if(this.cards[i].getFace().equals(""7""))thisCardNumber[i]=7;
            if(this.cards[i].getFace().equals(""6""))thisCardNumber[i]=6;
            if(this.cards[i].getFace().equals(""5""))thisCardNumber[i]=5;
            if(this.cards[i].getFace().equals(""4""))thisCardNumber[i]=4;
            if(this.cards[i].getFace().equals(""3""))thisCardNumber[i]=3;
            if(this.cards[i].getFace().equals(""2""))thisCardNumber[i]=2;
            
            if(that.cards[i].getFace().equals(""A""))thatCardNumber[i]=14;
            if(that.cards[i].getFace().equals(""K""))thatCardNumber[i]=13;
            if(that.cards[i].getFace().equals(""Q""))thatCardNumber[i]=12;
            if(that.cards[i].getFace().equals(""J""))thatCardNumber[i]=11;
            if(that.cards[i].getFace().equals(""10""))thatCardNumber[i]=10;
            if(that.cards[i].getFace().equals(""9""))thatCardNumber[i]=9;
            if(that.cards[i].getFace().equals(""8""))thatCardNumber[i]=8;
            if(that.cards[i].getFace().equals(""7""))thatCardNumber[i]=7;
            if(that.cards[i].getFace().equals(""6""))thatCardNumber[i]=6;
            if(that.cards[i].getFace().equals(""5""))thatCardNumber[i]=5;
            if(that.cards[i].getFace().equals(""4""))thatCardNumber[i]=4;
            if(that.cards[i].getFace().equals(""3""))thatCardNumber[i]=3;
            if(that.cards[i].getFace().equals(""2""))thatCardNumber[i]=2;            
        }
        
        int thisCardsType=0;
        int thatCardsType=0;
        int thisFullHouse=0;
        int thatFullHouse=0;
        int thisOnePair=0;
        int thatOnePair=0;
        int thisTwoPair=0;
        int thatTwoPair=0;
        
        
        if(thisCardNumber[0]==thisCardNumber[1]||thisCardNumber[1]==thisCardNumber[2]||thisCardNumber[2]==thisCardNumber[3]||thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=1;
            if(thisCardNumber[0]==thisCardNumber[1])
            {
                thisOnePair=thisCardNumber[0];
            }
            else if(thisCardNumber[1]==thisCardNumber[2])
            {
                thisOnePair=thisCardNumber[1];
            }
            else if(thisCardNumber[2]==thisCardNumber[3])
            {
                thisOnePair=thisCardNumber[2];
            }
            else if(thisCardNumber[3]==thisCardNumber[4])
            {
                thisOnePair=thisCardNumber[3];
            }
        }
        else if(thisCardNumber[0]==thisCardNumber[1]&&thisCardNumber[1]==thisCardNumber[2])
        {
            thisCardsType=5;
            thisFullHouse=thisCardNumber[0];
        }
        else if(thisCardNumber[2]==thisCardNumber[3]&&thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=5;
            thisFullHouse=thisCardNumber[4];
        }
        else if(this.cards[0].getSuit().equals(this.cards[1].getSuit()) && this.cards[1].getSuit().equals(this.cards[2].getSuit()) && this.cards[2].getSuit().equals(this.cards[3].getSuit()) && this.cards[3].getSuit().equals(this.cards[4].getSuit()))
        {
            thisCardsType=4;
        }
        else if(thisCardNumber[0]+1==thisCardNumber[1] && thisCardNumber[1]+1==thisCardNumber[2] && thisCardNumber[2]+1==thisCardNumber[3] && thisCardNumber[3]+1==thisCardNumber[4])
        {
            thisCardsType=3;
        }
        else if(thisCardNumber[0]==2&&thisCardNumber[1]==3&&thisCardNumber[2]==4&&thisCardNumber[3]==5&&thisCardNumber[4]==14)
        {
            thisCardsType=3;
        }        
        else if(thisCardNumber[0]==thisCardNumber[1]&&thisCardNumber[2]==thisCardNumber[3])
        {
            thisCardsType=2;
            thisTwoPair=thisCardNumber[3];
        }
        else if(thisCardNumber[1]==thisCardNumber[2]&&thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=2;
            thisTwoPair=thisCardNumber[3];
        }
        else
        {
            thisCardsType=0;
        }
        //
        if(thatCardNumber[0]==thatCardNumber[1]||thatCardNumber[1]==thatCardNumber[2]||thatCardNumber[2]==thatCardNumber[3]||thatCardNumber[3]==thatCardNumber[4])
        {
            thatCardsType=1;
            if(thatCardNumber[0]==thatCardNumber[1])
            {
                thatOnePair=thatCardNumber[0];
            }
            else if(thatCardNumber[1]==thatCardNumber[2])
            {
                thatOnePair=thatCardNumber[1];
            }
            else if(thatCardNumber[2]==thatCardNumber[3])
            {
                thatOnePair=thatCardNumber[2];
            }
            else if(thatCardNumber[3]==thatCardNumber[4])
            {
                thatOnePair=thatCardNumber[3];
            }
        }
        else if(thatCardNumber[0]==thatCardNumber[1]&&thatCardNumber[1]==thatCardNumber[2])
        {
            thatCardsType=5;
            thatFullHouse=thatCardNumber[0];
        }
        else if(thatCardNumber[2]==thatCardNumber[3]&&thatCardNumber[3]==thatCardNumber[4])
        {
            thatCardsType=5;
            thatFullHouse=thatCardNumber[4];
        }
        else if(that.cards[0].getSuit().equals(that.cards[1].getSuit()) && that.cards[1].getSuit().equals(that.cards[2].getSuit()) && that.cards[2].getSuit().equals(that.cards[3].getSuit()) && that.cards[3].getSuit().equals(that.cards[4].getSuit()))
        {
            thatCardsType=4;
        }
        else if(thatCardNumber[0]+1==thatCardNumber[1] && thatCardNumber[1]+1==thatCardNumber[2] && thatCardNumber[2]+1==thatCardNumber[3] && thatCardNumber[3]+1==thatCardNumber[4])
        {
            thatCardsType=3;
        }
        else if(thatCardNumber[0]==2&&thatCardNumber[1]==3&&thatCardNumber[2]==4&&thatCardNumber[3]==5&&thatCardNumber[4]==14)
        {
            thatCardsType=3;
        }        
        else if(thatCardNumber[0]==thatCardNumber[1]&&thatCardNumber[2]==thatCardNumber[3])
        {
            thatCardsType=2;
            thatTwoPair=thatCardNumber[3];
        }
        else if(thatCardNumber[1]==thatCardNumber[2]&&thatCardNumber[3]==thatCardNumber[4])
        {
            thatCardsType=2;
            thatTwoPair=thatCardNumber[3];
        }
        else
        {
            thatCardsType=0;
        }
        //
        if(thisCardsType>thatCardsType)
        {
            return 1;
        }
        else if(thisCardsType<thatCardsType)
        {
            return -1;
        }
        else
        {
            if(thisCardsType==5)
            {
                if(thisFullHouse>thatFullHouse)
                {
                    return 1;
                }
                if(thisFullHouse<thatFullHouse)
                {
                    return -1;
                }
            }
            if(thisCardsType==4)
            {
                if(thisCardNumber[4]>thatCardNumber[4])
                {
                    return 1;
                }
                if(thisCardNumber[4]<thatCardNumber[4])
                {
                    return -1;
                }
            }
            if(thisCardsType==3)
            {
                if(thisCardNumber[4]>thatCardNumber[4])
                {
                    return 1;
                }
                if(thisCardNumber[4]<thatCardNumber[4])
                {
                    return -1;
                }
            }
            if(thisCardsType==2)
            {
                if(thisTwoPair>thatTwoPair)
                {
                    return 1;
                }
                if(thisTwoPair<thatTwoPair)
                {
                    return -1;
                }
            }
            if(thisCardsType==1)
            {
                if(thisOnePair>thatOnePair)
                {
                    return 1;
                }
                if(thisOnePair<thatOnePair)
                {
                    return -1;
                }
            }
            if(thisCardsType==0)
            {
                if(thisCardNumber[4]>thatCardNumber[4])
                {
                    return 1;
                }
                if(thisCardNumber[4]<thatCardNumber[4])
                {
                    return -1;
                }
            }
        }
        
        
.
        return 0;
    }
}


