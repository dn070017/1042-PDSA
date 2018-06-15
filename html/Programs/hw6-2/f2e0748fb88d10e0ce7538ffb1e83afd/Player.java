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
        int thisFullHouseBig=0;
        int thatFullHouseBig=0;
        int thisFullHouseSmall=0;
        int thatFullHouseSmall=0;
        int thisOnePair=0;
        int thatOnePair=0;
        int thisOnePairBigSingle=0;
        int thatOnePairBigSingle=0;
        int thisOnePairMSingle=0;
        int thatOnePairMSingle=0;
        int thisOnePairSSingle=0;
        int thatOnePairSSingle=0;
        int thisTwoPairBig=0;
        int thatTwoPairBig=0;
        int thisTwoPairSmall=0;
        int thatTwoPairSmall=0;
        int thisTwoPairSingle=0;
        int thatTwoPairSingle=0;
        
        if(thisCardNumber[0]==thisCardNumber[1]||thisCardNumber[1]==thisCardNumber[2]||thisCardNumber[2]==thisCardNumber[3]||thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=1;
            if(thisCardNumber[0]==thisCardNumber[1])
            {
                thisOnePair=thisCardNumber[0];
                thisOnePairBigSingle=thisCardNumber[4];
                thisOnePairMSingle=thisCardNumber[3];
                thisOnePairSSingle=thisCardNumber[2];
            }
            else if(thisCardNumber[1]==thisCardNumber[2])
            {
                thisOnePair=thisCardNumber[1];
                thisOnePairBigSingle=thisCardNumber[4];
                thisOnePairMSingle=thisCardNumber[3];
                thisOnePairSSingle=thisCardNumber[0];
            }
            else if(thisCardNumber[2]==thisCardNumber[3])
            {
                thisOnePair=thisCardNumber[2];
                thisOnePairBigSingle=thisCardNumber[4];
                thisOnePairMSingle=thisCardNumber[1];
                thisOnePairSSingle=thisCardNumber[0];
            }
            else if(thisCardNumber[3]==thisCardNumber[4])
            {
                thisOnePair=thisCardNumber[3];
                thisOnePairBigSingle=thisCardNumber[2];
                thisOnePairMSingle=thisCardNumber[1];
                thisOnePairSSingle=thisCardNumber[0];
            }
        }
        else if(thisCardNumber[0]==thisCardNumber[1]&&thisCardNumber[1]==thisCardNumber[2])
        {
            thisCardsType=5;
            thisFullHouseBig=thisCardNumber[2];
            thisFullHouseSmall=thisCardNumber[4];
            thisOnePair=0;
        }
        else if(thisCardNumber[2]==thisCardNumber[3]&&thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=5;
            thisFullHouseBig=thisCardNumber[4];
            thisFullHouseSmall=thisCardNumber[0];
            thisOnePair=0;
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
            thisTwoPairBig=thisCardNumber[3];
            thisTwoPairSmall=thisCardNumber[1];
            thisTwoPairSingle=thisCardNumber[4];
            thisFullHouseBig=0;
            thisOnePair=0;
        }
        else if(thisCardNumber[1]==thisCardNumber[2]&&thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=2;
            thisTwoPairBig=thisCardNumber[4];
            thisTwoPairSmall=thisCardNumber[1];
            thisTwoPairSingle=thisCardNumber[0];
            thisFullHouseBig=0;
            thisOnePair=0;
        }
        else if(thisCardNumber[0]==thisCardNumber[1]&&thisCardNumber[3]==thisCardNumber[4])
        {
            thisCardsType=2;
            thisTwoPairBig=thisCardNumber[4];
            thisTwoPairSmall=thisCardNumber[1];
            thisTwoPairSingle=thisCardNumber[2];
            thisFullHouseBig=0;
            thisOnePair=0;
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
                thatOnePairBigSingle=thatCardNumber[4];
                thatOnePairMSingle=thatCardNumber[3];
                thatOnePairSSingle=thatCardNumber[2];
            }
            else if(thatCardNumber[1]==thatCardNumber[2])
            {
                thatOnePair=thatCardNumber[1];
                thatOnePairBigSingle=thatCardNumber[4];
                thatOnePairMSingle=thatCardNumber[3];
                thatOnePairSSingle=thatCardNumber[0];
            }
            else if(thatCardNumber[2]==thatCardNumber[3])
            {
                thatOnePair=thatCardNumber[2];
                thatOnePairBigSingle=thatCardNumber[4];
                thatOnePairMSingle=thatCardNumber[1];
                thatOnePairSSingle=thatCardNumber[0];
            }
            else if(thatCardNumber[3]==thatCardNumber[4])
            {
                thatOnePair=thatCardNumber[3];
                thatOnePairBigSingle=thatCardNumber[2];
                thatOnePairMSingle=thatCardNumber[1];
                thatOnePairSSingle=thatCardNumber[0];
            }
        }
        else if(thatCardNumber[0]==thatCardNumber[1]&&thatCardNumber[1]==thatCardNumber[2])
        {
            thatCardsType=5;
            thatFullHouseBig=thatCardNumber[2];
            thatFullHouseSmall=thatCardNumber[4];
            thatOnePair=0;
        }
        else if(thatCardNumber[2]==thatCardNumber[3]&&thatCardNumber[3]==thatCardNumber[4])
        {
            thatCardsType=5;
            thatFullHouseBig=thatCardNumber[4];
            thatFullHouseSmall=thatCardNumber[0];
            thatOnePair=0;
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
            thatTwoPairBig=thatCardNumber[3];
            thatTwoPairSmall=thatCardNumber[1];
            thatTwoPairSingle=thatCardNumber[4];
            thatFullHouseBig=0;
            thatOnePair=0;
        }
        else if(thatCardNumber[1]==thatCardNumber[2]&&thatCardNumber[3]==thatCardNumber[4])
        {
            thatCardsType=2;
            thatTwoPairBig=thatCardNumber[3];
            thatTwoPairSmall=thatCardNumber[1];
            thatTwoPairSingle=thatCardNumber[0];
            thatFullHouseBig=0;
            thatOnePair=0;
        }
        else if(thatCardNumber[0]==thatCardNumber[1]&&thatCardNumber[3]==thatCardNumber[4])
        {
            thatCardsType=2;
            thatTwoPairBig=thatCardNumber[4];
            thatTwoPairSmall=thatCardNumber[1];
            thatTwoPairSingle=thatCardNumber[2];
            thatFullHouseBig=0;
            thatOnePair=0;
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
                if(thisFullHouseBig>thatFullHouseBig)
                {
                    return 1;
                }
                else if(thisFullHouseBig<thatFullHouseBig)
                {
                    return -1;
                }
                else
                {
                    if(thisFullHouseSmall>thatFullHouseSmall)
                    {
                        return 1;
                    }
                    else if(thisFullHouseSmall<thatFullHouseSmall)
                    {
                        return -1;
                    }
                }
            }
            if(thisCardsType==4)
            {
                if(thisCardNumber[4]>thatCardNumber[4]){return 1;}                
                else if(thisCardNumber[4]<thatCardNumber[4]){return -1;}                
                else
                {
                    if(thisCardNumber[3]>thatCardNumber[3]){return 1;}                    
                    else if(thisCardNumber[3]<thatCardNumber[3]){return -1;}                    
                    else
                    {
                        if(thisCardNumber[2]>thatCardNumber[2]){return 1;}                        
                        else if(thisCardNumber[2]<thatCardNumber[2]){return -1;}                        
                        else
                        {
                            if(thisCardNumber[1]>thatCardNumber[1]){return 1;}
                            else if(thisCardNumber[1]<thatCardNumber[1]){return -1;}
                            else
                            {
                                if(thisCardNumber[0]>thatCardNumber[0]){return 1;}
                                else if(thisCardNumber[0]<thatCardNumber[0]){return -1;}
                            }                    
                        }                    
                    }
                }
            }
            if(thisCardsType==3)
            {
                if(thatCardNumber[0]==2 && thatCardNumber[4]==14 && thisCardNumber[0]==2 && thisCardNumber[4]==14)
                {
                    return 0;
                }
                else if(thatCardNumber[0]==2 && thatCardNumber[4]==14)
                {
                    return 1;
                }
                else if(thisCardNumber[0]==2 && thisCardNumber[4]==14)
                {
                    return -1;
                }
                else if(thisCardNumber[4]>thatCardNumber[4])
                {
                    return 1;
                }
                else if(thisCardNumber[4]<thatCardNumber[4])
                {
                    return -1;
                }
            }
            if(thisCardsType==2)
            {
                if(thisTwoPairBig>thatTwoPairBig)
                {
                    return 1;
                }
                else if(thisTwoPairBig<thatTwoPairBig)
                {
                    return -1;
                }
                else
                {
                    if(thisTwoPairSmall>thatTwoPairSmall)
                    {
                        return 1;
                    }
                    else if(thisTwoPairSmall<thatTwoPairSmall)
                    {
                        return -1;
                    }
                    else
                    {
                        if(thisTwoPairSingle>thatTwoPairSingle)
                        {
                            return 1;
                        }
                        else if(thisTwoPairSingle<thatTwoPairSingle)
                        {
                            return -1;
                        }
                        
                    }
                }
            }
            if(thisCardsType==1)
            {
                if(thisOnePair>thatOnePair){return 1;}                
                else if(thisOnePair<thatOnePair){return -1;}
                else
                {
                    if(thisOnePairBigSingle>thatOnePairBigSingle){return 1;}                
                    else if(thisOnePairBigSingle<thatOnePairBigSingle){return -1;}
                    else
                    {
                        if(thisOnePairMSingle>thatOnePairMSingle){return 1;}                
                        else if(thisOnePairMSingle<thatOnePairMSingle){return -1;}
                        else
                        {
                            if(thisOnePairSSingle>thatOnePairSSingle){return 1;}                
                            else if(thisOnePairSSingle<thatOnePairSSingle){return -1;}                            
                        }
                    }
                }
            }
            if(thisCardsType==0)
            {
                if(thisCardNumber[4]>thatCardNumber[4]){return 1;}                
                else if(thisCardNumber[4]<thatCardNumber[4]){return -1;}                
                else
                {
                    if(thisCardNumber[3]>thatCardNumber[3]){return 1;}                    
                    else if(thisCardNumber[3]<thatCardNumber[3]){return -1;}                    
                    else
                    {
                        if(thisCardNumber[2]>thatCardNumber[2]){return 1;}                        
                        else if(thisCardNumber[2]<thatCardNumber[2]){return -1;}                        
                        else
                        {
                            if(thisCardNumber[1]>thatCardNumber[1]){return 1;}
                            else if(thisCardNumber[1]<thatCardNumber[1]){return -1;}
                            else
                            {
                                if(thisCardNumber[0]>thatCardNumber[0]){return 1;}
                                else if(thisCardNumber[0]<thatCardNumber[0]){return -1;}
                            }                    
                        }                    
                    }
                }
            }
        }
        
        
.
        return 0;
    }
}


