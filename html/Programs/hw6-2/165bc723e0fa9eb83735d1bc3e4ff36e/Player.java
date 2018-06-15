
import java.util.Arrays;

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
        //for full house
        int s1 = 0, h1 = 0, d1 = 0, c1 = 0;
        int[] o = new int[15];
        int mount = 0;
        int count = 0;
        int count1 = 0;
        int[] A = new int[5];
        for (int j = 0; j < 5; j++) {
            switch (that.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    A[j] = 14;
                    break;
                case ""J"":
                    A[j] = 11;
                    break;
                case ""Q"":
                    A[j] = 12;
                    break;
                case ""K"":
                    A[j] = 13;
                    break;
                default:
                    A[j] = Integer.parseInt(that.cards[j].getFace());
            }
        }
        for (int i = 0; i < 5; i++) {
            int min = i;
            for (int j = i + 1; j < 5; j++) {
                if (A[j] < A[i]) {
                    min = j;
                    int mem = A[i];
                    A[i] = A[min];
                    A[min] = mem;
                }
            }
        }
        int[] B = new int[5];
        for (int j = 0; j < 5; j++) {
            switch (this.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    B[j] = 14;
                    break;
                case ""J"":
                    B[j] = 11;
                    break;
                case ""Q"":
                    B[j] = 12;
                    break;
                case ""K"":
                    B[j] = 13;
                    break;
                default:
                    B[j] = Integer.parseInt(this.cards[j].getFace());
            }
        }
        for (int i = 0; i < 5; i++) {
            int min = i;
            for (int j = i + 1; j < 5; j++) {
                if (B[j] < B[i]) {
                    min = j;
                    int mem = B[i];
                    B[i] = B[min];
                    B[min] = mem;
                }
            }
        }
        for (int j = 0; j <= 14; j++) {//歸零
            o[j] = 0;
        }
        for (int j = 0; j < 5; j++) {
            switch (that.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;
                    break;
                case ""J"":
                    o[11]++;
                    break;
                case ""Q"":
                    o[12]++;
                    break;
                case ""K"":
                    o[13]++;
                    break;
                default:
                    o[Integer.parseInt(that.cards[j].getFace())]++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if (o[j] == 3) {
                count = count + 10000;
                mount = j;
            }
            if (o[j] == 2) {
                count = count + 100;
            }
            if (o[j] == 4) {
                count = count + 200+j;
            }
            if (count == 10100) {
                count = count + mount;
            }
        }

        mount = 0;
        for (int j = 0; j <= 14; j++) {//歸零
            o[j] = 0;
        }
        for (int j = 0; j < 5; j++) {
            switch (this.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;
                    break;
                case ""J"":
                    o[11]++;
                    break;
                case ""Q"":
                    o[12]++;
                    break;
                case ""K"":
                    o[13]++;
                    break;
                default:
                    o[Integer.parseInt(this.cards[j].getFace())]++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if (o[j] == 3) {
                count1 = count1 + 10000;
                mount = j;
            }
            if (o[j] == 2) {
                count1 = count1 + 100;
            }
            if (o[j] == 4) {
                count1 = count1 + 200+j;
            }
            if (count1 == 10100) {
                count1 = count1 + mount;
            }
        }

        for (int i = 0; i < 5; i++) { //桐花
            switch (that.cards[i].getSuit()) {
                case ""Spades"":
                    s1++;
                    if (s1 == 5) {
                        count = count + 1000;
                    }
                    break;
                case ""Hearts"":
                    h1++;
                    if (h1 == 5) {
                        count = count + 1000;
                    }
                    break;
                case ""Diamonds"":
                    d1++;
                    if (d1 == 5) {
                        count = count + 1000;
                    }
                    break;
                case ""Clubs"":
                    c1++;
                    if (c1 == 5) {
                        count = count + 1000;
                    }
                    break;
            }
        }
        s1 = 0;
        h1 = 0;
        d1 = 0;
        c1 = 0;
        for (int i = 0; i < 5; i++) {
            switch (this.cards[i].getSuit()) {
                case ""Spades"":
                    s1++;
                    if (s1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
                case ""Hearts"":
                    h1++;
                    if (h1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
                case ""Diamonds"":
                    d1++;
                    if (d1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
                case ""Clubs"":
                    c1++;
                    if (c1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
            }
        }
        //順子
//                mount=0;
        for (int k = 0; k < 4; k++) {
            if (A[k + 1] == A[k] + 1) {
                mount++;
            }
        }
        if (mount == 4) {
            count = count + 500 + A[4];
        }
        mount = 0;
        for (int k = 0; k < 4; k++) {
            if (B[k + 1] == B[k] + 1) {
                mount++;
            }

        }
        if (mount == 4) {
            count1 = count1 + 500 + B[4];
        }
        if(count<100&&count1<100){
        count=A[4];
        count1=B[4];
        }
//            System.out.println(A[0]+"" ""+A[1]+"" ""+A[2]+"" ""+A[3]+"" ""+A[4]);
//System.out.println(B[0]+"" ""+B[1]+"" ""+B[2]+"" ""+B[3]+"" ""+B[4]);
//            System.out.println(count+"" ""+count1);
//        System.out.println(that.name + that.cards[0].getFace());
//        System.out.println(this.name + this.cards[0].getFace());//回傳出來 且下一個的輸入
        if (count > count1) {
            this.cards = that.cards;
            this.name = that.name;
//            System.out.println(this.name + this.cards[0].getFace()+""  ""+count+"" ""+count1);//回傳出來 且下一個的輸入
            return 0;
        }

        return 0;
    }
}

