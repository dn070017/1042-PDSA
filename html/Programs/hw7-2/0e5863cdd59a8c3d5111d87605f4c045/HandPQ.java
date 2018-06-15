
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String[] temp = new String[5];
            Card[][] big = new Card[count][5];
            Hand output = null;
            String[] temp2 = new String[2];
            Hand wei;
            MaxPQ<Hand> pq = new MaxPQ<Hand>(1);

            for (int i = 0; i < count; i++) {
                temp = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    temp2 = temp[j].split(""_"");
                    big[i][j] = new Card(temp2[1], temp2[0]);
                }
                wei = new Hand(big[i]);
                pq.insert(wei);
            }

            for (int i = 0; i < target; i++) {
                output = pq.delMax();
            }
            big[0] = output.getCards();

            System.out.println(big[0][0].getSuit() + ""_"" + big[0][0].getFace() + "","" + big[0][1].getSuit() + ""_"" + big[0][1].getFace() + "",""
                    + big[0][2].getSuit() + ""_"" + big[0][2].getFace() + "","" + big[0][3].getSuit() + ""_"" + big[0][3].getFace() + "","" + big[0][4].getSuit() + ""_"" + big[0][4].getFace());


        }
    }
}
