/**
 * @author CHIN LUNG
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class HandPQ {
    
    
    
    public static void main(String[] args) throws Exception {
        Hand outcome ; Card []targetcard  = new Card [5];int c = 0;int k = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(""in.txt""))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ PQ = new MinPQ(target-1);
             for(int a = 0; a < count ;a++) {
                
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                 Hand HPQ = new Hand(cardsArray);
                 
                 if(a <= target-1)
                 {PQ.insert(HPQ);}
                 if(HPQ.compareTo((Hand)PQ.min()) == 1 && a > target)
                 {
                     PQ.delMin();
                     PQ.insert(HPQ);
                 }
                 
            }
           
            outcome = (Hand)PQ.delMin();
            targetcard = outcome.getCards();
            Arrays.sort(targetcard);
                       
            System.out.println(targetcard[0].getSuit()+""_""+targetcard[0].getFace()+"",""+targetcard[1].getSuit()+""_""+targetcard[1].getFace()+"",""+targetcard[2].getSuit()+""_""+targetcard[2].getFace()+"",""+targetcard[3].getSuit()+""_""+targetcard[3].getFace()+"",""+targetcard[4].getSuit()+""_""+targetcard[4].getFace());
                         
        }
    } 
}

