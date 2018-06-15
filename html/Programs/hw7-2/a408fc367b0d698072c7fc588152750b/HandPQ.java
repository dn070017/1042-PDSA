
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String[] temp = new String[5];
            Hand output = null;
            String[] temp2 = new String[2];
            Card[] big = new Card[5];
            Hand wei;
            MaxPQ<Hand> pq = new MaxPQ<Hand>(count);

            for (int i = 0; i < count; i++) {
                temp = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    temp2 = temp[j].split(""_"");
                    Card big2 = new Card(temp2[1], temp2[0]);
                    big[j] = big2;
                }
                wei = new Hand(big);
                pq.insert(wei);
            }

            for (int i = 0; i < target; i++) {
                output = pq.delMax();
            }
            big = output.getCards();

            System.out.println(big[0].getSuit() + ""_"" + big[0].getFace() + "","" + big[1].getSuit() + ""_"" + big[1].getFace() + "",""
                    + big[2].getSuit() + ""_"" + big[2].getFace() + "","" + big[3].getSuit() + ""_"" + big[3].getFace() + "","" + big[4].getSuit() + ""_"" + big[4].getFace());




        }
    }
}
