import java.util.Arrays;


public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
    }
    
    public int getFaceValue(String face){
        int v = 0;
        if(tryParseInt(face))
        {
            v = Integer.parseInt(face);
        }
        else if(face.equals(""A""))
        {
            v = 14;
        }
        else if(face.equals(""K""))
        {
            v = 13;
        }
        else if(face.equals(""Q""))
        {
            v = 12;
        }
        else if(face.equals(""J""))
        {
            v = 11;
        }
        return v;
    }
    
    public int getSuitValue(String suit){
        int v1 = 0;  
        if(suit.equals(""Spades""))
            {
                v1 = 4;
            }
            else if(suit.equals(""Hearts""))
            {
                v1 = 3;
            }
            else if(suit.equals(""Diamonds""))
            {
                v1 = 2;
            }
            else if(suit.equals(""Clubs""))
            {
                v1 = 1;
            }
        return v1;
    }
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards = cards;
    }
    public double getHandValue(Hand hand){
        double c1 = 0;
        int i = 0,j =0;
        int[] PairIndex = new int[2];
        while(i < 4)
        {
            if(hand.cards[i].getFace().equals(hand.cards[++i].getFace()))
            {
                c1 = c1 + 2;
                PairIndex[j++] = i; 
                if(i == 4) break;
                if(hand.cards[i].getFace().equals(hand.cards[++i].getFace()))
                {
                    c1 = c1 + 1 + getFaceValue(hand.cards[i].getFace()) * 0.01;
                    if(i == 4) break;
                     if(hand.cards[i].getFace().equals(hand.cards[++i].getFace()))
                    {
                        c1 = c1 + 1 + getFaceValue(hand.cards[i].getFace()) * 0.0001;
                    }
                }
            }
        }
        //flush
        if(c1 < 5)
        {
            i = 0; 
            int k = 0;
            while(i < 4)
            {
                if(hand.cards[i].getSuit().equals(hand.cards[++i].getSuit()))
                {
                    k++;
                }
            }
            if(k == 4)
            {
                c1 = 4.8  + getFaceValue(hand.cards[4].getFace()) * 0.001 + getSuitValue(hand.cards[4].getSuit()) * 0.0001;
            }
        }
        //straight  4.5x  4.1x
        if(c1 < 4.8)
        {
            i = 0; 
            int k = 0;
            while(i < 4)
            {
                if(getFaceValue(hand.cards[i].getFace()) - getFaceValue(hand.cards[++i].getFace()) == -1) k++;
            }
            if(k == 4)
            {
                c1 = 4.5 + getFaceValue(hand.cards[4].getFace()) * 0.001 + getSuitValue(hand.cards[4].getSuit()) * 0.0001;
            }
            else if(k == 3 && getFaceValue(hand.cards[0].getFace()) == 2 && getFaceValue(hand.cards[4].getFace()) == 14)
            {
                c1 = 4.1 + getFaceValue(hand.cards[3].getFace()) * 0.001 + getSuitValue(hand.cards[3].getSuit()) * 0.0001;
            }
        }
        
        if(c1 == 4)
        {
            c1 =  c1 + getFaceValue(hand.cards[PairIndex[1]].getFace()) * 0.01 + getSuitValue(hand.cards[PairIndex[0]].getSuit()) * 0.0001;
        }
        else if(c1 == 2)
        {
             c1 = c1 +  getFaceValue(hand.cards[PairIndex[0]].getFace()) * 0.01;
        }
                
        else if(c1 == 0)
        {
            c1 = getFaceValue(hand.cards[4].getFace()) * 0.01 + getSuitValue(hand.cards[4].getSuit()) * 0.001;
        }
        return c1;
    }
    
    // TODO
    public int compareTo(Hand that) {
        double c1 = 0, c2 = 0;
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        
        c1 = getHandValue(this);
        c2 = getHandValue(that);
        
        
        if(c1 > c2)
        {
            return 1;
        }
        else if(c1 == c2)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

