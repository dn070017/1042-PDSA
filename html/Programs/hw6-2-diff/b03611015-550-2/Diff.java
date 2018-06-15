
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
                } else if (num == 3) {
                    num = 1;
                    fullhouse++;
                    fullhouseCard = face[i - 1];
                } else {
                    straight++;
                }
            }
            if (i == 4 ) {
                if (count == 1) {
                    if (num == 2) {
                        count++;
                        if (pairmax < face[3]) {
                            pairmax = face[3];
                        }
                    }
                    if (num == 3) {
                        fullhouse++;
                        fullhouseCard = face[i];
                    }
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
                straightValue = face[i];
            }

        }
        for (int i = 1; i < 5; i++) {
            if (suit[i] == suit[i - 1]) {
                flush++;
            }
            if (flush == 5) {
                flushValue = suit[i];
            }
        }

        if (fullhouse == 1) {
            return 600 + fullhouseCard;
        } else if (flush == 5) {
            return 500 + 10 * flushValue;
        } else if (straight == 5) {
            return 400 + straightValue;
        } else if (twopair == 1) {
            return 300 + pairmax;
        } else if (pair == 1) {
            return 200 + pairmax;
        } else {
            return 100;
        }
    }
}

