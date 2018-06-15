
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> mpq = null;
            
            if (target < count) {
                Hand[] playerArray = new Hand[target + 1];

                for (int j = 0; j < target + 1; j++) {
                    Card[] cardsArray = new Card[5];
                    String[] cardStr = br.readLine().split("","");
                    for (int i = 0; i < 5; i++) {
                        String[] sep = cardStr[i].split(""_"");
                        Card card = new Card(sep[1], sep[0]);
                        cardsArray[i] = card;
                    }
                    Hand hand = new Hand(cardsArray);
                    playerArray[j] = hand;
                }

                mpq = new MinPQ<Hand>(playerArray);
                mpq.delMin();
                for (int j = target + 1; j < count; j++) {
                    Card[] cardsArray = new Card[5];
                    String[] cardStr = br.readLine().split("","");
                    for (int i = 0; i < 5; i++) {
                        String[] sep = cardStr[i].split(""_"");
                        Card card = new Card(sep[1], sep[0]);
                        cardsArray[i] = card;
                    }
                    Hand hand = new Hand(cardsArray);
                    mpq.insert(hand);
                    mpq.delMin();
                }
            }
            else{
                Hand[] playerArray = new Hand[target];

                for (int j = 0; j < target; j++) {
                    Card[] cardsArray = new Card[5];
                    String[] cardStr = br.readLine().split("","");
                    for (int i = 0; i < 5; i++) {
                        String[] sep = cardStr[i].split(""_"");
                        Card card = new Card(sep[1], sep[0]);
                        cardsArray[i] = card;
                    }
                    Hand hand = new Hand(cardsArray);
                    playerArray[j] = hand;
                }
                mpq = new MinPQ<Hand>(playerArray);
            }

            Card goalcard[] = new Card[5];
            goalcard = mpq.delMin().getCards();
            Arrays.sort(goalcard);

            for (int i = 0; i < 5; i++) {
                StdOut.print(goalcard[i].getSuit() + ""_"" + goalcard[i].getFace());
                if (i == 4) {
                    break;
                }
                StdOut.print("","");
            }
        }
    }
}

