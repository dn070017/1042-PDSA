
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Card[] big = new Card[5];
            Hand wei;
            MaxPQ<Hand> pq = new MaxPQ<Hand>(count);

            for (int i = 0; i < count; i++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                wei = new Hand(cardsArray);
                pq.insert(wei);
            }

            for (int i = 0; i < target-1; i++) {
                pq.delMax();
            }
            wei = pq.max();
            big = wei.getCards();
            System.out.println(big[0].getSuit() + ""_"" + big[0].getFace() + "","" + big[1].getSuit() + ""_"" + big[1].getFace() + "",""
                    + big[2].getSuit() + ""_"" + big[2].getFace() + "","" + big[3].getSuit() + ""_"" + big[3].getFace() + "","" + big[4].getSuit() + ""_"" + big[4].getFace());


        }
    }
}
