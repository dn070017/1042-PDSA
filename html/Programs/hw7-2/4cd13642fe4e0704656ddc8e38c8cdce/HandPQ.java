import java.io.BufferedReader;
import java.io.FileReader;


public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int id=0;
           MinPQ<Hand> pq = new MinPQ<Hand>();
       
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                id++;
                Hand hand = new Hand();
                
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                hand.Hand(cardsArray);
                pq.insert(hand);
                if(id>target){ pq.delMin(); }
            }    
            Card[] c = pq.delMin().getCards();
            System.out.println(c[0].getSuit()+""_""+c[0].getFace()+"",""+
            c[1].getSuit()+""_""+c[1].getFace()+"",""+
                    c[2].getSuit()+""_""+c[2].getFace()+"",""+
                    c[3].getSuit()+""_""+c[3].getFace()+"",""+
                    c[4].getSuit()+""_""+c[4].getFace());
            
        }
    }
}

