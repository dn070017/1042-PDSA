
import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    // DO NOT MODIFY THIS   
    public String getFace() {
        return this.face;
    }

    // DO NOT MODIFY THIS    
    public String getSuit() {
        return this.suit;
    }

    // TODO
    public int compareTo(Card that) {
        int that_facenum = 0;
        int this_facenum = 0;
        
        if (that.face == ""A"") {
            that_facenum = 14;
        }
        else if (that.face == ""J"") {
            that_facenum = 11;
        }
        else if (that.face == ""Q"") {
            that_facenum = 12;
        }
        else if (that.face == ""K"") {
            that_facenum = 13;
        }
        else 
            that_facenum = Integer.parseInt(that.face);
        
        if (this.face == ""A"") {
            this_facenum = 14;
        }
        else if (this.face == ""J"") {
            this_facenum = 11;
        }
        else if (this.face == ""Q"") {
            this_facenum = 12;
        }
        else if (this.face == ""K"") {
            this_facenum = 13;
        }
        else 
            this_facenum = Integer.parseInt(this.face);
        
        
//        System.out.println(""that.face : "" + that_facenum);
//        System.out.println(""this.face : "" + this_facenum);
        
        if (this_facenum < that_facenum) {
//            System.out.println("" < : "" + ""-1"");
            return -1;
        } else if (this_facenum > that_facenum) {
//            System.out.println("" > : "" + ""+1"");
            return +1;
        } else {
            if (SUIT_ORDER.compare(this, that) == 1) {
                return +1;
            } else if (SUIT_ORDER.compare(this, that) == -1) {
                return -1;
            } else {
                return 0;
            }
        }
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            String[] suitsort = new String[4];
            suitsort[3] = ""Spades"";
            suitsort[2] = ""Hearts"";
            suitsort[1] = ""Diamonds"";
            suitsort[0] = ""Clubs"";

            int c1_tag = 0;
            int c2_tag = 0;
            
//            System.out.println(""c1.suit："" + c1.suit);
            
            for (int i = 0; i < 4; i++) {
                if (c1.suit.equals(suitsort[i])) {
//                    System.out.println(""suitsort："" + suitsort[i]);
                    c1_tag = i;
                    break;
                }
            }
//            System.out.println(""c1_tag : "" + c1_tag);
            
//            System.out.println(""c2.suit："" + c2.suit);
            for (int i = 0; i < 4; i++) {
                if (c2.suit.equals(suitsort[i])) {
//                    System.out.println(""suitsort："" + suitsort[i]);
                    c2_tag = i;
                    break;
                }
            }
//            System.out.println(""c2_tag : ""  + c2_tag);
            
            if (c1_tag > c2_tag) {
                return +1;
            } else if (c1_tag < c2_tag) {
                return -1;
            } else {
                return 0;
            }

        }
    }
//    public static void main(String[] args) throws Exception{
//        Card[] test = new Card[5];
//        test[0] = new Card(""A"", ""Hearts"");
//        test[1] = new Card(""A"", ""Clubs"");
//        System.out.println(test[0].compareTo(test[1]));
//        System.out.println(Card.SUIT_ORDER.compare(test[0], test[1]));
//        test[2] = new Card(""7"", ""Spades"");
//        test[3] = new Card(""A"", ""Diamonds"");
//        test[4] = new Card(""J"", ""Diamonds"");
//        System.out.println(test[2].compareTo(test[3]));
//        System.out.println(Card.SUIT_ORDER.compare(test[2], test[3]));
//        System.out.println(test[0].compareTo(test[1]));
//        
//    }
}

