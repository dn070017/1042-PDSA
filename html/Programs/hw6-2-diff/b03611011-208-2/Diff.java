
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
        int mount=0;
        int count = 0;
        int count1 = 0;
        for(int j =0;j<=14;j++){
        o[j]=0;
        }
        for (int j = 0; j < 5; j++) {
            switch (that.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;mount=14;break;
                case ""J"":
                    o[11]++;mount=11;break;
                case ""Q"":
                    o[12]++;mount=12;break;
                case ""K"":
                    o[13]++;mount=13;break;
                default:
                    o[Integer.parseInt(that.cards[0].getFace())]++;mount=Integer.parseInt(that.cards[0].getFace());
            }
        }
        for (int j = 0; j <= 14; j++) {
            if(o[j]==3){count=count+10000;}
            if(o[j]==2)count=count+100;
            if(count==10100){count=count*mount;}
        }
        mount=0;
        for (int j = 0; j < 5; j++) {
            switch (this.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;mount=14;break;
                case ""J"":
                    o[11]++;mount=11;break;
                case ""Q"":
                    o[12]++;mount=12;break;
                case ""K"":
                    o[13]++;mount=13;break;
                default:
                    o[Integer.parseInt(that.cards[0].getFace())]++;mount=Integer.parseInt(that.cards[0].getFace());
            }
        }
        for (int j = 0; j <= 14; j++) {
            if(o[j]==3){count1=count1+10000;}
            if(o[j]==2)count1=count1+100;
        }
                
        for (int i = 0; i < 5; i++) {
            switch (that.cards[i].getSuit()) {
                case ""Spades"":
                    s1++;
                    if (s1 == 5) {count=count+1000;}
                    break;
                case ""Hearts"":
                    h1++;
                    if (h1 == 5) {count=count+1000;}
                    break;
                case ""Diamonds"":
                    d1++;
                    if (d1 == 5) {count=count+1000;}
                    break;
                case ""Clubs"":
                    c1++;
                    if (c1 == 5) {count=count+1000;}
                    break;
                default:
                    return 0;
            }
        }
                    s1 = 0; h1 = 0; d1 = 0; c1 = 0;
                for (int i = 0; i < 5; i++) {
                                switch (this.cards[i].getSuit()) {
                case ""Spades"":
                    s1++;
                    if (s1 >= 5) {count1=count1+1000;}
                    break;
                case ""Hearts"":
                    h1++;
                    if (h1 >= 5) {count1=count1+1000;}
                    break;
                case ""Diamonds"":
                    d1++;
                    if (d1 >= 5) {count1=count1+1000;}
                    break;
                case ""Clubs"":
                    c1++;
                    if (c1 >= 5) {count1=count1+1000;}
                    break;
            }
                }
//        System.out.println(that.name + that.cards[0].getFace());
//        System.out.println(this.name + this.cards[0].getFace());//回傳出來 且下一個的輸入
                if (count > count1) {
            this.cards = that.cards;
            this.name=that.name;
//            System.out.println(this.name + this.cards[0].getFace()+count+"" ""+count1);//回傳出來 且下一個的輸入
            return 0;
        }

        return 0;
    }
}

