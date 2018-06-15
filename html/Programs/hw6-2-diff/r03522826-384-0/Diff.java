public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int Judge, Maxnum, Maxpair, index;
    
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
        Maxnum = 0;
        Maxpair = 0;
        index = 0;
    }
    
    public Integer getCase(){
        return Judge;
    }
    
    public Integer getIndex(){
        return index;
    }
    
    public Integer getNumber(int index){
        return cards[index].getNumber();
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     public void Pairs()
     {
//         int pairnum = 0;
         int pairsize = 0;
         int ind1 = 0;
         for(int i = 0; i < 5; i++){
//             while(cards[i].getNumber() != pairnum){
                for(int j = i+1; j < 5; j++)
                {
                    if(cards[i].getFace().equals(cards[j].getFace()))
                    {
                        ind1 = i;
                        if(pairsize < 3){
                            pairsize += 1;
//                            paircounts += 1;
                        }
                        if(cards[i].compareTo(cards[ind1]) > 0){
                            Maxpair = cards[i].getNumber();
                            index = i;
                        }
                        
                    }
                }
//             }
         }
         if(pairsize == 1)
             Judge = 2;
         if(pairsize == 2)
             Judge = 3;
         if(pairsize == 3)
             Judge = 6;
     }
     
     public void Straight(){
         int c0 = cards[0].getNumber();
         int c4 = cards[4].getNumber();
         int c3 = cards[3].getNumber();
         if(((c4 - c0) == 4)&&(Judge < 5)){
             Judge = 4;
             index = 4;
         }
         if((c4 == 1)&&(Judge < 5))
         {
             if(((c3-c0) == 3)&&(c3 == 5)){
                 Judge = 4;
                 Maxnum = 0;
                 index = 3;
             }
             if(((c3-c0) == 3)&&(c3 == 13)){
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
     
     
    // TODO 
    public int compareTo(Player that) {
.
        Insertion.sort(this.cards);
        Insertion.sort(that.cards);
        this.Judge = 1;
        that.Judge = 1;
        this.Maxnum = this.cards[4].getNumber();
        that.Maxnum = that.cards[4].getNumber();
        this.index = 4;
        that.index = 4;
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
        {
            return (this.cards[index].compareTo(that.cards[index]));
//            if(Judge == 2)
//                return (this.cards[index].compareTo(that.cards[index]));
//            else if(Judge == 3)
//                return (this.cards[index].compareTo(that.cards[index]));
//            else if(Judge == 6)
//                return (this.cards[index].compareTo(that.cards[index]));
//            else
//                return(this.cards[index].compareTo(that.cards[index]));
        }
    }
}

