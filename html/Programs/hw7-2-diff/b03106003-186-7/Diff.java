import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            MinPQ<Hand> minPQ= new MinPQ<Hand>();
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);           
            int idx = 0;
            //Hand[] handArray = new Hand[count];
            for(int j = 0; j < count; j++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand;
                hand = new Hand(cardsArray);
                //handArray[idx++] = hand;
                minPQ.insert(hand);
                if(minPQ.size()> target){
                 
                    minPQ.delMin();
                }
                
            }
            Hand hand;
            hand = minPQ.min();
            for(int i = 0; i<hand.getCards().length ; i++){
            System.out.print(hand.getCards()[i].getSuit()+""_""+hand.getCards()[i].getFace());
                if(i != hand.getCards().length-1){System.out.print("","");}
            }
        }
    }
}

