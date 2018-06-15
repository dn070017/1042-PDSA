
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
        for(int j =0;j<=14;j++){//歸零
        o[j]=0;
        }
        for (int j = 0; j < 5; j++) {
            switch (that.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;break;
                case ""J"":
                    o[11]++;break;
                case ""Q"":
                    o[12]++;break;
                case ""K"":
                    o[13]++;break;
                default:
                    o[Integer.parseInt(that.cards[j].getFace())]++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if(o[j]==3){count=count+10000;mount=j;}
            if(o[j]==2)count=count+100;
            if(count==10100){count=count+mount;}
        }

        mount=0;
        for (int j = 0; j <= 14; j++) {//歸零
            o[j] = 0;
        }
        for (int j = 0; j < 5; j++) {
            switch (this.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;break;
                case ""J"":
                    o[11]++;break;
                case ""Q"":
                    o[12]++;break;
                case ""K"":
                    o[13]++;break;
                default:
                    o[Integer.parseInt(this.cards[j].getFace())]++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if(o[j]==3){count1=count1+10000;mount=j;}
            if(o[j]==2)count1=count1+100;
            if(count1==10100){count1=count1+mount;}
        }
                
        for (int i = 0; i < 5; i++) { //桐花
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
                //順子
//                mount=0;
//                for(int k=1;k<5;k++){
//            switch (that.cards[k].getFace()) {//葫蘆算
//                case ""A"":
//                    break;
//                case ""J"":
//                    if(""10"".equals(that.cards[k-1].getFace())){
//                        mount++;
//                    break;}
//                case ""Q"":
//                    if(""J"".equals(that.cards[k-1].getFace())){
//                        mount++;
//                        break;}
//                case ""K"":
//                    if(""Q"".equals(that.cards[k-1].getFace())){
//                        mount++;
//                        break;}
//                default:
//                    if(Integer.toString(Integer.parseInt(that.cards[k-1].getFace())-1).equals(that.cards[k-1].getFace())){
//                        mount++;
//                        break;}
//    }
//            if(mount==5){count=count+100;}
//                }
//                mount=0;
//                for(int k=1;k<4;k++){
//            switch (this.cards[k].getFace()) {//葫蘆算
//                case ""A"":
//                    break;
//                case ""J"":
//                    if(""10"".equals(this.cards[k-1].getFace())){
//                        mount++;
//                    break;}
//                case ""Q"":
//                    if(""J"".equals(this.cards[k-1].getFace())){
//                        mount++;
//                        break;}
//                case ""K"":
//                    if(""Q"".equals(this.cards[k-1].getFace())){
//                        mount++;
//                        break;}
//                default:
//                    if(Integer.toString(Integer.parseInt(this.cards[k-1].getFace())-1).equals(this.cards[k-1].getFace())){
//                        mount++;
//                        break;}
//            }
//    }
//        if(mount==5){count1=count1+100;}
//        System.out.println(that.name + that.cards[0].getFace());
//        System.out.println(this.name + this.cards[0].getFace());//回傳出來 且下一個的輸入
                if (count > count1) {
            this.cards = that.cards;
            this.name=that.name;
//            System.out.println(this.name + this.cards[0].getFace()+""  ""+count+"" ""+count1);//回傳出來 且下一個的輸入
            return 0;
        }

        return 0;
    }
}

