import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            //MinPQ<Hand> pq=new MinPQ<Hand>();
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] pq = new Hand[target];

            int pqindex = 0;
            for (int i = 0; i < count; i++) {

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;

                }
                Hand hand = new Hand(cardsArray);

                if (i  < target) {
                    pq[pqindex] = hand;
                    //System.out.print(i);
                } else {
                    //System.out.print(i);
                    /*for (int k = 0; k < 5; k++) {
                        System.out.print(pq[0].getCards()[k].getFace());
                    }
                    for (int k = 0; k < 5; k++) {
                        System.out.print(pq[1].getCards()[k].getFace());
                    }
                    for (int k = 0; k < 5; k++) {
                        System.out.print(pq[2].getCards()[k].getFace());
                    }*/

                    Arrays.sort(pq);
                    if (pq[1].compareTo(hand) == -1) {
                        pq[1] = hand;
                    }
                }
                pqindex += 1;

            }
            Arrays.sort(pq);
            for (int i = 0; i < 5; i++) {
                System.out.print(pq[0].getCards()[i].getSuit()+""_"");
                if(i==4){
                    System.out.print(pq[0].getCards()[i].getFace());
                }else{
                    System.out.print(pq[0].getCards()[i].getFace()+"","");
                }
                
                
            }
        }
    }
}

