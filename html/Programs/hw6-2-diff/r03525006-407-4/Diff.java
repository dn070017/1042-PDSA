import java.util.Arrays;

public class Player implements Comparable<Player>{

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

    public int[] faceArray() {
        String[] faceArray = {""A"", ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (cards[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        }
        return ans;
    }

    public int[] faceArrayOrder() {
        String[] faceArray = {""A"", ""K"", ""Q"", ""J"", ""10"", ""9"", ""8"", ""7"", ""6"", ""5"", ""4"", ""3"", ""2""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (cards[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        }
        return ans;
    }

    public int[] suitArray() {
        String[] suitArray = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
        int[] ans = new int[5];
        int suit = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                if (cards[i].getSuit().equals(suitArray[j])) suit = j+1;
            ans[i] = suit;
        }
        return ans;
    }

    // TODO
    @Override
    public int compareTo(Player that) {
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
            if (orderPlayer1 == 1) {
                Card Player1 = this.getFullHouse();
                Card Player2 = that.getFullHouse();
                if (Player1.compareTo(Player2) == 1) return 1;
                else return -1;

            } else if (orderPlayer1 == 2) {
                Player Player1 = this;
                Player Player2 = that;
                if (Player1.cards[0].getSuit().equals(Player2.cards[0].getSuit())) {
                    Card card1 = Player1.getFlush();
                    Card card2 = Player2.getFlush();
                    if (card1.compareTo(card2) == 1) return 1;
                    else return -1;
                } else if (Player1.cards[0].compareTo(Player2.cards[0]) == 1) {
                    return 1;
                } else return -1;


            } else if (orderPlayer1 == 3) {
                Card Player1 = this.getStraight();
                Card Player2 = that.getStraight();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;

            } else if (orderPlayer1 == 4) {
                Card Player1 = this.getTwoPair();
                Card Player2 = that.getTwoPair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else return -1;

            } else if (orderPlayer1 == 5) {
                Card Player1 = this.getOnePair();
                Card Player2 = that.getOnePair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else return -1;

            } else if (orderPlayer1 == 6) {
                String[] Player1 = this.getHighCard().split(""/"");
                String[] Player2 = that.getHighCard().split(""/"");
                if (Integer.parseInt(Player1[0]) < Integer.parseInt(Player2[0])) return 1;
                else if (Integer.parseInt(Player1[0]) > Integer.parseInt(Player2[0])) return -1;
                else {
                    if (Integer.parseInt(Player1[1]) < Integer.parseInt(Player2[1])) return 1;
                    else if (Integer.parseInt(Player1[1]) > Integer.parseInt(Player2[1])) return -1;
                    else return 0;
                }
            } else return 0;
        }
    }

    public boolean isFullHouse() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        }
        return count == 4;
    }

    public boolean isFlush() {
        int[] a = this.suitArray();
        if ((a[0]==a[1]) & (a[1]==a[2]) & (a[2]==a[3]) & (a[3]==a[4]))
            return true;
        else
            return false;
    }

    public boolean isStraight() {
        int[] a = this.faceArray();
        int count = 0;
        int faceMin = a[0];
        int faceSum = a[0];
        for (int i = 1; i < 5; i++) {
            faceSum = faceSum + a[i];
            if (a[i] < faceMin) {
                faceMin = a[i];
            }
        }
        if (faceMin == 1) {
            if (faceSum < 16) {
                for (int i = 0; i < 5; i++) {
                    for (int j = faceMin; j < faceMin + 5; j++) {
                        if (a[i] == j) count++;
                    }
                }
                return count == 5;
            } else {
                for (int i = 0; i < 5; i++) {
                    for (int j = 10; j < 14; j++) {
                        if (a[i] == j) count++;
                    }
                }
                return count == 4;
            }
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = faceMin; j < faceMin + 5; j++) {
                    if (a[i] == j) count++;
                }
            }
            return count == 5;
        }
    }

    public boolean isTwoPair() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        }
        return count == 2;
    }

    public boolean isOnePair() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        }
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

    private String getHighCard() {
        int[] a = this.faceArrayOrder();
        int[] b = this.suitArray();
        int faceMax = a[0];
        int maxFaceIndex = 0;
        for (int i = 1; i < 5; i++) {
            if (a[i] < faceMax) {
                faceMax = a[i];
                maxFaceIndex = i;
            }
        }
        return faceMax + ""/"" + b[maxFaceIndex];
    }
}

