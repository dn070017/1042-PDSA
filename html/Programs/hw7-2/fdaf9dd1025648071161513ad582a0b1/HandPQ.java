import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){


            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
          //System.out.print(target);
            MinPQ<Hand> pq = new MinPQ<>();
           
            
        for(int j = 0;j<count;j++){            
            String[] cardStr =  br.readLine().split("",""); 
            Card[] cardsArray = new Card[5];
            for(int i = 0; i < 5; i++){
               
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
            Hand aa = new Hand(cardsArray);
       
            if(pq.size()<target){
                pq.insert(aa);
            }
            else{
                if(pq.min().compareTo(aa)==-1){
                    Hand bb = pq.delMin();
                    pq.insert(aa);
                }
                else{}
            }
            //System.out.print(pq.size());
            }
        Hand ans = pq.delMin();
       
        Card[] anss = ans.getCards();
        
        for(int i = 1;i<5;i++){
         for(int j = i;j>0;j--){
             if(anss[j].compareTo(anss[j-1])==-1){
                 Card temp = anss[j-1];
                    anss[j-1] = anss[j];
                    anss[j] = temp;
             }
             else break;
         }
        }
        
      for(int i = 0;i<5;i++){
          System.out.print(anss[i].getSuit());
          System.out.print(""_"");
          System.out.print(anss[i].getFace());
          if(i==4) break;
          System.out.print("","");
      }
        
      
        }
    }
}

    


