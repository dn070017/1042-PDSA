import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] HandArray = new Hand[count];
            Card[] cardsArray = new Card[5];
            
        for(int i=0; i<count; i++){
            String LineStr = br.readLine();
            String[] cardStr = LineStr.split("","");
            
            for(int j=0; j<5; j++){
            String[] sep = cardStr[j].split(""_"");
            Card card = new Card(sep[1], sep[0]);
            cardsArray[j] = card;
           // System.out.println(cardsArray[i].getSuit()+""_""+cardsArray[i].getFace());
            }
            HandArray[i] = new Hand(cardsArray);
        }
        
        Arrays.sort(HandArray);
        
    //    System.out.println(count - target);
    //    System.out.println(HandArray[1].getCards()[4].getSuit()+""_""+HandArray[1].getCards()[4].getFace());
        for(int i=0; i<5; i++){    
        System.out.println(HandArray[count - target].getCards()[i].getSuit()+""_""+HandArray[count - target].getCards()[i].getFace());    
        }    
            
        }
    }
}

