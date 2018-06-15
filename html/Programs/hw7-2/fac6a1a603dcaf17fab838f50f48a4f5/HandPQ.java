import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {
    
    
       
    
    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            MinPQ showhand = new MinPQ(target);
            Hand out= null;
            while(br.ready()) {
                String[] cardStr = br.readLine().split("","");
                Card[] cardsArray = new Card[5];
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                showhand.insert(hand);
                if (showhand.size() == target)
                    out = (Hand)showhand.delMin();
            }
            

                
            
            for (int i=0; i<5; i++){
                Card[] printout = out.getCards();
                System.out.print(printout[i].getSuit()+""_""+printout[i].getFace());
                if (!(i == 4))
                    System.out.print("","");
            }
            System.out.println();

        }
    }
}

