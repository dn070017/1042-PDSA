
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MaxPQ<Hand> pq = new MaxPQ<Hand>(count);

            for (int i = 0; i < count; i++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                pq.insert( new Hand(cardsArray) );
            }

            for (int i = 0; i < target-1; i++) {
                pq.delMax();
            }
           
            System.out.println(pq.max().getCards()[0].getSuit() + ""_"" + pq.max().getCards()[0].getFace() + "","" + pq.max().getCards()[1].getSuit() + ""_"" + pq.max().getCards()[1].getFace() + "",""
                    + pq.max().getCards()[2].getSuit() + ""_"" + pq.max().getCards()[2].getFace() + "","" + pq.max().getCards()[3].getSuit() + ""_"" + pq.max().getCards()[3].getFace() + "","" + pq.max().getCards()[4].getSuit() + ""_"" + pq.max().getCards()[4].getFace());


        }
    }
}
