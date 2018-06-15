
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {
    private static String[] temp = new String[5];
    private static Card[] big = new Card[5];
    private static String[] temp2 = new String[2];
    
    private static Hand stringtohand(String a){
        temp = a.split("","");
        for(int i=0;i<5;i++){
            temp2 = temp[i].split(""_"");
            big[i] = new Card(temp2[1],temp2[0]);
        }
        Hand out = new Hand(big);
        return out;
    } 
    
    
    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String s;
            Hand wei = null;
            Card[] big1 = new Card[5];
            MaxPQ<Hand> pq = new MaxPQ<Hand>(count);
            for(int i=0;i<count;i++){
                s = br.readLine();
                wei = stringtohand(s);
                pq.insert(wei);
            }
           
            

            for (int i = 0; i < target; i++) {
                wei = pq.delMax();
            }
            big1 = wei.getCards();

            System.out.println(big1[0].getSuit() + ""_"" + big1[0].getFace() + "","" + big1[1].getSuit() + ""_"" + big1[1].getFace() + "",""
                    + big1[2].getSuit() + ""_"" + big1[2].getFace() + "","" + big1[3].getSuit() + ""_"" + big1[3].getFace() + "","" + big1[4].getSuit() + ""_"" + big1[4].getFace());


        }
    }
}
