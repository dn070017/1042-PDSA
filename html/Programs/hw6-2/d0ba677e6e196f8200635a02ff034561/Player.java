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
        int player1 = this.cardType(this.cards);
        int player2 = that.cardType(that.cards);

        if (player1 > player2) return 1;
        else if (player1 < player2) return -1;
        else if (player1 == player2) {

            int SPlayer1 = this.secondRound(player1, this.cards);
            int SPlayer2 = that.secondRound(player2, that.cards);

            if (SPlayer1 > SPlayer2) return 1;
            else if (SPlayer1 < SPlayer2) return -1;
            else if (SPlayer1 == SPlayer2) {
//
//                int TPlayer1 = this.thirdRound(player1, this.cards);
//                int TPlayer2 = that.thirdRound(player2, that.cards);
//
//                if (TPlayer1 > TPlayer2) return 1;
//                else if (TPlayer1 < TPlayer2) return -1;
//                else if(TPlayer1 == TPlayer2)  return 0;
            }
        }
     return 0;
    }


    public int secondRound(int s, Card[] c) {
        int[] allFace = getAllFace(c);
        int secondValue = -1;
        if (s == 5) {
            for (int i = 0; i < 13; i++) {
                if (allFace[i] == 3) {
                    return i;
                }
            }
        } else if (s == 4) {
            String temp = c[0].getSuit();
            secondValue = suitIndex(temp);
            return secondValue;
        } else if (s == 3 || s == 0) {
            for (int i = 0; i < 13; i++) {
                if (allFace[i] == 2) {
                    secondValue = i;
                }
            }
            return secondValue;
        } else {
            for (int i = 0; i < 13; i++) {
                if (allFace[i] == 2) {
                    secondValue = i;
                }
            }
            return secondValue;
        }
        return secondValue;
    }


//    public int thirdRound(int t, Card[] c) {
//
//
//
//
//    }

    public int[] getAllFace(Card[] c) {
        int[] allFace = new int[13];

        for (int i = 0; i < allFace.length; i++) {
            allFace[i] = 0;
        }

        for (int i = 0; i < c.length; i++) {
            String temp = c[i].getFace();
            allFace[faceIndex(temp)]++;
        }

        return allFace;
    }


    public int cardType(Card[] c) {

//        int[] allFace = new int[13];
//
//        for (int i = 0; i < allFace.length; i++) {
//            allFace[i] = 0;
//        }
//
//        for (int i = 0; i < c.length; i++) {
//            String temp = c[i].getFace();
//            allFace[faceIndex(temp)]++;
//        }


        int[] allFace = getAllFace(c);

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
        } else if (allType[1] == 5) {
            if (isFlush(cards)) {
                type = 4;
            } else if (isStraight(allFace)) {
                type = 3;
            } else {
                type = 0;
            }
        }

//
//        for (int i = 0; i < allFace.length; i++) {
//            System.out.print(allFace[i] + "" "");
//        }
//
//        System.out.print(""["");
//        String sp = """";
//        for (int i : allType) {
//            System.out.print(sp + i);
//            sp = "","";
//        }
//        System.out.println(""]"");

        return type;
    }

    private boolean isStraight(int[] allFace) {

        int counter = 0;
        for (int i = 0; i < allFace.length; i++) {
            if (allFace[i] == 1) {


//                if (i == 0 && allFace[12] == 1) {
//                    counter++;
//                    if (allFace[1] == 1) {
//                        break;
//                    } else if (allFace[])
//                }
                for (int j = i + 1; j < i + 5; j++) {
                    if (allFace[j] - allFace[j - 1] == 0) {
                        counter++;
                    }
                    if (counter == 4) {
                        return true;
                    }
                }
                return false;
            }
        }


        if (counter == 4) return true;

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
        String[] faces = {""A"", ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K""};
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
