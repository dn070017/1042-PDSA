public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    public Card[] HighCard = new Card[1] ;
    
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
     
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
    public int compareTo(Player that) {
        
.
        
            Integer[] Points = new Integer [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                System.out.println(""yee"");
                Points[0] = 30 ;
            }
            else if(this.isOnePair())
                Points[0] = 20 ;
            
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair())
                Points[1] = 30 ;
            
            else if(that.isOnePair())
                Points[1] = 20 ;
            
            else 
                Points[1] = 10 ;
            
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0].equals(Points[1]))
                if(Points[0]==60){
                    if(this.HighCard[0].compareTo(that.HighCard[0])==1){
                        return 1 ;
                    }
                    else if(that.HighCard[0].compareTo(that.HighCard[0])==1){
                        return -1 ;
                    }
                }
                else if(Points[0] == 50){
                    if(this.HighCard[0].compareTo(that.HighCard[0])==1){
                        return 1 ;
                    }
                    else if(that.HighCard[0].compareTo(that.HighCard[0])==1){
                        return -1 ;
                    }
                } 
                else if(Points[0] == 40){
                    if(this.HighCard[0].compareTo(that.HighCard[0])==1){
                        return 1 ;
                    }
                    else if(that.HighCard[0].compareTo(that.HighCard[0])==1){
                        return -1 ;
                    }
                }
            else if(Points[0] == 30){
                    if(this.HighCard[0].compareTo(that.HighCard[0])==1){
                        return 1 ;
                    }
                    else if(that.HighCard[0].compareTo(that.HighCard[0])==1){
                        return -1 ;
                    }
                }
            else if(Points[0] == 20){
                    if(this.HighCard[0].compareTo(that.HighCard[0])==1){
                        return 1 ;
                    }
                    else if(that.HighCard[0].compareTo(that.HighCard[0])==1){
                        return -1 ;
                    }
                }
            else if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
               
                    if(this.HighCard[0].compareTo(that.HighCard[0])==1){
                        return 1 ;
                    }
                    else if(that.HighCard[0].compareTo(that.HighCard[0])==1){
                        return -1 ;
                    }
                }   
            return 0 ;
    }
//   
     public static void main(String[] args) {
     Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     fuck[0] = new Card(""10"",""Spades"") ;
     fuck[1] = new Card(""7"",""Hearts"") ;
     fuck[2] = new Card(""6"",""Diamonds"") ;
     fuck[3] = new Card(""5"",""Spades"") ;
     fuck[4] = new Card(""Q"",""Spades"") ;
     a[0].setCards(fuck);
     fuck[0] = new Card(""A"",""Spades"") ;
     fuck[1] = new Card(""10"",""Hearts"") ;
     fuck[2] = new Card(""8"",""Spades"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck);
     
     System.out.println(fuck[1].compareTo(fuck[0]));
}
}


