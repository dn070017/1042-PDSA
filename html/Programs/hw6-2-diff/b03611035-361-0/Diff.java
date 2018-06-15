import java.util.Arrays;
import java.util.Comparator;

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
        // 先確定牌型，用int來存
        int thistype;
        int thattype;
        // 要存最大牌
        int thishigh=0;
        int thathigh=0;
        
        
        // 先sort牌
        Arrays.sort(cards);
        Arrays.sort(that.cards);
        int[] thiscard=new int[5];
        int[] thatcard=new int[5];
        for(int i=0;i<5;i++){
            if(cards[i].getFace().equals(""A""))thiscard[i]=14;
            else if(cards[i].getFace().equals(""J""))thiscard[i]=11;
            else if(cards[i].getFace().equals(""Q""))thiscard[i]=12;
            else if(cards[i].getFace().equals(""K""))thiscard[i]=13;
            else thiscard[i]=Integer.parseInt(cards[i].getFace());
            if(that.cards[i].getFace().equals(""A""))thatcard[i]=14;
            else if(that.cards[i].getFace().equals(""J""))thatcard[i]=11;
            else if(that.cards[i].getFace().equals(""Q""))thatcard[i]=12;
            else if(that.cards[i].getFace().equals(""K""))thatcard[i]=13;
            else thatcard[i]=Integer.parseInt(that.cards[i].getFace());
        }
        // 先檢查this
        // 葫蘆 012同&34同 or 01同&&234同 
        if(thiscard[0]==thiscard[1]&&thiscard[0]==thiscard[2]&&thiscard[3]==thiscard[4]) {
            thistype=7;
            thishigh=thiscard[0];
        }
        else if(thiscard[0]==thiscard[1]&&thiscard[2]==thiscard[3]&&thiscard[2]==thiscard[4]) {
            thistype=7;
            thishigh=thiscard[2];
        }
        // 同花
        else if(cards[0].getSuit().equals(cards[1].getSuit())&&cards[0].getSuit().equals(cards[2].getSuit())&&cards[0].getSuit().equals(cards[3].getSuit())&&cards[0].getSuit().equals(cards[4].getSuit())) {
            thistype=6;
            thishigh=thiscard[4];
        }
        // 順子2-6~10~A
        else if(thiscard[0]+1==thiscard[1]&&thiscard[0]+2==thiscard[2]&&thiscard[0]+3==thiscard[3]&&thiscard[0]+4==thiscard[4]){
            thistype=5;            
            thishigh=thiscard[4];
        }// 順子A1234
        else if(thiscard[0]==2&&thiscard[1]==3&&thiscard[2]==3&&thiscard[3]==4&&thiscard[4]==14){
            thistype=5;            
            thishigh=4;
        }
        // 三條
        else if(thiscard[0]==thiscard[1]&&thiscard[0]==thiscard[2]){
            thistype=4;
            thishigh=thiscard[0];
        }
        else if(thiscard[2]==thiscard[4]&&thiscard[3]==thiscard[4]){
            thistype=4;
            thishigh=thiscard[2];
        }
        // 兩對 01+23 01+34 12+34
        else if(thiscard[0]==thiscard[1]&&thiscard[2]==thiscard[3]){
            thistype=3;
            if(thiscard[0]>thiscard[2])thishigh=thiscard[0];
            else if(thiscard[0]<thiscard[2])thishigh=thiscard[2];            
        }
        else if(thiscard[0]==thiscard[1]&&thiscard[4]==thiscard[3]){
            thistype=3;
            if(thiscard[0]>thiscard[4])thishigh=thiscard[0];
            else if(thiscard[0]<thiscard[4])thishigh=thiscard[4];            
        }
        else if(thiscard[2]==thiscard[1]&&thiscard[4]==thiscard[3]){
            thistype=3;
            if(thiscard[2]>thiscard[4])thishigh=thiscard[2];
            else if(thiscard[2]<thiscard[4])thishigh=thiscard[4];            
        }
        // 一對01 12 23 34
        else if(thiscard[0]==thiscard[1]){
            thistype=2;
            thishigh=thiscard[0];
        }
        else if(thiscard[1]==thiscard[2]){
            thistype=2;
            thishigh=thiscard[1];
        }
        else if(thiscard[2]==thiscard[3]){
            thistype=2;
            thishigh=thiscard[2];
        }
        else if(thiscard[3]==thiscard[4]){
            thistype=2;
            thishigh=thiscard[3];
        }
        // 單隻
        else {
            thistype=1;
            thishigh=thiscard[4];
        }
        // 再檢查that
        // 葫蘆 012同&34同 or 01同&&234同 
       if(thatcard[0]==thatcard[1]&&thatcard[0]==thatcard[2]&&thatcard[3]==thatcard[4]) {
            thattype=7;
            thathigh=thatcard[0];
        }
        else if(thatcard[0]==thatcard[1]&&thatcard[2]==thatcard[3]&&thatcard[2]==thatcard[4]) {
            thattype=7;
            thathigh=thatcard[2];
        }
        // 同花
        else if(that.cards[0].getSuit().equals(that.cards[1].getSuit())&&that.cards[0].getSuit().equals(that.cards[2].getSuit())&&that.cards[0].getSuit().equals(that.cards[3].getSuit())&&that.cards[0].getSuit().equals(that.cards[4].getSuit())) {
            thattype=6;
            thathigh=thatcard[4];
        }
        // 順子2-6~10~A
        else if(thatcard[0]+1==thatcard[1]&&thatcard[0]+2==thatcard[2]&&thatcard[0]+3==thatcard[3]&&thatcard[0]+4==thatcard[4]){
            thattype=5;            
            thathigh=thatcard[4];
        }// 順子A1234
        else if(thatcard[0]==2&&thatcard[1]==3&&thatcard[2]==3&&thatcard[3]==4&&thatcard[4]==14){
            thattype=5;            
            thathigh=4;
        }
        // 三條
        else if(thatcard[0]==thatcard[1]&&thatcard[0]==thatcard[2]){
            thattype=4;
            thathigh=thatcard[0];
        }
        else if(thatcard[2]==thatcard[4]&&thatcard[3]==thatcard[4]){
            thattype=4;
            thathigh=thatcard[2];
        }
        // 兩對 01+23 01+34 12+34
        else if(thatcard[0]==thatcard[1]&&thatcard[2]==thatcard[3]){
            thattype=3;
            if(thatcard[0]>thatcard[2])thathigh=thatcard[0];
            else if(thatcard[0]<thatcard[2])thathigh=thatcard[2];            
        }
        else if(thatcard[0]==thatcard[1]&&thatcard[4]==thatcard[3]){
            thattype=3;
            if(thatcard[0]>thatcard[4])thathigh=thatcard[0];
            else if(thatcard[0]<thatcard[4])thathigh=thatcard[4];            
        }
        else if(thatcard[2]==thatcard[1]&&thatcard[4]==thatcard[3]){
            thattype=3;
            if(thatcard[2]>thatcard[4])thathigh=thatcard[2];
            else if(thatcard[2]<thatcard[4])thathigh=thatcard[4];            
        }
        // 一對01 12 23 34
        else if(thatcard[0]==thatcard[1]){
            thattype=2;
            thathigh=thatcard[0];
        }
        else if(thatcard[1]==thatcard[2]){
            thattype=2;
            thathigh=thatcard[1];
        }
        else if(thatcard[2]==thatcard[3]){
            thattype=2;
            thathigh=thatcard[2];
        }
        else if(thatcard[3]==thatcard[4]){
            thattype=2;
            thathigh=thatcard[3];
        }
        // 單隻
        else {
            thattype=1;
            thathigh=thatcard[4];
        }
        // 比牌型    
        if(thistype>thattype)return 1;
        else if(thistype<thattype)return -1;
        // 再比最大牌
        else{
            if(thishigh>thathigh)return 1;
            else if(thishigh<thathigh)return -1;
            else return 0;
        }
        
    }
}


class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }
     
          // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
     // TODO
    public int compareTo(Card that) {//前<後-1 前>後1 
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        //先檢查face再檢查suit
        //先把face換成int
        int thisface;
        int thatface;
        if(this.face.equals(""A"")) thisface=14;
        else if(this.face.equals(""J"")) thisface=11;
        else if(this.face.equals(""Q"")) thisface=12;
        else if(this.face.equals(""K"")) thisface=13;
        else thisface=Integer.parseInt(this.face);
        if(that.face.equals(""A"")) thatface=14;
        else if(that.face.equals(""J"")) thatface=11;
        else if(that.face.equals(""Q"")) thatface=12;
        else if(that.face.equals(""K"")) thatface=13;
        else thatface=Integer.parseInt(that.face);
        //檢查face
        if(thisface>thatface) return 1;
        else if(thisface<thatface) return -1;
        else{//檢查suit            
            int face1 = 0;
            int face2 = 0;
            Card c1=this;
            Card c2=that;
            if(""Spades"".equals(c1.suit))face1=4;
            else if(""Hearts"".equals(c1.suit))face1=3;
            else if(""Diamonds"".equals(c1.suit))face1=2;
            else if(""Clubs"".equals(c1.suit))face1=1;
            if(""Spades"".equals(c2.suit))face2=4;
            else if(""Hearts"".equals(c2.suit))face2=3;
            else if(""Diamonds"".equals(c2.suit))face2=2;
            else if(""Clubs"".equals(c2.suit))face2=1;
            if(face1>face2)return 1;
            else if(face1<face2)return -1;
            else return 0;
        }
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            int face1 = 0;
            int face2 = 0;
            if(""Spades"".equals(c1.suit))face1=4;
            else if(""Hearts"".equals(c1.suit))face1=3;
            else if(""Diamonds"".equals(c1.suit))face1=2;
            else if(""Clubs"".equals(c1.suit))face1=1;
            if(""Spades"".equals(c2.suit))face2=4;
            else if(""Hearts"".equals(c2.suit))face2=3;
            else if(""Diamonds"".equals(c2.suit))face2=2;
            else if(""Clubs"".equals(c2.suit))face2=1;
            if(face1>face2)return 1;
            else if(face1<face2)return -1;
            else return 0;
        }
    }   
}


