import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int idx = 0;

            MaxPQ<Hand> pq = new MaxPQ<Hand>();
            
            for(idx=0;idx<count;idx++){
                Card[] cardsArray = new Card[5];
                
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                
                Hand item = new Hand(cardsArray);
                pq.insert(item);
                if(pq.size()>target-1){pq.delMax();}
            }

            Hand out = pq.delMax();
            for(int i=0;i<5;i++){
                System.out.print(out.getCards()[i].getSuit()+""_""+out.getCards()[i].getFace());
                if(i!=4){System.out.print("","");}
            }
        }
        }
    }


