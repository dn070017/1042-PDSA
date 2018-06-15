public class Hand implements Comparable<Hand> {
     // sorted by Card value are recommended but not necessary
    private Card[] cards=new Card[5]; 
    public int HandOrder,CardOrder;
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards=cards;
        Insertion.sort(this.cards);
        GetOrder();
       }
     // Do not modified this function
    public Card[] getCards() { return this.cards; }
    private int suittointeger (String suit){
         int num=0;
         switch (suit){
             case ""Spades"":
                 num=3;
                 break;
             case ""Hearts"":
                 num=2;
                 break;
             case ""Dimonds"":
                 num=1;
                 break;
             case ""Clubs"":
                 num=0;
                 break;
         }
         return(num);
     }
     private int facetointeger(String face) {
        int num1 = 0;
        switch (face) {
            case ""A"":
                num1 = 13;
                break;
            case ""K"":
                num1 = 12;
                break;
            case ""Q"":
                num1 = 11;
                break;
            case ""J"":
                num1 = 10;
                break;
            case ""10"":
                num1 = 9;
                break;
            case ""9"":
                num1 = 8;
                break;
            case ""8"":
                num1 = 7;
                break;
            case ""7"":
                num1 = 6;
                break;
            case ""6"":
                num1 = 5;
                break;
            case ""5"":
                num1 = 4;
                break;
            case ""4"":
                num1 = 3;
                break;
            case ""3"":
                num1 = 2;
                break;
            case ""2"":
                num1 = 1;
                break;

        }
        return num1;
    }
    private boolean isFullhouse(){
        if(cards[0].getFace().equals(cards[1].getFace()) && cards[1].getFace().equals(cards[2].getFace()) && cards[3].getFace().equals(cards[4].getFace()) && !cards[0].getFace().equals(cards[3].getFace()))
            return (true);
        if (cards[0].getFace().equals(cards[1].getFace()) && !cards[0].getFace().equals(cards[2].getFace()) && cards[2].getFace().equals(cards[3].getFace()) && cards[2].getFace().equals(cards[4].getFace()))
            return (true);
        else
            return (false);
       }
    private boolean isFlush(){
        String temp=cards[0].getSuit();
        int i=0;
        while (i<5 && cards[i].getSuit().equals(temp) )
            i++;
        return(i==5);
    }
    private boolean isStraight(){
        if (cards[0].getFace().equals(""2"") && cards[1].getFace().equals(""3"") && cards[2].getFace().equals(""4"") && cards[3].getFace().equals(""5"") && cards[4].getFace().equals(""A""))
        return (true);
      
        int i=facetointeger(cards[4].getFace());
        int temp=3;
        while (temp>=0 && facetointeger(cards[temp].getFace())==(i-1) ){
            temp--;
            i--;
        }
        return(i==(facetointeger(cards[4].getFace())-4));
    }
    private int PairNumber(){
        QuickFindUF cc=new QuickFindUF(5);
        for (int i=0 ; i<5 ; i++)
            for (int j=0 ; j<5 ; j++)
                if (facetointeger(cards[i].getFace())==facetointeger(cards[j].getFace()))
                    cc.union(i, j);
        if(cc.count()==5)
            return 0;
        else if(cc.count()==4)
            return 1;
        else if(cc.count()==3 && (cc.find(0)==cc.find(2)||cc.find(4)==cc.find(2)||cc.find(1)==cc.find(3)))
            return 1;
        else 
            return 2;
            }
    // TODO
    public void GetOrder(){
        if (isFullhouse()){
            HandOrder=5;
            CardOrder=facetointeger(cards[2].getFace());
         }
        else if (isFlush()){
            HandOrder=4;
            CardOrder=13*suittointeger(cards[4].getSuit())+facetointeger(cards[4].getFace());
        }
        else if (isStraight()){
            HandOrder=3;
            int i;
            if (cards[0].getFace().equals(""2"") && cards[4].getFace().equals(""A""))
                CardOrder=facetointeger(cards[3].getFace())*4+suittointeger(cards[3].getSuit())+1;
            else 
                CardOrder=facetointeger(cards[4].getFace())*4+suittointeger(cards[4].getSuit())+1;
        }
        else if  (PairNumber()==2){
            HandOrder=2;
            if (cards[4].getFace().equals(cards[3].getFace()))
               CardOrder=facetointeger(cards[4].getFace())*4+suittointeger(cards[4].getSuit())+1;
            else
               CardOrder=facetointeger(cards[3].getFace())*4+suittointeger(cards[3].getSuit())+1;
        }
        else if (PairNumber()==1){
            HandOrder=1;
            if (cards[4].getFace().equals(cards[3].getFace()))
                CardOrder=facetointeger(cards[4].getFace())*4+suittointeger(cards[4].getSuit())+1;
            else if (cards[3].getFace().equals(cards[2].getFace()))
                CardOrder=facetointeger(cards[3].getFace())*4+suittointeger(cards[3].getSuit())+1;
            else if (cards[2].getFace().equals(cards[1].getFace()))
                CardOrder=facetointeger(cards[2].getFace())*4+suittointeger(cards[2].getSuit())+1;
            else
                CardOrder=facetointeger(cards[1].getFace())*4+suittointeger(cards[1].getSuit())+1;
        }
        else{
            HandOrder=0;
            CardOrder=facetointeger(cards[4].getFace())*4+suittointeger(cards[4].getSuit())+1;
        }
    } 
    public int compareTo(Hand that) {
        if (this.HandOrder>that.HandOrder) return 1;
        if (this.HandOrder<that.HandOrder) return -1;
        if (this.CardOrder>that.CardOrder) return 1;
        if (this.CardOrder<that.CardOrder) return -1;
        return 0; 
}  
}
