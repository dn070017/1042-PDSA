
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int capacity = 0;
            
            if (target == count) capacity = target;
            else  capacity = target + 1;

            Hand[] playerArray = new Hand[capacity];
            for (int j = 0; j < capacity; j++) {
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

            MinPQ<Hand> mpq = new MinPQ<Hand>(playerArray);
            
            if (capacity > target) {
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

            Card goalcard[] = new Card[5];
            goalcard = mpq.delMin().getCards();
            Arrays.sort(goalcard);

            for (int i = 0; i < 5; i++) {
                StdOut.print(goalcard[i].getSuit() + ""_"" + goalcard[i].getFace());
                if (i == 4)  break;
                StdOut.print("","");
            }
        }
    }
}

