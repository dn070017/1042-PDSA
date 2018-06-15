import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MaxPQ<Hand> pq = new MaxPQ<Hand>();
            
            
            for(int i=0;i<count;i++){
                String[] cardline = br.readLine().split("","");
                Card[] cards =new Card[5];
                for(int k=0;k<5;k++){
                    String[] suitface =new String[2];
                    suitface = cardline[k].split(""_"");
                    cards[k]= new Card(suitface[1],suitface[0]);
                }
                pq.insert(new Hand(cards));
            }
            
            for(int i=0;i<target-2;i++){
                pq.delMax();
            }
            
            Card[] maxcards = pq.max().getCards();
            Arrays.sort(maxcards);
            for(int i=0;i<5;i++){
                StdOut.print(maxcards[i].getSuit()+""_""+maxcards[i].getFace());
                if(i!=4){
                    StdOut.print("","");
                }
            }
        }
    }
}

