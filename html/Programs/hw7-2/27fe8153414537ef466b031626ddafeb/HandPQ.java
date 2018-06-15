import edu.princeton.cs.algs4.MaxPQ;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int idx = 0;
            Hand[] playerArray = new Hand[count];
//            MaxPQ<Hand> pq = new MaxPQ<>(count);
//            Arrays.sort(playerArray);
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals(""-"")) pq.insert(item);
//            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + "" "");
//        }
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                playerArray[idx++] = hand;
            }

            for (int i = 0; i < count; i++) {
                Hand[] min = new Hand[1];
                for (int j = i + 1; j < count; j++) {
                    if (playerArray[i].compareTo(playerArray[j]) == -1) {
                        min[0] = playerArray[i];
                        playerArray[i] = playerArray[j];
                        playerArray[j] = min[0];
                    }
                }
            }
 
            Arrays.sort(playerArray[target-1].getCards());          
            System.out.println(playerArray[target-1].getCards()[0].getSuit()+""_""+playerArray[target-1].getCards()[0].getFace()+"",""
                    +playerArray[target-1].getCards()[1].getSuit()+""_""+playerArray[target-1].getCards()[1].getFace()+"",""+playerArray[target-1].getCards()[2].getSuit()+""_""+playerArray[target-1].getCards()[2].getFace()
                    +"",""+playerArray[target-1].getCards()[3].getSuit()+""_""+playerArray[target-1].getCards()[3].getFace()+"",""+playerArray[target-1].getCards()[4].getSuit()+""_""+playerArray[target-1].getCards()[4].getFace());
        }
    }
}

