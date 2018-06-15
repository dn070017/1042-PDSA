
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 
    private String face; 
    private String suit;
    
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){        
        this.cards = cards;
        //this.cards = this.getCards();
//        for(int i = 0 ; i < cards.length ; i++){
//            this.face = cards[i].getFace();
//            this.suit = cards[i].getSuit();
//        }       
        return;
    }

    // TODO
    public int compareTo(Hand that) {
        that.sort();
        this.sort();
        
        int this_handkind = 0;
        int that_handkind = 0;
        
        int[] onepair_thisans = this.onepair();
        int[] onepair_thatans = that.onepair();

        if (this.fullhouse() > 0) {
            this_handkind = 6;
//            System.out.println(""this fullhous"");
        } else if (this.flush() > 0) {
            this_handkind = 5;
//            System.out.println(""this flush"");
        } else if (this.straight() > 0) {
            this_handkind = 4;
//            System.out.println(""this straight"");
        } else if (this.twopairs() > 0) {
            this_handkind = 3;
//            System.out.println(""this 2pairs"");
        } else if (onepair_thisans[0] > 0) {
            this_handkind = 2;
//            System.out.println(""this 1pair"");
        } else {
            this_handkind = 1;
//            System.out.println(""this highcard"");
        }

        if (that.fullhouse() > 0) {
            that_handkind = 6;
//            System.out.println(""that fullhous"");
        } else if (that.flush() > 0) {
            that_handkind = 5;
//            System.out.println(""that flush"");
        } else if (that.straight() > 0) {
            that_handkind = 4;
//            System.out.println(""that straight"");
        } else if (that.twopairs() > 0) {
            that_handkind = 3;
//            System.out.println(""that 2pairs"");
        } else if (onepair_thatans[0] > 0) {
            that_handkind = 2;
//            System.out.println(""that 1pair"");
        } else {
            that_handkind = 1;
//            System.out.println(""that highcard"");
        }

//        System.out.println(that.flush());
//        System.out.println(that.straight());
//        System.out.println(that_handkind);
//        System.out.println(this.flush());
//        System.out.println(this_handkind);
        if (this_handkind > that_handkind) {
            return +1;
        } else if (this_handkind < that_handkind) {
            return -1;
        } else {
            if (this_handkind == 1) {
                return this.cards[4].compareTo(that.cards[4]);
            }
            if (this_handkind == 2) {
                return this.cards[onepair_thatans[1]].compareTo(that.cards[onepair_thatans[1]]);
            }
            if (this_handkind == 3) {
                return this.cards[3].compareTo(that.cards[3]);
            }
            if (this_handkind == 4) {
                if((this.cards[4].getFace()).equals(""A"") && (that.cards[4].getFace()).equals(""A"")){
                    return this.cards[3].compareTo(that.cards[3]);
                }
                else 
                    return this.cards[4].compareTo(that.cards[4]);
            }
            if (this_handkind == 5) {
                return this.cards[4].compareTo(that.cards[4]);
            }
            else{
                return this.cards[2].compareTo(that.cards[2]);
            }
        }
        
        //return 0;
    }

    private void sort() {
        for (int i = 0; i < 5; i++) {
            for (int j = i; j > 0; j--) {
                if (cards[j].compareTo(cards[j - 1]) < 0) {
                    exch(cards, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public int fullhouse() {
        if ((cards[0].getFace()).equals(cards[1].getFace()) && (cards[1].getFace()).equals(cards[2].getFace()) && ((cards[3].getFace()).equals(cards[4].getFace()))) {
            return 1;
        } else if ((cards[2].getFace()).equals(cards[3].getFace()) && (cards[3].getFace()).equals(cards[4].getFace()) && ((cards[0].getFace()).equals(cards[1].getFace()))) {
            return 1;
        } else {
            return 0;
        }
    }

    public int flush() {
        for (int i = 0; i < 4; i++) {
            if (!((cards[i].getSuit()).equals(cards[i + 1].getSuit()))) {
                return 0;
            }
        }
        return 1;
    }

    public int straight() {

        for (int i = 0; i < 4; i++) {
            int facenum1 = 0;
            if (cards[i].getFace().equals(""A"")) {
                facenum1 = 14;
            } else if (cards[i].getFace().equals(""J"")) {
                facenum1 = 11;
            } else if (cards[i].getFace().equals(""Q"")) {
                facenum1 = 12;
            } else if (cards[i].getFace().equals(""K"")) {
                facenum1 = 13;
            } else {
                facenum1 = Integer.parseInt(cards[i].getFace());
            }

            int facenum2 = 0;

            if (cards[i + 1].getFace().equals(""A"")) {
                facenum2 = 14;
            } else if (cards[i + 1].getFace().equals(""J"")) {
                facenum2 = 11;
            } else if (cards[i + 1].getFace().equals(""Q"")) {
                facenum2 = 12;
            } else if (cards[i + 1].getFace().equals(""K"")) {
                facenum2 = 13;
            } else {
                facenum2 = Integer.parseInt(cards[i + 1].getFace());
            }
            if (facenum2 != (facenum1 + 1)) {
                if (facenum1 == 5 && facenum2 == 14) {
//                    System.out.println(""---1"");
                    return 1;

                } else {
//                    System.out.println(""---2"");
                    return 0;
                }
            }
        }
//        System.out.println(""---3"");
        return 1;
    }

    public int twopairs() {
        if ((cards[2].getFace()).equals(cards[3].getFace()) && (cards[0].getFace()).equals(cards[1].getFace())) {
            return 1;
        } else if ((cards[1].getFace()).equals(cards[2].getFace()) && (cards[3].getFace()).equals(cards[4].getFace())) {
            return 1;
        } else if ((cards[0].getFace()).equals(cards[1].getFace()) && (cards[3].getFace()).equals(cards[4].getFace())) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[] onepair() {
        int[] ans = new int[2];
        int sum = 0;
        for (int i = 1; i < 5; i++) {
            if ((cards[i - 1].getFace()).equals(cards[i].getFace())) {
                sum++;
                ans[1] = i;
            }
        }

        if (sum == 1) {
            ans[0] = 1;
        } else {
            ans[0] = 0;
        }
        return ans;
    }

    public int highcard() {

        return 0;
    }
          // Do not modified this function
    public Card[] getCards() { return this.cards; }
//    public static void main(String[] args) throws Exception {
//        Card[] testthis = new Card[5];
//        testthis[0] = new Card(""9"", ""Clubs"");
//        testthis[1] = new Card(""J"", ""Diamonds"");
//        testthis[2] = new Card(""10"", ""Diamonds"");
//        testthis[3] = new Card(""K"", ""Diamonds"");
//        testthis[4] = new Card(""Q"", ""Diamonds"");
//
//        Card[] testthat = new Card[5];
//        testthat[0] = new Card(""10"", ""Hearts"");
//        testthat[1] = new Card(""8"", ""Hearts"");
//        testthat[2] = new Card(""9"", ""Hearts"");
//        testthat[3] = new Card(""J"", ""Hearts"");
//        testthat[4] = new Card(""Q"", ""Hearts"");
//
//        Hand[] testp = new Hand[2];
//        testp[0] = new Hand(testthat);
//        testp[1] = new Hand(testthis);
//        
//        
//        for(int i = 0 ; i < testthat.length ; i++){
//            System.out.println(testp[0].cards[i].getFace());
//            System.out.println(testp[0].cards[i].getSuit());
//        } 
//        for(int i = 0 ; i < testthat.length ; i++){
//            System.out.println(testp[1].cards[i].getFace());
//            System.out.println(testp[1].cards[i].getSuit());
//        }
////        testp[0].setCards(testthis);
////        testp[1].setCards(testthat);
//        System.out.println(testp[0].compareTo(testp[1]));
//        
//    }
}

