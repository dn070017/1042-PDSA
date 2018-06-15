import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
          //System.out.print(target);
            MinPQ<Hand> pq = new MinPQ<Hand>();
            target = 3;
            Card[] cardsArray = new Card[5];
        for(int j = 0;j<count;j++){            
            String[] cardStr =  br.readLine().split("","");
            for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
            Hand aa = new Hand(cardsArray);
     
            if(pq.size()<target){
                pq.insert(aa);
                
                // System.out.print(pq.size());
            }
            else if(pq.size()>=target){
                if(pq.min().compareTo(aa)==-1){
                    pq.insert(aa);
                    Hand bb = pq.delMin();
                   
                }
                else{ 
                }
            }
        }
        
        Hand ans = pq.delMin();
      
        
        Card[] anw = ans.getCards();
       for(int k = 0;k<5;k++){
            System.out.print(anw[k].getSuit());
            System.out.print(""_"");
            System.out.print(anw[k].getFace());
            System.out.print("",""); 
        }
        }
    }

    
}

