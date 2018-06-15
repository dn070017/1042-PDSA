
import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards;
    private int[] face=new int[5];
    private int[] suit=new int[5];
    private int cardSuit;

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards) {
        this.cards=cards;
        Arrays.sort(cards);
    }

    // TODO
    public int compareTo(Hand that) {
        this.getFace(this);//算好我的每一張牌的數字
        that.getFace(that);//算好你的每一張牌的數字
        this.getSuit(this);//算好我的每一張牌的花色
        that.getSuit(that);//算好你的每一張牌的花色
        this.handCard();//算好我的牌是什麼牌
        that.handCard();//算好你的牌是什麼牌
        if(this.cardSuit>that.cardSuit) return 1;//我的牌組合比你大
        if(this.cardSuit<that.cardSuit) return -1;//你的牌組合比我大
        //平手
        if(this.cardSuit==5){//如果兩個都是桐花
            return this.cards[4].compareTo(that.cards[4]);//比較最大的那張牌的大小
        }
        if(this.cardSuit==6){//兩個都是蘆
            return this.cards[2].compareTo(that.cards[2]);//蘆的話，比較中間那張牌就可以了(很酷的想法吧)            
        }
        if(this.cardSuit==4){//如果兩個都是順的話
            if(this.face[4]==14&&this.face[0]==2&&that.face[4]==14&&that.face[0]==2)//如果兩個都是A2345
                return this.cards[3].compareTo(that.cards[3]);//比較第四張牌，也就是5
            return this.cards[4].compareTo(that.cards[4]);//其餘的就是比較第五張牌
        }
        if(this.cardSuit==2||this.cardSuit==1){//兩個都是2pair或1pair
            Card myCard=null,yourCard=null;
            for(int i=1;i<5;i++){
                if(this.face[i]==this.face[i-1]) myCard=this.cards[i];
                if(that.face[i]==that.face[i-1]) yourCard=that.cards[i];
            }
            return myCard.compareTo(yourCard);
        }       
        return this.cards[4].compareTo(that.cards[4]);//剩下的case就是兩個都是雜牌
    }

    private void getFace(Hand hh) {//看數字        
        for (int i = 0; i < 5; i++) {
            switch (hh.cards[i].getFace()) {
                case ""A"":
                    hh.face[i] = 14;
                    break;
                case ""K"":
                    hh.face[i] = 13;
                    break;
                case ""Q"":
                    hh.face[i] = 12;
                    break;
                case ""J"":
                    hh.face[i] = 11;
                    break;
                default:
                    face[i] = Integer.valueOf(hh.cards[i].getFace());
                    break;
            }
        }
    }

    private void getSuit(Hand hh) {
        this.suit = new int[5];
        for (int i = 0; i < 5; i++) {
            switch (hh.cards[i].getSuit()) {
                case ""Spades"":
                    suit[i] = 4;//黑桃
                    break;
                case ""Hearts"":
                    suit[i] = 3;//愛心
                    break;
                case ""Diamonds"":
                    suit[i] = 2;//方塊
                    break;
                default:
                    suit[i] = 1;//梅花
                    break;
            }
        }
    }

    private void handCard() {//檢查花色
        if (this.checkFlush()) {
            this.cardSuit = 5;//同花=5
        } else if (this.checkStright()) {
            this.cardSuit = 4;//順=4
        } else {
            this.cardSuit = this.checkPair();
        }
    }

    private boolean checkFlush() {//檢查是否為桐花
        for (int i = 0; i < 4; i++) {
            if (this.suit[i] != this.suit[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkStright() {//順
        if (this.face[0] == 2 && this.face[1] == 3 && this.face[2] == 4 && this.face[3] == 5 && this.face[4] == 14) {
            return true;//A2345
        }
        for (int i = 0; i < 4; i++) {
            if (this.face[i + 1] - this.face[i] != 1) {
                return false;
            }
        }
        return true;
    }//先看是不是A2345在看是不是一班的順子

    private int checkPair() {//檢查蘆、2胚、1胚或是雜牌
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (this.face[i] == this.face[i + 1]) {
                count++;
            }
        }
        if (count == 3) {//蘆或鐵支
            if (this.face[0] == this.face[3] || this.face[1] == this.face[4]) {
                return 1;//在這裡鐵支算是1胚
            }
            return 6;//蘆
        }
        if (count == 2) {//2胚或3條
            if (this.face[0] == this.face[2] || this.face[1] == this.face[3] || this.face[2] == this.face[4]) {
                return 1;//在這裡三條算是1胚
            }
        }
        if (count == 1) {
            return 1;//一胚
        }
        return 0;//雜牌
    }

    // Do not modified this function
    public Card[] getCards() {
        return this.cards;
    }
}

