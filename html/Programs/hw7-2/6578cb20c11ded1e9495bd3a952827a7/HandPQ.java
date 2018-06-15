
/**
 *
 * @author CHIN LUNG
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {
    
    private static MaxPQ PQ = new MaxPQ();
    private static Hand HPQ;
    
    
    public static void main(String[] args) throws Exception {
        Hand outcome; Card []targetcard  = new Card [5];
        try(BufferedReader br = new BufferedReader(new FileReader(""in.txt""))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
       
             for(int a = 0; a < count ;a++) {
                
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                HPQ = new Hand(cardsArray); 
                 PQ.insert(HPQ);
            }
             for(int i = 1;i<target;i++)
             {
                 PQ.delMax();
             }
            outcome = (Hand)PQ.delMax();
            targetcard = outcome.getCards();
            Arrays.sort(targetcard);
            
           
            System.out.println(targetcard[0].getSuit()+""_""+targetcard[0].getFace()+"",""+targetcard[1].getSuit()+""_""+targetcard[1].getFace()+"",""+targetcard[2].getSuit()+""_""+targetcard[2].getFace()+"",""+targetcard[3].getSuit()+""_""+targetcard[3].getFace()+"",""+targetcard[4].getSuit()+""_""+targetcard[4].getFace());
            
             
        }
        
    }
}


