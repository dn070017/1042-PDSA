import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
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

        // String a = this.cards[1].getFace();
        int[] points = new int[5];
        int[] points2 = new int[5];
        for (int i = 0; i < 5; i++) {

            if (!this.cards[i].getFace().equals(""J"") && !this.cards[i].getFace().equals(""Q"") && !this.cards[i].getFace().equals(""K"") && !this.cards[i].getFace().equals(""A"")) {
                points[i] = Integer.parseInt(this.cards[i].getFace());
            } else {
                if (this.cards[i].getFace().equals(""J"")) {
                    points[i] = 11;
                }
                if (this.cards[i].getFace().equals(""Q"")) {
                    points[i] = 12;
                }
                if (this.cards[i].getFace().equals(""K"")) {
                    points[i] = 13;
                }
                if (this.cards[i].getFace().equals(""A"")) {
                    points[i] = 14;
                }
            }

            if (!that.cards[i].getFace().equals(""J"") && !that.cards[i].getFace().equals(""Q"") && !that.cards[i].getFace().equals(""K"") && !that.cards[i].getFace().equals(""A"")) {
                points2[i] = Integer.parseInt(that.cards[i].getFace());
            } else {
                if (that.cards[i].getFace().equals(""J"")) {
                    points2[i] = 11;
                }
                if (that.cards[i].getFace().equals(""Q"")) {
                    points2[i] = 12;
                }
                if (that.cards[i].getFace().equals(""K"")) {
                    points2[i] = 13;
                }
                if (that.cards[i].getFace().equals(""A"")) {
                    points2[i] = 14;
                }
            }

        }
        
        
        
        
        Arrays.sort(points);
        Arrays.sort(points2);
        int priority = 0;
        int priority2 = 0;
        
        
        
        
        int count = 0;
        int pairindex = 0;
        
        
        for (int i = 0; i < 4; i++) {
            if (points[i] == points[i + 1]) {
                count = count + 1;
            }
        }
        if (count == 0) {
            priority = 1;
        }
        count = 0;
        for (int i = 0; i < 4; i++) {
            if (points[i] == points[i + 1]) {
                count = count + 1;
                pairindex = i;
            }
        }
        if (count == 1) {
            priority = 2;
        }
        
        if (points[0] == points[1] && points[2] == points[3] && points[4] != points[0] && points[4] == points[2] || points[1] == points[2] && points[3] == points[4] && points[0] != points[1] || points[0] == points[1] && points[2] == points[3] && points[4] != points[3]) {
            priority = 3;
        }
        if (points[0] + 1 == points[1] && points[1] + 1 == points[2] && points[2] + 1 == points[3] && points[3] + 1 == points[4]) {
            priority = 4;
        }
        if (this.cards[0].getSuit().equals(this.cards[1].getSuit()) && this.cards[1].getSuit().equals(this.cards[2].getSuit()) && this.cards[2].getSuit().equals(this.cards[3].getSuit()) && this.cards[3].getSuit().equals(this.cards[4].getSuit())) {
            priority = 5;
        }
        if (points[0] == points[1] && points[2] == points[3] && points[3] == points[4] || points[0] == points[1] && points[1] == points[2] && points[3] == points[4]) {
            priority = 6;
        }
        

        
        
        
        
        count = 0;
        pairindex = 0;
       
        
        for (int i = 0; i < 4; i++) {
            if (points2[i] == points2[i + 1]) {
                count = count + 1;
            }
        }
        if (count == 0) {
            priority2 = 1;
        }
        count = 0;
         for (int i = 0; i < 4; i++) {
            if (points2[i] == points2[i + 1]) {
                count = count + 1;
                pairindex = i;
            }
        }
         
        if (count == 1) {
            priority2 = 2;
        }
        if (points2[0] == points2[1] && points2[2] == points2[3] && points2[4] != points2[0] && points2[4] == points2[2] || points2[1] == points2[2] && points2[3] == points2[4] && points2[0] != points2[1] || points2[0] == points2[1] && points2[2] == points2[3] && points2[4] != points2[3]) {
            priority2 = 3;
        }
        if (points2[0] + 1 == points2[1] && points2[1] + 1 == points2[2] && points2[2] + 1 == points2[3] && points2[3] + 1 == points2[4]) {
            priority2 = 4;
        }
        if (this.cards[0].getSuit().equals(this.cards[1].getSuit()) && this.cards[1].getSuit().equals(this.cards[2].getSuit()) && this.cards[2].getSuit().equals(this.cards[3].getSuit()) && this.cards[3].getSuit().equals(this.cards[4].getSuit())) {
            priority2 = 5;
        }
        if (points2[0] == points2[1] && points2[2] == points2[3] && points2[3] == points2[4] || points2[0] == points2[1] && points2[1] == points2[2] && points2[3] == points2[4]) {
            priority2 = 6;
        }
.
        Card c = this.cards[0];
        Card d = that.cards[0];
        

        if (priority > priority2) {
            
            return 1;
        }
        if (priority < priority2) {
            return -1;
        }
        if (priority == priority2) {
            if (priority == 1) {
                for (int i = 1; i < 4; i++) {
                    if (c.compareTo(this.cards[i + 1]) == -1) {
                        c = this.cards[i + 1];
                    }
                    if (d.compareTo(that.cards[i + 1]) == -1) {
                        d = that.cards[i + 1];
                    }
                }
                if (c.compareTo(d) == -1) {
                    return -1;
                }
                if (c.compareTo(d) == 1) {
                    return 1;
                }
            }
            /*if (priority == 2){
                
            }*/
            
        }
        return 0;
    }
}

