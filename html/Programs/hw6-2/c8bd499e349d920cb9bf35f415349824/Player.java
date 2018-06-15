import java.util.Arrays;
import static java.util.Arrays.sort;

public class Player implements Comparable<Player> {

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
        int c1 = this.cardsConbination(this.cards);
        int c2 = that.cardsConbination(that.cards);
        if (c1 > c2) {
            return 1;
        }
        if (c1 < c2) {
            return -1;
        } else {//兩者排大小一樣，比較數字
            if (c1 == 6) {
                return this.cards[this.maxFullhouse(this.cards)].compareTo(that.cards[that.maxFullhouse(that.cards)]);
            } else if (c1 == 5) {//兩者都是同花
                int s1 = this.compareSuit(this.cards[0]);//我的排的花色
                int s2 = that.compareSuit(that.cards[0]);//對手排的花色
                if (s1 > s2) {
                    return 1;
                } else if (s1 < s2) {
                    return -1;
                } else {
                    return this.cards[cards.length - 1].compareTo(that.cards[cards.length - 1]);//兩個同花相同比較最後一張牌的大小
                }
            } else if (c1 == 4) {//兩者都是順子
                if (this.cards[cards.length - 1].getFace().equals(""A"")|| that.cards[cards.length - 1].getFace().equals(""A"") ) {//如果兩個人有人有A
                    if(this.cards[0].getFace().equals(""2"")&&that.cards[0].getFace().equals(""2"")){//兩個人都是A2345
                        return this.cards[cards.length - 2].compareTo(that.cards[cards.length - 2]);
                    }
                    else if(this.cards[0].getFace().equals(""2"")&&that.cards[0].getFace().equals(""10"")){//我是A2345，你是10JQKA
                        return -1;
                    }
                    else if(this.cards[0].getFace().equals(""10"")&&that.cards[0].getFace().equals(""2"")){//我是10JQKA，你是A2345
                        return 1;
                    }
                    else {//兩個人都是10JQKA
                        return this.cards[cards.length - 1].compareTo(that.cards[cards.length - 1]);
                    }                    
                } else {
                    return this.cards[cards.length - 1].compareTo(that.cards[cards.length - 1]);//比較最後一張牌
                }
            } else if (c1 == 3 || 1 == 2) {//1pair或2pair
                return this.cards[this.maxPair(this.cards)].compareTo(that.cards[that.maxPair(that.cards)]);
            } else {//兩者都是雜牌
                return this.cards[cards.length - 1].compareTo(that.cards[cards.length - 1]);//比較最後一張牌的大小
            }
        }
    }

    public int compareSuit(Card card) {//回傳花色
        if (card.getSuit().equals(""Spades"")) {
            return 4;
        }
        if (card.getSuit().equals(""Hearts"")) {
            return 3;
        }
        if (card.getSuit().equals(""Diamonds"")) {
            return 2;
        }
        if (card.getSuit().equals(""Clubs"")) {
            return 1;
        } else {
            return 0;
        }
    }

    public int cardsConbination(Card[] card) {//回傳牌的組合
        if (Player.suitCompare(card)) {
            return 5;//同花
        }
        if (Player.stright(card)) {
            return 4;//順子
        } else {
            return Player.howManyPair(card);//蘆、2pairs、1pair、雜牌
        }
    }

    public static int compareFace(Card card) {//回傳數字
        if (card.getFace().equals(""A"")) {
            return 14;
        }
        if (card.getFace().equals(""K"")) {
            return 13;
        }
        if (card.getFace().equals(""Q"")) {
            return 12;
        }
        if (card.getFace().equals(""J"")) {
            return 11;
        } else {
            return Integer.valueOf(card.getFace());
        }
    }

    public static int maxFullhouse(Card[] card) {//回傳蘆中三個一樣的數中最大的
        int index = 0;
        for (int i = 0; i < card.length; i++) {
            if (card[i].getFace().equals(card[i + 1].getFace()) && card[i + 1].getFace().equals(card[i + 2].getFace())) {
                index = i + 2;
            }
        }
        return index;
    }

    public static int maxPair(Card[] card) {//回傳最大的pair的最大的牌
        int index = 0;
        for (int i = card.length - 1; i > 0; i--) {
            if (card[i].getFace() == card[i - 1].getFace()) {
                index = i;
            }
        }
        return index;
    }

    public static boolean suitCompare(Card[] card) {//檢查兩張卡片的花色是否相同(同花)
        int count = 0;
        for (int i = 0; i < card.length; i++) {
            if (card[0].getSuit().equals(card[i].getSuit())) {
                count++;
            }
        }
        if (count == card.length) {
            return true;
        } else {
            return false;
        }
    }

    public static int howManyPair(Card[] card) {//檢查排中有幾對相同的排(這裡用一個特別得方法，看一下card[i].face.equals(card[i+1].face)，三個equals代表蘆，兩個代表2pair，一個代表1pair
        int count = 0;
        for (int i = 0; i < card.length - 1; i++) {
            if (compareFace(card[i]) == compareFace(card[i + 1])) {
                count++;
            }
        }
        if (count == 3) {
            return 6;//FullHouse
        }
        if (count == 2) {//這裡會有三條的情況發生
            count=0;
            for (int i = 0; i < card.length - 1; i++) {
            if (compareFace(card[i]) == compareFace(card[i + 1])) {
                count++;
                i++;//如果遇到連續兩個相同，就多跳一個，避免有三個相同的連在一起
            }
        }
            if(count==2)
            return 3;//2pair
            if(count==1)
                return 2;//1pair
        }
        if (count == 1) {
            return 2;//1pair
        } else {
            return 1;//雜牌
        }
    }

    public static boolean stright(Card[] card) {//判斷是否為順子
        boolean strightOrNot = true;
        int[] face = new int[card.length];
        for (int i = 0; i < card.length; i++) {
            face[i] = compareFace(card[i]);
        }
        if (face[card.length - 1] == 14) {//數列中有A的情況
            for (int i = 0; i < card.length - 1; i++) {//判斷是否有A23456或10JQKA
                if (face[i] == i + 2 || face[i] == i + 10) {
                    strightOrNot = true;
                } else {
                    strightOrNot = false;
                }
            }
        } else {//數列中沒有A的情況
            for (int i = 0; i < card.length - 1; i++) {//若數列中後面檢前面都是1，代表這是順子，若有一個沒有則不是
                if (face[i + 1] - face[i] != 1) {
                    strightOrNot = false;
                }
            }
        }
        return strightOrNot;
    }
}

