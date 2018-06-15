import java.io.BufferedReader;
import java.io.FileReader;
public class HandPQ {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>(target);
            Hand wei;
            
            for (int i = 0; i < count; i++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                wei = new Hand(cardsArray);
                pq.insert(wei);
                if(pq.size()>target) pq.delMin();
            }
           
            System.out.println(pq.min().getCards()[0].getSuit() + ""_"" + pq.min().getCards()[0].getFace() + "","" + pq.min().getCards()[1].getSuit() + ""_"" + pq.min().getCards()[1].getFace() + "",""
                    + pq.min().getCards()[2].getSuit() + ""_"" + pq.min().getCards()[2].getFace() + "","" + pq.min().getCards()[3].getSuit() + ""_"" + pq.min().getCards()[3].getFace() + "","" + pq.min().getCards()[4].getSuit() + ""_"" + pq.min().getCards()[4].getFace());
        }
    }
}
