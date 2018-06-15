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
        int orderPlayer1 = 0;
        int orderPlayer2 = 0;
        if (isFullHouse()) orderPlayer1 = 1;
        if (isFlush()) orderPlayer1 = 2;
        if (isStraight()) orderPlayer1 = 3;
        if (isTwoPair()) orderPlayer1 = 4;
        if (isOnePair()) orderPlayer1 = 5;
        if (that.isFullHouse()) orderPlayer2 = 1;
        if (that.isFlush()) orderPlayer2 = 2;
        if (that.isStraight()) orderPlayer2 = 3;
        if (that.isTwoPair()) orderPlayer2 = 4;
        if (that.isOnePair()) orderPlayer2 = 5;
        
        if (orderPlayer1 < orderPlayer2) return 1;
        else if (orderPlayer1 > orderPlayer2) return -1;
        else return 0;
    }

    public boolean isFullHouse() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        }
        return count == 3;
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
}


