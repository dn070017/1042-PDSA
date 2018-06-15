import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    // DO NOT MODIFY THIS   
    public String getFace() {
        return this.face;
    }

    // DO NOT MODIFY THIS    
    public String getSuit() {
        return this.suit;
    }

    // TODO
    public int compareTo(Card that) {
        //先比數字再比花色
        if (this.face.equals(""A"")) {
            if (that.face.equals(""A"")) {//如果雙方都是A
                return Card.SUIT_ORDER.compare(this, that);
            } 
            else {
                return 1;//只有我是A，我贏
            }
        }
        if (that.face.equals(""A"")) {
            return -1;//已經確定我不是A，所以一定是對手贏
        }
        if(this.compareFace()>that.compareFace()) return 1;
        if(this.compareFace()<that.compareFace()) return -1;
        else{
            return Card.SUIT_ORDER.compare(this, that);
        }        
    }

    public int compareFace(){//比數字
        if(this.face.equals(""K"")) return 13;
        if(this.face.equals(""Q"")) return 12;
        if(this.face.equals(""J"")) return 11;
        else return Integer.valueOf(this.face);
    }
    public boolean suitCompare(Card[] card) {//檢查兩張卡片的花色是否相同(同花)
        int count = 0;
        for (int i = 0; i < card.length; i++) {
            if (this.suit.equals(card[i].suit)) {
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
            if (card[i].face.equals(card[i + 1].face)) {
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
            return 0;//雜牌
        }
    }

    public static boolean stright(Card[] card) {//判斷是否為順子
        boolean strightOrNot = true;
        if (card[card.length - 1].face.equals(""A"")) {//數列中有A的情況
            for (int i = 0; i < card.length; i++) {//判斷是否有A23456或者10JQKA的情況
                if (card[i].face.equals(String.valueOf(i + 2)) || card[i].face.endsWith(String.valueOf(i + 10))) {
                    strightOrNot = true;
                } else {
                    strightOrNot = false;
                }
            }
        } else {//數列中沒有A的情況
            for (int i = 0; i < card.length - 1; i++) {//若數列中後面檢前面都是1，代表這是順子，若有一個沒有則不是
                if (Integer.valueOf(card[i + 1].face) - Integer.valueOf(card[i].face) != 1) {
                    strightOrNot = false;
                }
            }
        }
        return strightOrNot;
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            int a1 = 0, a2 = 0;
            if (c1.suit.equals(""Spades"")) {
                a1 = 4;
            } else if (c1.suit.equals(""Hearts"")) {
                a1 = 3;
            } else if (c1.suit.equals(""Diamonds"")) {
                a1 = 2;
            } else if (c1.suit.equals(""Clubs"")) {
                a1 = 1;
            }
            if (c2.suit.equals(""Spades"")) {
                a2 = 4;
            } else if (c2.suit.equals(""Hearts"")) {
                a2 = 3;
            } else if (c2.suit.equals(""Diamonds"")) {
                a2 = 2;
            } else if (c2.suit.equals(""Clubs"")) {
                a2 = 1;
            }
            //比較花色
            if (a1 > a2) {
                return 1;
            } else if (a1 < a2) {
                return -1;
            } else {
                return 0;
            }
            // complete this function so the Card can be sorted according to the suit            
        }
    }
}

