import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>();
            
           for (String in = br.readLine(); in != null; in = br.readLine())
           {
               String[] cardstr = in.split("","");
               Card[] cardsArray = new Card[5];
               for(int i = 0; i < 5; i++){
                    String[] sep = cardstr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
               Hand hand = new Hand(cardsArray);
               pq.insert(hand);
               if(pq.size() > target)
                   pq.delMin();
           }
           Card[] Ans = pq.delMin().getCards();
           for(int i = 0; i < 5; i++){
               if(i != 4)
                   System.out.print(Ans[i].getSuit()+""_""+Ans[i].getFace()+"","");
               else
                   System.out.print(Ans[i].getSuit()+""_""+Ans[i].getFace());
           }
        }
    }
}

