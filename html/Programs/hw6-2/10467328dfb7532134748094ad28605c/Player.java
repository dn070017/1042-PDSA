
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
    @SuppressWarnings(""empty-statement"")
    public int compareTo(Player that) {
.
        int[] thisf = new int[5];
        int[] thiss = new int[5];
        int[] thatf = new int[5];
        int[] thats = new int[5];
        int[] thishand = new int[3];
        int[] thathand = new int[3];
        for (int i = 0; i < 5; i++) {
            if (this.cards[i].getFace().equals(""A"")) {
                thisf[i] = 14;
            } else if (this.cards[i].getFace().equals(""J"")) {
                thisf[i] = 11;
            } else if (this.cards[i].getFace().equals(""Q"")) {
                thisf[i] = 12;
            } else if (this.cards[i].getFace().equals(""K"")) {
                thisf[i] = 13;
            } else {
                thisf[i] = Integer.parseInt(this.cards[i].getFace());
            }
            if (that.cards[i].getFace().equals(""A"")) {
                thatf[i] = 14;
            } else if (that.cards[i].getFace().equals(""J"")) {
                thatf[i] = 11;
            } else if (that.cards[i].getFace().equals(""Q"")) {
                thatf[i] = 12;
            } else if (that.cards[i].getFace().equals(""K"")) {
                thatf[i] = 13;
            } else {
                thatf[i] = Integer.parseInt(that.cards[i].getFace());
            }
            if (this.cards[i].getSuit().equals(""Spades"")) {
                thiss[i] = 4;
            } else if (this.cards[i].getSuit().equals(""Hearts"")) {
                thiss[i] = 3;
            } else if (this.cards[i].getSuit().equals(""Diamonds"")) {
                thiss[i] = 2;
            } else if (this.cards[i].getSuit().equals(""Clubs"")) {
                thiss[i] = 1;
            }
            if (that.cards[i].getSuit().equals(""Spades"")) {
                thats[i] = 4;
            } else if (that.cards[i].getSuit().equals(""Hearts"")) {
                thats[i] = 3;
            } else if (that.cards[i].getSuit().equals(""Diamonds"")) {
                thats[i] = 2;
            } else if (that.cards[i].getSuit().equals(""Clubs"")) {
                thats[i] = 1;
            }
        }
        //pair 2pair full house
        int paircounta = 0;
        int ahigh = 0;
        int paircountb = 0;
        int bhigh = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (thisf[i] == thisf[j]) {
                    paircounta++;
                    if (thisf[i] > ahigh) {
                        ahigh = thisf[i];
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (thatf[i] == thatf[j]) {
                    paircountb++;
                    if (thatf[i] > bhigh) {
                        bhigh = thatf[i];
                    }
                }
            }
        }
        if (paircounta > 0) {
            //1 pair
            if (paircounta == 1 || paircounta == 3) {
                thishand[0] = 1;
                thishand[1] = ahigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thisf[i] == ahigh) {
                        if (thiss[i] > suithigh) {
                            suithigh = thiss[i];
                        }
                    }
                }
                thishand[2] = suithigh;
            }
            //2 pairs
            if (paircounta == 2) {
                thishand[0] = 2;
                thishand[1] = ahigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thisf[i] == ahigh) {
                        if (thiss[i] > suithigh) {
                            suithigh = thiss[i];
                        }
                    }
                }
                thishand[2] = suithigh;
            }
            //fullhouse
            if (paircounta == 4) {
                thishand[0] = 5;
                for (int i = 0; i < 5; i++) {
                    int a = 0;
                    for (int j = i + 1; j < 5; j++) {
                        if (thisf[i] == thisf[j]) {
                            a++;
                            if (a == 2) {
                                thishand[1] = thisf[i];
                            }
                        }
                    }
                }
            }
        }else{
            Arrays.sort(thisf);
            //straight
            for (int i = 0; i < 4; i++) {
                if (i == 3 && thisf[4] == 14) {
                    thishand[0] = 3;
                    thishand[1] = thisf[3];
                    thishand[2] = thiss[3];
                }
                if (thisf[i] + 1 != thisf[i+1]) {
                    break;
                }
                if (i == 3) {
                    thishand[0] = 3;
                    thishand[1] = thisf[i+1];
                    thishand[2] = thiss[i+1];
                }
            }
            //flush
            for (int i = 0; i < 4; i++) {
                if (thiss[i] != thiss[i+1]) {
                    break;
                }
                if (i == 3) {
                    thishand[0] = 4;
                    thishand[1] = thisf[i+1];
                    thishand[2] = thiss[i+1];
                }
            }
            //highcard
            if (thishand[0] == 0) {
                thishand[1] = thisf[4];
                thishand[2] = thiss[4];
            }
        }
        if (paircountb > 0) {
            //1 pair
            if (paircountb == 1 || paircountb == 3) {
                thathand[0] = 1;
                thathand[1] = bhigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thatf[i] == bhigh) {
                        if (thats[i] > suithigh) {
                            suithigh = thats[i];
                        }
                    }
                }
                thathand[2] = suithigh;
            }
            //2 pairs
            if (paircountb == 2) {
                thathand[0] = 2;
                thathand[1] = bhigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thatf[i] == bhigh) {
                        if (thats[i] > suithigh) {
                            suithigh = thats[i];
                        }
                    }
                }
                thathand[2] = suithigh;
            }
            //fullhouse
            if (paircountb == 4) {
                thathand[0] = 5;
                for (int i = 0; i < 5; i++) {
                    int a = 0;
                    for (int j = i + 1; j < 5; j++) {
                        if (thatf[i] == thatf[j]) {
                            a++;
                            if (a == 2) {
                                thathand[1] = thatf[i];
                            }
                        }
                    }
                }
            }
        }else{
            Arrays.sort(thatf);
            //straight
            for (int i = 0; i < 4; i++) {
                if (i == 3 && thatf[4] == 14) {
                    thathand[0] = 3;
                    thathand[1] = thatf[3];
                    thathand[2] = thats[3];
                }
                if (thatf[i] + 1 != thatf[i+1]) {
                    break;
                }
                if (i == 3) {
                    thathand[0] = 3;
                    thathand[1] = thatf[i+1];
                    thathand[2] = thats[i+1];
                }
            }
            //flush
            for (int i = 0; i < 4; i++) {
                if (thats[i] != thats[i+1]) {
                    break;
                }
                if (i == 3) {
                    thathand[0] = 4;
                    thathand[1] = thatf[i+1];
                    thathand[2] = thats[i+1];
                }
            }
            //highcard
            if (thathand[0] == 0) {
                thathand[1] = thatf[4];
                thathand[2] = thats[4];
            }
        }
        //compare
        for (int i = 0; i < 3; i++) {
            if (thishand[i] > thathand[i]) {
                return +1;
            }
            if (thishand[i] < thathand[i]) {
                return -1;
            }
        }
        
        return 0;
    }

}

