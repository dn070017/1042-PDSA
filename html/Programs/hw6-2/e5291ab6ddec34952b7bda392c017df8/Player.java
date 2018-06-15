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
        String[] faceArray = {""2"", ""A"", ""K"", ""Q"", ""J"", ""10"", ""9"", ""8"", ""7"", ""6"", ""5"", ""4"", ""3""};
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
        // Player1/Player2
        if (isOnePair()) orderPlayer1 = 5; if (that.isOnePair()) orderPlayer2 = 5;
        if (isTwoPair()) orderPlayer1 = 4; if (that.isTwoPair()) orderPlayer2 = 4;
        if (isStraight()) orderPlayer1 = 3; if (that.isStraight()) orderPlayer2 = 3;
        if (isFlush()) orderPlayer1 = 2; if (that.isFlush()) orderPlayer2 = 2;
        if (isFullHouse()) orderPlayer1 = 1; if (that.isFullHouse()) orderPlayer2 = 1;

        //
        if (orderPlayer1 < orderPlayer2) return 1;
        else if (orderPlayer1 > orderPlayer2) return -1;
        else {
            String[] Player1 = this.getHighCard().split(""/"");
            String[] Player2 = that.getHighCard().split(""/"");
            if (Integer.parseInt(Player1[0]) < Integer.parseInt(Player2[0])) return 1;
            else if (Integer.parseInt(Player1[0]) > Integer.parseInt(Player2[0])) return -1;
            else {
                if (Integer.parseInt(Player1[1]) < Integer.parseInt(Player2[1])) return 1;
                else if (Integer.parseInt(Player1[1]) > Integer.parseInt(Player2[1])) return -1;
                else return 0;
            }
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


//    private int getFullHouseFace() {
//        int[] a = this.faceArrayOrder();
//        int card1 = a[0];
//        int card2 = 0;
//        int count = 0;
//        for (int i = 1; i < 5; i++) {
//            if (a[i] == card1) count++;
//            else card2 = a[i];
//        }
//        if (count != 2) return card2;
//        else return card1;
//    }
//
//    public int getOnePair() {
//        int[] a = this.faceArray();
//        int face = 0;
//        for (int i = 0; i < 5; i++) {
//            for (int j = i + 1; j < 5; j++)
//                if (a[i] == a[j]) face = a[j];
//        }
//        return face;
//    }
}


