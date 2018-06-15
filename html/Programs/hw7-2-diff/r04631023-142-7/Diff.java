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
            
            for (int i = 0 ; i < count; i++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                Hand newHand = new Hand(cardsArray) ;
                pq.insert(newHand);
                if (pq.size() > target){
                    pq.delMin() ;
                }
            }
            
            Card[] handCards = pq.delMin().getCards() ;
            Arrays.sort(handCards);
            String ans = """" ;
            for (int i = 0 ; i < 5 ; i++){
                ans += handCards[i].getSuit() ;
                ans += ""_"" ;
                ans += handCards[i].getFace();
                if (i < 4){
                    ans += "","" ;
                }
            }
            System.out.println(ans) ;
        }
    }
}


