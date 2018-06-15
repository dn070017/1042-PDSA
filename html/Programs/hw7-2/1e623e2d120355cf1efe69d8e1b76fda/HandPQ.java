import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String in;
            MinPQ<Hand> pq = new MinPQ<Hand>();
            while((in = br.readLine()) != null){
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                pq.insert(hand);
                if (pq.size() > target)
                    pq.delMin();
            }
            Card[] out = pq.delMin().getCards();
            for(int i=0; i<5; i++){
                System.out.print(out[i].getSuit() + ""_"" + out[i].getFace());
                if(i<4)System.out.print("","");
                else   System.out.print(""\n"");
            }
        }
    }
}

