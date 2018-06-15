import java.util.Arrays;

/**
 *
 * @author Po-Lin
 */
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards;

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards) {
        this.cards=cards;
    }

    // TODO
    public int compareTo(Hand that) {
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        //檢查full house
        if (this.cards[0].getFace().equals(this.cards[1].getFace()) && this.cards[0].getFace().equals(this.cards[2].getFace())) { //若this是前三張相同
            if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[0].getFace().equals(that.cards[2].getFace())) { //判斷that是否也有full house
                if (this.cards[0].compareTo(that.cards[0]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[1].getFace().equals(that.cards[3].getFace())) {
                if (this.cards[0].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[2].getFace().equals(that.cards[3].getFace()) && that.cards[2].getFace().equals(that.cards[4].getFace())) {
                if (this.cards[0].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else {  //表示that不是full house
                return 1;
            }
        } else if (this.cards[1].getFace().equals(this.cards[2].getFace()) && this.cards[1].getFace().equals(this.cards[3].getFace())) { //若this是中間三張相同
            if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[0].getFace().equals(that.cards[2].getFace())) { //判斷that是否也有full house
                if (this.cards[1].compareTo(that.cards[0]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[1].getFace().equals(that.cards[3].getFace())) {
                if (this.cards[1].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[2].getFace().equals(that.cards[3].getFace()) && that.cards[2].getFace().equals(that.cards[4].getFace())) {
                if (this.cards[1].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else {  //表示that不是full house
                return 1;
            }
        } else if (this.cards[2].getFace().equals(this.cards[3].getFace()) && this.cards[2].getFace().equals(this.cards[4].getFace())) { //若this是後三張相同
            if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[0].getFace().equals(that.cards[2].getFace())) { //判斷that是否也有full house
                if (this.cards[2].compareTo(that.cards[0]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[1].getFace().equals(that.cards[3].getFace())) {
                if (this.cards[2].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[2].getFace().equals(that.cards[3].getFace()) && that.cards[2].getFace().equals(that.cards[4].getFace())) {
                if (this.cards[2].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else {  //表示that不是full house
                return 1;
            }
        }

        //檢查Flush
        int flush_check = 0;
        for (int i = 1; i < 5; i++) {
            if (Card.SUIT_ORDER.compare(this.cards[0], this.cards[i]) != 0) {
                flush_check = 1;
                break;
            }
        }
        if (flush_check == 0) {
            for (int i = 1; i < 5; i++) {  //this是flush時判斷that是不是flush
                if (Card.SUIT_ORDER.compare(that.cards[0], that.cards[i]) != 0) {
                    flush_check = 1;
                    break;
                }
            }
            if (flush_check == 1) { //that不是flush
                return 1;
            } else {
                if (Card.SUIT_ORDER.compare(this.cards[0], that.cards[0]) == 1) {
                    return 1;
                } else if (Card.SUIT_ORDER.compare(this.cards[0], that.cards[0]) == -1) {
                    return -1;
                } else //同花色時,比最後一張
                {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                }
            }
        } else {  //this不是flush時判斷that是不是flush
            flush_check = 0;
            for (int i = 1; i < 5; i++) {
                if (Card.SUIT_ORDER.compare(that.cards[0], that.cards[i]) != 0) {
                    flush_check = 1;
                    break;
                }
            }
            if (flush_check == 0) {
                return -1;
            }
        }

        //檢查Straight
        int this_straightcheck = 0; //this_straightcheck=1 時表示不是Straight
        int first = 0;
        int last = 0;
        Card[] this_maxcard = new Card[1];
        for (int i = 0; i < 5; i++) { //this是否是Straight
            switch (this.cards[i].getFace()) {
                case ""A"":
                    last = 13;
                    break;
                case ""2"":
                    last = 1;
                    break;
                case ""3"":
                    last = 2;
                    break;
                case ""4"":
                    last = 3;
                    break;
                case ""5"":
                    last = 4;
                    break;
                case ""6"":
                    last = 5;
                    break;
                case ""7"":
                    last = 6;
                    break;
                case ""8"":
                    last = 7;
                    break;
                case ""9"":
                    last = 8;
                    break;
                case ""10"":
                    last = 9;
                    break;
                case ""J"":
                    last = 10;
                    break;
                case ""Q"":
                    last = 11;
                    break;
                case ""K"":
                    last = 12;
                    break;
            }
            if (i > 0) {
                if (last - first != 1) {
                    if (i == 4 && last == 13 && first == 4) {  //A 2 3 4 5的情況
                        this_maxcard[0] = this.cards[3];
                        break;
                    }
                    this_straightcheck = 1;
                    break;
                }
            }
            if (i == 4) {
                this_maxcard[0] = this.cards[4];
            }
            first = last;
        }

        int that_straightcheck = 0;
        first = 0;
        last = 0;
        Card[] that_maxcard = new Card[1];
        for (int i = 0; i < 5; i++) { //that是否是Straight
            switch (that.cards[i].getFace()) {
                case ""A"":
                    last = 13;
                    break;
                case ""2"":
                    last = 1;
                    break;
                case ""3"":
                    last = 2;
                    break;
                case ""4"":
                    last = 3;
                    break;
                case ""5"":
                    last = 4;
                    break;
                case ""6"":
                    last = 5;
                    break;
                case ""7"":
                    last = 6;
                    break;
                case ""8"":
                    last = 7;
                    break;
                case ""9"":
                    last = 8;
                    break;
                case ""10"":
                    last = 9;
                    break;
                case ""J"":
                    last = 10;
                    break;
                case ""Q"":
                    last = 11;
                    break;
                case ""K"":
                    last = 12;
                    break;
            }
            if (i > 0) {
                if (last - first != 1) {
                    if (i == 4 && last == 13 && first == 4) {  //A 2 3 4 5的情況
                        that_maxcard[0] = that.cards[3];
                        break;
                    }
                    that_straightcheck = 1;
                    break;
                }
            }
            if (i == 4) {
                that_maxcard[0] = that.cards[4];
            }
            first = last;
        }

        if (this_straightcheck == 0 && that_straightcheck == 1) {
            return 1;
        } else if (this_straightcheck == 1 && that_straightcheck == 0) {
            return -1;
        } else if (this_straightcheck == 0 && that_straightcheck == 0) { //如果this與that都是straight
            if (this_maxcard[0].compareTo(that_maxcard[0]) == 1) {
                return 1;
            } else if (this_maxcard[0].compareTo(that_maxcard[0]) == -1) {
                return -1;
            }
        }

        //檢查 2 pair
        if (this.cards[0].getFace().equals(this.cards[1].getFace())) { //找出this第一個pair
            if (this.cards[2].getFace().equals(this.cards[3].getFace())) //this 是2 pair 且位置是0 1 2 3
            {
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[2].getFace().equals(that.cards[3].getFace())) //判斷that是否為2 pair
                {
                    if (this.cards[3].compareTo(that.cards[3]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[3]) == -1) {
                        return -1;
                    }
                } else if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[3].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[3].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else {  //表示that不是2 pair
                    return 1;
                }
            }
            if (this.cards[3].getFace().equals(this.cards[4].getFace())) //this 是2 pair 且位置是0 1 3 4
            {
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[2].getFace().equals(that.cards[3].getFace())) //判斷that是否為2 pair
                {
                    if (this.cards[4].compareTo(that.cards[3]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[3]) == -1) {
                        return -1;
                    }
                } else if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else {  //表示that不是2 pair
                    return 1;
                }
            }
        }
        if (this.cards[1].getFace().equals(this.cards[2].getFace())) { //找出this第一個pair
            if (this.cards[3].getFace().equals(this.cards[4].getFace())) //this 是2 pair 且位置是 1 2 3 4
            {
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[2].getFace().equals(that.cards[3].getFace())) //判斷that是否為2 pair
                {
                    if (this.cards[4].compareTo(that.cards[3]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[3]) == -1) {
                        return -1;
                    }
                } else if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else {  //表示that不是2 pair
                    return 1;
                }
            }
        }

        //檢查pair
        int this_paircheck = 0;
        int temp_thisj = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    temp_thisj = j;
                    this_paircheck = 1;
                    break;
                }
            }
        }

        int that_paircheck = 0;
        int temp_thatj = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    temp_thatj = j;
                    that_paircheck = 1;
                    break;
                }
            }
        }

        if (this_paircheck == 1 && that_paircheck == 0) {
            return 1;
        } else if (this_paircheck == 0 && that_paircheck == 1) {
            return -1;
        } else if (this_paircheck == 1 && that_paircheck == 1) {
            if (this.cards[temp_thisj].compareTo(that.cards[temp_thatj]) == 1) {
                return 1;
            } else {
                return -1;
            }
        }

        //檢查High Card
        if (this.cards[4].compareTo(that.cards[4]) == 1) //比最後一張
        {
            return 1;
        } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
            return -1;
        }

        return 0;
    }

    // Do not modified this function
    public Card[] getCards() {
        return this.cards;
    }
}

