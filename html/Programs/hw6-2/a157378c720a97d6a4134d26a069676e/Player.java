
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
    public int fullhouse(){
        Card[] cards = new Card[5];
        cards = this.cards;
        MergeX.sort(cards);
        int[] oop = pokeToint(cards);
        if (cards[0].getFace() == cards[1].getFace() && cards[4].getFace() == cards[3].getFace()) {
            if (cards[2].getFace() == cards[1].getFace() | cards[2].getFace() == cards[3].getFace()) {
                return oop[2] * 100000;
            }
        }
        return 0;
    }
    public int flushh(){ 
        Card[] cards = new Card[5];
        cards= this.cards; 
        MergeX.sort(cards);
        int[] oop = pokesuitToint(cards); 
        int[] oopp = pokeToint(cards);
        String target = cards[0].getSuit();
        if(target==cards[1].getSuit()&&target==cards[2].getSuit()&&target==cards[3].getSuit()&&target==cards[4].getSuit())
        {return oopp[4]*10000;}
    return 0;
    }
    
    public int Straight(){
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
                return oopp[4]*1000;}}
                        }
            }        
        }  
    return 0;
    }
    public int pairs(){
        Card[] cards = new Card[5];
        cards= this.cards; 
        MergeX.sort(cards);
        int[] oopp = pokeToint(cards);
        int[] oop = pokesuitToint(cards); 
        int[] wateroh = new int[5];
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
        if(coco==2)
        {for(int i = 0;i<wateroh.length;i++)
        {
            if(wateroh[i]==1)
            {               
                return oopp[i]*10;
            }
           
        }
        }
        if(coco==4)
        {for(int i = 0;i<wateroh.length;i++)
        {
            if(wateroh[i]!=1)
                return oopp[i]*100;
        }
        }
       
        
    return 0;
    }
    // TODO 
    public int compareTo(Player that) {
.
        int point1 =0;
        int point2=0;
        point1 =this.fullhouse()+this.flushh()+this.Straight()+this.pairs();
        point2 = that.Straight()+that.flushh()+that.fullhouse()+that.pairs();
        if(point1==0)
        {
            Card[] card1 = this.cards;
            MergeX.sort(card1);
            int[] oopp = pokeToint(cards);
            int[] oop = pokesuitToint(cards); 
            point1 = oopp[4];       
        }
         if(point2==0)
        {
            Card[] card2 = that.cards;
            MergeX.sort(card2);
            int[] oopp = pokeToint(card2);
            int[] oop = pokesuitToint(card2); 
            point2 = oopp[4];       
        }
          //System.out.println(point1+""          ""+point2);
         if(point1>point2)
             return 1;
         else if(point1<point2)
             return -1;
         else if(point1==point2)
             return(0);
        
        return 0;
    }
    public static void main(String[] args) {
       Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     Card[] fuck1 = new Card[5] ;
     fuck[0] = new Card(""10"",""Spades"") ;
     fuck[1] = new Card(""10"",""Hearts"") ;
     fuck[2] = new Card(""10"",""Diamonds"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""5"",""Clubs"") ;
     a[0].setCards(fuck);
     fuck1[0] = new Card(""4"",""Spades"") ;
     fuck1[1] = new Card(""K"",""Hearts"") ;
     fuck1[2] = new Card(""J"",""Clubs"") ;
     fuck1[3] = new Card(""3"",""Spades"") ;
     fuck1[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck1);
 
    // System.out.println(a[1].compareTo(a[0]));
    // System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}
}

