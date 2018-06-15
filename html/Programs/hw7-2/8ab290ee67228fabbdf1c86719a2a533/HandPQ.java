
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>();
            //(str = br.readLine())!= null
            String str = null;
            while ((str = br.readLine()) != null) {

                Card[] cardsArray = new Card[5];
                //String cards = br.readLine();
                String[] cardStr = str.split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                pq.insert(hand);
//                if (pq.size() > target) {
//                    pq.delMin();
//                }
            }
            //System.out.println(pq.size());
            Hand anshand = pq.min();
            Arrays.sort(anshand.getCards());
            String ans = """";
            for (int i = 0; i < 5; i++) {
                ans += (anshand.getCards())[i].getSuit();
                ans += (""_"");
                ans += ((anshand.getCards())[i].getFace());
                if (i < 4) {
                    ans += ("","");
                }
            }
            System.out.println(ans);
        }
    }
}

