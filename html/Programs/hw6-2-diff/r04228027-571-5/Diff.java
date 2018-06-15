
import java.util.Arrays;

public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    private int cardstype;

    private static int getfacecode(Card c) {
        switch (c.getFace()) {
            case ""A"":
                return 14;
            case ""K"":
                return 13;
            case ""Q"":
                return 12;
            case ""J"":
                return 11;
            default:
                return Integer.parseInt(c.getFace());
        }
    }

    private static int getsuitcode(Card c) {
        switch (c.getSuit()) {
            case ""Spades"":
                return 4;
            case ""Hearts"":
                return 3;
            case ""Diamonds"":
                return 2;
            case ""Clubs"":
                return 1;
            default:
                return 0;
        }
    }

    //full house = 5... high card = 0
    public int findcardstype() {

        int difffacecount = 1;
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) != getfacecode(this.cards[i + 1])) {
                difffacecount++;
            }
        }
        if (difffacecount == 2) {
            return 5; // full house
        }
        if (difffacecount == 3) {
            for (int i = 0; i < 3; i++) {
                if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1]) && getfacecode(this.cards[i + 1]) == getfacecode(this.cards[i + 2])) {
                    return 1; // 3 of a kind, equal to 1 pair
                }
            }
            return 2; // 2 pairs
        }
        if (difffacecount == 4) {
            return 1; // 1 pair
        }

        for (int i = 0; i < 4; i++) {
            if (getsuitcode(this.cards[i]) != getsuitcode(this.cards[i + 1])) {
                break;
            }
            if (i == 3) {
                return 4; // flush
            }
        }
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) + 1 != getfacecode(this.cards[i + 1])) {
                if (i == 3 && getfacecode(this.cards[i + 1]) == 14) {
                    return 3; // 2345A
                }
                break;
            }
            if (i == 3) {
                return 3; // straight         
            }
        }

        return 0;// high card
    }

    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }

    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
        Arrays.sort(this.cards);
        this.cardstype = this.findcardstype();
    }

    // TODO 
    public int compareTo(Player that) {
.

        if (this.cardstype < that.cardstype) {
            return -1;
        }
        if (this.cardstype > that.cardstype) {
            return +1;
        }

        //  
        switch (this.cardstype) {
            case 0: // high card
                return this.cards[4].compareTo(that.cards[4]);
            case 1: // 1 pair
                return this.find_onepair_max().compareTo(that.find_onepair_max());
            case 2: // 2 pairs
                return this.find_twopairs_max().compareTo(that.find_twopairs_max());
            case 3: // straight
                if (getsuitcode(this.cards[0]) == 2 && getsuitcode(that.cards[0]) == 10) {
                    return -1; //2345A & 10JQKA
                } else if (getsuitcode(this.cards[0]) == 10 &&getsuitcode(that.cards[0]) == 2) {
                    return +1; //10JQKA & 2345A
                } else if (getsuitcode(this.cards[0]) == 2 && getsuitcode(that.cards[0]) == 2) {
                    return this.cards[3].compareTo(that.cards[3]); // 2345A & 2345A, compare 4th card
                } else {
                    return this.cards[4].compareTo(that.cards[4]);
                }
            case 4: // flush
                return this.cards[4].compareTo(that.cards[4]);
            case 5: // full house
                return this.find_fullhouse_max().compareTo(that.find_fullhouse_max());
        }
        return 0;
    }

    private Card find_onepair_max() {
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1])) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_twopairs_max() {
        for (int i = 2; i < 4; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1])) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_fullhouse_max() {
        for (int i = 0; i < 3; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1]) &&getfacecode(this.cards[i + 1]) == getfacecode(this.cards[i + 2])) {
                return this.cards[i + 2];
            }
        }
        return null;
    }
}

