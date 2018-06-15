import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            target = count + 1 -target;

            Card[] cardsArray = new Card[5];
            MaxPQ<Hand> pq = new MaxPQ<Hand>();               
          
            for(int j = 0; j < count; j++){           
                String[] in = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = in[i].split(""_"");
                    
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;                           
                }
                
                Hand item = new Hand(cardsArray);                
                
                pq.insert(item);
                if (pq.size() > target)
                    pq.delMax();
            }
            print(pq.delMax());
        }
    }
    
    public  static  void printPQ(MinPQ<Hand> q  ){
            Iterator<Hand> iterator = q.iterator();        
            while(iterator.hasNext())
              print(iterator.next());  
    }    

   
    
    public static void print(Hand p){
            for(int i = 0; i < 4;i++)
            System.out.printf(p.getCards()[i].getSuit() + ""_"" + p.getCards()[i].getFace() + "","");
            System.out.printf(p.getCards()[4].getSuit() + ""_"" + p.getCards()[4].getFace()+""\n"");                              
    }
    
}

