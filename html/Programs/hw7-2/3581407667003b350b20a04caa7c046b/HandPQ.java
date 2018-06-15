import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            MinPQ<Hand> pq = new MinPQ<Hand>();
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            //Hand[] array = new Hand[target];

            for (int i = 0; i < count; i++) {

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;

                }
                Hand hand = new Hand(cardsArray);

                if (i < target) {
                    pq.insert(hand);

                } else {
                    Hand min = pq.delMin();
                    if (min.compareTo(hand) == -1) {
                        pq.insert(hand);
                    } else {
                        pq.insert(min);
                    }
                }

            }
            Hand min = pq.delMin();
            for (int i = 0; i < 5; i++) {
                System.out.print(min.getCards()[i].getSuit()+""_"");
                if(i==4){
                    System.out.print(min.getCards()[i].getFace());
                }else{
                    System.out.print(min.getCards()[i].getFace()+"","");
                }
                
                
            }

        }
    }
}

