import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

//            Card[] testtt = new Card[5];
//            Hand[] hand = new Hand[count];
            MinPQ<Hand> pq = new MinPQ<>();

            for (int i = 0; i < count; i++) {
                String[] cardStr = br.readLine().split("","");
                Card[] testtt = new Card[5];
                for (int j = 0; j < 5; j++) {
                    String[] handd = cardStr[j].split(""_"");
                    Card card = new Card(handd[1], handd[0]);
                    testtt[j] = card;
                }
                Arrays.sort(testtt);
                Hand hand = new Hand(testtt);
                pq.insert(hand);
                if (pq.size() > target) {
                    pq.delMin();
                }
            }

            Hand out = pq.delMin();
            System.out.println(out.getCards()[0].getSuit() + ""_"" + out.getCards()[0].getFace() + "","" + out.getCards()[1].getSuit() + ""_"" + out.getCards()[1].getFace() + "","" + out.getCards()[2].getSuit() + ""_"" + out.getCards()[2].getFace() + "","" + out.getCards()[3].getSuit() + ""_"" + out.getCards()[3].getFace() + "","" + out.getCards()[4].getSuit() + ""_"" + out.getCards()[4].getFace());

        }
    }
}

