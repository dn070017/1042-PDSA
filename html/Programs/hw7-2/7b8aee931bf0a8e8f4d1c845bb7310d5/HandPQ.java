
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            MinPQ<Hand> pq = new MinPQ<>();
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for (int i = 0; i < 5; i++) {String[] sep = cardStr[i].split(""_"");Card card = new Card(sep[1], sep[0]);cardsArray[i] = card;
                }
                Hand item = new Hand(cardsArray);
                pq.insert(item);
                if (pq.size() > target) {
                    pq.delMin();
                }
            }

//            for (String in = br.readLine(); in != null; in = br.readLine()) {
//                Card[] cardsArray = new Card[5];
//                String[] cardStr = in.split("","");
//                for (int i = 0; i < 5; i++) {
//                    String[] sep = cardStr[i].split(""_"");
//                    Card card = new Card(sep[1], sep[0]);
//                    cardsArray[i] = card;
//                }
//                Hand hand = new Hand(cardsArray);
//                playerArray[idx++] = hand;
//            }
//
//            for (int i = 0; i < count; i++) {
//                for (int j = i + 1; j < count; j++) {
//                    if (playerArray[i].compareTo(playerArray[j]) == -1) {
//                        playerArray[count] = playerArray[i];
//                        playerArray[i] = playerArray[j];
//                        playerArray[j] = playerArray[count];
//                    }
//                }
//            }
            Arrays.sort(pq.min().getCards());
            System.out.println(pq.min().getCards()[0].getSuit() + ""_"" + pq.min().getCards()[0].getFace() + "",""
                    + pq.min().getCards()[1].getSuit() + ""_"" + pq.min().getCards()[1].getFace() + "","" + pq.min().getCards()[2].getSuit()
                    + ""_"" + pq.min().getCards()[2].getFace()
                    + "","" + pq.min().getCards()[3].getSuit() + ""_"" + pq.min().getCards()[3].getFace() + "","" + pq.min().getCards()[4].getSuit()
                    + ""_"" + pq.min().getCards()[4].getFace());
        }
    }
}

