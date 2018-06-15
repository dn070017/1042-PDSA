
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 
    public Card[] HighCard = new Card[1] ;
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards= new Card[5];
        
        return;
    }
 public boolean isFullHouse(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards);
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace()))
                    this.HighCard(Cards[0]) ;
                return true ;
            }
            else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                    this.HighCard(Cards[4]) ;
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean isFlush(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getSuit().equalsIgnoreCase(Cards[1].getSuit())){
            if(Cards[1].getSuit().equalsIgnoreCase(Cards[2].getSuit())){
                if(Cards[2].getSuit().equalsIgnoreCase(Cards[3].getSuit())){
                 if(Cards[3].getSuit().equalsIgnoreCase(Cards[4].getSuit())){
                        this.HighCard(Cards[4]) ;
            return true ;
                    }
                }
            }
        }
            return false ;
        }
    public boolean isStraight(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
      
        MergeX.sort(Cards);
        Integer[] a1 = new Integer[Cards.length] ;
        for(int i = 0 ; i < 5 ; i++){
            if(Cards[i].getFace().equalsIgnoreCase(""J""))
                a1[i] = 11 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""Q""))
                a1[i] = 12 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""K""))
                a1[i] = 13 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""A""))
                a1[i] = 14 ;
            else
            a1[i] = Integer.parseInt(Cards[i].getFace());            
        }
        if(a1[1]==a1[0]+1){
            if(a1[2] == a1[1]+1){
                if(a1[3]== a1[2]+1){
                    if(a1[4] == a1[3]+1){
                        this.HighCard(Cards[4]) ;
                        return true ;
                    }
                }
            }
        }
        else if(a1[0]==2){
            if(a1[1]==3){
                if(a1[2]==4){
                    if(a1[3]==5){
                        if(a1[4]==14){
                            this.HighCard(Cards[3]) ;
                            return true ;
                        }
                    }
                }
            }
        }
        return false ;
    }
    public boolean isTwoPair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                this.HighCard(Cards[3]) ;
                return true ;
            }
            else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
       }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
        }
        return false;
    }
    public boolean isOnePair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            this.HighCard(Cards[1]) ;
            return true ;
        }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            this.HighCard(Cards[2]) ;
            return true ;
        }
        else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
            this.HighCard(Cards[3]) ;
            return true ;
        }
        else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
            this.HighCard(Cards[4]) ;
            return true ;
        }
        
        return false ;
    }
    // TODO
    public int compareTo(Hand that) {
        int[] Points = new int [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                Points[0] = 30 ;
            }
            else if(this.isOnePair()){
                Points[0] = 20 ;
            
            }
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair()){
                Points[1] = 30 ;
            }
            else if(that.isOnePair()){
                Points[1] = 20 ;

    }
            else 
                Points[1] = 10 ;
            
//            System.out.println(Points[0]+"" ""+Points[1] ) ;
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0]==Points[1])
            if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
                }
            else 
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
            return 0 ;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}
;
