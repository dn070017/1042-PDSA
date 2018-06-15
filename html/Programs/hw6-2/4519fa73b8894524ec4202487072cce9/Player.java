import java.util.Arrays;

public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    private int cardstype;

    //full house = 5... high card = 0
    public int findcardstype() {

        int difffacecount = 1;
        for (int i = 0; i < 4; i++) {
            if (this.cards[i].getfacecode() != this.cards[i + 1].getfacecode()) {
                difffacecount++;
            }
        }
        if (difffacecount == 2) {
            return 5; // full house
        }
        if (difffacecount == 3) {
            for (int i = 0; i < 3; i++) {
                if (this.cards[i].getfacecode() == this.cards[i + 1].getfacecode() && this.cards[i + 1].getfacecode() == this.cards[i + 2].getfacecode()) {
                    return 1; // 3 of a kind, equal to 1 pair
                }
            }
            return 2; // 2 pairs
        }
        if (difffacecount == 4) {
            return 1; // 1 pair
        }

        for (int i = 0; i < 4; i++) {
            if (this.cards[i].getsuitcode() != this.cards[i + 1].getsuitcode()) {
                break;
            }
            if (i == 3) {
                return 4; // flush
            }
        }
        for (int i = 0; i < 4; i++) {
            if (this.cards[i].getfacecode() + 1 != this.cards[i + 1].getfacecode()) {
                if (i == 3 && this.cards[i + 1].getfacecode() == 14) {
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

//        for (int i = 1; i < 5; i++) {
//            System.out.print(this.cards[i]);
//        }
//        System.out.print(this.cardstype);
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
                if (this.cards[0].getsuitcode() == 2 && that.cards[0].getsuitcode() == 10) {
                    return -1; //2345A & 10JQKA
                } else if (this.cards[0].getsuitcode() == 10 && that.cards[0].getsuitcode() == 2) {
                    return +1; //10JQKA & 2345A
                } else if (this.cards[0].getsuitcode() == 2 && that.cards[0].getsuitcode() == 2) {
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
            if (this.cards[i].getfacecode() == this.cards[i + 1].getfacecode()) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_twopairs_max() {
        for (int i = 2; i < 4; i++) {
            if (this.cards[i].getfacecode() == this.cards[i + 1].getfacecode()) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_fullhouse_max() {
        for (int i = 0; i < 3; i++) {
            if (this.cards[i].getfacecode() == this.cards[i + 1].getfacecode() && this.cards[i + 1].getfacecode() == this.cards[i + 2].getfacecode()) {
                return this.cards[i + 2];
            }
        }
        return null;
    }
}
