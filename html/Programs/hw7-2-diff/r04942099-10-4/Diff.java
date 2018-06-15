
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String[][] temp = new String[count][5];
            Card[][] big = new Card[count][5];
            Hand output = null;
            String[] temp2 = new String[2];
            Hand[] wei = new Hand[count];
            MaxPQ<Hand> pq = new MaxPQ<Hand>(count);

            
            for (int i = 0; i < count; i++) {
                temp[i] = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    temp2 = temp[i][j].split(""_"");
                    big[i][j] = new Card(temp2[1], temp2[0]);
                }
            }
            
            for (int i = 0; i < count; i++) {
                wei[i] = new Hand(big[i]);
                pq.insert(wei[i]);
            }
            

           for (int i = 0; i < target; i++) {
               output = pq.delMax();
           }
           Card[] test = output.getCards();
            

           System.out.println(test[0].getSuit() + ""_"" + test[0].getFace() + "",""+test[1].getSuit() + ""_"" + test[1].getFace() + "",""
           +test[2].getSuit() + ""_"" + test[2].getFace() + "",""+test[3].getSuit() + ""_"" + test[3].getFace() + "",""+test[4].getSuit() + ""_"" + test[4].getFace());

            
            

        }
    }
}

