
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 
     private int Judge, index;

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        index = 0;
        this.cards = cards;
        Judge = 1;
        index = 4;
    }

    // TODO
    public void Pairs(){
        int pairsize = 0;
         int ind1 = 0;
         for(int i = 0; i < 5; i++){
                for(int j = i+1; j < 5; j++)
                {
                    if(cards[i].getFace().equals(cards[j].getFace()))
                    {
                        ind1 = i;
                        if(pairsize < 4){
                            pairsize += 1;
                        }
                        if(cards[i].compareTo(cards[ind1]) > 0){                         
                            index = i;
                        }
                        
                    }
                }

        }
         if((pairsize == 1)||(pairsize == 3))
             Judge = 2;
         if((pairsize == 2))
             Judge = 3;
         if(pairsize == 4)
             Judge = 6;
    }
    
    public void Straight(){
        int[] Number = new int[5];
         for (int i = 0; i < 5; i++){ 
             if(cards[i].getFace().equals(""A""))
                 Number[i] = 1;
             else if(cards[i].getFace().equals(""K""))
                 Number[i] = 13;
             else if(cards[i].getFace().equals(""Q""))
                 Number[i] = 12;
             else if(cards[i].getFace().equals(""J""))
                 Number[i] = 11;
             else
                 Number[i] = Integer.parseInt(cards[i].getFace());
         }
         
         if(((Number[4] - Number[0]) == 4)&&(Judge < 5)){
             if((Number[3] - Number[1])==2)
                 if((Number[2]-Number[1])==1)
                    Judge = 4;
                    index = 4;
         }
         if((Number[4] == 1)&&(Judge < 5))
         {
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 5)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
//                    special  = 1;
                    index = 3;
             }
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 13)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
             }
         }
    }
    
     public void Flush(){
         int count = 1;
         for(int i = 0; i < 4; i++)
             if(cards[i].getSuit().equals(cards[i+1].getSuit()))
                 count += 1;
         if ((count == 5)&&(Judge < 6))
             Judge = 5;
     }
    
    public int compareTo(Hand that) {
       Insertion.sort(this.cards);
        Insertion.sort(that.cards);
//        this.Judge = 1;
//        that.Judge = 1;
//        this.index = 4;
//        that.index = 4;
        this.Pairs();
        this.Flush();
        this.Straight();
        that.Pairs();
        that.Flush();
        that.Straight();
        if (this.Judge > that.Judge)
            return 1;
        else if (this.Judge < that.Judge)
            return -1;
        else
            return (this.cards[this.index].compareTo(that.cards[that.index]));
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

