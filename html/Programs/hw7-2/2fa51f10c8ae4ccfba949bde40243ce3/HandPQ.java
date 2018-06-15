import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>();
            for (int j = 0; j < count; j++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand test = new Hand(cardsArray);

                pq.insert(test);
                if (pq.size() > target) {
                    pq.delMin();
                }
            }
            Hand target_hand = pq.delMin();
            Card[] to = target_hand.getCards();
            System.out.println(to[0].getSuit() + ""_"" + to[0].getFace() + "","" + to[1].getSuit() + ""_"" + to[1].getFace() + "","" + to[2].getSuit() + ""_"" + to[2].getFace() + "","" + to[3].getSuit() + ""_"" + to[3].getFace() + "","" + to[4].getSuit() + ""_"" + to[4].getFace());

        }
    }
}

