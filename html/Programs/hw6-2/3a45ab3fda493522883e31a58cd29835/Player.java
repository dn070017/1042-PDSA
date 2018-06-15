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
        //winner should be in the last

        Arrays.sort(this.cards);
        Arrays.sort(that.cards);

        int flower_a[] = new int[2];
        flower_a[0] = 0;
        flower_a[1] = 0;

        for (int k = 0; k < 2; k++) {
            int my[] = new int[5];
            Card[] see = new Card[5];
            switch (k) {
                case 0:
                    see = this.cards;
                    break;
                case 1:
                    see = that.cards;
            }

            for (int i = 0; i < 5; i++) {
                if (""J"".equals(see[i].getFace())) {
                    my[i] = 11;
                } else if (""Q"".equals(see[i].getFace())) {
                    my[i] = 12;
                } else if (""K"".equals(see[i].getFace())) {
                    my[i] = 13;
                } else if (""A"".equals(see[i].getFace())) {
                    my[i] = 14;
                } else {
                    my[i] = Integer.parseInt(see[i].getFace());
                }

//                System.out.printf(""%d\n "", my[i]);
            }

            int my_2[] = new int[5];
            my_2[0] = my[4];
            for (int i = 0; i < 4; i++) {
                my_2[i + 1] = my[i];
            }
            int save_0 = 0;
            int save_1 = 0;
//       culmulate how many 1 and 0
            for (int i = 0; i < 5; i++) {
                int temp = my_2[i] - my[i];
                if (temp == 0) {
                    save_0 = save_0 + 1;
                } else if (temp == -1) {
                    save_1 = save_1 + 1;
                }
            }
//            System.out.printf(""%d\n "", save_0);
//            System.out.printf(""%d\n "", save_1);
            if (save_0 == 3) {
                flower_a[k] = 6;
            } else if (see[0].getSuit() == see[1].getSuit()) {
                if (see[1].getSuit() == see[2].getSuit()) {
                    if (see[2].getSuit() == see[3].getSuit()) {
                        if (see[3].getSuit() == see[4].getSuit()) {
                            flower_a[k] = 5;
                        }
                    }
                }
            } else if (save_1 == 4) {
                flower_a[k] = 4;
            } else if (save_1 == 3) {
                if (""A"".equals(see[4].getFace())) {
                    flower_a[k] = 4;
                }
            } else if (save_0 == 2) {
                if (see[0] == see[1] && see[1] == see[2]) {
                    flower_a[k] = 2;
                } else if (see[2] == see[3] && see[3] == see[4]) {
                    flower_a[k] = 2;
                } else if (see[1] == see[2] && see[2] == see[3]) {
                    flower_a[k] = 2;
                } else {
                    flower_a[k] = 3;
                }
            } else {
                flower_a[k] = 1;
            }
//            System.out.printf(""%d\n "", flower_a[k]);
        }
       int roo = 0;
        if (flower_a[0] > flower_a[1]) {
            return 1;
        } else if (flower_a[0] < flower_a[1]) {
            return -1;
        } else {
            
            if (flower_a[0] == 6) {
                if (this.cards[0] == this.cards[1] && this.cards[1] == this.cards[2]) {
                    roo = this.cards[0].compareTo(that.cards[0]);
                } else {
                    roo = this.cards[4].compareTo(that.cards[4]);
                }

//                return roo;
            }

// //////////////////////
            if (flower_a[0] == 5) {
                if (flower_a[1] == 5) {
                    roo = 0;
                }
            }
//////////////////////////////
            if (flower_a[0] == 4) {
                roo = Card.SUIT_ORDER.compare(this.cards[4], that.cards[4]);
//                return roo;
            }
            ///////////////////////////////////////////////////
            if (flower_a[0] == 3) {
                roo = Card.SUIT_ORDER.compare(this.cards[3], that.cards[3]);
//                return roo;
            }
            ////////////////////////////////////////////////
            if (flower_a[0] == 2) {
                roo = Card.SUIT_ORDER.compare(this.cards[4], that.cards[4]);
//                return roo;
            }
            /////////////////////////////////////
            if (flower_a[0] == 1) {
                roo = Card.SUIT_ORDER.compare(this.cards[4], that.cards[4]);
//                return roo;
            }

        }

        return roo;
    }
}

