import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards = new Card[5];

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        for (int i = 0; i < 5; i++)
            this.cards[i] = cards[i];
    }

    public int[] faceArray(Card[] card) {
        String[] faceArray = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (card[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        } return ans;
    }

    // TODO
    public int compareTo(Hand that) {
.

        int orderPlayer1 = 6;
        int orderPlayer2 = 6;
        // one pair
        if (this.isOnePair()) orderPlayer1 = 5;
        if (that.isOnePair()) orderPlayer2 = 5;
        // two pair
        if (this.isTwoPair()) orderPlayer1 = 4;
        if (that.isTwoPair()) orderPlayer2 = 4;
        // straight
        if (this.isStraight()) orderPlayer1 = 3;
        if (that.isStraight()) orderPlayer2 = 3;
        // flush
        if (this.isFlush()) orderPlayer1 = 2;
        if (that.isFlush()) orderPlayer2 = 2;
        // full house
        if (this.isFullHouse()) orderPlayer1 = 1;
        if (that.isFullHouse()) orderPlayer2 = 1;


        // compare
        if (orderPlayer1 < orderPlayer2) return 1;
        else if (orderPlayer1 > orderPlayer2) return -1;
        else {
            // full house
            if (orderPlayer1 == 1) {
                Card Player1 = this.getFullHouse();
                Card Player2 = that.getFullHouse();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // flush
            else if (orderPlayer1 == 2) {
                Card Player1 = this.getFlush();
                Card Player2 = that.getFlush();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // straight
            else if (orderPlayer1 == 3) {
                Card Player1 = this.getStraight();
                Card Player2 = that.getStraight();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // two pair
            else if (orderPlayer1 == 4) {
                Card Player1 = this.getTwoPair();
                Card Player2 = that.getTwoPair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // one pair
            else if (orderPlayer1 == 5) {
                Card Player1 = this.getOnePair();
                Card Player2 = that.getOnePair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // high card
            else if (orderPlayer1 == 6) {
                Card Player1 = this.getHighCard();
                Card Player2 = that.getHighCard();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;

            } else return 0;
        }
    }

    public boolean isFullHouse() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 4;
    }

    public boolean isFlush() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getSuit().equals(cards[j].getSuit())) count++;
        return count == 10;
    }

    public boolean isStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int[] faceNum = this.faceArray(cardCopy);
        int count = 0;
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) {
            for (int i = 1; i < 4; i++)
                if (faceNum[i] == i+1) count++;
            return count == 3;
        } else {
            for (int i = 0; i < 4; i++)
                if (faceNum[i+1] - faceNum[i] == 1) count++;
            return count == 4;
        }
    }

    public boolean isTwoPair() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 2;
    }

    public boolean isOnePair() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 1;
    }

    private Card getFullHouse() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int face1Count = 0;
        String face1 = cardCopy[4].getFace();
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[2];
        else return cardCopy[4];
    }

    private Card getFlush() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }

    private Card getStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) return cardCopy[3];
        else return cardCopy[0];
    }

    private Card getTwoPair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        String face1 = cardCopy[4].getFace();
        int face1Count = 0;
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[4];
        else return cardCopy[3];
    }

    private Card getOnePair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        int pairIndex1 = 0;
        int pairIndex2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (cardCopy[i].getFace().equals(cardCopy[j].getFace())) {
                    pairIndex1 = i;
                    pairIndex2 = j;
                    break;
                }
            }
        }
        if (cardCopy[pairIndex1].compareTo(cardCopy[pairIndex2]) == 1) return cardCopy[pairIndex1];
        else return cardCopy[pairIndex2];
    }

    private Card getHighCard() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

