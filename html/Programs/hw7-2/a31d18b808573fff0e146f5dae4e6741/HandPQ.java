import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MaxPQ maxpq = new MaxPQ();
            MaxPQ temp_maxpq = new MaxPQ();

            Player[] playerArray = new Player[count];

            for (int idx = 0; idx < count; idx++) {
                Player player = new Player();
                playerArray[idx] = player;
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
                maxpq.insert(playerArray[idx]);
                if (maxpq.size() > target) {  //當PQ的size超過target時就把多的丟出來
                    int tempnum=maxpq.size() ;
                    for (int i = 1; i < tempnum; i++) //把PQ內要的東西先暫存起來
                    {
                        Player temp=(Player) maxpq.delMax();
                        temp_maxpq.insert(temp);
                  //      temp.printCards();
                    }
                //    System.out.println("" "");
                    Player temp1=(Player)maxpq.delMax(); //多出來的去除
                //    temp1.printCards();
              //      System.out.println("" "");
                    for (int i = 1; i < tempnum; i++) //把還要的東西再存回來
                    {
                        Player temp=(Player) temp_maxpq.delMax();
                        maxpq.insert(temp);
             //           temp.printCards();
                    }
                }
            }
            int tempnum = maxpq.size();
            for (int i = 1; i < tempnum; i++) //把PQ內的東西都丟光，最後只要拿剩下的那一個
            {
                if (i == target - 1) {
                    maxpq.delMax();
                    break;
                } else if (target - 1 == 0) {
                    break;
                } else {
                    maxpq.delMax();
                }
            }
            Player targetplayer = (Player) maxpq.delMax();
            //System.out.println("" "");
            targetplayer.printCards();

        }
    }

    private static class Player implements Comparable<Player> {

        private Card[] cards = new Card[5];

        public Player() {
        }

        // DO NOT MODIFY THIS
        public void setCards(Card[] cards) {
            this.cards = cards;
        }

        public void printCards() {
            for (int i = 0; i < 5; i++) {
                if (i == 4) {
                    System.out.println(this.cards[4].getSuit() + ""_"" + this.cards[4].getFace());
                } else {
                    System.out.print(this.cards[i].getSuit() + ""_"" + this.cards[i].getFace() + "","");
                }
            }

        }

        // TODO 
        public int compareTo(Player that) {
.
            Player A = this;
            Player B = that;
            Arrays.sort(A.cards);
            Arrays.sort(B.cards);
            //檢查full house
            if (A.cards[0].getFace().equals(A.cards[1].getFace()) && A.cards[0].getFace().equals(A.cards[2].getFace())) { //若A是前三張相同
                if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[0].getFace().equals(B.cards[2].getFace())) { //判斷B是否也有full house
                    if (A.cards[0].compareTo(B.cards[0]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (B.cards[1].getFace().equals(B.cards[2].getFace()) && B.cards[1].getFace().equals(B.cards[3].getFace())) {
                    if (A.cards[0].compareTo(B.cards[2]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (B.cards[2].getFace().equals(B.cards[3].getFace()) && B.cards[2].getFace().equals(B.cards[4].getFace())) {
                    if (A.cards[0].compareTo(B.cards[2]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {  //表示B不是full house
                    return 1;
                }
            } else if (A.cards[1].getFace().equals(A.cards[2].getFace()) && A.cards[1].getFace().equals(A.cards[3].getFace())) { //若A是中間三張相同
                if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[0].getFace().equals(B.cards[2].getFace())) { //判斷B是否也有full house
                    if (A.cards[1].compareTo(B.cards[0]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (B.cards[1].getFace().equals(B.cards[2].getFace()) && B.cards[1].getFace().equals(B.cards[3].getFace())) {
                    if (A.cards[1].compareTo(B.cards[2]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (B.cards[2].getFace().equals(B.cards[3].getFace()) && B.cards[2].getFace().equals(B.cards[4].getFace())) {
                    if (A.cards[1].compareTo(B.cards[2]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {  //表示B不是full house
                    return 1;
                }
            } else if (A.cards[2].getFace().equals(A.cards[3].getFace()) && A.cards[2].getFace().equals(A.cards[4].getFace())) { //若A是後三張相同
                if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[0].getFace().equals(B.cards[2].getFace())) { //判斷B是否也有full house
                    if (A.cards[2].compareTo(B.cards[0]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (B.cards[1].getFace().equals(B.cards[2].getFace()) && B.cards[1].getFace().equals(B.cards[3].getFace())) {
                    if (A.cards[2].compareTo(B.cards[2]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (B.cards[2].getFace().equals(B.cards[3].getFace()) && B.cards[2].getFace().equals(B.cards[4].getFace())) {
                    if (A.cards[2].compareTo(B.cards[2]) == 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {  //表示B不是full house
                    return 1;
                }
            }

            //檢查Flush
            int flush_check = 0;
            for (int i = 1; i < 5; i++) {
                if (Card.SUIT_ORDER.compare(A.cards[0], A.cards[i]) != 0) {
                    flush_check = 1;
                    break;
                }
            }
            if (flush_check == 0) {
                for (int i = 1; i < 5; i++) {  //A是flush時判斷B是不是flush
                    if (Card.SUIT_ORDER.compare(B.cards[0], B.cards[i]) != 0) {
                        flush_check = 1;
                        break;
                    }
                }
                if (flush_check == 1) { //B不是flush
                    return 1;
                } else {
                    if (Card.SUIT_ORDER.compare(A.cards[0], B.cards[0]) == 1) {
                        return 1;
                    } else if (Card.SUIT_ORDER.compare(A.cards[0], B.cards[0]) == -1) {
                        return -1;
                    } else //同花色時,比最後一張
                    {
                        if (A.cards[4].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[4].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    }
                }
            } else {  //A不是flush時判斷B是不是flush
                flush_check = 0;
                for (int i = 1; i < 5; i++) {
                    if (Card.SUIT_ORDER.compare(B.cards[0], B.cards[i]) != 0) {
                        flush_check = 1;
                        break;
                    }
                }
                if (flush_check == 0) {
                    return -1;
                }
            }

            //檢查Straight
            int A_straightcheck = 0; //A_straightcheck=1 時表示不是Straight
            int first = 0;
            int last = 0;
            Card[] A_maxcard = new Card[1];
            for (int i = 0; i < 5; i++) { //A是否是Straight
                switch (A.cards[i].getFace()) {
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
                            A_maxcard[0] = A.cards[3];
                            break;
                        }
                        A_straightcheck = 1;
                        break;
                    }
                }
                if (i == 4) {
                    A_maxcard[0] = A.cards[4];
                }
                first = last;
            }

            int B_straightcheck = 0;
            first = 0;
            last = 0;
            Card[] B_maxcard = new Card[1];
            for (int i = 0; i < 5; i++) { //B是否是Straight
                switch (B.cards[i].getFace()) {
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
                            B_maxcard[0] = B.cards[3];
                            break;
                        }
                        B_straightcheck = 1;
                        break;
                    }
                }
                if (i == 4) {
                    B_maxcard[0] = B.cards[4];
                }
                first = last;
            }

            if (A_straightcheck == 0 && B_straightcheck == 1) {
                return 1;
            } else if (A_straightcheck == 1 && B_straightcheck == 0) {
                return -1;
            } else if (A_straightcheck == 0 && B_straightcheck == 0) { //如果A與B都是straight
                if (A_maxcard[0].compareTo(B_maxcard[0]) == 1) {
                    return 1;
                } else if (A_maxcard[0].compareTo(B_maxcard[0]) == -1) {
                    return -1;
                }
            }

            //檢查 2 pair
            if (A.cards[0].getFace().equals(A.cards[1].getFace())) { //找出A第一個pair
                if (A.cards[2].getFace().equals(A.cards[3].getFace())) //A 是2 pair 且位置是0 1 2 3
                {
                    if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[2].getFace().equals(B.cards[3].getFace())) //判斷B是否為2 pair
                    {
                        if (A.cards[3].compareTo(B.cards[3]) == 1) {
                            return 1;
                        } else if (A.cards[3].compareTo(B.cards[3]) == -1) {
                            return -1;
                        }
                    } else if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[3].getFace().equals(B.cards[4].getFace())) {
                        if (A.cards[3].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[3].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    } else if (B.cards[1].getFace().equals(B.cards[2].getFace()) && B.cards[3].getFace().equals(B.cards[4].getFace())) {
                        if (A.cards[3].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[3].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    } else {  //表示B不是2 pair
                        return 1;
                    }
                }
                if (A.cards[3].getFace().equals(A.cards[4].getFace())) //A 是2 pair 且位置是0 1 3 4
                {
                    if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[2].getFace().equals(B.cards[3].getFace())) //判斷B是否為2 pair
                    {
                        if (A.cards[4].compareTo(B.cards[3]) == 1) {
                            return 1;
                        } else if (A.cards[4].compareTo(B.cards[3]) == -1) {
                            return -1;
                        }
                    } else if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[3].getFace().equals(B.cards[4].getFace())) {
                        if (A.cards[4].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[4].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    } else if (B.cards[1].getFace().equals(B.cards[2].getFace()) && B.cards[3].getFace().equals(B.cards[4].getFace())) {
                        if (A.cards[4].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[4].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    } else {  //表示B不是2 pair
                        return 1;
                    }
                }
            }
            if (A.cards[1].getFace().equals(A.cards[2].getFace())) { //找出A第一個pair
                if (A.cards[3].getFace().equals(A.cards[4].getFace())) //A 是2 pair 且位置是 1 2 3 4
                {
                    if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[2].getFace().equals(B.cards[3].getFace())) //判斷B是否為2 pair
                    {
                        if (A.cards[4].compareTo(B.cards[3]) == 1) {
                            return 1;
                        } else if (A.cards[3].compareTo(B.cards[3]) == -1) {
                            return -1;
                        }
                    } else if (B.cards[0].getFace().equals(B.cards[1].getFace()) && B.cards[3].getFace().equals(B.cards[4].getFace())) {
                        if (A.cards[4].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[3].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    } else if (B.cards[1].getFace().equals(B.cards[2].getFace()) && B.cards[3].getFace().equals(B.cards[4].getFace())) {
                        if (A.cards[4].compareTo(B.cards[4]) == 1) {
                            return 1;
                        } else if (A.cards[3].compareTo(B.cards[4]) == -1) {
                            return -1;
                        }
                    } else {  //表示B不是2 pair
                        return 1;
                    }
                }
            }

            //檢查pair
            int A_paircheck = 0;
            int temp_Aj = 0;

            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 5; j++) {
                    if (A.cards[i].getFace().equals(A.cards[j].getFace())) {
                        temp_Aj = j;
                        A_paircheck = 1;
                        break;
                    }
                }
            }

            int B_paircheck = 0;
            int temp_Bj = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 5; j++) {
                    if (B.cards[i].getFace().equals(B.cards[j].getFace())) {
                        temp_Bj = j;
                        B_paircheck = 1;
                        break;
                    }
                }
            }

            if (A_paircheck == 1 && B_paircheck == 0) {
                return 1;
            } else if (A_paircheck == 0 && B_paircheck == 1) {
                return -1;
            } else if (A_paircheck == 1 && B_paircheck == 1) {
                if (A.cards[temp_Aj].compareTo(B.cards[temp_Bj]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }

            //檢查High Card
            if (A.cards[4].compareTo(B.cards[4]) == 1) //比最後一張
            {
                return 1;
            } else if (A.cards[4].compareTo(B.cards[4]) == -1) {
                return -1;
            }

            return 0;
        }
    }
}

