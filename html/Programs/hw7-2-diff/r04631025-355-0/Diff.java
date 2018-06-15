
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ implements Comparable<HandPQ>{
        
    public HandPQ(Card[] cards) {
        return;
    }
    
    public int compareTo(HandPQ that) {
        return 0;
    }
    
    
    public static void main(String[] args) throws Exception {
        MaxPQ<String> pq = new MaxPQ<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            while(true){
                String[] hand=br.readLine().split("","");
                
            }
            

        }
    }
}
