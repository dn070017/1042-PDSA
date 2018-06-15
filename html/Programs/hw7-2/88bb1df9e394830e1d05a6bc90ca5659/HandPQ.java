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
            

            
        for(int i=0; i<count; i++){
            String LineStr = br.readLine();
            String[] cardStr = LineStr.split("","");
            Card[] cardsArray = new Card[5];
            for(int j=0; j<5; j++){
            String[] sep = cardStr[j].split(""_"");
            Card card = new Card(sep[1], sep[0]);
            cardsArray[j] = card;
        //    System.out.println(card.getSuit()+""_""+card.getFace());            
            }
            HandArray[i] = new Hand(cardsArray);
        }
        
        /*Card[] cardsArray1 = new Card[5];
            
            cardsArray1[0] = new Card(""2"", ""Spades"");
            cardsArray1[1] = new Card(""2"", ""Spades"");
            cardsArray1[2] = new Card(""2"", ""Spades"");
            cardsArray1[3] = new Card(""2"", ""Spades"");
            cardsArray1[4] = new Card(""2"", ""Spades"");
            HandArray[4] = new Hand(cardsArray1);
            
        
        for(int a=0; a<5; a++){    
        System.out.println(HandArray[4].getCards()[a].getSuit()+""_""+HandArray[4].getCards()[a].getFace());    
        }*/
        
        Arrays.sort(HandArray);
        
        for(int a=0; a<5; a++){    
        System.out.println(HandArray[count - target].getCards()[a].getSuit()+""_""+HandArray[count - target].getCards()[a].getFace());    
        }
        
        }
    }
}

