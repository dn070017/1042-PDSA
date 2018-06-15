
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int id = 0;
            
            MinPQ<Hand> PQ = new MinPQ<Hand>();
            MinPQ<Card> pq = new MinPQ<Card>(5);
            Card[] cards = new Card[5];

            while (br.ready()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                PQ.insert(new Hand(cardsArray));
                if(PQ.size() > target){
                    PQ.delMin();
                }
            }
            
            cards = PQ.delMin().getCards();
            
            for(int i = 0; i < 5; i++){
                pq.insert(cards[i]);
            }
            
            Card c = pq.delMin();
            String s = (c.getSuit()) + ""_"" + (c.getFace());
            while(!pq.isEmpty()){
                c = pq.delMin();
                s = s + ("","" + c.getSuit()) + ""_"" + (c.getFace());
            }
            System.out.println(s);
        }
   }
} 
