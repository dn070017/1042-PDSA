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
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        int this_order = 0;
        int that_order = 0;
        
        int[] this_face = new int[5];
        int[] that_face = new int[5];
        int[] this_suit = new int[5];
        int[] that_suit = new int[5];
        for (int i = 0; i < 5; i++) {
            if (!this.cards[i].getFace().equals(""J"") && !this.cards[i].getFace().equals(""Q"") && !this.cards[i].getFace().equals(""K"") && !this.cards[i].getFace().equals(""A"")) {
                this_face[i] = Integer.parseInt(this.cards[i].getFace());
            } else {
                if (this.cards[i].getFace().equals(""J"")) {
                    this_face[i] = 11;
                }
                if (this.cards[i].getFace().equals(""Q"")) {
                    this_face[i] = 12;
                }
                if (this.cards[i].getFace().equals(""K"")) {
                    this_face[i] = 13;
                }
                if (this.cards[i].getFace().equals(""A"")) {
                    this_face[i] = 14;
                }
            }
            if (!that.cards[i].getFace().equals(""J"") && !that.cards[i].getFace().equals(""Q"") && !that.cards[i].getFace().equals(""K"") && !that.cards[i].getFace().equals(""A"")) {
                that_face[i] = Integer.parseInt(that.cards[i].getFace());
            } else {
                if (that.cards[i].getFace().equals(""J"")) {
                    that_face[i] = 11;
                }
                if (that.cards[i].getFace().equals(""Q"")) {
                    that_face[i] = 12;
                }
                if (that.cards[i].getFace().equals(""K"")) {
                    that_face[i] = 13;
                }
                if (that.cards[i].getFace().equals(""A"")) {
                    that_face[i] = 14;
                }
            }
            if (this.cards[i].getSuit().equals(""Spades"")) {
                this_suit[i] = 4;
            }
            if (this.cards[i].getSuit().equals(""Hearts"")) {
                this_suit[i] = 3;
            }
            if (this.cards[i].getSuit().equals(""Diamonds"")) {
                this_suit[i] = 2;
            }
            if (this.cards[i].getSuit().equals(""Clubs"")) {
                this_suit[i] = 1;
            }
            if (that.cards[i].getSuit().equals(""Spades"")) {
                that_suit[i] = 4;
            }
            if (that.cards[i].getSuit().equals(""Hearts"")) {
                that_suit[i] = 3;
            }
            if (that.cards[i].getSuit().equals(""Diamonds"")) {
                that_suit[i] = 2;
            }
            if (that.cards[i].getSuit().equals(""Clubs"")) {
                that_suit[i] = 1;
            }
        }
        //this.cards, if order = Full house; this_order = 5;
        int this_full_3m = 0;
        int that_full_3m = 0;
        int this_full_3m_suit = 0;
        int that_full_3m_suit = 0;
        int this_full_2m = 0;
        int that_full_2m = 0;
        int this_full_2m_suit = 0;
        int that_full_2m_suit = 0;
        if (this_face[0] == this_face[1] && this_face[1] == this_face[2] && this_face[3] == this_face[4]) {
            this_order = 5;   //3 match: 2 match
            this_full_3m = this_face[2];
            this_full_3m_suit = this_suit[2];
            this_full_2m = this_face[4];
            this_full_2m_suit = this_suit[4];
        }
        if (this_face[0] == this_face[1] && this_face[2] == this_face[3] && this_face[3] == this_face[4]) {
            this_order = 5;   //2 match: 3 match
            this_full_3m = this_face[4];
            this_full_3m_suit = this_suit[4];
            this_full_2m = this_face[1];
            this_full_2m_suit = this_suit[1];
        }
        //that.cards, if order = Full house; that_order = 5;
        if (that_face[0] == that_face[1] && that_face[1] == that_face[2] && that_face[3] == that_face[4]) {
            that_order = 5;   //3 match: 2 match
            that_full_3m = that_face[2];
            that_full_3m_suit = that_suit[2];
            that_full_2m = that_face[4];
            that_full_2m_suit = that_suit[4];
        }
        if (that_face[0] == that_face[1] && that_face[2] == that_face[3] && that_face[3] == that_face[4]) {
            that_order = 5;   //2 match: 3 match
            that_full_3m = that_face[4];
            that_full_3m_suit = that_suit[4];
            that_full_2m = that_face[1];
            that_full_2m_suit = that_suit[1];
        }   // Full house finished
        //this.cards, if order = Flush; this_order = 4;
        if (this.cards[0].getSuit() == this.cards[1].getSuit() && this.cards[1].getSuit() == this.cards[2].getSuit() && this.cards[2].getSuit() == this.cards[3].getSuit() && this.cards[3].getSuit() == this.cards[4].getSuit()) {
            this_order = 4;
        }
        //that.cards, if order = Flush; that_order = 4;
        if (that.cards[0].getSuit() == that.cards[1].getSuit() && that.cards[1].getSuit() == that.cards[2].getSuit() && that.cards[2].getSuit() == that.cards[3].getSuit() && that.cards[3].getSuit() == that.cards[4].getSuit()) {
            that_order = 4;
        }   // Flush finished;
        
        int this_straight = 0;
        int that_straight = 0;
        
        //this.cards, if order = Straight; this_order = 3;
        if (this_face[1] - this_face[0] == 1 && this_face[2] - this_face[1] == 1 && this_face[3] - this_face[2] == 1 && this_face[4] - this_face[3] == 1) {
            this_order = 3;
        }
        if (this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"") && this.cards[2].getFace().equals(""4"")&& this.cards[3].getFace().equals(""5"")&& this.cards[4].getFace().equals(""A"")) {
            this_order = 3;
        }
        //that.cards, if order = Straight; that_order = 3
        if (that_face[1] - that_face[0] == 1 && that_face[2] - that_face[1] == 1 && that_face[3] - that_face[2] == 1 && that_face[4] - that_face[3] == 1) {
            that_order = 3;
        }
        if (that.cards[0].getFace().equals(""2"") && that.cards[1].getFace().equals(""3"") && that.cards[2].getFace().equals(""4"")&& that.cards[3].getFace().equals(""5"")&& that.cards[4].getFace().equals(""A"")) {
            this_order = 3;
        }
        if (this_order == 3){
            if (this.cards[4].getFace().equals(""5"")){
                this_straight = 5;
            } else {
                this_straight = this_face[4];
            }
            if (that.cards[4].getFace().equals(""5"")){
                that_straight = 5;
            } else {
                that_straight = that_face[4];
            }
        }   // Straight finished;
        //this.cards, if order = Two pair; this_order = 2;
        int this_two_2m_hi = 0;
        int this_two_2m_hi_suit = 0;
        int this_two_2m_lo = 0;
        int this_two_2m_lo_suit = 0;
        int this_two_1s = 0;
        int this_two_1s_suit = 0;
        int that_two_2m_hi = 0;
        int that_two_2m_hi_suit = 0;
        int that_two_2m_lo = 0;
        int that_two_2m_lo_suit = 0;
        int that_two_1s = 0;
        int that_two_1s_suit = 0;
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[2].getFace() == this.cards[3].getFace() && this.cards[4].getFace() != this.cards[0].getFace() && this.cards[4].getFace() != this.cards[2].getFace()) {
            this_order = 2;   //2 match: 2 match: 1 single;
            this_two_2m_hi = this_face[3];
            this_two_2m_hi_suit = this_suit[3];
            this_two_2m_lo = this_face[1];
            this_two_2m_lo_suit = this_suit[1];
            this_two_1s = this_face[4];
            this_two_1s_suit = this_suit[4];
        }
        if (this.cards[1].getFace() == this.cards[2].getFace() && this.cards[3].getFace() == this.cards[4].getFace() && this.cards[0].getFace() != this.cards[1].getFace() && this.cards[0].getFace() != this.cards[3].getFace()) {
            this_order = 2;   //1 single: 2 match: 2 match;
            this_two_2m_hi = this_face[4];
            this_two_2m_hi_suit = this_suit[4];
            this_two_2m_lo = this_face[2];
            this_two_2m_lo_suit = this_suit[2];
            this_two_1s = this_face[0];
            this_two_1s_suit = this_suit[0];
        }
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[3].getFace() == this.cards[4].getFace() && this.cards[2].getFace() != this.cards[0].getFace() && this.cards[2].getFace() != this.cards[3].getFace()) {
            this_order = 2;   //2 match: 1 single: 2 match;
            this_two_2m_hi = this_face[4];
            this_two_2m_hi_suit = this_suit[4];
            this_two_2m_lo = this_face[1];
            this_two_2m_lo_suit = this_suit[1];
            this_two_1s = this_face[2];
            this_two_1s_suit = this_suit[2];
        }
        //that hands order = Two pair 2
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[2].getFace() == that.cards[3].getFace() && that.cards[4].getFace() != that.cards[0].getFace() && that.cards[4].getFace() != that.cards[2].getFace()) {
            that_order = 2;   //2 match: 2 match: 1 single;
            that_two_2m_hi = that_face[3];
            that_two_2m_hi_suit = that_suit[3];
            that_two_2m_lo = that_face[1];
            that_two_2m_lo_suit = that_suit[1];
            that_two_1s = that_face[4];
            that_two_1s_suit = that_suit[4];
        }
        if (that.cards[1].getFace() == that.cards[2].getFace() && that.cards[3].getFace() == that.cards[4].getFace() && that.cards[0].getFace() != that.cards[1].getFace() && that.cards[0].getFace() != that.cards[3].getFace()) {
            that_order = 2;   //1 single: 2 match: 2 match;
            that_two_2m_hi = that_face[4];
            that_two_2m_hi_suit = that_suit[4];
            that_two_2m_lo = that_face[2];
            that_two_2m_lo_suit = that_suit[2];
            that_two_1s = that_face[0];
            that_two_1s_suit = that_suit[0];
        }
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[3].getFace() == that.cards[4].getFace() && that.cards[2].getFace() != that.cards[0].getFace() && that.cards[2].getFace() != that.cards[3].getFace()) {
            that_order = 2;   //2 match: 1 single: 2 match;
            that_two_2m_hi = that_face[4];
            that_two_2m_hi_suit = that_suit[4];
            that_two_2m_lo = that_face[1];
            that_two_2m_lo_suit = that_suit[1];
            that_two_1s = that_face[2];
            that_two_1s_suit = that_suit[2];
        }   // Two pair finished;
        //this.cards, if order = One pair; this_order = 1;
        int this_one_m = 0;
        int this_one_m_suit = 0;
        int this_one_s_hi = 0;
        int this_one_s_hi_suit = 0;
        int this_one_s_mi = 0;
        int this_one_s_mi_suit = 0;
        int this_one_s_lo = 0;
        int this_one_s_lo_suit = 0;
        int that_one_m = 0;
        int that_one_m_suit = 0;
        int that_one_s_hi = 0;
        int that_one_s_hi_suit = 0;
        int that_one_s_mi = 0;
        int that_one_s_mi_suit = 0;
        int that_one_s_lo = 0;
        int that_one_s_lo_suit = 0;
        if (this.cards[0].getFace() == this.cards[1].getFace() && this.cards[2].getFace() != this.cards[0].getFace() && this.cards[3].getFace() != this.cards[0].getFace() && this.cards[4].getFace() != this.cards[0].getFace()) {
            this_order = 1;
            this_one_m = this_face[1];
            this_one_m_suit = this_suit[1];
            this_one_s_hi = this_face[4];
            this_one_s_hi_suit = this_suit[4];
            this_one_s_mi = this_face[3];
            this_one_s_mi_suit = this_suit[3];
            this_one_s_lo = this_face[2];
            this_one_s_lo_suit = this_suit[2];
        }
        if (this.cards[1].getFace() == this.cards[2].getFace() && this.cards[0].getFace() != this.cards[1].getFace() && this.cards[3].getFace() != this.cards[1].getFace() && this.cards[4].getFace() != this.cards[1].getFace()) {
            this_order = 1;
            this_one_m = this_face[2];
            this_one_m_suit = this_suit[2];
            this_one_s_hi = this_face[4];
            this_one_s_hi_suit = this_suit[4];
            this_one_s_mi = this_face[3];
            this_one_s_mi_suit = this_suit[3];
            this_one_s_lo = this_face[0];
            this_one_s_lo_suit = this_suit[0];
        }
        if (this.cards[2].getFace() == this.cards[3].getFace() && this.cards[0].getFace() != this.cards[2].getFace() && this.cards[1].getFace() != this.cards[2].getFace() && this.cards[4].getFace() != this.cards[2].getFace()) {
            this_order = 1;
            this_one_m = this_face[3];
            this_one_m_suit = this_suit[3];
            this_one_s_hi = this_face[4];
            this_one_s_hi_suit = this_suit[4];
            this_one_s_mi = this_face[1];
            this_one_s_mi_suit = this_suit[1];
            this_one_s_lo = this_face[0];
            this_one_s_lo_suit = this_suit[0];
        }
        if (this.cards[3].getFace() == this.cards[4].getFace() && this.cards[0].getFace() != this.cards[3].getFace() && this.cards[1].getFace() != this.cards[3].getFace() && this.cards[2].getFace() != this.cards[3].getFace()) {
            this_order = 1;
            this_one_m = this_face[4];
            this_one_m_suit = this_suit[4];
            this_one_s_hi = this_face[2];
            this_one_s_hi_suit = this_suit[2];
            this_one_s_mi = this_face[1];
            this_one_s_mi_suit = this_suit[1];
            this_one_s_lo = this_face[0];
            this_one_s_lo_suit = this_suit[0];
        }
        //that.cards, if order = One pair; that_order = 1;
        if (that.cards[0].getFace() == that.cards[1].getFace() && that.cards[2].getFace() != that.cards[0].getFace() && that.cards[3].getFace() != that.cards[0].getFace() && that.cards[4].getFace() != that.cards[0].getFace()) {
            that_order = 1;
            that_order = 1;
            that_one_m = that_face[1];
            that_one_m_suit = that_suit[1];
            that_one_s_hi = that_face[4];
            that_one_s_hi_suit = that_suit[4];
            that_one_s_mi = that_face[3];
            that_one_s_mi_suit = that_suit[3];
            that_one_s_lo = that_face[2];
            that_one_s_lo_suit = that_suit[2];
        }
        if (that.cards[1].getFace() == that.cards[2].getFace() && that.cards[0].getFace() != that.cards[1].getFace() && that.cards[3].getFace() != that.cards[1].getFace() && that.cards[4].getFace() != that.cards[1].getFace()) {
            that_order = 1;
            that_one_m = that_face[2];
            that_one_m_suit = that_suit[2];
            that_one_s_hi = that_face[4];
            that_one_s_hi_suit = that_suit[4];
            that_one_s_mi = that_face[3];
            that_one_s_mi_suit = that_suit[3];
            that_one_s_lo = that_face[0];
            that_one_s_lo_suit = that_suit[0];
        }
        if (that.cards[2].getFace() == that.cards[3].getFace() && that.cards[0].getFace() != that.cards[2].getFace() && that.cards[1].getFace() != that.cards[2].getFace() && that.cards[4].getFace() != that.cards[2].getFace()) {
            that_order = 1;
            that_one_m = that_face[3];
            that_one_m_suit = that_suit[3];
            that_one_s_hi = that_face[4];
            that_one_s_hi_suit = that_suit[4];
            that_one_s_mi = that_face[1];
            that_one_s_mi_suit = that_suit[1];
            that_one_s_lo = that_face[0];
            that_one_s_lo_suit = that_suit[0];
        }
        if (that.cards[3].getFace() == that.cards[4].getFace() && that.cards[0].getFace() != that.cards[3].getFace() && that.cards[1].getFace() != that.cards[3].getFace() && that.cards[2].getFace() != that.cards[3].getFace()) {
            that_order = 1;
            that_one_m = that_face[4];
            that_one_m_suit = that_suit[4];
            that_one_s_hi = that_face[2];
            that_one_s_hi_suit = that_suit[2];
            that_one_s_mi = that_face[1];
            that_one_s_mi_suit = that_suit[1];
            that_one_s_lo = that_face[0];
            that_one_s_lo_suit = that_suit[0];
        }   // One pair finished;
        if (this_order < that_order) return -1;
        if (this_order > that_order) return 1;
        if (this_order == that_order){
            if (this_order == 5){
                if (this_full_3m < that_full_3m) return -1;
                if (this_full_3m > that_full_3m) return 1;    
                if (this_full_3m == that_full_3m) {
                    if (this_full_3m_suit < that_full_3m_suit) return -1;
                    if (this_full_3m_suit > that_full_3m_suit) return 1;
                    if (this_full_3m_suit == that_full_3m_suit) {
                        if (this_full_2m < that_full_2m) return -1;
                        if (this_full_2m > that_full_2m) return 1;
                        if (this_full_2m == that_full_2m) {
                            if (this_full_2m_suit < that_full_2m_suit) return -1;
                            if (this_full_2m_suit > that_full_2m_suit) return 1;
                        }
                    }
                }
            }
            if (this_order == 4){
                if (this_face[4] < that_face[4]) return -1;
                if (this_face[4] > that_face[4]) return 1;
                if (this_face[4] == that_face[4]) {
                    if (this_face[3] < that_face[3]) return -1;
                    if (this_face[3] > that_face[3]) return 1;
                    if (this_face[3] == that_face[3]) {
                        if (this_face[2] < that_face[2]) return -1;
                        if (this_face[2] > that_face[2]) return 1;
                        if (this_face[2] == that_face[2]) {
                            if (this_face[1] < that_face[1]) return -1;
                            if (this_face[1] > that_face[1]) return 1;
                            if (this_face[1] == that_face[1]) {
                                if (this_face[0] < that_face[0]) return -1;
                                if (this_face[0] > that_face[0]) return 1;
                            }
                        }
                    }
                }
            }
            if (this_order == 3){
                if (this_suit[4] < that_suit[4]) return -1;
                if (this_suit[4] > that_suit[4]) return 1;
                if (this_suit[4] == that_suit[4]) {
                    if (this_suit[3] < that_suit[3]) return -1;
                    if (this_suit[3] > that_suit[3]) return 1;
                    if (this_suit[3] == that_suit[3]) {
                        if (this_suit[2] < that_suit[2]) return -1;
                        if (this_suit[2] > that_suit[2]) return 1;
                        if (this_suit[2] == that_suit[2]) {
                            if (this_suit[1] < that_suit[1]) return -1;
                            if (this_suit[1] > that_suit[1]) return 1;
                            if (this_suit[1] == that_suit[1]) {
                                if (this_suit[0] < that_suit[0]) return -1;
                                if (this_suit[0] > that_suit[0]) return 1;
                            }
                        }
                    }
                }
            }
            if (this_order == 2){
                if (this_two_2m_hi < that_two_2m_hi) return -1;
                if (this_two_2m_hi > that_two_2m_hi) return 1;
                if (this_two_2m_hi == that_two_2m_hi) {
                    if (this_two_2m_hi_suit < that_two_2m_hi_suit) return -1;
                    if (this_two_2m_hi_suit > that_two_2m_hi_suit) return 1;
                    if (this_two_2m_hi_suit == that_two_2m_hi_suit) {
                        if (this_two_2m_lo < that_two_2m_lo) return -1;
                        if (this_two_2m_lo > that_two_2m_lo) return 1;
                        if (this_two_2m_lo == that_two_2m_lo) {
                            if (this_two_2m_lo_suit < that_two_2m_lo_suit) return -1;
                            if (this_two_2m_lo_suit > that_two_2m_lo_suit) return 1;
                            if (this_two_2m_lo_suit == that_two_2m_lo_suit) {
                                if (this_two_1s < that_two_1s) return -1;
                                if (this_two_1s > that_two_1s) return 1;
                                if (this_two_1s == that_two_1s) {
                                    if (this_two_1s_suit < that_two_1s_suit) return -1;
                                    if (this_two_1s_suit > that_two_1s_suit) return 1;
                                }
                            }
                        }
                    }
                }
            }
            if (this_order == 1){
                if (this_one_m < that_one_m) return -1;
                if (this_one_m > that_one_m) return 1;
                if (this_one_m == that_one_m) {
                    if (this_one_m_suit < that_one_m_suit) return -1;
                    if (this_one_m_suit > that_one_m_suit) return 1;
                    if (this_one_m_suit == that_one_m_suit) {
                        if (this_one_s_hi < that_one_s_hi) return -1;
                        if (this_one_s_hi > that_one_s_hi) return 1;
                        if (this_one_s_hi == that_one_s_hi) {
                            if (this_one_s_hi_suit < that_one_s_hi_suit) return -1;
                            if (this_one_s_hi_suit > that_one_s_hi_suit) return 1;
                            if (this_one_s_hi_suit == that_one_s_hi_suit) {
                                if (this_one_s_mi < that_one_s_mi) return -1;
                                if (this_one_s_mi > that_one_s_mi) return 1;
                                if (this_one_s_mi == that_one_s_mi) {
                                    if (this_one_s_mi_suit < that_one_s_mi_suit) return -1;
                                    if (this_one_s_mi_suit > that_one_s_mi_suit) return 1;
                                    if (this_one_s_mi_suit == that_one_s_mi_suit) {
                                        if (this_one_s_lo < that_one_s_lo) return -1;
                                        if (this_one_s_lo > that_one_s_lo) return 1;
                                        if (this_one_s_lo == that_one_s_lo) {
                                            if (this_one_s_lo_suit < that_one_s_lo_suit) return -1;
                                            if (this_one_s_lo_suit > that_one_s_lo_suit) return 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this_order == 0){
                if (this_face[4] < that_face[4]) return -1;
                if (this_face[4] > that_face[4]) return 1;
                if (this_face[4] == that_face[4]) {
                    if (this_suit[4] < that_suit[4]) return -1;
                    if (this_suit[4] > that_suit[4]) return 1;
                    if (this_suit[4] == that_suit[4]) {
                        if (this_face[3] < that_face[3]) return -1;
                        if (this_face[3] > that_face[3]) return 1;
                        if (this_face[3] == that_face[3]) {
                            if (this_suit[3] < that_suit[3]) return -1;
                            if (this_suit[3] > that_suit[3]) return 1;
                            if (this_suit[3] == that_suit[3]) {
                                if (this_face[2] < that_face[2]) return -1;
                                if (this_face[2] > that_face[2]) return 1;
                                if (this_face[2] == that_face[2]) {
                                    if (this_suit[2] < that_suit[2]) return -1;
                                    if (this_suit[2] > that_suit[2]) return 1;
                                    if (this_suit[2] == that_suit[2]) {
                                        if (this_face[1] < that_face[1]) return -1;
                                        if (this_face[1] > that_face[1]) return 1;
                                        if (this_face[1] == that_face[1]) {
                                            if (this_suit[1] < that_suit[1]) return -1;
                                            if (this_suit[1] > that_suit[1]) return 1;
                                            if (this_suit[1] == that_suit[1]) {
                                                if (this_face[0] < that_face[0]) return -1;
                                                if (this_face[0] > that_face[0]) return 1;
                                                if (this_face[0] == that_face[0]) {
                                                    if (this_suit[0] < that_suit[0]) return -1;
                                                    if (this_suit[0] > that_suit[0]) return 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

