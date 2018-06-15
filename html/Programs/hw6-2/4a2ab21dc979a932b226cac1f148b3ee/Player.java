
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
        int[] counttable1 = new int[15];
        int[] counttable2 = new int[15];
        int[] suittable1 = new int[4];
        int[] suittable2 = new int[4];
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);

        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i].getFace().equalsIgnoreCase(""A"")) {
                counttable1[14]++;
            } else if (this.cards[i].getFace().equalsIgnoreCase(""K"")) {
                counttable1[13]++;
            } else if (this.cards[i].getFace().equalsIgnoreCase(""Q"")) {
                counttable1[12]++;
            } else if (this.cards[i].getFace().equalsIgnoreCase(""J"")) {
                counttable1[11]++;
            } else {
                counttable1[Integer.parseInt(this.cards[i].getFace())]++;
            }

            if (this.cards[i].getSuit().equalsIgnoreCase(""Spades"")) {
                suittable1[3]++;
            } else if (this.cards[i].getSuit().equalsIgnoreCase(""Hearts"")) {
                suittable1[2]++;
            } else if (this.cards[i].getSuit().equalsIgnoreCase(""Diamonds"")) {
                suittable1[1]++;
            } else {
                suittable1[0]++;
            }
        }//making table for p1

        for (int i = 0; i < that.cards.length; i++) {
            if (that.cards[i].getFace().equals(""A"")) {
                counttable2[14]++;
            } else if (that.cards[i].getFace().equalsIgnoreCase(""K"")) {
                counttable2[13]++;
            } else if (that.cards[i].getFace().equalsIgnoreCase(""Q"")) {
                counttable2[12]++;
            } else if (that.cards[i].getFace().equalsIgnoreCase(""J"")) {
                counttable2[11]++;
            } else {
                counttable2[Integer.parseInt(that.cards[i].getFace())]++;
            }

            if (that.cards[i].getSuit().equalsIgnoreCase(""Spades"")) {
                suittable2[3]++;
            } else if (that.cards[i].getSuit().equalsIgnoreCase(""Hearts"")) {
                suittable2[2]++;
            } else if (that.cards[i].getSuit().equalsIgnoreCase(""Diamonds"")) {
                suittable2[1]++;
            } else {
                suittable2[0]++;
            }
        }//making table for p2

        int isfull_house1 = 0;
        int isfull_house2 = 0;
        for (int i = 0; i < counttable1.length; i++) {
            if (counttable1[i] == 3) {
                for (int j = 0; j < counttable1.length; j++) {
                    if (counttable1[j] == 2) {
                        isfull_house1 = 1;
                    }
                }
            }
        }
        for (int i = 0; i < counttable2.length; i++) {
            if (counttable2[i] == 3) {
                for (int j = 0; j < counttable2.length; j++) {
                    if (counttable2[j] == 2) {
                        isfull_house2 = 1;
                    }
                }
            }
        }
        if (isfull_house1 > isfull_house2) {
            return 1;
        }
        if (isfull_house1 < isfull_house2) {
            return -1;
        }
        if (isfull_house1 == 1 && isfull_house2 == 1) {
            return this.cards[4].compareTo(that.cards[4]);
        }// for full house

        int isflush1 = 0;
        int isflush2 = 0;
        for (int i = 0; i < suittable1.length; i++) {
            if (suittable1[i] == 5) {
                isflush1 = 1;
            }
        }
        for (int i = 0; i < suittable2.length; i++) {
            if (suittable2[i] == 5) {
                isflush2 = 1;
            }
        }

        if (isflush1 > isflush2) {
            return 1;
        }
        if (isflush1 < isflush2) {
            return -1;
        }
        if (isflush1 == 1 && isflush2 == 1) {
            return this.cards[4].compareTo(that.cards[4]);
        }// for flush     

        int isstraight1 = 0;
        int isstraight2 = 0;
        for (int i = 0; i < (counttable1.length - 5); i++) {
            if (counttable1[i] == 1) {
                if (counttable1[(i + 1)] == 1 && counttable1[(i + 2)] == 1 && counttable1[(i + 3)] == 1 && counttable1[(i + 4)] == 1) {
                    isstraight1 = 1;
                    break;
                }
                else if(counttable2[(i + 1)] == 1 && counttable2[(i + 2)] == 1 && counttable2[(i + 3)] == 1 && counttable2[14] == 1){
                    isstraight2 = 1;
                    break;
                }//account for A 2 3 4 5
            }
        }
        for (int i = 0; i < (counttable2.length - 5); i++) {
            if (counttable2[i] == 1) {
                if (counttable2[(i + 1)] == 1 && counttable2[(i + 2)] == 1 && counttable2[(i + 3)] == 1 && counttable2[(i + 4)] == 1) {
                    isstraight2 = 1;
                    break;
                }
                else if(counttable2[(i + 1)] == 1 && counttable2[(i + 2)] == 1 && counttable2[(i + 3)] == 1 && counttable2[14] == 1){
                    isstraight2 = 1;
                    break;
                }//account for A 2 3 4 5
                    
            }
        }

        if (isstraight1 > isstraight2) {
            return 1;
        }
        if (isstraight1 < isstraight2) {
            return -1;
        }
        if (isstraight1 == 1 && isstraight2 == 1) {
            return this.cards[4].compareTo(that.cards[4]);
        }// for flush        

        int count = 0;
        int istwopairs1 = 0;
        int istwopairs2 = 0;
        int maxpair1 = 0;
        int maxpair2 = 0;
        int pairs1 = 0;
        int pairs2 = 0;
        
        Card[] maxcard1 = new Card[2];
        for (int i = 0; i < counttable1.length; i++) {
            if (counttable1[i] == 2) {
            pairs1++;
            maxpair1 = i;               
            }
        }

        if (pairs1 == 2) {
            istwopairs1 = 1;
            String number = String.valueOf(maxpair1);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < this.cards.length; j++){
                if(this.cards[j].getFace().equalsIgnoreCase(number))
                maxcard1[count++] = this.cards[j]; 
            }
            Arrays.sort(maxcard1);
        }
        
        count = 0;
        Card[] maxcard2 = new Card[2];
        
        for (int i = 0; i < counttable2.length; i++) {
            if (counttable2[i] == 2) {
                pairs2++;
                maxpair2 = i;
            }
        }
        
        if (pairs2 == 2) {
            istwopairs2 = 1;
            String number = String.valueOf(maxpair2);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < that.cards.length; j++){
                if(that.cards[j].getFace().equalsIgnoreCase(number))
                maxcard2[count++] = that.cards[j]; 
            }
            Arrays.sort(maxcard2);
        }

        if (istwopairs1 > istwopairs2) {
            return 1;
        }
        if (istwopairs1 < istwopairs2) {
            return -1;
        }
        if (istwopairs1 == 1 && istwopairs2 == 1) {
            return maxcard1[1].compareTo(maxcard2[1]);
        }// for flush        
        
        
        int ispairs1 = 0;
        int ispairs2 = 0;

        count = 0;
        if (pairs1 == 1) {
            ispairs1 = 1;
            String number = String.valueOf(maxpair1);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < this.cards.length; j++){
                if(this.cards[j].getFace().equalsIgnoreCase(number))
                maxcard1[count++] = this.cards[j]; 
            }
            Arrays.sort(maxcard1);
        }

        count = 0;       
        if (pairs2 == 1) {
            ispairs2 = 1;
            String number = String.valueOf(maxpair2);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < that.cards.length; j++){
                if(that.cards[j].getFace().equalsIgnoreCase(number))
                maxcard2[count++] = that.cards[j]; 
            }
            Arrays.sort(maxcard2);
        }
        
        if (ispairs1 > ispairs2) {
            return 1;
        }
        if (ispairs1 < ispairs2) {
            return -1;
        }
        if (ispairs1 == 1 && ispairs2 == 1) {
            return maxcard1[1].compareTo(maxcard2[1]);
        }// for flush         
.
        return this.cards[4].compareTo(that.cards[4]);
    }
}

