
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
        if (gettyperank(this.cards) < gettyperank(that.cards)) {
            return 1;
        }
        else if (gettyperank(this.cards) > gettyperank(that.cards)) {
            return -1;
        }
        if(gettyperank(this.cards) == gettyperank(that.cards)){
            if(gettyperank(this.cards)==2)
                if(rank(this.cards[4])<rank(that.cards[4]))
                        return 1;
                else return -1;
                }
        return 0;
    }
    public int rank(Card c){
        if(c.getFace().equalsIgnoreCase(""A""))
            return 1;
        else if(c.getFace().equalsIgnoreCase(""K""))
            return 2;
        else if(c.getFace().equalsIgnoreCase(""Q""))
            return 3;
        else if(c.getFace().equalsIgnoreCase(""J""))
            return 4;
        else if(c.getFace().equalsIgnoreCase(""10""))
            return 5;
        else if(c.getFace().equalsIgnoreCase(""9""))
            return 6;
        else if(c.getFace().equalsIgnoreCase(""8""))
            return 7;
        else if(c.getFace().equalsIgnoreCase(""7""))
            return 8;
        else if(c.getFace().equalsIgnoreCase(""6""))
            return 9;
        else if(c.getFace().equalsIgnoreCase(""5""))
            return 10;
        else if(c.getFace().equalsIgnoreCase(""4""))
            return 11;
        else if(c.getFace().equalsIgnoreCase(""3""))
            return 12;
        else if(c.getFace().equalsIgnoreCase(""2""))
            return 13;
        
        return 0;
    }
    public int fullHouse(Card[] c) {
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
//            if (Integer.parseInt(c[i - 1].getFace()) != (Integer.parseInt(c[i].getFace()) - 1)) {
//                return 0;
//            }

        }
        return 1;
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

