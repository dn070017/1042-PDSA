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
        // 先檢查this
        // 葫蘆 012同&34同 or 01同&&234同 
        if(cards[0].getFaceGG()==cards[1].getFaceGG()&&cards[0].getFaceGG()==cards[2].getFaceGG()&&cards[3].getFaceGG()==cards[4].getFaceGG()) {
            thistype=7;
            thishigh=cards[0].getFaceGG();
        }
        else if(cards[0].getFaceGG()==cards[1].getFaceGG()&&cards[2].getFaceGG()==cards[3].getFaceGG()&&cards[2].getFaceGG()==cards[4].getFaceGG()) {
            thistype=7;
            thishigh=cards[2].getFaceGG();
        }
        // 同花
        else if(cards[0].getSuit().equals(cards[1].getSuit())&&cards[0].getSuit().equals(cards[2].getSuit())&&cards[0].getSuit().equals(cards[3].getSuit())&&cards[0].getSuit().equals(cards[4].getSuit())) {
            thistype=6;
            thishigh=cards[4].getFaceGG();
        }
        // 順子2-6~10~A
        else if(cards[0].getFaceGG()+1==cards[1].getFaceGG()&&cards[0].getFaceGG()+2==cards[2].getFaceGG()&&cards[0].getFaceGG()+3==cards[3].getFaceGG()&&cards[0].getFaceGG()+4==cards[4].getFaceGG()){
            thistype=5;            
            thishigh=cards[4].getFaceGG();
        }// 順子A1234
        else if(cards[0].getFaceGG()==2&&cards[1].getFaceGG()==3&&cards[2].getFaceGG()==3&&cards[3].getFaceGG()==4&&cards[4].getFaceGG()==14){
            thistype=5;            
            thishigh=4;
        }
        // 三條
        else if(cards[0].getFaceGG()==cards[1].getFaceGG()&&cards[0].getFaceGG()==cards[2].getFaceGG()){
            thistype=4;
            thishigh=cards[0].getFaceGG();
        }
        else if(cards[2].getFaceGG()==cards[4].getFaceGG()&&cards[3].getFaceGG()==cards[4].getFaceGG()){
            thistype=4;
            thishigh=cards[2].getFaceGG();
        }
        // 兩對 01+23 01+34 12+34
        else if(cards[0].getFaceGG()==cards[1].getFaceGG()&&cards[2].getFaceGG()==cards[3].getFaceGG()){
            thistype=3;
            if(cards[0].getFaceGG()>cards[2].getFaceGG())thishigh=cards[0].getFaceGG();
            else if(cards[0].getFaceGG()<cards[2].getFaceGG())thishigh=cards[2].getFaceGG();            
        }
        else if(cards[0].getFaceGG()==cards[1].getFaceGG()&&cards[4].getFaceGG()==cards[3].getFaceGG()){
            thistype=3;
            if(cards[0].getFaceGG()>cards[4].getFaceGG())thishigh=cards[0].getFaceGG();
            else if(cards[0].getFaceGG()<cards[4].getFaceGG())thishigh=cards[4].getFaceGG();            
        }
        else if(cards[2].getFaceGG()==cards[1].getFaceGG()&&cards[4].getFaceGG()==cards[3].getFaceGG()){
            thistype=3;
            if(cards[2].getFaceGG()>cards[4].getFaceGG())thishigh=cards[2].getFaceGG();
            else if(cards[2].getFaceGG()<cards[4].getFaceGG())thishigh=cards[4].getFaceGG();            
        }
        // 一對01 12 23 34
        else if(cards[0].getFaceGG()==cards[1].getFaceGG()){
            thistype=2;
            thishigh=cards[0].getFaceGG();
        }
        else if(cards[1].getFaceGG()==cards[2].getFaceGG()){
            thistype=2;
            thishigh=cards[1].getFaceGG();
        }
        else if(cards[2].getFaceGG()==cards[3].getFaceGG()){
            thistype=2;
            thishigh=cards[2].getFaceGG();
        }
        else if(cards[3].getFaceGG()==cards[4].getFaceGG()){
            thistype=2;
            thishigh=cards[3].getFaceGG();
        }
        // 單隻
        else {
            thistype=1;
            thishigh=cards[4].getFaceGG();
        }
        // 再檢查that
        // 葫蘆 012同&34同 or 01同&&234同 
       if(that.cards[0].getFaceGG()==that.cards[1].getFaceGG()&&that.cards[0].getFaceGG()==that.cards[2].getFaceGG()&&that.cards[3].getFaceGG()==that.cards[4].getFaceGG()) {
            thattype=7;
            thathigh=that.cards[0].getFaceGG();
        }
        else if(that.cards[0].getFaceGG()==that.cards[1].getFaceGG()&&that.cards[2].getFaceGG()==that.cards[3].getFaceGG()&&that.cards[2].getFaceGG()==that.cards[4].getFaceGG()) {
            thattype=7;
            thathigh=that.cards[2].getFaceGG();
        }
        // 同花
        else if(that.cards[0].getSuit().equals(that.cards[1].getSuit())&&that.cards[0].getSuit().equals(that.cards[2].getSuit())&&that.cards[0].getSuit().equals(that.cards[3].getSuit())&&that.cards[0].getSuit().equals(that.cards[4].getSuit())) {
            thattype=6;
            thathigh=that.cards[4].getFaceGG();
        }
        // 順子2-6~10~A
        else if(that.cards[0].getFaceGG()+1==that.cards[1].getFaceGG()&&that.cards[0].getFaceGG()+2==that.cards[2].getFaceGG()&&that.cards[0].getFaceGG()+3==that.cards[3].getFaceGG()&&that.cards[0].getFaceGG()+4==that.cards[4].getFaceGG()){
            thattype=5;            
            thathigh=that.cards[4].getFaceGG();
        }// 順子A1234
        else if(that.cards[0].getFaceGG()==2&&that.cards[1].getFaceGG()==3&&that.cards[2].getFaceGG()==3&&that.cards[3].getFaceGG()==4&&that.cards[4].getFaceGG()==14){
            thattype=5;            
            thathigh=4;
        }
        // 三條
        else if(that.cards[0].getFaceGG()==that.cards[1].getFaceGG()&&that.cards[0].getFaceGG()==that.cards[2].getFaceGG()){
            thattype=4;
            thathigh=that.cards[0].getFaceGG();
        }
        else if(that.cards[2].getFaceGG()==that.cards[4].getFaceGG()&&that.cards[3].getFaceGG()==that.cards[4].getFaceGG()){
            thattype=4;
            thathigh=that.cards[2].getFaceGG();
        }
        // 兩對 01+23 01+34 12+34
        else if(that.cards[0].getFaceGG()==that.cards[1].getFaceGG()&&that.cards[2].getFaceGG()==that.cards[3].getFaceGG()){
            thattype=3;
            if(that.cards[0].getFaceGG()>that.cards[2].getFaceGG())thathigh=that.cards[0].getFaceGG();
            else if(that.cards[0].getFaceGG()<that.cards[2].getFaceGG())thathigh=that.cards[2].getFaceGG();            
        }
        else if(that.cards[0].getFaceGG()==that.cards[1].getFaceGG()&&that.cards[4].getFaceGG()==that.cards[3].getFaceGG()){
            thattype=3;
            if(that.cards[0].getFaceGG()>that.cards[4].getFaceGG())thathigh=that.cards[0].getFaceGG();
            else if(that.cards[0].getFaceGG()<that.cards[4].getFaceGG())thathigh=that.cards[4].getFaceGG();            
        }
        else if(that.cards[2].getFaceGG()==that.cards[1].getFaceGG()&&that.cards[4].getFaceGG()==that.cards[3].getFaceGG()){
            thattype=3;
            if(that.cards[2].getFaceGG()>that.cards[4].getFaceGG())thathigh=that.cards[2].getFaceGG();
            else if(that.cards[2].getFaceGG()<that.cards[4].getFaceGG())thathigh=that.cards[4].getFaceGG();            
        }
        // 一對01 12 23 34
        else if(that.cards[0].getFaceGG()==that.cards[1].getFaceGG()){
            thattype=2;
            thathigh=that.cards[0].getFaceGG();
        }
        else if(that.cards[1].getFaceGG()==that.cards[2].getFaceGG()){
            thattype=2;
            thathigh=that.cards[1].getFaceGG();
        }
        else if(that.cards[2].getFaceGG()==that.cards[3].getFaceGG()){
            thattype=2;
            thathigh=that.cards[2].getFaceGG();
        }
        else if(that.cards[3].getFaceGG()==that.cards[4].getFaceGG()){
            thattype=2;
            thathigh=that.cards[3].getFaceGG();
        }
        // 單隻
        else {
            thattype=1;
            thathigh=that.cards[4].getFaceGG();
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
    public int getFaceGG(){
        if(this.face.equals(""A"")) return 14;
        else if(this.face.equals(""J"")) return 11;
        else if(this.face.equals(""Q"")) return 12;
        else if(this.face.equals(""K"")) return 13;
        else {
            int a=Integer.parseInt(this.face);
            return a;
        }
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


