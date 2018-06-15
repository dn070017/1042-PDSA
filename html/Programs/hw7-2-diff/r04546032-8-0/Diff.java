
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
        
        try(BufferedReader br = new BufferedReader(new FileReader(""in.txt""))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
       
             for(String in = br.readLine(); in != null; in = br.readLine()) {
                
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
             for(int i = count;i==count-target;i--)
             {
                 PQ.delMax();
                 if(i==target)
                 {
                     System.out.println(PQ.delMax());
                 }
             }
        }
        
    }
}


