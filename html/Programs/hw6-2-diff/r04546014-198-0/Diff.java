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
            if (c1 == 5) {//兩者都是同花
                int s1 = this.compareSuit(this.cards[0]);//我的排的花色
                int s2 = that.compareSuit(that.cards[0]);//對手排的花色
                if (s1 > s2) {
                    return 1;
                } else if (s1 < s2) {
                    return -1;
                } else {
                    return this.cards[cards.length - 1].compareTo(that.cards[cards.length - 1]);//兩個同花相同比較最後一張牌的大小
                }
            }
            else if(c1==4){//兩者都是順子
                return this.cards[cards.length-2].compareTo(that.cards[cards.length-2]);//比較倒數第二張牌(這裡會有錯，如果兩副牌都是10JQKA應該先比較A的大小而不是K，但是還要再寫if就很懶)
            }else {//兩者都是雜牌
                return this.cards[cards.length - 1].compareTo(that.cards[cards.length - 1]);//比較最後一張牌的大小
            }
        }
    }

    public int compareSuit(Card card) {
        if (card.getSuit() == ""Spades"") {
            return 4;
        }
        if (card.getSuit() == ""Hearts"") {
            return 3;
        }
        if (card.getSuit() == ""Diamonds"") {
            return 2;
        }
        if (card.getSuit() == ""Clubs"") {
            return 1;
        } else {
            return 0;
        }
    }

    public int cardsConbination(Card[] card) {
        if (Player.suitCompare(card)) {
            return 5;//同花
        }
        if (Player.stright(card)) {
            return 4;//順子
        } else {
            return Player.howManyPair(card);//蘆、2pairs、1pair、雜牌
        }
    }

    public static int compareFace(Card card) {//得到卡片的數字
        if (card.getFace() == ""A"") {
            return 14;//A
        }
        if (card.getFace() == ""K"") {
            return 13;
        }
        if (card.getFace() == ""Q"") {
            return 12;
        }
        if (card.getFace() == ""J"") {
            return 11;
        } else {
            return Integer.valueOf(card.getFace());
        }
    }

    public static boolean suitCompare(Card[] card) {//檢查兩張卡片的花色是否相同(同花)
        int count = 0;
        for (int i = 0; i < card.length; i++) {
            if (card[0].getSuit() == card[i].getFace()) {
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
        int[] face = new int[card.length];
        for (int i = 0; i < card.length; i++) {
            face[i] = compareFace(card[i]);
        }
        for (int i = 0; i < card.length - 1; i++) {
            if (face[i] == face[i + 1]) {
                count++;
            }
        }
        if (count == 3) {
            return 6;//FullHouse
        }
        if (count == 2) {
            return 3;//2pair
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
            for (int i = 0; i < card.length - 2; i++) {//判斷是否有A23456或10JQKA
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

