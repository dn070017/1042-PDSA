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
        int playerOne = this.cardType(this.cards);
        int playerTwo = that.cardType(that.cards);

        if (playerOne > playerTwo) return 1;
        else if (playerOne < playerTwo) return -1;
        else if (playerOne == playerTwo) {
        }
        return 0;
    }


    public int cardType(Card[] c) {

        int[] allFace = new int[13];

        for (int i = 0; i < allFace.length; i++) {
            allFace[i] = 0;
        }

        for (int i = 0; i < c.length; i++) {
            String temp = c[i].getFace();
            allFace[faceIndex(temp)]++;
        }

        int[] allType = new int[4];
        for (int i = 0; i < allFace.length; i++) {
            if (allFace[i] == 3) allType[3]++;
            else if (allFace[i] == 2) allType[2]++;
            else if (allFace[i] == 1) allType[1]++;
        }

        int type = -1;
        if (allType[3] == 1) {
            if (allType[2] == 1) {
                type = 5;
            } else if (allType[1] == 2) {
                type = 1;
            }
        } else if (allType[2] == 2) {
            type = 2;
        } else if (allType[2] == 1) {
            type = 1;
        } else if (allType[1] != 0) {
            if (isFlush(cards)) {
                type = 4;
            } else if (isStraight(allFace)) {
                type = 0;
            }
        }
        

//        System.out.print(""["");
//        String sp = """";
//        for (int i : allFace) {
//            System.out.print(sp + i);
//            sp = "","";
//        }
//        System.out.println(""]"");

//
//        int type = -1;
//        for (int i = 0; i < allFace.length; i++) {
//            if (allFace[i] == 3) {
//                for (int j = i + 1; j < allFace.length; j++) {
//                    if (allFace[j] == 2) {
//                        type = 5;
//                        break;
//                    }
//                }
//
//                if (type != 5) {
//                    type = 1;
//                }
//                break;
//
//            } else if (allFace[i] == 2) {
//                for (int j = i + 1; j < allFace.length; j++) {
//                    if (allFace[j] == 2) {
//                        type = 2;
//                        break;
//                    }
//                }
//
//                if (type != 2) {
//                    type = 1;
//                }
//                break;
//
//            } else if (allFace[i] != 0) {
//                if (isFlush(cards)) {
//                    type = 4;
//                    break;
//                } else if (isStraight(allFace, i)) {
//                    type = 3;
//                    break;
//                } else {
//                    type = 0;
//                    break;
//                }
//            }
//        }
        return type;
    }

    private boolean isStraight(int[] allFace) {

        int i = 0;
        while (allFace[i] == 0) {
            i++;
        }
        int start = i;
        while (i < 13 && allFace[i + 1] - allFace[i] == 0) i++;
        if (i - start == 4) return true;
        else return false;
    }

    public boolean isFlush(Card[] c) {
        int[] allSuit = new int[4];
        for (int i = 0; i < c.length; i++) {
            String temp = c[i].getSuit();
            allSuit[suitIndex(temp)]++;
        }
        for (int j = 0; j < 4; j++) {
            if (allSuit[j] == 5) return true;
        }
        return false;
    }

    public int faceIndex(String f) {
        String[] faces = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        int face_index = 0;
        while (!f.equals(faces[face_index])) face_index++;
        return face_index;
    }

    public int suitIndex(String s) {
        String[] suits = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
        int suit_index = 0;
        while (!s.equals(suits[suit_index])) suit_index++;
        return suit_index;
    }

}
