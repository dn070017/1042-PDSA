import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            //Hand[] playerArray = new Hand[count];
           MinPQ<Hand> pq = new MinPQ<Hand>(count);
           String in = br.readLine();
           while(in != null) {
                //String name = in;
                //Player player = new Player(name);
                //playerArray[idx++] = player;
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand player = new Hand(cardsArray);
                pq.insert(player);
                //else if (!pq.isEmpty()) StdOut.print(pq.delMax() + "" "");
                if (pq.size() > target) pq.delMin();
          // top M entries are on the PQ
                in = br.readLine();
            }
            

            //StdOut.println(""("" + pq.size() + "" left on pq)"");
            //StdOut.println(""("" + pq.delMax() + "" left on pq)"");
            
            Hand ans = null;
            /*while (target>0) {
                ans = pq.delMin();
                target-=1;
            }*/
            ans = pq.delMin();
            Card[] anscard = ans.getCards();
            Arrays.sort(anscard);
            for(int i = 0; i<5;i++){
                StdOut.print(anscard[i].getSuit()+""_""+anscard[i].getFace());
                if(i<4)StdOut.print("","");
            }
        }
    }
}

