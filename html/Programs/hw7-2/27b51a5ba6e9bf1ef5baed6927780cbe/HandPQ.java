
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            Hand[] playerArray = new Hand[count];
            MinPQ<Hand> pq = new MinPQ<Hand>();
            for (int x = 0; x < count; x++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                playerArray[x] = new Hand(cardsArray);
                pq.insert(playerArray[x]);
                if (pq.size() > target) {
                    pq.delMin();
                }
            }
           
            Card[] A = pq.delMin().getCards();
            for (int i = 0; i < 4; i++) {
                if(i!=4)
                System.out.printf(A[i].getSuit() + ""_"" + A[i].getFace() + "","");
            }

        }

    }
}

