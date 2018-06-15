import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>();
            for (String in = br.readLine(); in != null; in = br.readLine()) {
               

                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
               Hand haha =  new Hand(cardsArray);
               pq.insert(haha);
               if (pq.size() > target){
               pq.delMin();
               }
             
            }
           Card[] a = new Card[5];
              a  = pq.delMin().getCards(); 
System.out.print(a[0].getSuit()+""_""+a[0].getFace()+"","");
System.out.print(a[1].getSuit()+""_""+a[1].getFace()+"","");
System.out.print(a[2].getSuit()+""_""+a[2].getFace()+"","");
System.out.print(a[3].getSuit()+""_""+a[3].getFace()+"","");
System.out.print(a[4].getSuit()+""_""+a[4].getFace());
            

        }
    }
}

