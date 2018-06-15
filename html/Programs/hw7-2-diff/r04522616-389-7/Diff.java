import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {
    
   

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            MinPQ<Hand> pq = new MinPQ<Hand>();
            
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                pq.insert(new Hand(cardsArray));
                if(pq.size() > target)
                {
                    pq.delMin();
                }
            }
            
            Hand target_hand = pq.min();
            Card[] target_cards = target_hand.getCards();
            StdOut.println(target_cards[0].getSuit() + ""_"" + target_cards[0].getFace()
            + "","" + target_cards[1].getSuit() + ""_"" + target_cards[1].getFace()
            + "","" + target_cards[2].getSuit() + ""_"" + target_cards[2].getFace()
            + "","" + target_cards[3].getSuit() + ""_"" + target_cards[3].getFace()
            + "","" + target_cards[4].getSuit() + ""_"" + target_cards[4].getFace());
            
            
            
            

        }
    }
}

