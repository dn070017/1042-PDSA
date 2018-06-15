import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int id=0;

            MinPQ pq = new MinPQ();
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                id++;
                Hand hand = new Hand();
                
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                hand.Hand(cardsArray);
                pq.insert(hand);
                if(id>target){ pq.delMin(); }
            }    
            System.out.println(pq.delMin());
            
        }
    }
}

