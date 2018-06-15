
import java.util.Arrays;

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
        int typethis = this.typesofhand(this.cards);
        int typethat = this.typesofhand(that.cards);

        if (typethis > typethat) {
            return 1;
        } else if (typethis < typethat) {
            return -1;
        } else {
            if (typethis == 5) {
                return this.compareFull(that.cards);
            } else if (typethis == 4) {
                return this.compareFlush(that.cards);
            } else if (typethis == 3) {
                return this.compareStraight(that.cards);
            } else if (typethis == 2) {
                return this.compareTwopair(that.cards);
            } else if (typethis == 1) {
                return this.comparePair(that.cards);
            } else {
                return this.compareHigh(that.cards);
            }

        }
    }

    public static int toface(Card A) {
        if (""A"".equals(A.getFace())) {
            return 14;
        } else if (""J"".equals(A.getFace())) {
            return 11;
        } else if (""Q"".equals(A.getFace())) {
            return 12;
        } else if (""K"".equals(A.getFace())) {
            return 13;
        } else {
            return Integer.parseInt(A.getFace());
        }
    }

//    4 3 2 1 黑陶、紅心、方塊、梅花
    public static int tosuit(Card A) {
        if (""Spades"".equals(A.getSuit())) {
            return 4;
        } else if (""Hearts"".equals(A.getSuit())) {
            return 3;
        } else if (""Diamonds"".equals(A.getSuit())) {
            return 2;
        } else {
            return 1;
        }
    }
//    5 Full、4 Flush、3 Straight、2 two pair、1 one pair、0 High

    public int typesofhand(Card[] cards) {
        Arrays.sort(cards);

        int[] face = new int[5];
        int[] suit = new int[5];
        int[] numberofface = new int[15];  // 從2到14(A)
        int[] numberofsuit = new int[5];    // 從1(clubs)到4(spades)

        int fullhouse = 0;
        int pair = 0;
        int straight = 0;
        int flush = 0;

        for (int i = 0; i < 5; i++) {
            face[i] = Player.toface(cards[i]);
            suit[i] = Player.tosuit(cards[i]);
            numberofface[face[i]]++;
            numberofsuit[suit[i]]++;
        }

//        測重複 full、pair
//        Full house 要符合 fullhouse == 1、pair ==2
//        Pair 即 pair個數
//        Straight 符合 straight == 4
        for (int i = 2; i < 15; i++) {
            if (numberofface[i] == 4) {
                pair += 2;
            }
            if (numberofface[i] == 3) {
                fullhouse += 1;
                pair += 1;
            }
            if (numberofface[i] == 2) {
                pair += 1;
            }
            if (numberofface[i] == 1 && numberofface[i - 1] == 1) {
                straight += 1;
            }
            if (i == 5 && straight == 3) {
                if (numberofface[14] == 1) {
                    straight += 1;
                    break;
                }
            }
        }

//        flush == 1則是 Flush
        for (int i = 0; i < 4; i++) {
            if (numberofsuit[i] == 5) {
                flush += 1;
            }
        }

        if (fullhouse == 1 && pair == 2) {
            return 5;
        } else if (flush == 1) {
            return 4;
        } else if (straight == 4) {
            return 3;
        } else if (pair == 2) {
            return 2;
        } else if (pair == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    public int compareFull(Card[] that) {
        int thisthree = Player.toface(this.cards[2]);
        int thistwo;
        int thatthree = Player.toface(that[2]);
        int thattwo;

        if (thisthree == Player.toface(this.cards[0])) {
            thistwo = Player.toface(this.cards[4]);
        } else {
            thistwo = Player.toface(this.cards[2]);
        }

        if (thatthree == Player.toface(that[0])) {
            thattwo = Player.toface(that[4]);
        } else {
            thattwo = Player.toface(that[2]);
        }

        if (thisthree > thatthree) {
            return 1;
        } else if (thisthree < thatthree) {
            return -1;
        } else {
            if (thistwo > thattwo) {
                return 1;
            } else {
                return -1;
            }
        }

    }

    public int compareFlush(Card[] that) {
        if (Player.toface(this.cards[4]) > Player.toface(that[4])) {
            return 1;
        } else if (Player.toface(this.cards[4]) < Player.toface(that[4])) {
            return -1;
        } else {
            if (Player.toface(this.cards[3]) > Player.toface(that[3])) {
                return 1;
            } else if (Player.toface(this.cards[3]) < Player.toface(that[3])) {
                return -1;
            } else {
                if (Player.toface(this.cards[2]) > Player.toface(that[2])) {
                    return 1;
                } else if (Player.toface(this.cards[2]) < Player.toface(that[2])) {
                    return -1;
                } else {
                    if (Player.toface(this.cards[1]) > Player.toface(that[1])) {
                        return 1;
                    } else if (Player.toface(this.cards[1]) < Player.toface(that[1])) {
                        return -1;
                    } else {
                        if (Player.toface(this.cards[0]) > Player.toface(that[0])) {
                            return 1;
                        } else if (Player.toface(this.cards[0]) < Player.toface(that[0])) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    public int compareStraight(Card[] that) {
        if (Player.toface(this.cards[4]) > Player.toface(that[4])) {
            return 1;
        } else if (Player.toface(this.cards[4]) < Player.toface(that[4])) {
            return -1;
        } else {
            return 0;
        }
    }

    public int compareTwopair(Card[] that) {
        int thispair1 = Player.toface(this.cards[3]);
        int thatpair1 = Player.toface(that[3]);
        int thispair2 = Player.toface(this.cards[1]);
        int thatpair2 = Player.toface(that[1]);
        int thiskicker1;
        int thatkicker1;

        if (thispair1 == Player.toface(this.cards[4])) {
            if (thispair2 == Player.toface(this.cards[2])) {
                thiskicker1 = Player.toface(this.cards[0]);
            } else {
                thiskicker1 = Player.toface(this.cards[2]);
            }
        } else {
            thiskicker1 = Player.toface(this.cards[4]);
        }

        if (thatpair1 == Player.toface(that[4])) {
            if (thatpair2 == Player.toface(that[2])) {
                thatkicker1 = Player.toface(that[0]);
            } else {
                thatkicker1 = Player.toface(that[2]);
            }
        } else {
            thatkicker1 = Player.toface(that[4]);
        }

        if (thispair1 > thatpair1) {
            return 1;
        } else if (thispair1 < thatpair1) {
            return -1;
        } else {
            if (thispair2 > thatpair2) {
                return 1;
            } else if (thispair2 < thatpair2) {
                return -1;
            } else {
                if (thiskicker1 > thatkicker1) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

    }

    public int comparePair(Card[] that) {
        int thispair;
        int thatpair;
        int pair1rank = 0;
        int pair2rank = 0;

        for (int i = 1; i < 5; i++) {
            if (Player.toface(this.cards[i]) == Player.toface(this.cards[i - 1])) {
                thispair = Player.toface(this.cards[i]);
                pair1rank = i;
            }
            if (Player.toface(that[i]) == Player.toface(that[i - 1])) {
                thatpair = Player.toface(that[i]);
                pair2rank = i;
            }
        }

        int[] thiskicker = new int[3];
        int[] thatkicker = new int[3];

        if (Player.toface(this.cards[pair1rank]) > Player.toface(that[pair2rank])) {
            return 1;
        } else if (Player.toface(this.cards[pair1rank]) < Player.toface(that[pair2rank])) {
            return -1;
        } else {
            if (pair1rank == 4) {
                thiskicker[0] = 0;
                thiskicker[1] = 1;
                thiskicker[2] = 2;
            } else if (pair1rank == 3) {
                thiskicker[0] = 0;
                thiskicker[1] = 1;
                thiskicker[2] = 4;
            } else if (pair1rank == 2) {
                thiskicker[0] = 0;
                thiskicker[1] = 3;
                thiskicker[2] = 4;
            } else if (pair1rank == 1) {
                thiskicker[0] = 2;
                thiskicker[1] = 3;
                thiskicker[2] = 4;
            }
            if (pair2rank == 4) {
                thatkicker[0] = 0;
                thatkicker[1] = 1;
                thatkicker[2] = 2;
            } else if (pair2rank == 3) {
                thatkicker[0] = 0;
                thatkicker[1] = 1;
                thatkicker[2] = 4;
            } else if (pair2rank == 2) {
                thatkicker[0] = 0;
                thatkicker[1] = 3;
                thatkicker[2] = 4;
            } else if (pair1rank == 1) {
                thatkicker[0] = 2;
                thatkicker[1] = 3;
                thatkicker[2] = 4;
            }

            if (Player.toface(this.cards[thiskicker[2]]) > Player.toface(that[thatkicker[2]])) {
                return 1;
            } else if (Player.toface(this.cards[thiskicker[2]]) < Player.toface(that[thatkicker[2]])) {
                return -1;
            } else {
                if (Player.toface(this.cards[thiskicker[1]]) > Player.toface(that[thatkicker[1]])) {
                    return 1;
                } else if (Player.toface(this.cards[thiskicker[1]]) < Player.toface(that[thatkicker[1]])) {
                    return -1;
                } else {
                    if (Player.toface(this.cards[thiskicker[0]]) > Player.toface(that[thatkicker[0]])) {
                        return 1;
                    } else if (Player.toface(this.cards[thiskicker[0]]) < Player.toface(that[thatkicker[0]])) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }

        }
    }

    public int compareHigh(Card[] that) {
        if (Player.toface(this.cards[4]) > Player.toface(that[4])) {
            return 1;
        } else if (Player.toface(this.cards[4]) < Player.toface(that[4])) {
            return -1;
        } else {
            if (Player.toface(this.cards[3]) > Player.toface(that[3])) {
                return 1;
            } else if (Player.toface(this.cards[3]) < Player.toface(that[3])) {
                return -1;
            } else {
                if (Player.toface(this.cards[2]) > Player.toface(that[2])) {
                    return 1;
                } else if (Player.toface(this.cards[2]) < Player.toface(that[2])) {
                    return -1;
                } else {
                    if (Player.toface(this.cards[1]) > Player.toface(that[1])) {
                        return 1;
                    } else if (Player.toface(this.cards[1]) < Player.toface(that[1])) {
                        return -1;
                    } else {
                        if (Player.toface(this.cards[0]) > Player.toface(that[0])) {
                            return 1;
                        } else if (Player.toface(this.cards[0]) < Player.toface(that[0])) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }

    }
}

