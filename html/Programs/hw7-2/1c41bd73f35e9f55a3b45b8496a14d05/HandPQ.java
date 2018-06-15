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
            Hand TEMP;
 //************************* PriootyQ where **********************************\\   
        MinPQ<Hand> pq = new MinPQ<Hand>();
            
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
            Hand HandPQ = new Hand(cardsArray);
            pq.insert(HandPQ); 
        }
        
        for(int j=0; j<count-target+1; j++){
          pq.delMin();
        }
        TEMP = pq.delMin();
        
        for(int a=0; a<5; a++){    
        System.out.print(TEMP.getCards()[a].getSuit()+""_""+TEMP.getCards()[a].getFace());
        if(a<4)System.out.print("","");
       }
   //*************************************************************************\\     
      /*  
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
        Arrays.sort(HandArray);
        for(int a=0; a<5; a++){    
        System.out.print(HandArray[count - target].getCards()[a].getSuit()+""_""+HandArray[count - target].getCards()[a].getFace());
        if(a<4)System.out.print("","");
        }
        */


        
        
        
        
        
        }
    }
}

