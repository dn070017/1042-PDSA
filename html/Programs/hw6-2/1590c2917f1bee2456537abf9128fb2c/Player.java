
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
     
    // TODO 
    public int compareTo(Player that) {
.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        int c1size = -1;
        int c2size = -1;
        int flag1 = -1;
        int flag2 = -1;
        //葫蘆
        if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[1].getFace().equals(this.cards[2].getFace())  
            &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 14;
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[2].getFace().equals(this.cards[3].getFace())  
            &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 14;
        //同花
        else if(this.cards[0].getSuit().equals(this.cards[1].getSuit())  &&  this.cards[1].getSuit().equals(this.cards[2].getSuit())  
            &&  this.cards[2].getSuit().equals(this.cards[3].getSuit())  && this.cards[3].getSuit().equals(this.cards[4].getSuit()))
            c1size = 13;
        //順子
        else if(this.cards[0].getFace().equals(""A"") && this.cards[1].getFace().equals(""K"")  && this.cards[2].getFace().equals(""Q"")
            && this.cards[3].getFace().equals(""J"") && this.cards[4].getFace().equals(""10""))
            c1size = 12;
        else if(this.cards[0].getFace().equals(""K"") && this.cards[1].getFace().equals(""Q"")  && this.cards[2].getFace().equals(""J"")
            && this.cards[3].getFace().equals(""10"") && this.cards[4].getFace().equals(""9""))
            c1size = 11;
        else if(this.cards[0].getFace().equals(""Q"") && this.cards[1].getFace().equals(""J"")  && this.cards[2].getFace().equals(""10"")
            && this.cards[3].getFace().equals(""9"") && this.cards[4].getFace().equals(""8""))
            c1size = 10;
        else if(this.cards[0].getFace().equals(""J"") && this.cards[1].getFace().equals(""10"")  && this.cards[2].getFace().equals(""9"")
            && this.cards[3].getFace().equals(""8"") && this.cards[4].getFace().equals(""7""))
            c1size = 9;
        else if(this.cards[0].getFace().equals(""10"") && this.cards[1].getFace().equals(""9"")  && this.cards[2].getFace().equals(""8"")
            && this.cards[3].getFace().equals(""7"") && this.cards[4].getFace().equals(""6""))
            c1size = 8;
        else if(this.cards[0].getFace().equals(""9"") && this.cards[1].getFace().equals(""8"")  && this.cards[2].getFace().equals(""7"")
            && this.cards[3].getFace().equals(""6"") && this.cards[4].getFace().equals(""5""))
            c1size = 7;
        else if(this.cards[0].getFace().equals(""8"") && this.cards[1].getFace().equals(""7"")  && this.cards[2].getFace().equals(""6"")
            && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""4""))
            c1size = 6;
        else if(this.cards[0].getFace().equals(""7"") && this.cards[1].getFace().equals(""6"")  && this.cards[2].getFace().equals(""5"")
            && this.cards[3].getFace().equals(""4"") && this.cards[4].getFace().equals(""3""))
            c1size = 5;
        else if(this.cards[0].getFace().equals(""6"") && this.cards[1].getFace().equals(""5"")  && this.cards[2].getFace().equals(""4"")
            && this.cards[3].getFace().equals(""3"") && this.cards[4].getFace().equals(""2""))
            c1size = 4;
        else if(this.cards[0].getFace().equals(""A"") && this.cards[1].getFace().equals(""5"")  && this.cards[2].getFace().equals(""4"")
            && this.cards[3].getFace().equals(""3"") && this.cards[4].getFace().equals(""2""))
            c1size = 3;
        //兩ㄆㄟ
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[2].getFace().equals(this.cards[3].getFace()))
            c1size = 2;
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 2;
        else if(this.cards[1].getFace().equals(this.cards[2].getFace())  &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 2;
        //一ㄆㄟ
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())){
            c1size = 1;
            flag1 = 0;}
        else if(this.cards[1].getFace().equals(this.cards[2].getFace())){
            c1size = 1;
            flag1 = 1;}
        else if(this.cards[2].getFace().equals(this.cards[3].getFace())){
            c1size = 1;
            flag1 = 2;}
        else if(this.cards[3].getFace().equals(this.cards[4].getFace())){
            c1size = 1;
            flag1 = 3;}
        //散牌
        else c1size = 0;
        
        //葫蘆
        if(that.cards[0].getFace().equals(that.cards[1].getFace())  &&  that.cards[1].getFace().equals(that.cards[2].getFace())  
            &&  that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 14;
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())  &&  that.cards[2].getFace().equals(that.cards[3].getFace())  
            &&  that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 14;
        //同花
        else if(that.cards[0].getSuit().equals(that.cards[1].getSuit())  &&  that.cards[1].getSuit().equals(that.cards[2].getSuit())  
            &&  that.cards[2].getSuit().equals(that.cards[3].getSuit())  && that.cards[3].getSuit().equals(that.cards[4].getSuit()))
            c2size = 13;
        //順子
        else if(that.cards[0].getFace().equals(""A"") && that.cards[1].getFace().equals(""K"")  && that.cards[2].getFace().equals(""Q"")
            && that.cards[3].getFace().equals(""J"") && that.cards[4].getFace().equals(""10""))
            c2size = 12;
        else if(that.cards[0].getFace().equals(""K"") && that.cards[1].getFace().equals(""Q"")  && that.cards[2].getFace().equals(""J"")
            && that.cards[3].getFace().equals(""10"") && that.cards[4].getFace().equals(""9""))
            c2size = 11;
        else if(that.cards[0].getFace().equals(""Q"") && that.cards[1].getFace().equals(""J"")  && that.cards[2].getFace().equals(""10"")
            && that.cards[3].getFace().equals(""9"") && that.cards[4].getFace().equals(""8""))
            c2size = 10;
        else if(that.cards[0].getFace().equals(""J"") && that.cards[1].getFace().equals(""10"")  && that.cards[2].getFace().equals(""9"")
            && that.cards[3].getFace().equals(""8"") && that.cards[4].getFace().equals(""7""))
            c2size = 9;
        else if(that.cards[0].getFace().equals(""10"") && that.cards[1].getFace().equals(""9"")  && that.cards[2].getFace().equals(""8"")
            && that.cards[3].getFace().equals(""7"") && that.cards[4].getFace().equals(""6""))
            c2size = 8;
        else if(that.cards[0].getFace().equals(""9"") && that.cards[1].getFace().equals(""8"")  && that.cards[2].getFace().equals(""7"")
            && that.cards[3].getFace().equals(""6"") && that.cards[4].getFace().equals(""5""))
            c2size = 7;
        else if(that.cards[0].getFace().equals(""8"") && that.cards[1].getFace().equals(""7"")  && that.cards[2].getFace().equals(""6"")
            && that.cards[3].getFace().equals(""5"") && that.cards[4].getFace().equals(""4""))
            c2size = 6;
        else if(that.cards[0].getFace().equals(""7"") && that.cards[1].getFace().equals(""6"")  && that.cards[2].getFace().equals(""5"")
            && that.cards[3].getFace().equals(""4"") && that.cards[4].getFace().equals(""3""))
            c2size = 5;
        else if(that.cards[0].getFace().equals(""6"") && that.cards[1].getFace().equals(""5"")  && that.cards[2].getFace().equals(""4"")
            && that.cards[3].getFace().equals(""3"") && that.cards[4].getFace().equals(""2""))
            c2size = 4;
        else if(that.cards[0].getFace().equals(""A"") && that.cards[1].getFace().equals(""5"")  && that.cards[2].getFace().equals(""4"")
            && that.cards[3].getFace().equals(""3"") && that.cards[4].getFace().equals(""2""))
            c2size = 3;
        //兩ㄆㄟ
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())  &&  that.cards[2].getFace().equals(that.cards[3].getFace()))
            c2size = 2;
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())  && that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 2;
        else if(that.cards[1].getFace().equals(that.cards[2].getFace())  &&  that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 2;
        //一ㄆㄟ
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())){
            c2size = 1;
            flag2 = 0;}
        else if(that.cards[1].getFace().equals(that.cards[2].getFace())){
            c2size = 1;
            flag2 = 1;}
        else if(that.cards[2].getFace().equals(that.cards[3].getFace())){
            c2size = 1;
            flag2 = 2;}
        else if(that.cards[3].getFace().equals(that.cards[4].getFace())){
            c2size = 1;
            flag2 = 3;}
        //散牌
        else c2size = 0;
        
        if(c1size>c2size) return +1;
        if(c1size<c2size) return -1;
        //c1size == c2size
        
        //都是葫蘆，直接比中間的大小
        if(c1size == 14) return this.cards[2].compareTo(that.cards[2]);
        //都是同花
        else if(c1size == 13) return this.cards[4].compareTo(that.cards[4]);
        //都是2ㄆㄟ
        else if(c1size == 2) return this.cards[3].compareTo(that.cards[3]);
        //都是1ㄆㄟ
        else if (c1size == 1) return this.cards[flag1].compareTo(that.cards[flag2]);
        //散牌
        else  return this.cards[4].compareTo(that.cards[4]);
        
       
        
            
    }
}

