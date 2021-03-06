
import java.util.Arrays;


public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
         this.cards = cards;
    }

    // TODO
    public int compareTo(Hand that) {
.
        Card[] thishand = this.cards;
        Card[] thathand = that.cards;
        if (handtype(thishand) > handtype(thathand)) {
            return 1;
        } else if (handtype(thishand) == handtype(thathand)) {
            return 0;
        } else {
            return -1;
        }
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }

public static int face(Card that) {
        if (""A"".equals(that.getFace())) {
            return 14;
        } else if (""J"".equals(that.getFace())) {
            return 11;
        } else if (""Q"".equals(that.getFace())) {
            return 12;
        } else if (""K"".equals(that.getFace())) {
            return 13;
        } else {
            return Integer.parseInt(that.getFace());
        }

    }

    public static double suit(Card A) {
        if (""Spades"".equals(A.getSuit())) {
            return 0.4;
        } else if (""Hearts"".equals(A.getSuit())) {
            return 0.3;
        } else if (""Diamonds"".equals(A.getSuit())) {
            return 0.2;
        } else {
            return 0.1;
        }
    }

    public double handtype(Card[] A) {

        Arrays.sort(A);
        int face[] = new int[5];
        double suit[] = new double[5];
        for (int i = 0; i < 5; i++) {
            suit[i] = suit(A[i]);
        }
        for (int i = 0; i < 5; i++) {
            face[i] = face(A[i]);
        }
        int num = 1;
        int count = 0;
        int fullhouse = 0;
        int fullhouseCard = 0;
        int straight = 1;
        int pairValue = 0;
        int pair = 0;
        int twopair = 0;
        int straightValue = 0;
        int flush = 1;
        double flushValue = 0;
        int pairmax = 0;

        for (int i = 1; i < 5; i++) {

            if (face[i] == face[i - 1]) {
                num++;
            }
            if (face[i] > face[i - 1]) {
                if (num == 2) {
                    num = 1;
                    count++;
                    pairmax = face[i - 1];
                }
                if (num == 3) {
                    num = 1;
                    fullhouse++;
                    fullhouseCard = face[i - 1];
                }
                if (face[i] == face[i - 1] + 1) {
                    straight++;
                }
            }
            if (i == 4) {
                if (num == 2) 
                        count++;
                if (count == 1) {
                    

                        if (pairmax < face[3]) {
                            pairmax = face[3];
                        }
                    
                    if (num == 3) {
                        fullhouse++;
                        fullhouseCard = face[i];
                    }
                }
                if (fullhouse == 1 && count == 0) {
                    pair = 1;
                    fullhouse = fullhouse - 1;
                }
            }
            if (count == 2 && i == 4) {
                twopair = 1;
            }
            if (count == 1 && i == 4) {
                if (fullhouse == 0) {
                    pair = 1;
                }

            }

            if (i == 4 && straight == 5) {
                straightValue = face[4];
            }

        }
        if (face[0] == 2 && face[1] == 3 && face[2] == 4 && face[3] == 5 && face[4] == 14) {
            straight = 5;
            straightValue = 5;
        }
        for (int i = 1; i < 5; i++) {
            if (suit[i] == suit[i - 1]) {
                flush++;
            }
            if (flush == 5) {
                flushValue = face[i];
            }
        }

        if (fullhouse == 1) {
            return 600 + fullhouseCard;
        } else if (flush == 5) {
            return 500 + flushValue;
        } else if (straight == 5) {
            return 400 + straightValue;
        } else if (twopair == 1) {
            return 300 + face[3];
        } else if (pair == 1) {
            return 200 + pairmax;
        } else {
            return 100 + face[4];
        }
    }
}
