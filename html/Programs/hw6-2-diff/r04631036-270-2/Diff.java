
public class Player implements Comparable<Player> {

    int checkpair = 0;
    int checktwopair = 0;
    //int checkpair=0;
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
        if (gettyperank(this.cards) < gettyperank(that.cards)) {
            return 1;
        } else if (gettyperank(this.cards) > gettyperank(that.cards)) {
            return -1;
        }
        
        if (gettyperank(this.cards) == gettyperank(that.cards)) {
            if (gettyperank(this.cards) == 6) {
                if (rank(this.cards[4]) < rank(that.cards[4])) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (gettyperank(this.cards) == 5) {
                if (this.cards[findpairMax(this.cards)].compareTo(that.cards[findpairMax(that.cards)] )==1) {
                    return 1;
                } else {
                    return -1;
                }

            }
            else if (gettyperank(this.cards) == 3) {
                if (this.cards[4].compareTo(that.cards[4] )==1) {
                    return 1;
                } else {
                    return -1;
                }

            }

        }

        return 0;
    }

    public int rank(Card c) {
        if (c.getFace().equalsIgnoreCase(""A"")) {
            return 1;
        } else if (c.getFace().equalsIgnoreCase(""K"")) {
            return 2;
        } else if (c.getFace().equalsIgnoreCase(""Q"")) {
            return 3;
        } else if (c.getFace().equalsIgnoreCase(""J"")) {
            return 4;
        } else if (c.getFace().equalsIgnoreCase(""10"")) {
            return 5;
        } else if (c.getFace().equalsIgnoreCase(""9"")) {
            return 6;
        } else if (c.getFace().equalsIgnoreCase(""8"")) {
            return 7;
        } else if (c.getFace().equalsIgnoreCase(""7"")) {
            return 8;
        } else if (c.getFace().equalsIgnoreCase(""6"")) {
            return 9;
        } else if (c.getFace().equalsIgnoreCase(""5"")) {
            return 10;
        } else if (c.getFace().equalsIgnoreCase(""4"")) {
            return 11;
        } else if (c.getFace().equalsIgnoreCase(""3"")) {
            return 12;
        } else if (c.getFace().equalsIgnoreCase(""2"")) {
            return 13;
        }

        return 0;
    }

    public int numberrank(Card c) {
        if (c.getFace().equalsIgnoreCase(""A"")) {
            return 1;
        } else if (c.getFace().equalsIgnoreCase(""K"")) {
            return 13;
        } else if (c.getFace().equalsIgnoreCase(""Q"")) {
            return 12;
        } else if (c.getFace().equalsIgnoreCase(""J"")) {
            return 11;
        } else if (c.getFace().equalsIgnoreCase(""10"")) {
            return 10;
        } else if (c.getFace().equalsIgnoreCase(""9"")) {
            return 9;
        } else if (c.getFace().equalsIgnoreCase(""8"")) {
            return 8;
        } else if (c.getFace().equalsIgnoreCase(""7"")) {
            return 7;
        } else if (c.getFace().equalsIgnoreCase(""6"")) {
            return 6;
        } else if (c.getFace().equalsIgnoreCase(""5"")) {
            return 5;
        } else if (c.getFace().equalsIgnoreCase(""4"")) {
            return 4;
        } else if (c.getFace().equalsIgnoreCase(""3"")) {
            return 3;
        } else if (c.getFace().equalsIgnoreCase(""2"")) {
            return 2;
        }

        return 0;
    }

    public int fullHouse(Card[] c) {
        int test;
        int comparison = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (c[counter - 1].getFace().equalsIgnoreCase(c[counter].getFace())) {
                comparison++;
            }
        }
        if (comparison == 3) {
            return 1;
        } else {
            return 0;
        }
    }

    private int flush(Card[] c) {
        for (int i = 1; i < 5; i++) {
            if (!c[0].getSuit().equalsIgnoreCase(c[i].getSuit())) {
                return 0;
            }
        }
        return 1;
    }

    private int straight(Card[] c) {
        for (int i = 1; i < 5; i++) {
            if (numberrank(c[i - 1]) != (numberrank(c[i]) - 1)) {

                return 0;
            }

        }
        return 1;
    }

    public int findtwopairMax(Card[] c) {
        int check = 0;
        int max1 = 0;
        int max2 = 0;
        int index=0;
        for (int i = 1; i < 5; i++) {
            if (c[i - 1].getFace().equalsIgnoreCase(c[i].getFace())) {
                check++;
            if(check==1)
                max1=i;
            if(check==2)
                max2=i;
            }
        }
        if(c[max1].compareTo(c[max2])==1)
            return max1;
        else if(c[max1].compareTo(c[max2])==-1)
            return max2;
        
        
            return 0;
        
    }

    private int twopairs(Card[] c) {
        int check = 0;
        for (int i = 1; i < 5; i++) {
            if (c[i - 1].getFace().equalsIgnoreCase(c[i].getFace())) {
                check++;
            }
        }
        if (check == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    public int findpairMax(Card[] c) {
        int check = 0;
        int index=0;
        for (int i = 1; i < 5; i++) {
            if (c[i - 1].getFace().equalsIgnoreCase(c[i].getFace())) {
                index=i;
                check++;
            }
        }
        if (check == 1) {
            return index;
        } else {
            return 0;
        }
    }

    private int pair(Card[] c) {
        int check = 0;
        for (int i = 1; i < 5; i++) {
            if (c[i - 1].getFace().equalsIgnoreCase(c[i].getFace())) {
                check++;
            }
        }
        if (check == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    private int gettyperank(Card[] c) {
        if (fullHouse(c) == 1) {
            return 1;
        } else if (flush(c) == 1) {
            return 2;
        } else if (straight(c) == 1) {
            return 3;
        } else if (twopairs(c) == 1) {
            return 4;
        } else if (pair(c) == 1) {
            return 5;
        }

        return 6;
    }
}

