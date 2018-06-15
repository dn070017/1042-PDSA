
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards)
    {
        this.cards = cards;
        return;
    }
private static int faceToInt(String thatFace)
    {
         // String thatFace = that.getFace();
          int faceInt2 = 0;
        
    if(thatFace.equals(""2""))
            faceInt2 = 2;
        if(thatFace.equals(""3""))
            faceInt2 = 3;
        if(thatFace.equals(""4""))
            faceInt2 = 4;
        if(thatFace.equals(""5""))
            faceInt2 = 5;
        if(thatFace.equals(""6""))
            faceInt2 = 6;
        if(thatFace.equals(""7""))
            faceInt2 = 7;
        if(thatFace.equals(""8""))
            faceInt2 = 8;
        if(thatFace.equals(""9""))
            faceInt2 = 9;
        if(thatFace.equals(""10""))
            faceInt2 = 10;
        if(thatFace.equals(""J""))
            faceInt2 = 11;
        if(thatFace.equals(""Q""))
            faceInt2 = 12;
        if(thatFace.equals(""K""))
            faceInt2 = 13;
        if(thatFace.equals(""A""))
            faceInt2 = 14;
        return faceInt2;
    }
       private static int suitToInt(String suitStr) 
       {
        int num1 = 0;
      //  String suitStr = that.getSuit();
        if (suitStr.equals(""Spades""))
            num1=  3;
        else if (suitStr.equals(""Hearts""))
            num1 =2;
        else if (suitStr.equals(""Diamonds""))
            num1=1;
        else if(suitStr.equals(""Clubs""))
            num1 = 0;
        
        return num1;
       }
    
    
    
    public int compareTo(Hand that) 
    {
        Card[] thisCard = this.getCards();
        Card[] thatCard = that.getCards();
        
        int thisKey = checkCombination(this.cards);
        int thatKey = checkCombination(that.cards);
.
        return thisKey-thatKey;
    }
    
    public static boolean isStraight(List<List<Integer>> that)
    {
        
        //Arrays.sort(that);
        int key0=that.get(0).get(0);//
        for( int i =1; i<5; i++)
        {
           if( i==4 && that.get(0).get(0) == 2 && that.get(i).get(0) ==14)
               return true;
           else if(that.get(i).get(0)-key0 != i)
           // if(Card.faceToMap(that[i].getFace()) - key0 != i)
                return false;
        }
        return true;
    }
    
public static boolean isFlush(Card[] that)
{
    String lastSuit = that[0].getSuit();
    for(int i=1; i< 5; i++)
    {
        if(!that[i].getSuit().equals(lastSuit))
            return false;
        
    }
    return true;
    
}
    



public static Integer checkCombination(Card[] that)
    {
        Arrays.sort(that);
        String lastCardFace = that[0].getFace();
        int kind =1;//how many kind of face;
        
        for(int i =1; i<5; i++)
        {
            if(!that[i].getFace().equals(lastCardFace))
                kind++;
        }
        List<List<Integer>> count = new ArrayList<List<Integer>>() {} ;// how many 
        int index = 0;
        count.add(new ArrayList<Integer>());
        count.get(index).add(faceToInt(lastCardFace));
        int maxIndexCount =1;
        int key= 4*(faceToInt(that[4].getFace())-1) + suitToInt(that[4].getSuit()) ;
        
        for( int i =1; i<5; i++)
        {
            if(that[i].getFace().equals(lastCardFace))
            {
                count.get(index).add(faceToInt(lastCardFace));
                if(count.get(index).size() == maxIndexCount)
                {
                  //  maxIndexCount++;
                    key = 4*(faceToInt(that[i].getFace())-1) + suitToInt(that[i].getSuit()) ;
                }
                
                if(count.get(index).size() > maxIndexCount)
                {
                    maxIndexCount++;
                    key = 4*(faceToInt(that[i].getFace())-1) + suitToInt(that[i].getSuit()) ;
                }
            }
            
            else
            {
              index ++;
                lastCardFace = that[i].getFace();
                 count.add(new ArrayList<Integer>());
                count.get(index).add(faceToInt(lastCardFace));
            }         
        }       
        if (count.size() == 2 && maxIndexCount ==3)// fullhouse
            return 6*56+key;
        
        else if(count.size() ==5 )
        {
            if (isFlush(that))
                return 5*56 +key;
            
            else if(Player.isStraight(count))
            {
                if(that[4].getFace().equals(""A"") && that[3].getFace().equals(""5""))
                {
                  // System.out.print(that[3].getFace());
                    key = 4*(faceToInt(that[3].getFace())-1) + suitToInt(that[3].getSuit()) ;
                   // return 4*56 +key
                }
                return 4*56 + key;
            }

            
            else return 1*56+key;
        }
        
        if(maxIndexCount ==2 &&count.size() == 3 )
            return 3*56+key;
        
        else if(maxIndexCount ==2 &&count.size() == 4 )
            return 2*56+key;
      
       // String one = Integer.toString(maxIndexCount);
       // String two = Integer.toString(count.size());
    //    String three = new String(one + "" ""+two);
        return 0;
        

    }
    // TODO


      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

