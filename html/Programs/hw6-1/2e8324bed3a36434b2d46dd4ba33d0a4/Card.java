


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
        
        if (that.face == ""A"") {
            that.face = String.valueOf(14);
            //that_facenum = Integer.parseInt(that.face);
        }
        if (that.face == ""J"") {
            that.face = String.valueOf(11);
            //that_facenum = Integer.parseInt(that.face);
        }
        if (that.face == ""Q"") {
            that.face = String.valueOf(12);
            //that_facenum = Integer.parseInt(that.face);
        }
        if (that.face == ""K"") {
            that.face = String.valueOf(13);
            //that_facenum = Integer.parseInt(that.face);
        }
        if (this.face == ""A"") {
            this.face = String.valueOf(14);
            //this_facenum = Integer.parseInt(that.face);
        }
        if (this.face == ""J"") {
            this.face = String.valueOf(11);
            //this_facenum = Integer.parseInt(that.face);
        }
        if (this.face == ""Q"") {
            this.face = String.valueOf(12);
            //this_facenum = Integer.parseInt(that.face);
        }
        if (this.face == ""K"") {
            this.face = String.valueOf(13);
            //this_facenum = Integer.parseInt(that.face);
        }
        int that_facenum = Integer.parseInt(that.face);
        int this_facenum = Integer.parseInt(this.face);
        
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

            // complete this function so the Card can be sorted according to the suit
        }
    }
//    public static void main(String[] args) throws Exception{
//        Card[] test = new Card[2];
//        test[0] = new Card(""A"", ""Clubs"");
//        test[1] = new Card(""2"", ""Hearts"");
////        System.out.println(test[1].compareTo(test[0]));
////        System.out.println(Card.SUIT_ORDER.compare(test[1], test[0]));
//    }
}

