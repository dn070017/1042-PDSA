
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
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        int this_hands_order = 0;
        int that_hands_order = 0;
        //this hands order = Full house 5
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[1].getFace() == this.cards[2].getFace() && this.cards[3].getFace() == this.cards[4].getFace()) {
            this_hands_order = 5;//3:2
        }
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[2].getFace() == this.cards[3].getFace() && this.cards[3].getFace() == this.cards[4].getFace()) {
            this_hands_order = 5;//2:3
        }
        //that hands order = Full house 5
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[1].getFace() == that.cards[2].getFace() && that.cards[3].getFace() == that.cards[4].getFace()) {
            that_hands_order = 5;//3:2
        }
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[2].getFace() == that.cards[3].getFace() && that.cards[3].getFace() == that.cards[4].getFace()) {
            that_hands_order = 5;//2:3
        }
        //this hands order = Flush 4
        if (this.cards[0].getSuit() == this.cards[1].getSuit() && this.cards[1].getSuit() == this.cards[2].getSuit() && this.cards[2].getSuit() == this.cards[3].getSuit() && this.cards[3].getSuit() == this.cards[4].getSuit()) {
            this_hands_order = 4;
        }
        //that hands order = Flush 4
        if (that.cards[0].getSuit() == that.cards[1].getSuit() && that.cards[1].getSuit() == that.cards[2].getSuit() && that.cards[2].getSuit() == that.cards[3].getSuit() && that.cards[3].getSuit() == that.cards[4].getSuit()) {
            that_hands_order = 4;
        }
        int[] this_hands_face_straight = new int[5];
        int[] that_hands_face_straight = new int[5];
        int[] this_hands_face_other = new int[5];
        int[] that_hands_face_other = new int[5];

        int[] this_hands_suit = new int[5];
        int[] that_hands_suit = new int[5];
        for (int i = 0; i < 5; i++) {
            if (!this.cards[i].getFace().equals(""J"") && !this.cards[i].getFace().equals(""Q"") && !this.cards[i].getFace().equals(""K"") && !this.cards[i].getFace().equals(""A"")) {
                this_hands_face_straight[i] = Integer.parseInt(this.cards[i].getFace());
            } else {
                if (this.cards[i].getFace().equals(""J"")) {
                    this_hands_face_straight[i] = 11;
                    this_hands_face_other[i] = 11;
                }
                if (this.cards[i].getFace().equals(""Q"")) {
                    this_hands_face_straight[i] = 12;
                    this_hands_face_other[i] = 12;
                }
                if (this.cards[i].getFace().equals(""K"")) {
                    this_hands_face_straight[i] = 13;
                    this_hands_face_other[i] = 13;
                }
                if (this.cards[i].getFace().equals(""A"")) {
                    this_hands_face_straight[i] = 1;
                    this_hands_face_other[i] = 14;
                }
            }
            if (!that.cards[i].getFace().equals(""J"") && !that.cards[i].getFace().equals(""Q"") && !that.cards[i].getFace().equals(""K"") && !that.cards[i].getFace().equals(""A"")) {
                that_hands_face_straight[i] = Integer.parseInt(that.cards[i].getFace());
            } else {
                if (that.cards[i].getFace().equals(""J"")) {
                    that_hands_face_straight[i] = 11;
                    that_hands_face_other[i] = 11;
                }
                if (that.cards[i].getFace().equals(""Q"")) {
                    that_hands_face_straight[i] = 12;
                    that_hands_face_other[i] = 12;
                }
                if (that.cards[i].getFace().equals(""K"")) {
                    that_hands_face_straight[i] = 13;
                    that_hands_face_other[i] = 13;
                }
                if (that.cards[i].getFace().equals(""A"")) {
                    that_hands_face_straight[i] = 1;
                    that_hands_face_other[i] = 14;
                }
            }
            if (this.cards[i].getSuit().equals(""Spades"")) {
                this_hands_suit[i] = 4;
            }
            if (this.cards[i].getSuit().equals(""Hearts"")) {
                this_hands_suit[i] = 3;
            }
            if (this.cards[i].getSuit().equals(""Diamonds"")) {
                this_hands_suit[i] = 2;
            }
            if (this.cards[i].getSuit().equals(""Clubs"")) {
                this_hands_suit[i] = 1;
            }
            if (that.cards[i].getSuit().equals(""Spades"")) {
                that_hands_suit[i] = 4;
            }
            if (that.cards[i].getSuit().equals(""Hearts"")) {
                that_hands_suit[i] = 3;
            }
            if (that.cards[i].getSuit().equals(""Diamonds"")) {
                that_hands_suit[i] = 2;
            }
            if (that.cards[i].getSuit().equals(""Clubs"")) {
                that_hands_suit[i] = 1;
            }
        }
        //this hands order = Straight 3
        if (this_hands_face_straight[1] - this_hands_face_straight[0] == 1 && this_hands_face_straight[2] - this_hands_face_straight[1] == 1 && this_hands_face_straight[3] - this_hands_face_straight[2] == 1 && this_hands_face_straight[4] - this_hands_face_straight[3] == 1) {
            this_hands_order = 3;
        }
        //that hands order = Straight 3
        if (that_hands_face_straight[1] - that_hands_face_straight[0] == 1 && that_hands_face_straight[2] - that_hands_face_straight[1] == 1 && that_hands_face_straight[3] - that_hands_face_straight[2] == 1 && that_hands_face_straight[4] - that_hands_face_straight[3] == 1) {
            that_hands_order = 3;
        }
        //this hands order = Two pair 2
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[2].getFace() == this.cards[3].getFace() && this.cards[4].getFace() != this.cards[0].getFace() && this.cards[4].getFace() != this.cards[2].getFace()) {
            this_hands_order = 2;
        }
        if (this.cards[1].getFace() == this.cards[2].getFace() && this.cards[3].getFace() == this.cards[4].getFace() && this.cards[0].getFace() != this.cards[1].getFace() && this.cards[0].getFace() != this.cards[3].getFace()) {
            this_hands_order = 2;
        }
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[3].getFace() == this.cards[4].getFace() && this.cards[2].getFace() != this.cards[0].getFace() && this.cards[2].getFace() != this.cards[3].getFace()) {
            this_hands_order = 2;
        }
        //that hands order = Two pair 2
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[2].getFace() == that.cards[3].getFace() && that.cards[4].getFace() != that.cards[0].getFace() && that.cards[4].getFace() != that.cards[2].getFace()) {
            that_hands_order = 2;
        }
        if (that.cards[1].getFace() == that.cards[2].getFace() && that.cards[3].getFace() == that.cards[4].getFace() && that.cards[0].getFace() != that.cards[1].getFace() && that.cards[0].getFace() != that.cards[3].getFace()) {
            that_hands_order = 2;
        }
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[3].getFace() == that.cards[4].getFace() && that.cards[2].getFace() != that.cards[0].getFace() && that.cards[2].getFace() != that.cards[3].getFace()) {
            that_hands_order = 2;
        }
        //this hands order = one pair 1
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[2].getFace() != this.cards[0].getFace() && this.cards[3].getFace() != this.cards[0].getFace() && this.cards[4].getFace() != this.cards[0].getFace()) {
            this_hands_order = 1;
        }
        if (this.cards[1].getFace() == this.cards[2].getFace() && this.cards[0].getFace() != this.cards[1].getFace() && this.cards[3].getFace() != this.cards[1].getFace() && this.cards[4].getFace() != this.cards[1].getFace()) {
            this_hands_order = 1;
        }
        if (this.cards[2].getFace() == this.cards[3].getFace() && this.cards[0].getFace() != this.cards[2].getFace() && this.cards[1].getFace() != this.cards[2].getFace() && this.cards[4].getFace() != this.cards[2].getFace()) {
            this_hands_order = 1;
        }
        if (this.cards[3].getFace() == this.cards[4].getFace() && this.cards[0].getFace() != this.cards[3].getFace() && this.cards[1].getFace() != this.cards[3].getFace() && this.cards[2].getFace() != this.cards[3].getFace()) {
            this_hands_order = 1;
        }
        //that hands order = one pair 1
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[2].getFace() != that.cards[0].getFace() && that.cards[3].getFace() != that.cards[0].getFace() && that.cards[4].getFace() != that.cards[0].getFace()) {
            that_hands_order = 1;
        }
        if (that.cards[1].getFace() == that.cards[2].getFace() && that.cards[0].getFace() != that.cards[1].getFace() && that.cards[3].getFace() != that.cards[1].getFace() && that.cards[4].getFace() != that.cards[1].getFace()) {
            that_hands_order = 1;
        }
        if (that.cards[2].getFace() == that.cards[3].getFace() && that.cards[0].getFace() != that.cards[2].getFace() && that.cards[1].getFace() != that.cards[2].getFace() && that.cards[4].getFace() != that.cards[2].getFace()) {
            that_hands_order = 1;
        }
        if (that.cards[3].getFace() == that.cards[4].getFace() && that.cards[0].getFace() != that.cards[3].getFace() && that.cards[1].getFace() != that.cards[3].getFace() && that.cards[2].getFace() != that.cards[3].getFace()) {
            that_hands_order = 1;
        }
        if (this_hands_order < that_hands_order) {
            return -1;
        }
        if (this_hands_order > that_hands_order) {
            return 1;
        }
        if (this_hands_order == that_hands_order) {
            if (this_hands_order == 5) {
                int this_full_house = 0;
                int that_full_house = 0;
                if (this_hands_face_other[0] == this_hands_face_other[1] && this_hands_face_other[1] == this_hands_face_other[2]) {
                    this_full_house = this_hands_face_other[2];
                }
                if (this_hands_face_other[2] == this_hands_face_other[3] && this_hands_face_other[3] == this_hands_face_other[4]) {
                    this_full_house = this_hands_face_other[2];
                }
                if (that_hands_face_other[0] == that_hands_face_other[1] && that_hands_face_other[1] == that_hands_face_other[2]) {
                    that_full_house = that_hands_face_other[2];
                }
                if (that_hands_face_other[2] == that_hands_face_other[3] && that_hands_face_other[3] == that_hands_face_other[4]) {
                    that_full_house = that_hands_face_other[2];
                }
                if (this_full_house < that_full_house) {
                    return -1;
                }
                if (this_full_house > that_full_house) {
                    return 1;
                }
            }
            if (this_hands_order == 4) {
                if (this_hands_suit[4] < that_hands_suit[4]) {
                    return -1;
                }
                if (this_hands_suit[4] > that_hands_suit[4]) {
                    return 1;
                }
            }
            if (this_hands_order == 3) {
                if (this_hands_face_straight[4] < that_hands_face_straight[4]) {
                    return -1;
                }
                if (this_hands_face_straight[4] > that_hands_face_straight[4]) {
                    return 1;
                }
            }
            if (this_hands_order == 2) {
                if (this_hands_face_other[3] < that_hands_face_other[3]) {
                    return -1;
                }
                if (this_hands_face_other[3] > that_hands_face_other[3]) {
                    return 1;
                }
            }
            if (this_hands_order == 1) {
                int this_one_pair = 0;
                int that_one_pair = 0;
                if (this_hands_face_other[0] == this_hands_face_other[1]) {
                    this_one_pair = this_hands_face_other[0];
                }
                if (that_hands_face_other[0] == that_hands_face_other[1]) {
                    that_one_pair = that_hands_face_other[0];
                }
                if (this_hands_face_other[1] == this_hands_face_other[2]) {
                    this_one_pair = this_hands_face_other[1];
                }
                if (that_hands_face_other[1] == that_hands_face_other[2]) {
                    that_one_pair = that_hands_face_other[1];
                }
                if (this_hands_face_other[2] == this_hands_face_other[3]) {
                    this_one_pair = this_hands_face_other[2];
                }
                if (that_hands_face_other[2] == that_hands_face_other[3]) {
                    that_one_pair = that_hands_face_other[2];
                }
                if (this_hands_face_other[3] == this_hands_face_other[4]) {
                    this_one_pair = this_hands_face_other[3];
                }
                if (that_hands_face_other[3] == that_hands_face_other[4]) {
                    that_one_pair = that_hands_face_other[3];
                }
                if (this_one_pair < that_one_pair) {
                    return -1;
                }
                if (this_one_pair > that_one_pair) {
                    return 1;
                }
            }
            if (this_hands_order == 0) {
                if (this_hands_face_other[4] < that_hands_face_other[4]) {
                    return -1;
                }
                if (this_hands_face_other[4] > that_hands_face_other[4]) {
                    return 1;
                }
            }
        }
.
        return 0;
    }
}

