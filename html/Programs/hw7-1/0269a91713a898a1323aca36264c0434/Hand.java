
import java.util.Comparator;



public class Hand implements Comparable<Hand> {

    private Hand[] cards = new Hand[5];
    private String name;

    // DO NOT MODIFY THIS
    public Hand(String name) {
        this.name = name;
    }

    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
    }

    // DO NOT MODIFY THIS
    public void setCards(Hand[] cards) {
        this.cards = cards;
    }

    // TODO 
    @Override
    public int compareTo(Hand that) {
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
                        if(i==4){count=count+A[4];}
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
                        if(i==4){count1=count1+B[4];}
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
        count=A[4]*10;
        count1=B[4]*10;
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
            return 1;
        }
                if (count < count1) {
//            System.out.println(this.name + this.cards[0].getFace()+""  ""+count+"" ""+count1);//回傳出來 且下一個的輸入
            return -1;
        }
        return 0;
    }
    
    


    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
    public static final Comparator<Hand> SUIT_ORDER = new SuitOrder();
    public static final Comparator<Hand> face_ORDER = new facesuitOrder();

    // DO NOT MODIFY THIS
    public Hand(String face, String suit) {
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
    public int compareToo(Hand that) {
        int i, o;
        switch (this.face) {
            case ""A"":
                i = 14;
                break;
            case ""J"":
                i = 11;
                break;
            case ""Q"":
                i = 12;
                break;
            case ""K"":
                i = 13;
                break;
            default:
                i = Integer.parseInt(this.face);
        }
        switch (that.face) {
            case ""A"":
                o = 14;
                break;
            case ""J"":
                o = 11;
                break;
            case ""Q"":
                o = 12;
                break;
            case ""K"":
                o = 13;
                break;
            default:
                o = Integer.parseInt(that.face);
        }
        if (i < o) {
            return -1;
        }
        if (i > o) {
            return 1;
        }
        switch (this.suit) {
            case ""Spades"":
                i = 4;
                break;
            case ""Hearts"":
                i = 3;
                break;
            case ""Diamonds"":
                i = 2;
                break;
            case ""Clubs"":
                i = 1;
                break;
            default:
                return 0;
        }
        switch (that.suit) {
            case ""Spades"":
                o = 4;
                break;
            case ""Hearts"":
                o = 3;
                break;
            case ""Diamonds"":
                o = 2;
                break;
            case ""Clubs"":
                o = 1;
                break;
            default:
                return 0;
        }
        if (i < o) {
            return -1;
        }
        if (i > o) {
            return 1;
        }
        return 0;
    }

    // TODO
    private static class SuitOrder implements Comparator<Hand> {

        @Override
        public int compare(Hand c1, Hand c2) {
            // complete this function so the Card can be sorted according to the suit
            int i, j;
            switch (c1.suit) {
                case ""Spades"":
                    i = 4;
                    break;
                case ""Hearts"":
                    i = 3;
                    break;
                case ""Diamonds"":
                    i = 2;
                    break;
                case ""Clubs"":
                    i = 1;
                    break;
                default:
                    return 0;
            }
            switch (c2.suit) {
                case ""Spades"":
                    j = 4;
                    break;
                case ""Hearts"":
                    j = 3;
                    break;
                case ""Diamonds"":
                    j = 2;
                    break;
                case ""Clubs"":
                    j = 1;
                    break;
                default:
                    return 0;
            }
            if (i < j) {
                return -1;
            }
            if (i > j) {
                return 1;
            }
            return 0;
        }
    }

    private static class facesuitOrder implements Comparator<Hand> {

        @Override
        public int compare(Hand c1, Hand c2) {
            // complete this function so the Card can be sorted according to the suit
            return c1.compareToo(c2);
        }

    }

    
    
    
}

