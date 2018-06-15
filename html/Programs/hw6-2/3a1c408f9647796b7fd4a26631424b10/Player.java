
import java.util.*;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author YuChing
 */
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
    public int[] pokeToint(Card[] cards){     
        int[] oop = new int[5];
        for (int i=0;i<cards.length;i++)
        {
        String b1 = cards[i].getFace();      
        switch (b1) {
            case ""A"":
                oop[i] = 14;
                break;
            case ""J"":
                oop[i] = 11;
                break;
            case ""Q"":
                oop[i] = 12;
                break;
            case ""K"":
                oop[i] = 13;
                break;
            default:
                oop[i] = Integer.parseInt(b1);
        }  
        }
    return oop;
    }
    public int[] pokesuitToint(Card[] cards){
    int[] oop = new int[5];
        for (int i=0;i<cards.length;i++)
        {
        String b1 = cards[i].getSuit();
        switch (b1) {
             case ""Spades"":
                    oop[i] = 4;
                    break;
                case ""Hearts"":
                    oop[i] = 3;
                    break;
                case ""Diamonds"":
                    oop[i] = 2;
                    break;
                case ""Clubs"":
                    oop[i] = 1;
                    break; 
        }  
        }
    
    return oop;
    }
    public double fullhouse(){
        Card[] cards = new Card[5];
        cards = this.cards;
        MergeX.sort(cards);
        int[] oop = pokeToint(cards);
        int[] p =  pokesuitToint(cards); 
        if (cards[0].getFace() == cards[1].getFace() && cards[4].getFace() == cards[3].getFace()) {
            if (cards[2].getFace() == cards[1].getFace() | cards[2].getFace() == cards[3].getFace()) {
                return (oop[2] * 100000)+(0.1*p[2]);
            }
        }
        return 0;
    }
    public double flushh(){ 
        Card[] cards = new Card[5];
        cards= this.cards; 
        MergeX.sort(cards);
        int[] oop = pokesuitToint(cards); 
        int[] oopp = pokeToint(cards);
        String target = cards[0].getSuit();
        if(target==cards[1].getSuit()&&target==cards[2].getSuit()&&target==cards[3].getSuit()&&target==cards[4].getSuit())
        {return (oopp[4]*10000)+(oop[4]*0.1);}
    return 0;
    }
    
    public double Straight(){
        Card[] cards = new Card[5];
        cards= this.cards; 
        MergeX.sort(cards);
        int[] oopp = pokeToint(cards);
        int[] oop = pokesuitToint(cards); 
        for (int i=0;i<cards.length;i++)
        {
     // System.out.println(oopp[i]);
        }
        if(oopp[0]<=10){
            if(oopp[1]==(oopp[0]+1)) {
                if(oopp[2]==(oopp[1]+1)){
                if(oopp[3]==(oopp[2]+1)){
                if(oopp[4]==(oopp[3]+1)){
                return (oopp[4]*1000)+oop[4]*0.1;}}
                        }
            }        
        }  
    return 0;
    }
    public double pairs(){
        Card[] cards = new Card[5];
        cards= this.cards; 
        MergeX.sort(cards);
        int[] oopp = pokeToint(cards);
        int[] oop = pokesuitToint(cards); 
        int[] wateroh = new int[5];
        for(int i = 0;i<cards.length;i++)
        {
            //System.out.println(""suit""+oop[i]+""int""+oopp[i]);
        }
        for(int i = 0;i<cards.length-1;i++)
        {if(oopp[i]==oopp[i+1])
        {wateroh[i]=1;
        wateroh[i+1] =1;}       
        }
        int coco=0;
        for(int i = 0;i<wateroh.length;i++)
        {
            coco=coco+wateroh[i];
        }
      //  System.out.println(coco);
        double[] oo =new double[5];
        int a = 0;
        int y =0;
        if(coco==2)
        {for(int i = 0;i<wateroh.length;i++)
        {
            
            if(wateroh[i]==1)
            {          
           a = oopp[i];
           y = oop[i+1];
          break;
            }
           
        }
    return a*1000+y*0.1;
        }
        
        if(coco==4)
        {for(int i = 0;i<wateroh.length;i++)
        {
            if(wateroh[i]!=1)
            {if(i==0)
               return (oop[4]*0.1)+(oopp[4]*100000)+(oopp[2]*100)+(oop[2]*0.01);
            if(i==2)
               return (oop[4]*0.1)+(oopp[4]*100000)+(oopp[1]*100)+(oop[1]*0.01);
            if(i==4)
               return (oop[3]*0.1)+(oopp[3]*100000)+(oopp[1]*100)+(oop[1]*0.01);} }
            //return (oopp[i]*100000)+a*0.1;}
        
        }
       
        
    return 0;
    }
    // TODO 
    public int compareTo(Player that) {
.
        double point1 =0;
        double point2 =0;
        if(this.fullhouse()!=0|that.fullhouse()!=0){
        point1 =this.fullhouse();
        point2 =that.fullhouse();
         if(point1>point2)
             return 1;
         else if(point1<point2)
             return -1;
         else if(point1==point2)
             return 0;}
       else if(this.flushh()!=0|that.flushh()!=0){
        point1 =this.flushh();
        point2 =that.flushh();
         if(point1>point2)
             return 1;
         else if(point1<point2)
             return -1;
         else if(point1==point2)
             return 0;}
       else if(this.Straight()!=0|that.Straight()!=0){
        point1 =this.Straight();
        point2 =that.Straight();
         if(point1>point2)
             return 1;
         else if(point1<point2)
             return -1;
         else if(point1==point2)
             return 0;}
       else if(this.pairs()!=0|that.pairs()!=0){
        point1 =this.pairs();
        point2 =that.pairs();
        System.out.println(point1+""        ""+point2);
         if(point1>point2)
             return 1;
         else if(point1<point2)
             return -1;
         else if(point1==point2)
             return 0;}
       else{
        Card[] cards = new Card[5];
        cards= this.cards; 
        MergeX.sort(cards);
        int[] oopp = pokeToint(cards);
        int[] oop = pokesuitToint(cards); 
       Card[] cards2 = new Card[5];
        cards2= that.cards; 
        MergeX.sort(cards2);
        int[] oopp2 = pokeToint(cards2);
        int[] oop2 = pokesuitToint(cards2); 
        point1 = oopp[4]+0.1*oop[4];
        point2=oopp2[4]+0.1*oop2[4];
      //   System.out.println(point1+""        ""+point2);
        
       if(point1>point2)
             return 1;
         else if(point1<point2)
             return -1;
         else if(point1==point2)
             return 0;
       
       }
        return 0;
    }
    public static void main(String[] args) {
       Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     Card[] fuck1 = new Card[5] ;
     fuck[0] = new Card(""5"",""Spades"") ;
     fuck[1] = new Card(""A"",""Spades"") ;
     fuck[2] = new Card(""A"",""Diamonds"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""5"",""Clubs"") ;
     a[0].setCards(fuck);
     fuck1[0] = new Card(""4"",""Spades"") ;
     fuck1[1] = new Card(""Q"",""Hearts"") ;
     fuck1[2] = new Card(""A"",""Spades"") ;
     fuck1[3] = new Card(""4"",""Spades"") ;
     fuck1[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck1);
       // double y = a[0].pairs();
 
     System.out.println(a[0].compareTo(a[1]));
    // System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}
}

