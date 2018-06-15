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
            String[] cardStr = br.readLine().split("","");
            for(int j=0; j<5; j++){
            String[] sep = cardStr[i].split(""_"");
            Card card = new Card(sep[1], sep[0]);
            cardsArray[j] = card;
            }
            HandArray[i] = new Hand(cardsArray);
        }
        
        Arrays.sort(HandArray);
        for(int i=0; i<5; i++){    
        System.out.println(HandArray[count - target].getCards()[i]);    
        }    
            
        }
    }
}

