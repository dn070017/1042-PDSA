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
    public int Faceint(Card c){
          if(c.getFace().equals(""A"")) return 14;
          else if(c.getFace().equals(""K"")) return 13;
          else if(c.getFace().equals(""Q"")) return 12;
          else if(c.getFace().equals(""J"")) return 11;
          return Integer.parseInt(c.getFace());} 
    // TODO 
    public int compareTo(Player that) {
.
        //sort from small to large
         Arrays.sort(this.cards);
         Arrays.sort(that.cards);
         //full house > 3+2 
         if ((this.cards[2].getFace().equals(this.cards[0].getFace())|this.cards[2].getFace().equals(this.cards[4].getFace()))|that.cards[2].getFace().equals(that.cards[0].getFace())|that.cards[2].getFace().equals(that.cards[4].getFace()) ){
             if((this.cards[2].getFace().equals(this.cards[0].getFace())|this.cards[2].getFace().equals(this.cards[4].getFace()))&(that.cards[2].getFace().equals(that.cards[0].getFace())|that.cards[2].getFace().equals(that.cards[4].getFace()))){
                return this.cards[2].compareTo(that.cards[2]); //both are full house and compare face&suit
             }
             else if ((this.cards[2].getFace().equals(this.cards[0].getFace())|this.cards[2].getFace().equals(this.cards[4].getFace()))){
                return +1; //only this is full house
             }
             else return -1;// only that is full house
         }
         //flush > same suit
         boolean x0=true;
         boolean x1=true;
         for(int i=1;i<5;i++){
           if(!this.cards[0].getSuit().equals(this.cards[i].getSuit())) {x0=false;}
           if(!that.cards[0].getSuit().equals(that.cards[i].getSuit())) {x1=false;}}//unless everyone same
         if (x0|x1){
           if (x0&x1) {return this.cards[4].compareTo(that.cards[4]);}
           else if (x0){return +1;}//只有x0是flush
           else {return -1;}}//只有x1是flush}
         
         //straight > 
         x0=true;
         x1=true;
         for(int i=0;i<4;i++){
           if(Faceint(this.cards[i])!=(Faceint(this.cards[i+1])-1)) {x0=false;}
           if(Faceint(that.cards[i])!=(Faceint(that.cards[i+1])-1)) {x1=false;}}//unless everyone continuous
         //if(x0){maxs0=Faceint(this.cards[4]);}
         //if(x1){maxs1=Faceint(that.cards[4]);}
         if(this.cards[4].getFace().equals(""A"")&this.cards[0].getFace().equals(""2"")){
             x0=true;
             for(int i=0;i<4;i++){
                 if(Faceint(this.cards[i])!=i+2){x0=false;}
             }}
         if(that.cards[4].getFace().equals(""A"")&that.cards[0].getFace().equals(""2"")){
             x1=true;
             for(int i=0;i<4;i++){
                 if(Faceint(that.cards[i])!=i+2){x1=false;}
             }}
         if(x0|x1){
             if(x0 & x1){
                 if(this.cards[4].getFace().equals(""A"")) {return -1;}
                 else if(that.cards[4].getFace().equals(""A"")) {return +1;}
                 else return this.cards[4].compareTo(that.cards[4]);}  
             else if(x0){return +1;} // this straight
             else {return -1;}//that straight
         } 
         
         //two pair >
         x0=false;
         x1=false;
         int pair0=0;
         int pair1=0; 
         Card[] pairs = new Card[2];
         //Card pairn0=new Card(name, name);
         for(int i=0;i<4;i++){
           if(this.cards[i].getFace().equals(this.cards[i+1].getFace())){
               pairs[0] = this.cards[i];
               pair0++;}
           if(that.cards[i].getFace().equals(that.cards[i+1].getFace())){
               pairs[1] = that.cards[i];
               //Card p1 = new Card(that.cards[i].getFace(),that.cards[i].getSuit());
               pair1++;}
           }
         if(pair0==2){x0=true;}
         if(pair1==2){x1=true;}
         //Card p1 = new Card(""3"",""Hearts"");
         //Card p2= new Card(""J"",""Clubs""); 
         //int K = p1.compareTo(p2);
         if(x0|x1){
            if(x0 & x1) {return pairs[0].compareTo(pairs[1]);}
            else if(x0) {return +1;}
            else {return -1;}
         }
         //one pair > 
         if(pair0==1){x0=true;}
         if(pair1==1){x1=true;}
         if(x0|x1){
            if(x0 & x1) {return pairs[0].compareTo(pairs[1]);}
            else if(x0) {return +1;}
            else {return -1;}
         }
         //high card
         return this.cards[4].compareTo(that.cards[4]);
    }

}
