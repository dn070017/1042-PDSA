import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {
    
    
       
    
    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] handArray = new Hand[count];
            
            int c = 0;
            while(br.ready()) {
                
                String[] cardStr = br.readLine().split("","");
                Card[] cardsArray = new Card[5];
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                handArray[c++] = new Hand(cardsArray);      
            }
            MaxPQ showhand = new MaxPQ(handArray);

            for (int i=0; i<target-1; i++) {
                showhand.delMax();
            }
            Hand out = (Hand)showhand.delMax();
                
            
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

